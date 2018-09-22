FROM openjdk:10.0.2-jdk-slim as build
WORKDIR /app

# Copy files necessary to run gradle wrapper
COPY ./gradle ./gradle
COPY ./gradlew ./
# Trigger download of gradle distribution
RUN ./gradlew --version

# Copy build.gradle files
COPY ./*.gradle ./
# Trigger builds of subprojects
RUN ./gradlew dependencies

# Copy the rest of the source code
COPY . ./
ENV APP_NAME=echo
RUN mkdir build-output && \
    ./gradlew build -x test && \
    tar xvf build/distributions/${APP_NAME}.tar -C build-output

######################################################
FROM openjdk:10.0.2-jre-slim
ENV APP_NAME=echo

COPY --from=build /app/build-output/${APP_NAME}/. /app
WORKDIR /app/bin

EXPOSE 80

ENTRYPOINT ["./echo"]

