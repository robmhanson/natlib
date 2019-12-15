#National Library Demo

A simple Spring Boot/Vue demo.

The project contains two maven modules:

1. frontend - which contains a Vue frontend
1. backend - which contains a SpringBoot backend

###Deploy and Run
To run execute and deploy the entire project, execute the following from the project root directory:

__mvn jetty:run-war__

Once build, the war will be deployed to jetty on port 8888 and can be accessed at:

__http://localhost:8888/natlib/__

###Using the Demo
The demo consists of two pages:

1. People - displaying the people in the database
1. Books - displaying the books in the database

Each page contains an add button for adding either a Person or Book to the system.  All fields are required to add.  There is a Search field which will 'filter' the table results based on what has been entered.

The data model is very simplistic, but selecting a person displays which (if any) books the person has borrowed.  Selecting a book provides the ability to borrow or return a book.  

###Technologies Used
1. Backend
    * SpringBoot
    * Maven
    * H2 (in memory)
    * Jetty
    * Junit 5 / Mockito
    
2. Frontend
    * Vue.js/Vuex
    * Vue Router
    * BootstrapVue
    * Webpack
    * Node
    * Jest
   
I have added a few unit tests examples but the code coverage is in no way thorough. 






