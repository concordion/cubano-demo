# Demonstrates the use of the `LoggingTooltipExtension` class.

This Concordion extension adds logging information unobtrusively to the Concordion output. The logging information is only displayed when hovering over the tooltip. 

This extension allows us to reveal implementation details in the Concordion output, without obscuring the intent of the specification.

## [Add tooltip info to the Specification](-)
Given a test Scenario that could do with some tooltip Logging Info
And the __LoggingTooltipExtension__ is configured in __ConcordionFixture__
Then [the resulting specification will include tooltip loggging info](- "c:assertTrue=addToolTipLogging()")