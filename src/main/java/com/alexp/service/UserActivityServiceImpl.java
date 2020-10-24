package com.alexp.service;

import com.alexp.model.ActivityCell;
import com.alexp.model.ActivityTableRow;
import com.alexp.model.User;
import com.alexp.model.UserActivityRecord;
import com.alexp.repository.UserActivityRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserActivityServiceImpl implements UserActivityService{

    private final UserActivityRepository userActivityRepository;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public List<ActivityTableRow> searchActivityTableRowsByParams(List<Long> userIds, Date startDate, Date endDate, CalculationPeriod period){
        startDate = prepareDate(startDate, period);
        endDate = prepareDate(endDate, period);

        List<UserActivityRecord> userActivityRecords = userActivityRepository.searchUserActivityRecordsByParams(userIds, startDate, endDate);
        return groupRecordsByPeriod(userActivityRecords, startDate, period, userIds);
    }

    private List<ActivityTableRow> groupRecordsByPeriod(List<UserActivityRecord> userActivityRecords, Date startDate,
                                                        CalculationPeriod period, List<Long> userIds) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Date periodStartDate = calendar.getTime();
        calendar.add(period.getValue(), 1);
        Date periodEndDate = calendar.getTime();

        List<ActivityTableRow> activityTableRows = new ArrayList<>();

        ActivityTableRow activityTableRow = new ActivityTableRow();
        activityTableRow.setPeriodStartDate(periodStartDate);

        Iterator<UserActivityRecord> iterator = userActivityRecords.iterator();
        while (iterator.hasNext()) {
            UserActivityRecord userActivityRecord = iterator.next();

            // process empty periods
            while (userActivityRecord.getActivityDate().compareTo(periodEndDate) > 0) {
                fillGapsAndSortByUserId(activityTableRow, userIds);
                activityTableRows.add(activityTableRow);

                periodStartDate = periodEndDate;
                calendar.add(period.getValue(), 1);
                periodEndDate = calendar.getTime();

                activityTableRow = new ActivityTableRow();
                activityTableRow.setPeriodStartDate(periodStartDate);
            }

            // record matches period
            addToTableRow(activityTableRow, userActivityRecord);

            if(!iterator.hasNext()) {
                //process last record
                fillGapsAndSortByUserId(activityTableRow, userIds);
                activityTableRows.add(activityTableRow);
            }
        }

        return activityTableRows;
    }

    private void addToTableRow(ActivityTableRow activityTableRow, UserActivityRecord userActivityRecord) {
        ActivityCell activityCell = activityTableRow.findActivityCellByUserId(userActivityRecord.getUser().getId());
        if (activityCell == null) {
            activityCell = new ActivityCell(
                    new User(userActivityRecord.getUser().getId(),
                            userActivityRecord.getUser().getName()),
                      0);
            activityTableRow.getActivityCells().add(activityCell);
        }
        activityCell.setCount(activityCell.getCount() + userActivityRecord.getActivityCount());
    }

    private Date prepareDate(Date date, CalculationPeriod period) {
        // Reset time units to have proper ranges for search

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        if (period == CalculationPeriod.HOUR) {
            return calendar.getTime();
        } else {
            calendar.set(Calendar.HOUR, 0);
        }

        if (period == CalculationPeriod.DAY) {
            return calendar.getTime();
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        }

        // ToDo process week case

        if (period == CalculationPeriod.MONTH) {
            return calendar.getTime();
        } else {
            calendar.set(Calendar.MONTH, 1);
        }

        // if (period == CalculationPeriod.YEAH)
        return calendar.getTime();
    }

    private void fillGapsAndSortByUserId(ActivityTableRow activityTableRow, List<Long> userIds) {
        for (Long userId : userIds) {
            ActivityCell activityCell = activityTableRow.findActivityCellByUserId(userId);
            if (activityCell == null) {
                activityCell = new ActivityCell(new User(userId, ""), 0);
                activityTableRow.getActivityCells().add(activityCell);
            }
        }
        activityTableRow.sortCells();
    }
}
