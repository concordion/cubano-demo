# Set Command

We want the first name (Bob) to be a parameter and the greeting (Hello Bob) to be verified against the result returned by the system.

## [Assert Equals with a Parameter using AssertEquals shortcut ?] (-)
Given I have a user with the first name [Bob](- "#firstName")
Then the greeting will be: [Hello Bob](- "?=hello(#firstName)")

## [Assert Equals with a Parameter using AssertEquals] (-)
Given I have a user with the first name [Jan](- "#firstName") 
Then the greeting will be: [Hello Jan](- "c:assertEquals=hello(#firstName)")