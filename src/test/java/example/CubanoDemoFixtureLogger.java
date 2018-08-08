package example;

import org.concordion.cubano.framework.ConcordionBase;
import org.concordion.cubano.framework.fixture.FixtureLogger;
import org.slf4j.Logger;

public class CubanoDemoFixtureLogger extends FixtureLogger {
    @Override
    public final void beforeSpecification(Class<? extends ConcordionBase> aClass, Logger logger) {
        logger.info("Initialising the acceptance test class {} on thread {}", getRelativeTestClassName(aClass), Thread.currentThread().getName());
    }

    @Override
    public final void beforeExample(Class<? extends ConcordionBase> aClass, String exampleName, Logger logger) {
        logger.info("Starting example {} for test fixture {}", exampleName, getRelativeTestClassName(aClass));
    }

    @Override
    public void afterExample(Class<? extends ConcordionBase> aClass, String exampleName, Logger logger) {
        logger.info("Finishing example {}", exampleName);
    }

    private String getRelativeTestClassName(Class<? extends ConcordionBase> aClass) {
        // This is the name that can be given to the RunSingleTest job in Jenkins
        return aClass.getName().replace(CubanoDemoBrowserFixture.class.getPackage().getName() + ".", "");
    }
}
