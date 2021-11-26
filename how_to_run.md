# Build and Run
Build and Run DockerFile in "EveronAssignment" folder by create an image:

docker build -f Dockerfile -t assignment .
## to see images:
```
docker images
```
## to run the image:
```
docker run -p 8086:8086 assignment
```
## to see all container:
```
docker container ls
```
## to stop a container
```
docker container stop ${container ID}
```
## to remove the container
```
docker container rm ${container ID}
```


## Run
This project can be run by three ways:
1.  Run the docker image on port 8086 (or every available port)
2.	Run com.everon.assignment.AssignmentApplication class from your IDE.
3.	Run this command in "EveronAssignment" folder by
      ```
      mvn package
      ```
and then run:
java -jar /target/assignment-0.0.1-SNAPSHOT.jar
### New Charging Session
Web service URL to save a new car charging session:
**http://localhost:8086/chargingSessions**
      
      curl --location --request POST 'http://localhost:8086/chargingSessions/' \
      --header 'Content-Type: application/json' \
      --data-raw '{"stationId":"ABC-12345"}'
      
### Stop Charging Session
Web service URL to stop car charging session:
**http://localhost:8086/chargingSessions/{id}**
      
      curl --location --request PUT 'http://localhost:8086/chargingSessions/49ffb96a-4e0d-11ec-bf51-0339fbaae89b'

### Get All Charging Session
Web service URL to get all car charging session:
**http://localhost:8086/chargingSessions**

      
      curl --location --request GET 'http://localhost:8086/chargingSessions'

### Summary Report
Web service URL to get a summary report:
**http://localhost:8086/chargingSessions/summary**

      
      curl --location --request GET 'http://localhost:8086/chargingSessions/summary'


### Swagger
After running the project it can be used by any rest client or swagger.
You can open Swagger by this link: **http://localhost:8086/swagger-ui.html**