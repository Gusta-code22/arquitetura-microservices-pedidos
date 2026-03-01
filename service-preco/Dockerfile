# build
FROM maven:3.9.10-amazoncorretto-21-al2023 as build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests


#run
FROM amazoncorretto:21.0.5

WORKDIR /app

COPY --from=build ./build/target/*.jar ./service-preco.jar

EXPOSE 8002




ENV TZ='America/Sao_Paulo'

ENTRYPOINT ["java", "-jar", "service-preco.jar"]
