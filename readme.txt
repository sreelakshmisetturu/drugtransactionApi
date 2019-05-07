1)How to start the webservice?
Download the project and unzip it. Make sure maven_home is set in your machine. 
from command line, co to project folder and run "mvn spring-boot:run" command to start the service.
2)How to start client?
3)Functionalities covered in api
--> RestService that supports get and post requests only.
--> Junit test cases for the service.
--> partially implemented logging
--> Exception handling(ex: enforcing some fields as mandatory,custom exceptions with appropriate messages and error codes )
--> In memory data store and querying
--> swagger integration (http://localhost:8080/swagger-ui.html and http://localhost:8080/v2/api-docs)
--> Haetos
Outstanding:
--> basic authentication
--> performance metrics

resource URIs

Fetch top three visited merchants algorithm:
I have used bucket sort fashioned algorithm which has runtime complexity of O(n) for fetching and
O(1) for storing. Algorithm explained in code comments.

Alternatively, priority queue c an also be used to retrive top three visited merchants. 
for that, I need to have 