set -e

currdir=$(pwd)

# Building Zally
cd ../zally/server
./gradlew clean build

cd $currdir
cd backend/spring-server

# Building Backend
mvn package
sudo docker-compose build
sudo docker-compose up

cd $currdir
