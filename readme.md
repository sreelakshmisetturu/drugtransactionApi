1)How to start the webservice?
Download drugtransactionApi project and unzip it. Make sure MAVEN_HOME is set in your machine. 
from command line, cd to project folder and run "mvn clean spring-boot:run" command to start the service.

2)How to start client?
Download drugtransactionclient project and unzip it. Make sure MAVEN_HOME is set in your machine. 
from command line, cd to project folder and run "mvn compile" then "mvn exec:java -Dexec.mainClass="com.restclient.ClientStarter"" (commands enclosed in quotes)
command to start the client.

3)Where to find log files?
I have implemented logging for only service. Which can be found under drugtransactionApi/logs/spring-boot-logging.log.
These logs capture both request and response. Client logs are displayed in the console.

4)Functionalities covered in this api
--> RestService that supports get and post requests only.
--> Junit test cases for the service.
--> Implemented logging
--> Exception handling(ex: enforcing some fields as mandatory,custom exceptions with appropriate messages and error codes )
--> In memory data store and querying
--> swagger integration

5)Outstanding:
--> basic authentication
--> Haetos
--> Externalizing urls,configuration/properties [I believe externalizing is good practice, however due to time constraint I couldn't implement that]
--> performance metrics

6)Resource URIs:
swagger-ui: http://localhost:8080/swagger-ui.html
api-docs: http://localhost:8080/v2/api-docs
POST:http://localhost:8080/transactions/transaction
GET:http://localhost:8080/frequentmerchant/{userId}


7)Fetch top three visited merchants algorithm:

I have used bucket sort fashioned algorithm which has runtime complexity of O(n) for fetching and
O(1) for storing at each get and post request respectively.Algorithm is explained in code comments.

Alternatively, priority queue can also be used instead of bucketing, to retrieve top three visited merchants. 
The complexity of that algorithm would be n.log(K), where n is number of merchants visited by the user and k is top k elements (three in this case).
for each get request and o(1) for each post request. 