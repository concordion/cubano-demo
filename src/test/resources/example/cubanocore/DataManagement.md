# Demonstrates the use of the `ResourceRegistry` interface.  

View the comments inside `DataManagementFixture` and the entries in the `Log File` (top right of each example below), to follow along with the management of the test data.

_Note that we do not encourage chaining examples as per this test, 
but checking in the 2nd example is the only way to demonstrate that data was cleaned up when the first example finished._

## [Insert data and register a closeable service](-)
Given I have [registered a closeable service that cleans up the database at example scope](- "registerCloseableResource()")
When I [insert data](- "insertData()")
Then the [data exists in the database](- "c:assertTrue=existsInDatabase()") (and will be subsequently cleaned up once this example finishes) 

## [Data is cleaned up in subsequent example](-)
Given data was inserted in the previous example with a closeable service at example scope
When this example executes
Then the data [does not exist in the database](- "c:assertFalse=existsInDatabase()").
