# trade-store

[![Build Status](https://travis-ci.org/krishnabitmca/trade-store.svg?branch=master)](https://travis-ci.org/krishnabitmca/trade-store) 

Trade Store is software to store the trade request sumbitted by user before placing it to stock market, It stores the trade in the following order
Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired

T1	1	CP-1	B1	20/05/2020	<today date>	N
T2	2	CP-2	B1	20/05/2021	<today date>	N
T2	1	CP-1	B1	20/05/2021	14/03/2015	N
T3	3	CP-3	B2	20/05/2014	<today date>	Y

Following are conditions which system takes care.
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update the expire flag if in a store the trade crosses the maturity date.

# Clone the project and do the following to build and run the project.
$ git pull https://github.com/krishnabitmca/trade-store.git
goto to your project folder and run following command.
$ mvnw clean install
$ mvnw test
$ mvnw spring-boot:run

Following are REST API's exposed by trade-store application.
Get -- http://localhost:8080/trades this will fetch all the trades exist in the system.
Post -- http://localhost:8080/trade This will store the trade in the system.
Get -- http://localhost:8080/trade/id this will fetch one trade for given id.
Delete -- http://localhost:8080/trade/id this will delete one trade for given id. 


# Requirements
Java 8
# Includes
Spring boot starter.
H2 Database,
JUNIT plugis,
