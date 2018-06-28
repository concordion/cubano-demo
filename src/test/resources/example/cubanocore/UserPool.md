# Demonstrates the use of the `EntityPool` class and the `DataCleanup` interface.

View the `Log File` (top right of each example below), to follow along with the management of the users.

## [Reserve User for Scenario](-)
Given I need a User for my Scenario
When I reserve one from the User Pool
Then I am provided with __[ ](- "c:echo=grabUser()")__

## [Reserve User for Scenario by Role](-)
Given I need a User for my Scenario, with a specific role type of __[ROLE3](- "#roleType")__ 
When I reserve one from the User Pool
Then I am provided with __[ ](- "c:echo=grabUserByRole(#roleType)")__

## [Reserved User is confirmed as being in use](-)
Given I have requested a User for my Scenario
Then [the User Pool confirms the use has been reserved](- "c:assertTrue=userPoolContainsRequestedUser()")

## [Reserved User is released from the User Pool](-)
Given I have requested a User for my Scenario
And [the User Pool confirms the use has been reserved](- "c:assertTrue=userPoolContainsRequestedUser()")
When I request the User to be released from the Pool
Then the [requested User is confirmed as released from the User Pool](- "c:assertTrue=userIsReleasedFromThePool()")