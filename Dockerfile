FROM openjdk:17-alpine3.14

COPY CodeTestRunner.jar ./
ENTRYPOINT ["java", "-jar", "CodeTestRunner.jar"]
CMD ["aqui-va-la-clase", "aqui-va-el-directorio"]
