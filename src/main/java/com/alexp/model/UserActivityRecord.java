package com.alexp.model;

import java.util.Date;
import java.util.Objects;

public class UserActivityRecord {
    private User user;
    private Date activityDate;
    private Integer activityCount;

    public UserActivityRecord() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivityRecord that = (UserActivityRecord) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(activityDate, that.activityDate) &&
                Objects.equals(activityCount, that.activityCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, activityDate, activityCount);
    }
}
