# Importar a imagem do maven para fazer o build da aplicação
FROM maven:3.8.4-jdk-8 AS build

# Trazer para dentro do contêiner os arquivos da aplicação
COPY src /app/src
# Está copiando a pasta src do projeto e colocando dentro de um diretorio app/src dentro do contêiner

#Copiando o pom.xml para dentro da pasta app
COPY pom.xml /app

# Mudar para o diretório /app
WORKDIR /app

# Rodar comando para build do projeto
RUN mvn clean install

# Pegar o jdk
FROM openjdk:8-jre-alpine

# Copiar .jar para dentro de /app
COPY --from=build /app/target/lucasmarinho-0.0.1-SNAPSHOT /app/app.jar

# Mudar diretório para /app
WORKDIR /app

# Declara exposição de uma porta
EXPOSE 8080

# Rodar comando para execução da aplicação
CMD ["java","-jar","app.jar"]

# docker build -t user-crud-comp /Codigo/Dockerfile
# Comando para gerar imagem docker