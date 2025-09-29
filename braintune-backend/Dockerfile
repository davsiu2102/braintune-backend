# ---- Stage 1: Build ----
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew shadowJar --no-daemon

# Verificar JAR
RUN if [ -f build/libs/*all.jar ]; then \
        echo "JAR generado correctamente:"; \
        ls -l build/libs/; \
    else \
        echo "ERROR: JAR no se creó en build/libs/"; \
        exit 1; \
    fi

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=build /app/build/libs/*all.jar ./app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

