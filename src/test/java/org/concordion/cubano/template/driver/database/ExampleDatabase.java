package org.concordion.cubano.template.driver.database;

import java.sql.SQLException;
import java.util.Map;

import org.codejargon.fluentjdbc.api.mapper.Mappers;
import org.concordion.cubano.template.AppConfig;

public class ExampleDatabase {
    protected static final String SCHEMA = AppConfig.getInstance().getDatabaseSchema();

    private String setSchema(String schmema, String sql) {
        return sql.replace("${SCHEMA}", schmema);
    }

    /* Clearly requires updating ... :-) */
    public String getExampleQuery(String valueToMatch) throws SQLException {
        Map<String, Object> result = ExampleDataSourceFactory.fluentJDBC()
                .query()
                .select(setSchema(SCHEMA, "SELECT * from ${SCHEMA}.TABLENAME WHERE COLUMNNAME = :valueToMatch"))
                .namedParam("valueToMatch", valueToMatch)
                .singleResult(Mappers.map());

        return result.get("VALUETOMATCH").toString();
    }
}
