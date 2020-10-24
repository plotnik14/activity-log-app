package com.alexp.repository;

import com.alexp.model.User;
import com.alexp.model.UserActivityRecord;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class UserActivityRepositoryImpl implements UserActivityRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserActivityRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserActivityRecord> searchUserActivityRecordsByParams(List<Long> userIds, Date startDate, Date endDate) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(SqlConstants.MARKUP_USER_IDS, userIds)
                .addValue(SqlConstants.MARKUP_START_DATE, startDate)
                .addValue(SqlConstants.MARKUP_END_DATE, endDate);
        return jdbcTemplate.query(
                SqlConstants.QUERY_GET_USER_ACTIVITY_RECORDS,
                params,
                new UserActivityRecordRowMapper()
        );
    }

    private static class UserActivityRecordRowMapper implements RowMapper<UserActivityRecord> {
        @Override
        public UserActivityRecord mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            UserActivityRecord userActivityRecord = new UserActivityRecord();
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            userActivityRecord.setUser(user);
            userActivityRecord.setActivityDate(resultSet.getDate("activity_date"));
            userActivityRecord.setActivityCount(resultSet.getInt("activity_count"));
            return userActivityRecord;
        }
    }
}
