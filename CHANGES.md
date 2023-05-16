<h3>Project from book Spring Microservices in Action</h3>
Source code from book https://github.com/ihuaylupo/manning-smia

Trying to migrate to newer versions </br>
- Spring Boot 2.2.3.RELEASE => 3.0.6
- Java 11 => 17
- Spring Cloud Hoxton.SR1 => 2022.0.2

There will be a final project in the master branch, but in the process of writing I will merge the current progress.
In this repository will be all the branches that correspond to a particular chapter that are related to the O-Stock License project.
Some code will be changed according to my personal view of the rules of clean code.

<h4> Chapter 02 changes: </h4>
- pom does not include an exclusion for junit-vintage-engine as this module is not included in the spring-boot-starter-test dependencies for Spring Boot (2.4 and up)

<h4> Chapter 03 changes: </h4>
- Moving localization beans to the LocalizationConfig class

<h4> Chapter 04 changes: </h4>
Change jdk version in Dockerfile - eclipse-temurin:17-jdk-jammy

<h4> Chapter 05 changes: </h4>

For config-server, licensing-service and all where bootstrap.yml </br>
- added dependency to enable bootstrap, which is disabled by default in spring cloud 2020.0.0
(set spring.cloud.bootstrap.enabled=true or spring.config.use-legacy-processing=true or include the new spring-cloud-starter-bootstrap in your POM file)

ExceptionController (licensing-service)-
- Change javax.servlet.http.HttpServletRequest to jakarta.servlet.http.HttpServletRequest;

Added in licensing-service.properties:
- spring.jpa.defer-datasource-initialization=true(to populate the database with test data via the data.sql script. By default, data.sql scripts are now run before Hibernate is initialized. )
- spring.sql.init.mode=always (to initialize the database with a script schema.sql)

VAULT (Works locally but doesn't work via docker-compose)
docker run --cap-add=IPC_LOCK -e 'VAULT_DEV_ROOT_TOKEN_ID=myroot' -e 'VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200' -p 8200:8200 --name=dev-vault vault
curl -X "GET" "http://localhost:8071/licensing-service/default" -H "X-Config-Token: myroot"

<h4> Chapter 06 </h4>
The @EnableEurekaClient annotation is not required anymore. Simply adding spring-cloud-starter-netflix-eureka-client to dependencies will enable the client.
If you want to disable it, set the property value of eureka.client.enabled to false.

<h4>Chapter 07 </h4>

https://resilience4j.readme.io/docs/circuitbreaker

ringBufferSizeInClosedState and ringBufferSizeInHalfOpenState -> slidingWindow

retry: maxRetryAttempts: -> maxAttempts

