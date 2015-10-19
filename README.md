# probes simulation in Mars

This is a RESTful implementation of a Mars's exploration simulation
to demonstrate the use of Apache CXF as the REST framework.

This project is composed of 3 modules:

- api: porject commom classes
- webservice: a REST webservice that expose a POST operation called /explore
- cli: the command line client used to send request

## Building

To build this project you need:

- Java 7 or greater
- Maven 3

To get it built just type "mvn clean install" at command line. This may take
a while until maven finish download dependencies

## Running

In order, to run this project, you need to deploy the webservice module on a
Tomcat installation. The webservice was tested in Tomcat 7.

If you are running locally, the /explore endpoint will be accessible at http://localhost:8080/webservice/explore

Once the webservice is running, you can run the cli module with the following commands

$ cd cli/target

$ java -jar cli-1.0-SNAPSHOT-jar-with-dependencies.jar -f ../sample.txt -u http://localhost:8080/webservice
