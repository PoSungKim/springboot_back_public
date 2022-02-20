# Heroku
> https://chatbot-spring.herokuapp.com/

> https://chatbot-spring.herokuapp.com/swagger-ui/
* Github Page Frontend <-> Heroku Backend <-> AWS RDS, Kafka <-> Spark <-> HDFS

<hr>
<br>

## 간단 실행 방법
#### 실행 및 빌드 명령어

<br>

### [Terminal]
```bash
gradle bootRun # port: 8080
gradle build   >> jar
java -jar -Dspring.active.profile=local jarFileName 
```

<br>
<hr>
<br>

## Application 구성 SW
#### 각 Dependency별 기능

<br>

### [구성도]
* Spring Boot
  * Spring Boot WebSocket
  * Spring Boot Web MVC
  * Spring Boot WebFlux
  * Spring Boot JPA
    * Hibernate, JPA, LomBok
* H2
  * `/h2-console`
* MySQL Driver
  * AWS RDS
* Swagger
  * `/swagger-ui/` 
* Spring 
  * Kafka
* JUnit5

<br>
<hr>
<br>

## Authorization 정보
#### 모두에게 오픈되어 있는 서비스로 로그인은 절차상 생성

<br>

### [Authorization Info]
* admin:admin
* user:user
