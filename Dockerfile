FROM java:8-jdk-alpine

ENV TZ America/Bogota
ENV LANG es_CO.UTF-8
ENV LANGUAGE es_CO.UTF-8
ENV LC_ALL es_CO.UTF-8

WORKDIR /
ADD target/ChallengeMeli-0.0.1-SNAPSHOT.jar ChallengeMeli.jar

EXPOSE 8080
CMD java -jar ChallengeMeli.jar