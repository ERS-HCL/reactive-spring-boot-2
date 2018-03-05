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


# Used Cassandra - Local 
1. Create Keyspace
     CREATE KEYSPACE sales_cloud_data WITH replication = {'class':'SimpleStrategy', 'replication_factor': '1'} AND durable_writes = true;
2. Create Table 

CREATE TABLE sales_cloud_data.cloud_key_value_data (
groupId  varchar,
groupName text,
category varchar,
subcategory varchar,
id  varchar primary key ,
configName text,
application varchar,
keyName text,
valueType varchar,
keyValue text,
customerType text,
includedGeo text,
excludedGeo text,
browsingDeviceType text,
includedFlows text,
includedSubFlows text,
excludedSubFlows text,
includedCommimentTerms text,
startDate text,
endDate text)


3. INSERT INTO sales_cloud_data.cloud_key_value_data(id,category,subcategory,keyname,valuetype,keyvalue) VALUES ('1234','payment','payment','key1','boolean','true');


# Micrometer- Metrics
![Atlas-Metrics](https://github.com/ERS-HCL/reactive-spring-boot-2/blob/master/docs/metrics-graph.png?raw=true)
