FROM openjdk:8
EXPOSE 8080
ADD target/tejal_ipranges_otto.jar tejal_ipranges_otto.jar
ENTRYPOINT ["java","-jar","/tejal_ipranges_otto.jar"]