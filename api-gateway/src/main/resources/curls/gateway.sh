curl --location 'http://127.0.0.1:8084/api/v1/users' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

curl --location 'http://127.0.0.1:8084/api/v1/users?include-ratings=true' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

curl --location 'http://127.0.0.1:8084/api/v1/hotels' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

curl --location 'http://127.0.0.1:8084/api/v1/hotels?include-ratings=true' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

curl --location 'http://127.0.0.1:8084/api/v1/ratings' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

curl --location 'http://127.0.0.1:8084/api/v1/ratings?user-id=7a8cbe61-c917-4ef7-8450-f7fadc82b327' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

curl --location 'http://127.0.0.1:8084/api/v1/ratings?hotel-id=95eedbc1-d118-42a3-95ba-80ebcdfb04cc' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

curl --location 'http://127.0.0.1:8084/api/v1/ratings?user-id=7a8cbe61-c917-4ef7-8450-f7fadc82b327&hotel-id=95eedbc1-d118-42a3-95ba-80ebcdfb04cc' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'