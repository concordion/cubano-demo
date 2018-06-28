# Verify Rows Command

When you want to check the contents of a collection of results returned from the system, use the verify-rows command.

## [Verify Rows](-)
Given a set of expected outputs
When a fixture is executed 
Then we can use the verify-rows command to compare the actuals vs expecteds

| [Rows][][Expected Data Value1][] | [Expected Data Value2][] |  
| -------------------------------- | ------------------------ |   
| Col1Row1				 		   | Col2Row1  		          |  
| Col1Row2 						   | Col2Row2    	          |  
| Col1Row3						   | Col2Row3				  |

[Rows]: - "c:verify-rows=#result:verifyActualDataReturned()"
[Expected Data Value1]: - "?=#result.expectedDataValue1"
[Expected Data Value2]: - "?=#result.expectedDataValue2"