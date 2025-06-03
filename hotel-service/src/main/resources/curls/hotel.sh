# Create Hotel
curl --location --request POST 'http://127.0.0.1:8082/api/v1/hotels' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Hotel IRA by Orchid",
    "location": "Mumbai",
    "description": "Mumbai branch of IRA"
}'


# Get All Hotels
curl --location --request GET 'http://127.0.0.1:8082/api/v1/hotels' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Get Hotel By Id
curl --location --request GET 'http://127.0.0.1:8081/api/v1/hotels/:hotelId' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Delete Hotel
curl --location --request DELETE 'http://127.0.0.1:8082/api/v1/hotels/:hotelId' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

