# SAIT-LUTW-Database-Web-Application

This application has made by Spring boot.

The current build is deployed at http://sait-lutw-reports.azurewebsites.net
The username and password is 'admin'

### Running localy

lutw-reports is a Spring Boot app built using Gradle. You can build an executable Jar file including all dependencies using the gradle command:
```sh
gradle bootJar
```
After starting the application, the tomcat server will be listening on port 8080.

### Database Information

The application is currently using a sqlite3 database that is initialized on application-start to the application directory in the file `database.db`. Backups are created in a `/backup` folder in the application directory. 

### Development Dependencies

lutw-reports was build using
* Java 11
* Gradle 6.8.2
* npm (updating the src/js files will require running webpack, but a prepackaged bundle is included)
