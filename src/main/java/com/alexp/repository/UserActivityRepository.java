package com.alexp.repository;

import com.alexp.model.UserActivityRecord;

import java.util.Date;
import java.util.List;

public interface UserActivityRepository {
    List<UserActivityRecord> searchUserActivityRecordsByParams(List<Long> userIds, Date startDate, Date endDate);
}
