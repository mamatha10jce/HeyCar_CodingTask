# HeyCar_CodingTask
Coding task given by heycar

problem

context

Imagine you are creating the first version of heycar. We need to provide a platform
that can receive the listings from the dealers through different providers, and make
them available in the platform.
  • Dealer - the company that is publishing the car
  • Listing - the car that is being published
  • Provider - the platform the dealers already use to manage their own listings
description
We need to get vehicle listings from different sources and make it available in the
platform in a standardized format. Some of our providers are sending data via CSV,
with the following format (+example) to the /upload_csv/<dealer_id>/endpoint via
POST, where <dealer_id> will be the id of the dealer who is sending the data:
code,make/model,power-in-ps,year,color,price
1,mercedes/a 180,123,2014,black,15950
2,audi/a3,111,2016,white,17210
3,vw/golf,86,2018,green,14980
4,skoda/octavia,86,2018,16990
The rest of the providers are sending the information via JSON API, using the
following format to the /vehicle_listings/endpoint via POST:
[
 {
 "code": "a",
 "make": "renault",
 "model": "megane",
 "kW": 132,
 "year": 2014,
 "color": "red",
 "price": 13990
 }
]
requirements
• Different dealers may have listings with the same code, and the listings should
be treated as different listings
• If a listing is sent again by the same dealer (based on the code), it should be
updated
• All the uploaded listings should be available in json on the /search endpoints
via GET
• A client should be able to search using the following parameters: make,
model, year and color
For this challenge you don't need to cover:
• Authentication
• Authorization
• Data removal

Solution:

# Technologies Used

1. Spring Boot.
2. Java 8.
3. H2 in-memory database
4. Lombok
5. JPA Specifications
6. Swagger
7. Eclipse IDE
8. Maven Build

# APIs

1. GET : http://localhost:8080/search?make=audi&model=a3&year=2016&color=white(User query parameter for search and optional)
2. POST : http://localhost:8080/upload_csv/{dealerId}/
3. POST : http://localhost:8080/vehicle_listings/{dealerId}/

Build steps:
clone project from git repository
`mvn clean Install`
`mvn spring-boot:run`
 
 Created Dockerfile to dockerise the application, run Dockerfile

Test using below url" 

http://localhost:8080/search

Sample Output:
{
    "content": [
        {
            "id": 1,
            "make": "mercedes",
            "model": "a 180",
            "color": "black",
            "price": 15950,
            "year": 2014,
            "kw": 123
        },
        {
            "id": 3,
            "make": "audi",
            "model": "a3",
            "color": "white",
            "price": 17210,
            "year": 2016,
            "kw": 111
        },
        {
            "id": 5,
            "make": "vw",
            "model": "golf",
            "color": "green",
            "price": 14980,
            "year": 2018,
            "kw": 86
        },
        {
            "id": 7,
            "make": "skoda",
            "model": "octavia",
            "color": "16990",
            "price": null,
            "year": 2018,
            "kw": 86
        }
    ]
   

# Documentation

The documentation swagger API : http://localhost:8080/swagger-ui.html# 

Solution approach :
• Problems you discovered

1. The Dealers data is in differnt format. I created csv and Json format to deal with this.
2. Each Dealer, the way the provide data might be different, that should be handle wiht good csv parser instead of mapping.

• Executed tests and results
Junit and Mockito test cases are written in the test folders which can be executed to get the results

• Ideas you would like to implement if you had time - explain how you would
I would like to implement different way of parsing CSV files using Univosity parser to deal with columns which might get in different order and parser is fast as well.
We can create microservice architecture to deal with dealer data and car details. We can use Kafka to handle even driver architecture.

• Decisions you had to take and why
Decided to use Spring Boot for development it made our job easy 
To achieve clean code, used Lombak  
since it is a case study, H2 is inmemory database and have choosed with JPA and Hibernate ORM framework

• Tested architectures and reasons why you chose them instead of other options
  - Spring Boot with Web application development is loosely coupled and it is layered architecture. With this we can achieve Interface 
  Implementaion pattern(Abstract pattern)
  - Query Search in Get operations has recommended according to Restful API development best practices.
  - 
Sample outputs:


