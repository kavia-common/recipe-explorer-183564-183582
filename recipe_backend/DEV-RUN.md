# Running the Recipe Backend locally or in CI

This project uses Gradle wrapper with Spring Boot 3.3.x.

Typical commands:
- ./gradlew bootRun --no-daemon
- ./gradlew test --no-daemon

Notes:
- The Gradle wrapper jar files may be bootstrapped in CI. The wrapper script has been updated to include both:
  - gradle/wrapper/gradle-wrapper.jar
  - gradle/wrapper/gradle-wrapper-shared.jar
- The app listens on port 3001 and binds to 0.0.0.0 as configured in src/main/resources/application.yml.

Health/Status:
- GET /actuator/health
- GET / (RootController)
- GET /api/status (HealthController)
