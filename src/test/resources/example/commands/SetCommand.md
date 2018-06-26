# Set Command

We want the first name (Bob) to be a parameter and the greeting (Hello Bob) to be verified against the result returned by the system.

## Assert Equals with a Parameter using AssertEquals shortcut (i.e. ?)
The greeting for user [Bob](- "#firstName") will be: [Hello Bob](- "?=hello(#firstName)")

## Assert Equals with a Parameter using AssertEquals 
The greeting for user [Jan](- "#firstName") will be: [Hello Jan](- "c:assertEquals=hello(#firstName)")