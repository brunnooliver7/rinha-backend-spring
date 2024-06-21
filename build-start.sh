mvn clean install
docker build -t api-rinha-spring .
docker-compose up --force-recreate -V
