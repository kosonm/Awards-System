# Awards-System

This is a Java / Maven / Spring Boot application which provides RESTful services.

## Coding task:

Retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent over $50 in each transaction(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).Given a record of every
transaction during a three-month period, calculate the reward points earned for each customer per month and total.

## Environment setup
Minimal requirements:
* [OpenJDK 17](https://adoptium.net/releases.html?variant=openjdk17&jvmVariant=hotspot)
* [Maven 3.8.1](https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.8.1/) or later

## Build and run:
````
 mvn clean install 
 java -jar /build/libs/awardssystem-1.0.0-SNAPSHOT.jar
````

## Endpoints:
__GET__ http://localhost:8080/purchase/{customerId}/points