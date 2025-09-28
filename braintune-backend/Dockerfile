# 1. Usamos una imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# 2. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos todos los archivos del proyecto (desde la carpeta app)
COPY app .

# 4. Construimos el JAR usando el wrapper de Gradle
RUN ./gradlew shadowJar --no-daemon

# 5. Exponemos el puerto que usa tu backend
EXPOSE 8080

# 6. Comando para ejecutar el JAR generado
CMD ["java", "-jar", "build/libs/braintune-backend-all.jar"]
