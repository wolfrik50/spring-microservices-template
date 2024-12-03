# Create User
curl --location --request POST 'http://127.0.0.1:8080/v1/users' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Dummy001",
    "email": "dummy001@hotmail.com",
    "about": "The first testing user"
}'

# Get All Users
curl --location --request GET 'http://127.0.0.1:8080/v1/users' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Get User By Id
curl --location --request GET 'http://127.0.0.1:8080/v1/users/:userId' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# Update User
curl --location --request PUT 'http://127.0.0.1:8080/v1/users/:userId' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Dummy001",
    "email": "dummyssss001@hotmail.com",
    "about": "dummy about"
}'

# Modify User
curl --location --request PATCH 'http://127.0.0.1:8080/v1/users/:userId?fields=name%2Cemail' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Dummy001",
    "email": "dummyyyyyyyyy001@hotmail.com"
}'

curl --location --request PATCH 'http://127.0.0.1:8080/v2/users/:userId' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Dummy001",
    "email": "dummyyyyyyyyy001@hotmail.com"
}'