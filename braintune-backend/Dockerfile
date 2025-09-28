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

# 6. Opcional: listar archivos para verificar que el JAR se creó correctamente (debugging)
RUN ls -l build/libs/

# 7. Exponemos el puerto que usa el backend
EXPOSE 8080

# 8. Ejecutamos el JAR generado usando un patrón flexible para el nombre
CMD ["sh", "-c", "java -jar build/libs/*all.jar"]
