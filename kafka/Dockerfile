# Start with a base image containing Java runtime
FROM openjdk:11

# Add Maintainer Info
LABEL maintainer="Steve Gachanja <sngachanja@co-opbank.co.ke>"

#Build image
# Make port 8080 available to the world outside this container

# The application's jar file
ARG JAR_FILE=kafka/target/kafka-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ./${JAR_FILE} app.jar

#Run jar file
ENTRYPOINT ["java","-jar","app.jar"]
