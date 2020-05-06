# Marvel Character API

A Spring Boot project fetching characters data from the Marvel Api, aka the Yapily coding challenge.


### Built With

* 	[Java Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - Java™ Platform, Standard Edition Development Kit 11
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[IntelliJ Idea](https://www.jetbrains.com/idea/download/) - An integrated development environment written in Java for developing computer software.
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Swagger2 & SwaggerUI](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.


## Running the application locally

To run the application locally, you will need to run the 'main' method in the application which is in 'com.yapily.marvel' -> 'MarvelApplication.java'

- Unzip the downloaded zip file
- Open IntelliJ on your machine and click on File -> Import Project. Now navigate to the directory where you just unzipped the file
- The project should now be loaded up. To verify your dependencies, open 'build.gradle' and make sure it looks like the following
```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	compile('org.springframework.boot:spring-boot-starter-web')
	compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.7.0'
	compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.7.0'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	annotationProcessor 'org.projectlombok:lombok'
}
```
- Now click on 'Edit configurations' next to the 'Run' button on the top right corner in IntelliJ
- Click on the '+' in the top left and add the 'Application Configuration' and set the values as follows:
```
Main class: com.yapily.marvel.MarvelApplication
Working directory: *PATH OF UNZIPPED PROJECT*
Environment variables: *SEE NEXT STEP*
Use classpath of module: marvel.main
JRE: 11 - SDK of 'marvel.main' module
```
Now click 'OK' and continue
- Now run the application and Spring Boot will load up your application. You can double check the port within your logs at the bottom but it's typically 8080. 
- Open your browser and search for localhost:8080/characters for the first endpoint
- Search for localhost:8080/characters/{id}, where '{id}' is any character you want to query from the previous endpoint
- To check the final feature you can search for localhost:8080/characters/{id}?language={language}, where '{language}' is an ISO-639 code for any language you'd like the description of the character translated into eg 'en' for English or 'nl' for Dutch/Flemish.


## Setting Environment Variables
This is very important to do so that we're hiding our public and private key for accessing the Marvel API
- When creating your build configuration, you need to click on the button on the right of the text box for the input of 'Environment Variables'
- Click '+' on the right side of User environment variables and make sure to add the following written in the exact same way:
```
(name): PUBLIC_KEY - (value): c89f61e9a213cb3bae3a62bdc32dce3c
(name): PRIVATE_KEY - (value): ed2c46a8dd1915580ff758d8a65893f86c56758e
```
- Click 'OK' to save them

## Documentation

* [Swagger](http://localhost:8080/swagger-ui.html) - Documentation & Testing


## Endpoints

|  URL |  Method | Remarks |
|----------|--------------|--------------|
|`http://localhost:8080`  				    		| GET |  This page will return no results|
|`http://localhost:8080/characters/`             | GET | A list of character ids represented as integers |
|`http://localhost:8080/characters/{id}`    	| GET | Information about a particular character identified by a character id |


## Packages
- `com.yapily.marvel` — parent package of our project;
- `models` — to hold our entities;
- `services` — to hold our application logic;
- `controllers` — to listen to the client;

- `libraries` — any 3rd party code used or imported;

