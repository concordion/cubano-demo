package org.concordion.cubano.template.driver.services;

import org.concordion.cubano.data.DataCleanup;
import org.concordion.cubano.template.driver.domain.AbcDomainData;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

public class AbcService implements DataCleanup {

    private final ReportLogger logger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

    private AbcDomainData abcDomainData;

    public AbcService(AbcDomainData abcDomainData) {
        this.abcDomainData = abcDomainData;
    }

    public AbcDomainData getAbcDomainData() {
        return abcDomainData;
    }

    @Override
    public void cleanup() {

        logger.debug("Before clean up");

        // Could be a Database, filesystem, API call ... whatever needs to be cleaned up.
        logger.debug(String.format("Using AbcDomain Key '%s' to tear down my test data.", abcDomainData.getDomainDataId()));

        logger.debug("After clean up");

    }

}
