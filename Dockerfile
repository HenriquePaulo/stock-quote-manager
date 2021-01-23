FROM openjdk:8-jdk-alpine
ENV SPRING_BD_URL=jdbc:mysql://docker-mysql:3306/bootdb?useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
ENV LINK_NOTIFY=http://app:8080/notification
ENV LINK_STOCK=http://app:8080/stock
COPY ./target/stock-quote-manager-0.0.1-SNAPSHOT.war .
ENTRYPOINT ["java","-jar","/stock-quote-manager-0.0.1-SNAPSHOT.war"]