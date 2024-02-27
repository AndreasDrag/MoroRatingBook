# Moro Rating Book Application

Moro Rating Book Application provides APIs to search rate and get book details.

### Swagger
```
http://localhost:9000/moro/rating/swagger-ui/index.html#/
```
## For running the application:

### For the Database

First we should create the DB. Redirect to postgres file:
```
cd docker/
```
and run the  docker compose command:
```
docker-compose up
```
### For the moro-rating-book-app

At the root file of the project, we should create the application jar to the target folder:
```
mvn clean package
```
then create the docker image with name moro-rating-book-app:
```
docker build -t moro-rating-book-app .
```
and finally run the docker contain with name moro-rating-book-app:
```
docker run -d -p 9000:9000 -e DATABASE_URL='jdbc:postgresql://host.docker.internal:5432/moro_rating_book_db' --name moro-rating-book-app moro-rating-book-app
```

Enjoy the application.