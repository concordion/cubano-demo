# Execute Command

The execute command has three main uses:
* Executing an instruction with a “void” result.
* Executing an instruction with an object result (to allow multiple properties of the object to be checked).
* Handling unusual sentence structures.


## Executing an instruction with a void result
If the time is [09:00AM](- "setCurrentTime(#TEXT)") 
then the greeting will say:
[Good Morning World!](- "?=getGreeting()") it is [ ] (- "c:echo=getCurrentTime()")