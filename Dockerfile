# Usar una imagen base con JDK 17 y Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package -DskipTests

# Crear una nueva imagen basada en OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-slim

# Exponer el puerto que utilizará la aplicación
EXPOSE 8080

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/bankInc-0.0.1-SNAPSHOT.jar /app/bankInc-0.0.1-SNAPSHOT.jar

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/bankInc-0.0.1-SNAPSHOT.jar"]