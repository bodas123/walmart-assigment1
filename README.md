#  store-service 

###  Build Project
- mvn clean install


### Docker Image Create:
docker build -t driver-service:1.0 .


### Run Project
docker-compose up

### Store API - For Adding New Store:

POST : http://127.0.0.1:9080/stores

{
  "storeID": "1234",
  "latitude": 27.876,
  "longitude": -128.33
}

### Driver Location API - For adding driver new location:

POST : http://127.0.0.1:9080/drivers/location

{
  "driverID": "m123@gmail.com",
  "latitude": 27.876,
  "longitude": -128.33
}


### Nearest Driver:


GET : http://127.0.0.1:9080/drivers/nearest?StoreID=1234&N=2








