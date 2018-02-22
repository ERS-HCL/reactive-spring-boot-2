# reactive-spring-boot-2
reactive microservices using spring boot 2.0

Spring Boot 2 brings lot of features.

# Programming Language 
Java 8 is the baseline. This is the 1st framework which  supports Java 9. There is no support for 6 and 7.

# Reactive
 WebFlux, WebFlux.fn support enabled implicitly
 Reactive data enabled with Mondo DB, Redis and Cassandra
 @WebFluxTest is enabled to test Reactive REST Controllers

# Metrics
  Support enabled with Micro meter for Metrics 
# Spring 5 
   all spring 5 features would be supported here 
# Build 
   mvn clean install
# Debug in Development
  mvn spring-boot:run 



# API end points on this sample
  1. /config-values/ [GET ]  - to fetch all configuration values
  2. /config-values/{id}  [ GET ]  - to fetch specified configuration value
  3. /config-values/      [POST]  -  to add new Configuration values 
