# Zendesk Search Application - Sankash Thakuria

## Scope
This application provides basic search funtions that can be used to query data from the organizations.json, tickets.json and users.json. All searches work on exact matches, partial match isn't implemented in this version of the application. Dates are matched as strings without locale information.

## Assumptions
* The use of external libraries is allowed
* Fast searches are required and hence in-memory caching is implemented

## Requirements

* [Java 8](https://www.java.com/en/download/) or higher
* [Maven](https://maven.apache.org/) 3.5.4


## Usage

The application can be run via Apache Maven from the commandline. `cd` into the project directory that contains the pom.xml file and issue `mvn clean install`. This will build the project.

To run the application with the json files that were provided for the challenge, issue the following:

```shell
mvn exec:java
```
To run the tests issue the following:
```shell
mvn test
```

The project can also be run from Intellij Idea. To do so, import as maven project, navigate to the `App.java` class and run the `main` method.

 
## Search 

* Strings are matched in a case-insensitive fashion
* To search for array elements in json such just pass the name of the field. If the array contains that field, match will occur
* Timestamps are matched as strings
* The user is shown a list of choices in via a menu when the application is launched. Every field name maps to an number. This number has to be typed in to perform the match. See a sample execution in the [Example](#Example) section

## Limitations
* Partial matches isn't implemented, only exact matches are performed.

## Example
![](zdesk.gif)
## Tech

Some of the libraries that this project uses are the following:

* [Google Guice](https://github.com/google/guice) - Dependency Injection Framework
* [Junit 5](https://junit.org/junit5/) - Tesing framework
* [Mockito](https://site.mockito.org/) - Tesing Framework
* [PowerMock](https://github.com/powermock/powermock) -Tesing Framework
* [Jackson](https://github.com/FasterXML/jackson) - JSON Parsing library
* [Project Lombok](https://projectlombok.org/) - Fantastic library that takes away a lot of boiler plate

