FROM eclipse-temurin:21.0.1_12-jdk
COPY . .
RUN sed -i '/JAVA_HOME/d' build.sh && chmod +x /start.sh && chmod +x build.sh && chmod +x gradlew
VOLUME ["/tmp/db"]
EXPOSE 8181
ENTRYPOINT ["/start.sh"]
