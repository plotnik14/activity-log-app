package com.alexp.repository;

public class SqlConstants {
    public static final String MARKUP_USER_IDS =   "user_ids";
    public static final String MARKUP_START_DATE = "start_date";
    public static final String MARKUP_END_DATE =   "end_date";

    public static final String QUERY_GET_ALL_USERS =
            "select user_id, user_name from t_user";

    public static final String QUERY_GET_USERS_BY_IDS =
            "select user_id, user_name " +
            "from t_user where user_id in (:"+MARKUP_USER_IDS+") " +
            "order by user_id";

    public static final String QUERY_GET_USER_ACTIVITY_RECORDS =
            "select \n" +
                "u.user_name,\n" +
                "ual.user_id,\n" +
                "ual.activity_date,\n" +
                "ual.activity_count\n" +
            "from \n" +
                "t_user_activity_log ual,\n" +
                "t_user u\n" +
            "where ual.user_id in (:"+MARKUP_USER_IDS+") \n" +
                "and :"+MARKUP_START_DATE+" <= ual.activity_date and ual.activity_date <= :"+MARKUP_END_DATE+" \n" +
                "and ual.user_id = u.user_id\n" +
            "order by activity_date";

// TODO?   public static final String COLUMN_USER_ID = "user_id";
}
