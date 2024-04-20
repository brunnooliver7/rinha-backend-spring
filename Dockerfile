# Escolha a imagem base
FROM maven:3.8.4-openjdk-11-slim AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Baixa as dependências definidas no pom.xml (isso é feito antes para aproveitar o cache de dependências)
RUN mvn dependency:go-offline

# Copia todos os arquivos do código-fonte para o diretório de trabalho
COPY src ./src

# Executa o comando mvn clean install para compilar o código-fonte e criar o pacote JAR
RUN mvn clean install

# Usando uma imagem base mínima do OpenJDK para reduzir o tamanho final da imagem
FROM openjdk:11-jdk-slim

# Copia o arquivo JAR da fase anterior da construção para a imagem final
COPY --from=build /app/target/rinha-backend-spring-0.0.1-SNAPSHOT.jar /app/

# Defina o ponto de entrada
ENTRYPOINT ["java","-jar","/app/rinha-backend-spring-0.0.1-SNAPSHOT.jar"]
