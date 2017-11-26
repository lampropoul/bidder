## Real-time bidder 

### Description

- bla bla
- bla bla


### Run instructions
This project requires Docker CE.

#### Install Docker
https://docs.docker.com/engine/installation/

#### Run bidder (docker image) on Unix-based systems 
```
cd /path/to/project
./bidder -p <port>
```
e.g.
```
cd /Users/vlamp/Developer/bidder
./bidder -p 8888
```



## Test HTTP responses
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