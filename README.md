# Leiga


To run:

With IntelliJ:

    File -> New -> Project From Existing Sources
    Choose Leiga-Server click OK

    Checkbox "Import project from external model"
    Select "maven"
    Click Next

    Checkbox "Import Maven projects automatically"
    Next -> Next -> Next -> Finish

    In IntelliJ go to src/main/java/Leiga and right click App.java and select Run App.main()


With maven:

    Download and install maven
  	https://maven.apache.org/download.cgi
  	https://maven.apache.org/install.html
    
    in project folder write:
    mvn package

    Next run:
    java -jar target/leiga-1.0.0.jar


Go to http://localhost:8080/

Try:
http://localhost:8080/
http://localhost:8080/service
http://localhost:8080/service/hello/yourname
http://localhost:8080/service/user?name=yourname
http://localhost:8080/service/json