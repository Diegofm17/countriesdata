# Countries Population Application

## Description
Application implemented following the ApiFirst methodology and using SpringBoot.
You can check de API definition in 'src\main\resources\countriespopulationapi-rest.yml' and view it using 'https://editor.swagger.io/'

### Prerequisites
- Java JDK installed
- Maven installed

## Running the project with Maven

### Step 1: Build the Project
1. Open a terminal/command prompt.
2. Navigate to the project's root directory.
3. Run the following command to build the project:

```mvn clean install```

### Step 2: Run the Application
1. To run the application, use the following command:

```mvn exec:java```

This starts the Spring Boot embedded web server, and the application would now be accessible at `http://localhost:8080`.
When the application starts it inserts all the data extracted from https://restcountries.com/ to an in-memory database H2.
When you restart all changes will be lost.

## Services

### GET /api/v1/data/country
This service will return all the information stored in the database. You can filter by country using the query param 'country' (is not case sensitive).

### POST /api/v1/data/country
This service allows you to store and update the data. The values ​​must be reported in a list and it will be checked which values ​​should be added (if not exist) and which should be updated (if already exist).

You could see examples in 'CountryPopulationApi.postman_collection' file.