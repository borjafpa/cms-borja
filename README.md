# CMS
Thi is the backend for a content management system build with:
- SpringBoot, 
- Spring 3 
- MongoDb
- Local AWS S3 (localstack)

# Software Architecture
I tried to follow the hexagonal architecture (bu it could be improved, for sure)

# Endpoints Definition
All the available endpoints are completely described into the openapi.yml file that could be found [here](/docs/api/openapi.yml)

Besides that, a postman collection is available [here](/docs/postman-collection/cms-borja.json)

# About testing
Currently, it only has:
- One unit test example for the RemoveArticleAdapter
- One integration test example for the RemoveArticleUseCase

Unit test could be executed:
```shell
./gradlew test
```

Integration test could be executed:
```shell
./gradlew testIntegration
```

# Local setup
1. Start all the local stack
- Go to the docker directory and execute the following:
```shell
docker compose up
```

2. To mongodb setup:
- Go to the mongodb container
```shell
docker ps
docker exec -it CONTAINER_ID /bin/bash
```
- Login as admin
```shell
mongosh -u "writeApp" -p writeApp9779 --authenticationDatabase "admin"
```

- Create database
```shell
use cms_borja_db
```

- Create User
```shell
db.createUser(
   {
     user: "cms_borja_user",
     pwd: "cms_borja_password",
     roles: [ "readWrite", "dbAdmin" ]
   }
)
```

3. To AWS s3 setup
- Set the following variables in your local setup
  In linux, you can add the following in your .bashrc file
```shell
#AWS_CREDENTIALS_FOR_TEST
export AWS_ACCESS_KEY_ID=123
export AWS_SECRET_KEY=xyz
export AWS_SECRET_ACCESS_KEY=blah
export AWS_BUCKET_NAME=cms-bucket
```

- Apply those changes
```shell
source .bashrc
```

- Check the current buckets in local S3
```shell
aws --endpoint-url="http://localhost:4566" s3 ls
```

- If the cms-bucket is not listed, you can create it in the following way:
```shell
aws --endpoint-url=http://localhost:4566 s3 mb s3://cms-bucket --region eu-west-3
```

- After that, to list the information on the bucket:
```shell
aws --endpoint-url="http://localhost:4566" s3 ls s3://cms-bucket
```

# References
- About spring boot with mongodb: https://github.com/amrutprabhu/spring-boot-3-with-mongodb/
- About spring security: https://www.bezkoder.com/spring-boot-security-jwt/
- About aws s3 in local (localstack): https://gist.github.com/sats17/493d05d8d4dfd16b7dad399163075156

