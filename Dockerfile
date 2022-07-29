#Dockerfile
FROM java:8

COPY *.jar /app.jar

EXPOSE 8090

VOLUME ["/usr/local/myapp/fav/upload", "/usr/local/fav-upload"]

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]