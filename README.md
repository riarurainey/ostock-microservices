VAULT
docker run --cap-add=IPC_LOCK -e 'VAULT_DEV_ROOT_TOKEN_ID=myroot' -e 'VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200' -p 8200:8200 --name=dev-vault vault
curl -X "GET" "http://localhost:8071/licensing-service/default" -H "X-Config-Token: myroot"

Docker
$ mvn clean package dockerfile:build
$ docker-compose -f docker/docker-compose.yml up