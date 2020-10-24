package com.alexp.controller;

import com.alexp.model.ActivityTableRow;
import com.alexp.model.ReportRequest;
import com.alexp.model.User;
import com.alexp.service.CalculationPeriod;
import com.alexp.service.UserActivityService;
import com.alexp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityReportController {

    private final UserActivityService userActivityService;
    private final UserService userService;

    public ActivityReportController(UserActivityService userActivityService, UserService userService) {
        this.userActivityService = userActivityService;
        this.userService = userService;
    }

    @RequestMapping(value = { "/activityReport" }, method = RequestMethod.GET)
    public String viewActivityReport(Model model) {
        // Fake request from form

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);

        String startDateString = "1980-01-01 00:00:00";
        String endDateString = "2020-01-01 00:00:00";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(startDateString);
            endDate = dateFormat.parse(endDateString);
        } catch (ParseException e) {
            // validation..
            e.printStackTrace();
        }

        List<User> users = userService.getUsersByIds(ids);
        List<String> activityTableHeader = new ArrayList<>();
        activityTableHeader.add("Период");
        for (User user : users) {
            activityTableHeader.add(user.getName());
        }

        List<ActivityTableRow> activityTableRows =
                userActivityService.searchActivityTableRowsByParams(
                        ids,
                        startDate,
                        endDate,
                        CalculationPeriod.YEAR
                );

        model.addAttribute("activityTableHeader", activityTableHeader);
        model.addAttribute("activityTableRows", activityTableRows);
//        model.addAttribute("reportRequest", new ReportRequest());

        return "activityReport";
    }

    @RequestMapping(value = { "processRequest" }, method = RequestMethod.POST)
    public String processRequest(@ModelAttribute("reportRequest") ReportRequest reportRequest, Model model) {
        throw new NotImplementedException();
//        return "activityReport";
    }
}
