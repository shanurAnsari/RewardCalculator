# RewardCalculator
As part of project you need to complete the below assignment in a days’ time. Please work on this and share with us the update.

Please keep the below following points noted while working in the assignment:
Max Reusability
Exception/Error Handling
Optimal code and performance
Documentation
Test Coverage
 
WebAPI Developer 
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.
·       Solve using Spring Boot
·       Create a RESTful endpoint
·       Make up a data set to best demonstrate your solution

1. The package : com.customer.rewards
2. Packing using maven dependancies and building
3. Mysql database is used and configured using application.properties file for storing CUSTOMER and TRANSACTION details in database.scrip.sql file is used to populate records into tables.
4. Exception handling is done for Resource not found(if customer does not exists) and all the global exceptions are taken care is thrown.
5. Used springdoc for API documentation and swagger UI 
   use http://localhost:8080/v3/api-docs for json response
   use http://localhost:8080/swagger-ui/index.html for web ui
6. Logging implemented using org.slf4j.Logger
7. use http://localhost:8080/customerReward/rewardPoints/{customerId} for calculating the reward points 
   ex :http://localhost:8080/customerReward/rewardPoints/100 
8. Refer postman collection available in parent project directory with name "Reward points.postman_collection"
