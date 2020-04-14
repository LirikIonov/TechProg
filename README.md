This is repo with the homework completed during the Java learning course.

hw1 and hw2 contain some simple tasks, nothing to be surprised about.

hw3 is simple bank app, which interacts with Oracle DB while performing some operations like adding user and transferring money via accounts. 

hw4 is doing the same operations as previous, but written in Spring with usage of Security, Web and JPA components.

This maven project uses OJDBC, so run this command before starting the app:
```
mvn install:install-file -Dfile=etc/oracle/ojdbc7.jar -DgroupId=com.oracle 
    -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
```