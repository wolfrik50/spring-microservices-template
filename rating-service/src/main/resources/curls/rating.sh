# Create Rating
curl --location --request POST 'http://127.0.0.1:8083/api/v1/ratings' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
    "userId": "DummyUser002",
    "hotelId": "DummyHotel002",
    "rating": 2,
    "remark": "Well, it'\''s a dummy hotel"
}'

# Get Rating By ID
curl --location --request GET 'http://127.0.0.1:8083/api/v1/ratings/:ratingId' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Get All Ratings
curl --location --request GET 'http://127.0.0.1:8083/api/v1/ratings' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Get Ratings By User ID
curl --location --request GET 'http://127.0.0.1:8083/api/v1/ratings?userId={{userId}}' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Get Ratings By Hotel ID
curl --location --request GET 'http://127.0.0.1:8083/api/v1/ratings?hotelId={{hotelId}}' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Delete Rating
curl --location --request DELETE 'http://127.0.0.1:8083/api/v1/ratings/:ratingId' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

