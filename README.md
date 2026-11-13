<h1 style="text-align: center;">spring method based security example with custom PermissionEvaluator</h1>

<p style="text-align: center;">
  <a href="https://github.com/deepaksorthiya/spring-boot-3-security-with-permission-evaluator/actions/workflows/maven-jvm-non-native-build.yml">
    <img src="https://img.shields.io/github/actions/workflow/status/deepaksorthiya/spring-boot-3-security-with-permission-evaluator/maven-jvm-non-native-build.yml?style=for-the-badge&label=JVM%20Maven%20Build" alt="JVM Maven Build"/>
  </a>  
  <a href="https://github.com/deepaksorthiya/spring-boot-3-security-with-permission-evaluator/actions/workflows/maven-graalvm-native-build.yml">
    <img src="https://img.shields.io/github/actions/workflow/status/deepaksorthiya/spring-boot-3-security-with-permission-evaluator/maven-graalvm-native-build.yml?style=for-the-badge&label=GraalVM%20Maven%20Build" alt="GraalVM Maven Build"/>
  </a>
  <a href="https://hub.docker.com/r/deepaksorthiya/spring-boot-3-security-with-permission-evaluator">
    <img src="https://img.shields.io/docker/pulls/deepaksorthiya/spring-boot-3-security-with-permission-evaluator?style=for-the-badge" alt="Docker"/>
  </a>
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://img.shields.io/badge/spring--boot-3.5.16-brightgreen?logo=springboot&style=for-the-badge" alt="Spring Boot"/>
  </a>
</p>

# Getting Started

## Requirements:

```
Git: 2.51.0
Spring Boot: 3.5.16
Java: 25
Maven: 3.10+
Docker Desktop(Optional): Tested on 4.47.0
```

### Clone this repository:

```bash
git clone https://github.com/deepaksorthiya/spring-boot-3-security-with-permission-evaluator.git
```

```bash
cd spring-boot-3-security-with-permission-evaluator
```

### Run Spring Boot Application Using

```bash
./mvnw spring-boot:run
```  

### (Optional)Build Docker Image (Docker should be running)

```bash
./mvnw clean spring-boot:build-image -DskipTests
```

### (Optional)Running On Docker Image

```bash
docker run -p 8080:8080 --name spring-boot-3-security-with-permission-evaluator deepaksorthiya/spring-boot-3-security-with-permission-evaluator:0.0.1-SNAPSHOT
```

### Users for Testing

```
USER1 ==> Username: user Password: password
USER2 ==> Username: admin Password : admin
```

### Rest APIs

http://localhost:8080/user <br>
http://localhost:8080/admin <br>
http://localhost:8080/hasPermission <br>
http://localhost:8080/preAuthWithMethodObjectArgsHasPermissionOfWrite <br>
http://localhost:8080/postAuthWithMethodReturnObjectArgsHasPermissionOfWrite <br>
http://localhost:8080/payment <br>
http://localhost:8080/principal <br>
http://localhost:8080/authentication

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/maven-plugin/build-image.html)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/reference/actuator/index.html)
* [Spring Web](https://docs.spring.io/spring-boot/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot//io/validation.html)
* [Flyway Migration](https://docs.spring.io/spring-boot/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
