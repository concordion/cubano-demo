package org.concordion.cubano.template.driver.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.codejargon.fluentjdbc.api.query.listen.AfterQueryListener;
import org.hsqldb.jdbc.JDBCDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataManagementDataSourceFactory {
    private static DataSource datasource = null;
    private static FluentJdbc fluentJDBC = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleDataSourceFactory.class);

    private DataManagementDataSourceFactory() {
    }

    public static synchronized DataSource getMySQLDataSource() throws SQLException {
        if (datasource == null) {

            JDBCDataSource dataSource = new JDBCDataSource();
            dataSource.setUrl("jdbc:hsqldb:mem:DATAMGMT");
            dataSource.setUser("sa");
            dataSource.setPassword("");

            datasource = dataSource;
        }

        return datasource;
    }

    public static synchronized FluentJdbc fluentJDBC() throws SQLException {
        if (fluentJDBC == null) {
            AfterQueryListener listener = execution -> {
                LOGGER.trace("Executed SQL: " + execution.sql());
            };

            fluentJDBC = new FluentJdbcBuilder()
                    .connectionProvider(getMySQLDataSource())
                    .afterQueryListener(listener)
                    .build();
        }

        return fluentJDBC;
    }
}
