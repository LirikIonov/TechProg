This is repo with the homework completed during the Java learning course.

hw1 and hw2 contain some simple tasks, nothing to be surprised about.

hw3 is simple bank app, which interacts with Oracle DB while performing some operations like adding user and transferring money via accounts. 

This is maven project with JDBC operations, use the following command before running code:
```
mvn install:install-file -Dfile=etc/oracle/ojdbc7.jar -DgroupId=com.oracle 
    -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
```