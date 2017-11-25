# Real-time bidder 



## Test HTTP responses 
```
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
      \"country\": \"USA\",
      \"lat\": 0,
      \"lon\": 0
    }
  }
}" \
http://localhost:<port>/bid
```