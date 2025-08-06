# springboot-docker-setup
# Spring Boot Docker Setup

This repository demonstrates how to package and run a Spring Boot application inside a Docker container.  
The process includes building the application JAR, creating a Docker image, running the container, exposing ports, and optionally pushing the image to Docker Hub.

---

## 1. Pull a Base Image
Use an official JDK image from Docker Hub as the base for running the Spring Boot JAR.

```bash
docker pull openjdk:17-jdk-slim
```

---

## 2. Build the Spring Boot Project
Generate the application JAR file using Maven.

```bash
mvn clean package
```

The output JAR will typically be located in:

```plaintext
target/<application-name>.jar
```

---

## 3. Create and Run a Container

### Option 1: Build a Custom Docker Image
Create a `Dockerfile`:

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar /tmp/app.jar
WORKDIR /tmp
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

Build the image:

```bash
docker build -t my-springboot-app .
```

Run the container:

```bash
docker run -p 8080:8080 my-springboot-app
```

---

### Option 2: Directly Push JAR into a Running Container

Run a container from the JDK image:

```bash
docker run -it --name springboot-container -p 8080:8080 openjdk:17-jdk-slim
```

Copy the JAR into the container:

```bash
docker cp target/<application-name>.jar springboot-container:/tmp/app.jar
```

Execute the JAR inside the container:

```bash
docker exec -it springboot-container java -jar /tmp/app.jar
```

---

## 4. Expose the Application Port
When running the container, map the internal port to the host:

```bash
docker run -p 8080:8080 my-springboot-app
```

Access the application via:

```plaintext
http://localhost:8080
```

---

## 5. Push Image to Docker Hub

Tag the image:

```bash
docker tag my-springboot-app <dockerhub-username>/my-springboot-app
```

Push the image:

```bash
docker push <dockerhub-username>/my-springboot-app
```

---

## Commands Summary

```bash
# Build JAR
mvn clean package

# Build Docker image
docker build -t my-springboot-app .

# Run container with port mapping
docker run -p 8080:8080 my-springboot-app

# Copy JAR into container
docker cp target/app.jar container-name:/tmp/app.jar

# Execute JAR in container
docker exec -it container-name java -jar /tmp/app.jar
```

---

## Folder Structure

```plaintext
.
├── src/                # Spring Boot source code
├── target/             # Compiled JAR files
├── Dockerfile          # Docker configuration
├── pom.xml             # Maven dependencies
└── README.md           # Project documentation
```

---

## Workflow Diagram

```plaintext
+---------------------+
| Spring Boot Project |
+---------------------+
          |
          v
+---------------------+
| Build JAR (Maven)   |
+---------------------+
          |
          v
+---------------------+
| Docker Image (JDK)  |
+---------------------+
          |
          v
+---------------------+
| Docker Container    |
|  Port 8080 Exposed  |
+---------------------+
          |
          v
 http://localhost:8080
```
