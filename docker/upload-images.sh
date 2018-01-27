set -e

currdir=$(pwd)

cd ../backend/spring-server

mvn package
sudo docker-compose build

sudo docker tag prserviceengineeringws1718_rest-quality-api:latest 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:rest-quality-api
sudo docker tag prserviceengineeringws1718_comparison-api:latest 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:comparison-api
sudo docker tag prserviceengineeringws1718_violation-api:latest 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:violation-api
sudo docker tag prserviceengineeringws1718_frontend:latest 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:frontend

sudo docker push 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:rest-quality-api
sudo docker push 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:comparison-api
sudo docker push 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:violation-api
sudo docker push 689213771070.dkr.ecr.eu-central-1.amazonaws.com/rest-quality-api:frontend

cd $currdir
