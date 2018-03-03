# Turvo Shipment Tracker

Shipment tracker to notify the shippers about the status of their Shipments.

## Softwares and Libraries used

* Spring Boot - 1.5.2.RELEASE - (REST, JMS - ActiveMQ, JPA, Mail)
* H2 embedded - 1.4 - Main data store
* SoapUI - 5.3.0 - for REST setup
* CURL

## Set up 
### Configurations
#### Mail Configurations
In application.properties file update mailing properties. These properties set up the connectivity to mail server.

```
mail.sender=schauhan.iitkgp@gmail.com
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<username>
spring.mail.password=<password>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Launching application
 - Build Application using Maven
 
 ```
 mvn clean install
 ```
 
 - Launch the resulting jar file
 
 ```
 java -jar shipment-0.0.1-SNAPSHOT.jar
 ```
 
### Preparing Data
Below are the rest calls for setting up a sample data set for Shippers, Shipments. Later on ad hoc notifications can be fired using notification REST services:

```

curl -XPUT 'http://localhost:8080/shipper/0384dcd9d3a24895ba9a7508abd76614' -H 'Content-Type: application/json' -d '
{
  "firstName" : "sumit",
  "lastName" : "chauhan",
  "addresses" : [
    {
      "channelType" : "EMAIL",
      "addressValue" : "schauhan.iitkgp@gmail.com"

    },
    {
      "channelType" : "SMS",
      "addressValue" : "+91-8106411845"

    }
  ]
}
'

curl -XPUT 'http://localhost:8080/shipper/0384dcd9d3a24895ba9a7508abd76623' -H 'Content-Type: application/json' -d '
{
  "firstName" : "elon",
  "lastName" : "musk",
  "addresses" : [
    {
      "channelType" : "EMAIL",
      "addressValue" : "elon.musk1111@gmail.com"

    }
  ]
}
'

curl -XPUT 'http://localhost:8080/location/0484dcd9d3a24895ba9a7508abd76615' -H 'Content-Type: application/json' -d '{
  "name" : "Minnesota"
}
'

curl -XPUT 'http://localhost:8080/location/0484dcd9d3a24895ba9a7508abd76616' -H 'Content-Type: application/json' -d '{
  "name" : "New Jersey"
}
'

curl -XPUT 'http://localhost:8080/location/0484dcd9d3a24895ba9a7508abd76617' -H 'Content-Type: application/json' -d '{
  "name" : "Dallas"
}
'
curl -XPUT 'http://localhost:8080/location/0484dcd9d3a24895ba9a7508abd76619' -H 'Content-Type: application/json' -d '{
  "name" : "New York"
}
'
curl -XPUT 'http://localhost:8080/shipment/0384dcd9d3a24895ba9a7508abd76615' -H 'Content-Type: application/json' -d '
{
  "shipmentName" : "Design Patterns Cookbook",
  "shipperId" : "0384dcd9d3a24895ba9a7508abd76614",
  "arrivals" : [
    {
      "locationId" : "0484dcd9d3a24895ba9a7508abd76615",
      "date" : "05/01/2018, 12:27:51"

    },
    {
      "locationId" : "0484dcd9d3a24895ba9a7508abd76616",
      "date" : "06/01/2018, 12:27:51"

    },
    {
      "locationId" : "0484dcd9d3a24895ba9a7508abd76617",
      "date" : "07/01/2018, 12:27:51"

    }
  ]
}
'

curl -XPUT 'http://localhost:8080/shipment/0384dcd9d3a24895ba9a7508abd88615' -H 'Content-Type: application/json' -d '
{
  "shipmentName" : "Rocket Fuel",
  "shipperId" : "0384dcd9d3a24895ba9a7508abd76623",
  "arrivals" : [
    {
      "locationId" : "0484dcd9d3a24895ba9a7508abd76615",
      "date" : "05/01/2018, 12:27:51"

    },
    {
      "locationId" : "0484dcd9d3a24895ba9a7508abd76619",
      "date" : "06/01/2018, 12:27:51"

    }
  ]
}
'

```

## Firing Notifications
Notification firing is exposed via REST URL. Sample call below

```
curl -XPOST 'http://localhost:8080/notifier/' -H 'Content-Type: application/json' -d '
{
  "shipmentId" : "0384dcd9d3a24895ba9a7508abd76615",
  "lastSeenLocId" : "0484dcd9d3a24895ba9a7508abd76617",
  "lastSeenDate" : "06/01/2018, 12:27:51",
  "nextDestinations" : [
    "0484dcd9d3a24895ba9a7508abd76617"
  ]
}
'
```

Below is a sample delivered notification

![alt text](https://raw.githubusercontent.com/sumitkchauhan/shipment-tracker/master/docs/sample-mail-delivery.png)

# Unimplemented Stuff
Below aspects were considered, however not implemented:
- Security
- App can be broken down into multiple microservices. Mailing is an example, however kept in same jar for now.
- Shipment Time Zone differences
- Unit Testing
- Emitter functionality - There was some discussion needed around this, hence not implemented.
