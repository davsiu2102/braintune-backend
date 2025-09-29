# Usamos JDK completo para ejecutar el JAR
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el JAR generado por el workflow
COPY build/libs/*all.jar ./app.jar

# Log para verificar que el JAR exista
RUN if [ -f app.jar ]; then \
        echo "✅ JAR copiado correctamente:"; \
        ls -l ./app.jar; \
    else \
        echo "❌ ERROR: JAR no existe"; \
        exit 1; \
    fi

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]