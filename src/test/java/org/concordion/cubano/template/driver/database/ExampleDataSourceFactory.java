package org.concordion.cubano.template.driver.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.codejargon.fluentjdbc.api.query.listen.AfterQueryListener;
import org.concordion.cubano.template.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleDataSourceFactory {
    private static DataSource datasource = null;
    private static FluentJdbc fluentJDBC = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleDataSourceFactory.class);

    private ExampleDataSourceFactory() {
    }

    // If using Oracle - See Adding OJDBC https://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/
    // And add reference to build.gradle
    // public static synchronized DataSource getOracleDataSource() throws SQLException {
    // if (datasource == null) {
    // OracleDataSource oracleDS = new OracleDataSource();
    // oracleDS.setURL(AppConfig.getInstance().getDatabaseUrl());
    //
    // datasource = oracleDS;
    // }
    //
    // return datasource;
    // }

    public static synchronized DataSource getMySQLDataSource() throws SQLException {
        if (datasource == null) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(AppConfig.getInstance().getDatabaseUrl());

            datasource = mysqlDataSource;
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
