## Local deployment
- cd db && docker-compose up
- mvn clean spring-boot:run

## To deploy on kubernetes 
- cd db && helm install my-postgresql bitnami/postgresql -f values.yaml
- mvn clean package
- docker build -t web-app-grpc:0.0.1-SNAPSHOT .
- helm install web-app-grpc charts/
- helm upgrade web-app-grpc charts/
- helm uninstall web-app-grpc