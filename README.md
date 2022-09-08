### Real-time bidder 

#### Description
This project is a real-time bidder (RTB),
which means that it receives bid requests from 3rd-party ad exchanges
and delivers bid responses accordingly.
The technology stack comprises of
- Java 18
- Maven 3.8
- Spring Boot 2.7
- JUnit 5
- Apache Tomcat 9
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
    The application should now automatically download and install a Java 18 docker image,
    then download maven binaries that are needed to build, test and run the project,
    run maven application tests and finally fire up Tomcat server with the deployed Bidder API. 
    Enjoy!

#### Manual Test (Requires Maven and Java installed)
```
mvn clean test
```
