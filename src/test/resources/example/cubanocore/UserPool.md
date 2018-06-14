# Demonstrates the use of the `EntityPool` class and the `DataCleanup` interface.

View the `Log File` (top right of each example below), to follow along with the management of the users.


## [Grab User](-)
Have obtained User[ ](- "c:echo=grabUser()") for this run.

## [Grab User by Role](-)
Have obtained User[ ](- "c:echo=grabUserByRole()") for this run.

## [Requested User is confirmed in the User Pool](-)
[Requested User is in the User Pool](- "c:assertTrue=userPoolContainsRequestedUser()")

## [Requested User is released from the User Pool](-)
[Requested User is released from the User Pool](- "c:assertTrue=userIsReleasedFromThePool()")