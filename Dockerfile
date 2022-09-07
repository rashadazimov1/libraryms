FROM openjdk:11

COPY libraryms-2.5.2.jar libraryms.jar

ENTRYPOINT ["java","-jar","/libraryms.jar"]