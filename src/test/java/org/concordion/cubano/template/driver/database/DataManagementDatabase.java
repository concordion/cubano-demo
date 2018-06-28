package org.concordion.cubano.template.driver.database;

import java.sql.SQLException;

import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;
import org.codejargon.fluentjdbc.api.query.Mapper;
import org.codejargon.fluentjdbc.api.query.UpdateResult;
import org.concordion.cubano.template.driver.domain.AbcDomainData;

public class DataManagementDatabase {

    public Long insertIntoAbcDomain(AbcDomainData abcDomainData) throws SQLException {

        UpdateResult query = DataManagementDataSourceFactory.fluentJDBC()
                .query()
                .update("INSERT into DATAMGMT (DOMAINDATAID, NAMEOFTHEDOMAIN) values (:domainDataId,:nameOfTheDomain)")
                .namedParam("domainDataId", abcDomainData.getDomainDataId())
                .namedParam("nameOfTheDomain", abcDomainData.getNameOfTheDomain())
                .run();

        return query.affectedRows();
    }

    public AbcDomainData findAbcDomainUsing(AbcDomainData abcDomainData) throws SQLException {

        ObjectMappers objectMappers = ObjectMappers.builder().build();
        Mapper<AbcDomainData> abcMapper = objectMappers.forClass(AbcDomainData.class);

        AbcDomainData fromDatabase = DataManagementDataSourceFactory.fluentJDBC()
                .query()
                .select("SELECT * FROM DATAMGMT where DOMAINDATAID = :domainDataId")
                .namedParam("domainDataId", abcDomainData.getDomainDataId())
                .singleResult(abcMapper);

        return fromDatabase;

    }

    public Long cleanUpAbcDomain(AbcDomainData abcDomainData) throws SQLException {

        UpdateResult query = DataManagementDataSourceFactory.fluentJDBC()
                .query()
                .update("DELETE from DATAMGMT where DOMAINDATAID = :domainDataId")
                .namedParam("domainDataId", abcDomainData.getDomainDataId())
                .run();

        return query.affectedRows();
    }

    public void initialiseDatabase() throws SQLException {

        DataManagementDataSourceFactory.fluentJDBC()
                .query()
                .update("CREATE TABLE DATAMGMT (DOMAINDATAID VARCHAR(50) NOT NULL, NAMEOFTHEDOMAIN VARCHAR(50) NOT NULL, PRIMARY KEY (DOMAINDATAID))")
                .run();

    }
}
