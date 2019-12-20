### Real-time bidder 

#### Description
This project is a real-time bidder (RTB),
which means that it receives bid requests from 3rd-party ad exchanges
and delivers bid responses accordingly.
The technology stack comprises of
- Java 11
- Maven 3.5
- Spring Boot 2.2
- JUnit 5
- Apache Tomcat
- Docker CE

#### Run instructions for *nix-based systems

- [Install Docker](https://docs.docker.com/engine/installation/)
- Open up a terminal and ```cd /path/to/project```
- In `src/main/resources/application.properties` change `global.pacing.limit` if you like. Default value is already set to 1.
- Run bidder script:
    ```
    ./bidder.sh -p <port>
    ```
    e.g.
    ```
    ./bidder.sh -p 8888
    ```
    The application should now automatically download and install a Java 11 docker image,
    then download maven binaries that are needed to build, test and run the project,
    run maven application tests and finally fire up Tomcat server with the deployed Bidder API. 
    Enjoy!

#### Manual Test (Requires Maven and Java installed)
```
mvn clean test
```
