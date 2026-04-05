# Usar a imagem oficial do OpenJDK 17 como base
FROM eclipse-temurin:17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o container
COPY target/*.jar app.jar

# Expor a porta que a aplicação irá rodar
EXPOSE 9090

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
