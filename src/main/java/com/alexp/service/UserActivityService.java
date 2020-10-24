package com.alexp.service;

import com.alexp.model.ActivityTableRow;

import java.util.Date;
import java.util.List;

public interface UserActivityService {
    List<ActivityTableRow> searchActivityTableRowsByParams(List<Long> userIds, Date startDate,Date endDate, CalculationPeriod period);
}
