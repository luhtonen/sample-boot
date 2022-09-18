# Sample Spring Boot Application

## Prerequisites

This is a sample Spring Boot application which have REST endpoints to create, read, update and delete (CRUD) persons 
with connection to Postgres database. 

To run this application Java 17 should be installed. 

Database is configured to run in docker container, so Docker should be installed. Application's configurations assumes 
that docker is run on same machine. Before running application Docker engine and container should be up and running.
Make sure that docker engine is running and execute following command:
```shell
docker compose up
```

## Run the application

The application is build and run with Gradle.
To start the application execute following command:
```shell
./gradlew bootRun
```

## Test the application

The application have some unit tests.
To execute the test use following command:
```shell
./gradlew test
```

In `postman` folder is located sample Postman collection, which can be imported into Postman and used to do manual
testing of the application.
