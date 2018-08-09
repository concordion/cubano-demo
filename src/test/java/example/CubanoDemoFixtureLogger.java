package example;

import org.concordion.api.ExampleName;
import org.concordion.cubano.framework.ConcordionBase;
import org.concordion.cubano.framework.FixtureLogger;
import org.slf4j.Logger;

public class CubanoDemoFixtureLogger extends FixtureLogger {
    @Override
    public final void beforeSpecification(Class<? extends ConcordionBase> aClass,
                                           Logger logger) {
        logger.info("Initialising the acceptance test class {} on thread {}", getRelativeTestClassName(), Thread.currentThread().getName());
    }

    @Override
    public final void beforeExample(Class<? extends ConcordionBase> aClass,
            @ExampleName String exampleName, Logger logger) {
        logger.info("Starting example {} for test fixture {}", exampleName, getRelativeTestClassName());
    }

    @Override
    public void afterExample(Class<? extends ConcordionBase> aClass, String exampleName, Logger logger) {
        logger.info("Finishing example {}", exampleName);
    }

    private String getRelativeTestClassName() {
        // This is the name that can be given to the RunSingleTest job in Jenkins
        return this.getClass().getName().replace(CubanoDemoBrowserFixture.class.getPackage().getName() + ".", "");
    }
}
