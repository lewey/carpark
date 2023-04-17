# Car Park
The car park app has been designed with the following parameters:

1. When a car wishes to park, a check will be made to see if there are any spaces available. If there are, a space will be allocated and a status of Parked returned else no space will be allocated and a status of Refused returned.
2. The amount of car parking spaces is configurable.
3. A barrier has been implemented to handle multiple cars arriving.
4. When a car leaves, a charge is calculated based upon £2 per hour.

All tests can be run using:
mvn clean test
