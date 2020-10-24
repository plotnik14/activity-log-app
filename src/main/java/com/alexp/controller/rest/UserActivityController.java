package com.alexp.controller.rest;

import com.alexp.model.ActivityTableRow;
import com.alexp.service.CalculationPeriod;
import com.alexp.service.UserActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("activities")
public class UserActivityController {

    private final UserActivityService userActivityService;

    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @GetMapping
    public List<ActivityTableRow> searchActivityTableRowsByParams(@RequestParam("ids") List<Long> ids,
                                                                  @RequestParam("startDate") String startDateString,
                                                                  @RequestParam("endDate") String endDateString,
                                                                  @RequestParam("period") CalculationPeriod period){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(startDateString);
            endDate = dateFormat.parse(endDateString);
        } catch (ParseException e) {
            //ToDo Response with error code
            e.printStackTrace();
        }

        List<ActivityTableRow> activityTableRows =
                userActivityService.searchActivityTableRowsByParams(ids, startDate, endDate, period);
        return activityTableRows;
    }
}
