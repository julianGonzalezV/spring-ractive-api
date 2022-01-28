#Note this is a multi stage Dockerfile (more than one FROM to take another base image)
# For production you can use a slim/lightweight version sell line 21 (FROM openjdk:11.0.8-jre-slim)
# To create my new image based on the next one
FROM gradle:7.3.1-jdk11-alpine as BUILDER
# Recommended practice to trace version changes
ARG VERSION=0.0.1-SNAPSHOT
# Define the work directory where the following commands will be executed
# You can have multile WORKDIR lines to change the ws in any moment you need it
# https://www.educative.io/edpresso/what-is-the-workdir-command-in-docker
WORKDIR /docker_build/
# Copy the buil.gradle From  our local pc To the /docker_build/ folder in the new image
COPY build.gradle /docker_build/
# Copy the src folder From  our local pc To the /build/ folder in the new image
COPY src /docker_build/src/
RUN gradle build --no-daemon
COPY target/reactive-api-${VERSION}.jar target/application.jar

# Setup the final image I want to create
# Using a slim version of JRE image instead of JDK (in production it is common to see)
FROM openjdk:11.0.8-jre-slim
# EXPOSE <RUNNING-PORT> <DEBUG-PORT>
EXPOSE 8080 5005
# Porduction(stage) work directory
WORKDIR /app/
#From BUILDER stage (created above) just copy
# /docker_build/target/application.jar TO /app/ directory
COPY --from:BUILDER /docker_build/target/application.jar /app/
#ENV _JAVA_OPTIONS 'XXXX' SEARCH ON INTERNET THIS INSTRUCTION IF YOU WANT
# TO DEBUG THE JAVA application
CMD java -jar /app/application.jar
#finaly, run the next command line $ docker build -t reactive-api .