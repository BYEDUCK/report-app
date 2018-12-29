FROM java:8
LABEL mantainer="mateuszbajdak@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/report-app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} report-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/report-app.jar"]