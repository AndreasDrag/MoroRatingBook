FROM openjdk:17-jdk-slim

ADD target/*.jar moro-rating-book-app.jar

ENTRYPOINT java -Xmx800m \
           -Dds.url=${DATABASE_URL} -Dds.username=${DATABASE_USER} -Dds.password=${DATABASE_PASS} \
           -jar /moro-rating-book-app.jar