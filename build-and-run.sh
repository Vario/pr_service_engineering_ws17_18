set -e

currdir=$(pwd)

cd backend/spring-server

mvn package
sudo docker-compose build
sudo docker-compose up

cd $currdir
