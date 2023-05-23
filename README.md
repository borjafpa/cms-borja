- docker image for mongodb
1. User docker compose file
docker compose up

2. Go to the mongodb container

docker ps
docker exec -it CONTAINER_ID /bin/bash 

3. Login as admin
mongosh -u "writeApp" -p writeApp9779 --authenticationDatabase "admin"

4. Create database
use cms_borja_db

5. Create User

db.createUser(
   {
     user: "cms_borja_user",
     pwd: "cms_borja_password",
     roles: [ "readWrite", "dbAdmin" ]
   }
)

- Based on
https://github.com/amrutprabhu/spring-boot-3-with-mongodb/
https://www.bezkoder.com/spring-boot-security-jwt/

