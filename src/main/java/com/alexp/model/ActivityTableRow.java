package com.alexp.model;

import java.util.*;

public class ActivityTableRow {
    private Date periodStartDate;
    private List<ActivityCell> activityCells;

    public ActivityTableRow() {
        activityCells = new ArrayList<>();
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public List<ActivityCell> getActivityCells() {
        return activityCells;
    }

    public void setActivityCells(List<ActivityCell> activityCells) {
        this.activityCells = activityCells;
    }


    public ActivityCell findActivityCellByUserId(Long userId) {
        if (userId == null) return null; // ToDo validation

        return activityCells.stream()
                .filter(ac -> userId.equals(ac.getUser().getId()))
                .findAny()
                .orElse(null);
    }

    public void sortCells() {
        activityCells.sort(Comparator.comparing(a -> a.getUser().getId()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityTableRow that = (ActivityTableRow) o;
        return Objects.equals(periodStartDate, that.periodStartDate) &&
                Objects.equals(activityCells, that.activityCells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodStartDate, activityCells);
    }
}
