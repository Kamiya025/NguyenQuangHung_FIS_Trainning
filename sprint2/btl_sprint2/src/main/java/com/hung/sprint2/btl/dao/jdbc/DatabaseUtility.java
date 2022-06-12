package com.hung.sprint2.btl.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class DatabaseUtility {

    public static Connection getConnection() throws Exception {
        Connection con = ds.getConnection();
        return con;
    }


    private static HikariConfig config = new HikariConfig("/db.properties");
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource( config );
    }
}
