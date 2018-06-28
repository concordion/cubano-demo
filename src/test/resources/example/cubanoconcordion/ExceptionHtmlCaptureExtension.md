# Demonstrates the use of the `ExceptionHtmlCaptureExtension` class.

View the Storyboard output for a page that has been downloaded and can be interrogated for missing elements.

## [Scenario fails with No Such Element Exception](- "Forcing a element not found exception. So this failure is expected. Review the HTML screen capture. c:status=expectedToFail")
Given a test Scenario fails because an element is not found
And the __ExceptionHtmlCaptureExtension__ is configured in __ConcordionFixture__
Then [the resulting storyboard will capture and download the page where the element was not found](- "c:assertTrue=noSuchElementExtension()")