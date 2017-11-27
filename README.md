## Real-time bidder 

### Description
This project is a real-time bidder (RTB),
which means that it receives bid requests from 3rd-party ad exchanges
and delivers bid responses accordingly.
The technology stack comprises of
- `Java 8` as the programming language
- `Maven` as the dependency management and build tool
- `Spring Boot` as the web application framework
- `JUnit` for unit testing
- `Apache Tomcat` as a lightweight Java Servlet container
- `Docker CE` for quick and easy deployment

### Run instructions

- [Install Docker](https://docs.docker.com/engine/installation/)

- Run bidder script on Unix-based systems: 
    ```
    cd /path/to/project
    ./bidder -p <port>
    ```
    e.g.
    ```
    cd /Users/vlamp/Developer/bidder
    ./bidder -p 8888  #be careful to select a port that is not used in your system
    ```

The application now should automatically download and install a Java 8 docker image,
then download maven binaries that are needed to test and run the project,
run maven application tests and finally fire up Tomcat server with the deployed Bidder API. 
Enjoy!


### Test HTTP responses
```
export BIDDER_PORT=<port>
export BIDDER_COUNTRY=<country-code>
curl --include \
     --request POST \
     --header "Content-Type: application/json" \
     --data-binary "{
  \"id\": \"e7fe51ce4f6376876353ff0961c2cb0d\",
  \"app\": {
    \"id\": \"e7fe51ce-4f63-7687-6353-ff0961c2cb0d\",
    \"name\": \"Morecast Weather\"
  },
  \"device\": {
    \"os\": \"Android\",
    \"geo\": {
      \"country\": \"$BIDDER_COUNTRY\",
      \"lat\": 0,
      \"lon\": 0
    }
  }
}" \
http://localhost:$BIDDER_PORT/bid
```
e.g.
```
export BIDDER_PORT=8888
export BIDDER_COUNTRY=USA
curl --include \
     --request POST \
     --header "Content-Type: application/json" \
     --data-binary "{
  \"id\": \"e7fe51ce4f6376876353ff0961c2cb0d\",
  \"app\": {
    \"id\": \"e7fe51ce-4f63-7687-6353-ff0961c2cb0d\",
    \"name\": \"Morecast Weather\"
  },
  \"device\": {
    \"os\": \"Android\",
    \"geo\": {
      \"country\": \"$BIDDER_COUNTRY\",
      \"lat\": 0,
      \"lon\": 0
    }
  }
}" \
http://localhost:$BIDDER_PORT/bid
```