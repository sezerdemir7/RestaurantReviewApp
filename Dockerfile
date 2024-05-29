FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY target/RestaurantReview-0.0.1-SNAPSHOT.jar restaurantreviewapp.jar
ENTRYPOINT ["java","-jar","restaurantreviewapp.jar"]