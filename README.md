# Java 25 Spring Boot 4 Quality Demo

A clean starter project with:

- Java 25 configuration
- Spring Boot 4 WebMVC
- One REST controller
- One static HTML page that fetches API data
- JaCoCo code coverage
- SpotBugs static analysis
- Checkstyle linting
- Simple Windows and macOS/Linux scripts

## Requirements

- https://nvd.nist.gov/developers/request-an-api-key

```
setx NVD_API_KEY "your-key-here"
```

Install:

- JDK 25
- Maven 3.9+

Check your versions:

```bash
java --version
mvn --version
```

## Run the app

macOS/Linux:

```bash
./scripts/run.sh
```

Windows:

```bat
scripts\run.bat
```

Open:

```text
http://localhost:8080
```

API endpoints:

```text
GET http://localhost:8080/api/messages
GET http://localhost:8080/api/health
```

## Run tests and quality checks

macOS/Linux:

```bash
./scripts/verify.sh
```

Windows:

```bat
scripts\verify.bat
```

Or run Maven directly:

```bash
mvn clean verify
```

## Reports

After `mvn clean verify`, open these files:

```text
target/site/jacoco/index.html       JaCoCo HTML coverage report
target/spotbugs.html                SpotBugs HTML report
target/checkstyle-result.xml        Checkstyle XML report
```

## Directory layout

```text
java25-springboot4-quality-demo/
├── pom.xml
├── README.md
├── config/
│   └── checkstyle.xml
├── scripts/
│   ├── run.bat
│   ├── run.sh
│   ├── verify.bat
│   └── verify.sh
└── src/
    ├── main/
    │   ├── java/com/zactonics/demo/
    │   │   ├── DemoApplication.java
    │   │   ├── DemoController.java
    │   │   └── MessageResponse.java
    │   └── resources/
    │       ├── application.properties
    │       └── static/index.html
    └── test/java/com/zactonics/demo/
        └── DemoControllerTest.java
```

## Notes

Spring Boot 4 uses the newer `spring-boot-starter-webmvc` dependency for Spring MVC applications. The older `spring-boot-starter-web` classic starter still exists, but this project uses the cleaner Boot 4 starter name.

## Spring Boot 4 test package note

Spring Boot 4 moved MVC test annotations into the new WebMVC test package. Use:

```java
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
```

Do not use the Spring Boot 3 import:

```java
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
```

"# javabasecheckers" 
