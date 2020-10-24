package com.alexp.model;

import java.util.Objects;

public class ActivityCell {
    private User user;
    private Integer count;

    public ActivityCell() {
    }

    public ActivityCell(User user, Integer count) {
        this.user = user;
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityCell that = (ActivityCell) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, count);
    }
}
