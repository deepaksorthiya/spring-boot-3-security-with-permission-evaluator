[![Java CI with Maven](https://github.com/deepaksorthiya/spring-boot-3-security-with-permission-evaluator/actions/workflows/maven.yml/badge.svg)](https://github.com/deepaksorthiya/spring-boot-3-security-with-permission-evaluator/actions/workflows/maven.yml)
# Getting Started
## Requirements:
```
Spring Boot: 3.3.0
Java: 17
Maven: 3.9+
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
### Build Docker Image(Docker should be running)
```bash
./mvnw clean spring-boot:build-image -DskipTests
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
http://localhost:8080/principal <br>
http://localhost:8080/authentication
