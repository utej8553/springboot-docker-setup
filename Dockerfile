FROM openjdk:22-jdk
ADD target/demoapp.jar demoapp.jar
ENTRYPOINT ["java", "-jar", "/demoapp.jar"]