FROM node:9.4.0

COPY . /opt/frontend
WORKDIR /opt/frontend
RUN npm install
RUN npm install gulp-cli -g

CMD ["gulp", "run"]

EXPOSE 8080
