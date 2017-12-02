package com.mark.dao.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by yuuto on 12/1/17.
 */
public class MySQLDataSourceConfig {

    private static DataSource dataSource;

    public static DataSource getDataSource() throws SQLException {
        if (dataSource == null) {
            try {
                InitialContext initContext = new InitialContext();
                dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/mark");
            } catch (NamingException e) {
                throw new SQLException("Cant create data source. " + e.getMessage());
            }
        }
        return dataSource;
    }
}