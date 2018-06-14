package example;

import org.concordion.api.AfterExample;
import org.concordion.api.AfterSpecification;
import org.concordion.api.AfterSuite;
import org.concordion.api.BeforeExample;
import org.concordion.api.BeforeSpecification;
import org.concordion.api.ConcordionResources;
import org.concordion.api.ConcordionScoped;
import org.concordion.api.ExampleName;
import org.concordion.api.FailFast;
import org.concordion.api.Scope;
import org.concordion.api.ScopedObjectHolder;
import org.concordion.api.extension.Extension;
import org.concordion.cubano.data.DataCleanupHelper;
import org.concordion.cubano.driver.concordion.ExceptionHtmlCaptureExtension;
import org.concordion.cubano.template.driver.domain.Role;
import org.concordion.cubano.template.driver.domain.User;
import org.concordion.cubano.template.driver.domain.UserPool;
import org.concordion.cubano.template.driver.workflow.Workflow;
import org.concordion.ext.LogbackLogMessenger;
import org.concordion.ext.LoggingFormatterExtension;
import org.concordion.ext.LoggingTooltipExtension;
import org.concordion.ext.StoryboardLogListener;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

/**
 * Customises the test specification and provides some helper methods
 * so the tests can access the storyboard, browser, etc.
 */
@RunWith(ConcordionRunner.class)
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class ConcordionFixture extends ConcordionDomainBase {
    private final ReportLogger logger = ReportLoggerFactory.getReportLogger(this.getClass().getName());
    private final Logger tooltipLogger = LoggerFactory.getLogger("TOOLTIP_" + this.getClass().getName());

    private User user = null;
    private UserPool userPoolManager = null;

    @Extension
    private final LoggingTooltipExtension tooltipExtension = new LoggingTooltipExtension(new LogbackLogMessenger(tooltipLogger.getName(), Level.ALL, true, "%msg%n"));

    @Extension
    private final ExceptionHtmlCaptureExtension htmlCapture = new ExceptionHtmlCaptureExtension(getStoryboard(), getBrowser());

    @Extension
    private final LoggingFormatterExtension loggerExtension = new LoggingFormatterExtension()
            .registerListener(new StoryboardLogListener(getStoryboard()));

    @ConcordionScoped(Scope.SPECIFICATION)
    private ScopedObjectHolder<DataCleanupHelper> dataHolder = new ScopedObjectHolder<DataCleanupHelper>() {
        @Override
        public DataCleanupHelper create() {
            return new DataCleanupHelper();
        }
    };

    private Workflow workflow = null;

    /**
     * Gets the logger for the current test.
     *
     * @return Logger
     */
    public ReportLogger getLogger() {
        return logger;
    }

    @BeforeSpecification
    private final void beforeSpecification() {
        // This is the name that can be given to the RunSingleTest job in Jenkins
        String testName = this.getClass().getName().replace(ConcordionFixture.class.getPackage().getName() + ".", "");

        logger.info("Initialising the acceptance test class {} on thread {}", testName, Thread.currentThread().getName());
    }

    @BeforeExample
    private final void beforeExample(@ExampleName String exampleName) {
        String testName = this.getClass().getName().replace(ConcordionFixture.class.getPackage().getName() + ".", "");

        logger.info("Starting example {} for test fixture {}", exampleName, testName);
    }

    @AfterExample
    private final void afterExample() {
        // Prevent any further cards being added to the storyboard
        getStoryboard().setAcceptCards(false);

        // Cleanup any data registered with data cleanup service
        if (dataHolder.isCreated() && dataHolder.get().hasCleanupItems()) {
            logger.step("Clean up data for " + dataHolder.get());
            dataHolder.get().cleanup();
        }

        // Shouldn't need to do this, but this event getting triggered AFTER the Storyboard's afterExample event listener.
        getStoryboard().setAcceptCards(true);
    }

    @AfterSpecification
    private final void afterSpecification() {
        logger.info("Tearing down the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());

        // Prevent any further cards being added to the storyboard
        getStoryboard().setAcceptCards(false);

        // Cleanup any data registered with data cleanup service
        if (dataHolder.isCreated() && dataHolder.get().hasCleanupItems()) {
            logger.step("Clean up data for '%s'", dataHolder.get());

            dataHolder.get().cleanup();
        }
    }

    @AfterSuite
    private final void afterSuite() {

        logger.info("@'afterSuite' start method. Tearing down the acceptance test class {} on thread {}. ", this.getClass().getSimpleName(),
                Thread.currentThread().getName());

    }

    public DataCleanupHelper getCleanupService() {
        return dataHolder.get();
    }

    public void addConcordionTooltip(final String message) {
        // Logging at debug level means the message won't make it to the console, but will make
        // it to the logs (based on included logback configuration files)
        tooltipLogger.debug(message);
    }

    /**
     * @return An object that performs common tasks against the system under test
     */
    public Workflow workflow() {
        if (workflow == null) {
            workflow = new Workflow(this);
        }

        return workflow;
    }

    public Workflow workflowUsingDataCleanupHelper() {
        if (workflow == null) {
            workflow = new Workflow(this, dataHolder.get());
        }

        return workflow;
    }

    protected User getUser() {
        if (user == null) {
            user = userPool().requestUser(Role.ROLE1);
        }

        return user;
    }

    public UserPool userPool() {
        if (userPoolManager == null) {
            userPoolManager = UserPool.createManager(getCleanupService());
        }

        return userPoolManager;
    }
}
