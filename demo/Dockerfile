FROM eclipse-temurin:25-jdk AS build
WORKDIR /src
COPY . .
RUN chmod +x mvnw \
  && ./mvnw -B -DskipTests package \
  && find target -maxdepth 1 -type f -name 'demo-*.jar' ! -name '*.original' -exec cp {} /tmp/app.jar \;

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /tmp/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
