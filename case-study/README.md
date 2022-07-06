# case-study

## API Reference

**Endpoints**

To see billing info service details, please enter the following url on your web browser after run project

http://localhost:8080/swagger-ui.html

| Request   | Route    | Body                       |  Description |
| :-------- | :------- | :------------------------- | :------------|
| POST      |  | {"amount": 10,"billNo": "TR000","email": "john@doe.com","firstName": "John","lastName": "Doe","productName": "USB DISC"} | Insert Billing Info  |
| GET    | /accepted/{email} |  | list of accepted bills  |
| GET       | /refected/{email} |  | list of not accepted bills  |

## Curl Commands
```
curl -X POST "http://localhost:8080/api/bill" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"amount\": 500, \"billNo\": \"TR000\", \"email\": \"john@emm.com\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"productName\": \"USB DISC\"}"
```
```
curl -X GET "http://localhost:8080/api/bill/accepted/john%40emm.com" -H "accept: */*"
```
```
curl -X GET "http://localhost:8080/api/bill/rejected/john%40emm.com" -H "accept: */*"
```

## Postman

You can find the postman collection in casestudy folder.

## Dockerfile

For running application execute these commands :
```
docker-compose up --build
cd casestudy
mvn spring-boot:run 
```
Application will start at port 8080
