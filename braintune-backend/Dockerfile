# 1. Usamos una imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# 2. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos todos los archivos del proyecto (incluyendo gradlew y gradle/)
COPY . .

# 4. Damos permisos de ejecución al wrapper de Gradle
RUN chmod +x ./gradlew

# 5. Construimos el JAR usando el wrapper de Gradle
RUN ./gradlew shadowJar --no-daemon

# 6. Exponemos el puerto que usa tu backend
EXPOSE 8080

# 7. Comando para ejecutar el JAR generado
CMD ["java", "-jar", "build/libs/braintune-backend-all.jar"]
