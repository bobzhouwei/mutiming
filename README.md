# MuTiming: MuTiming Online Trading Service

## What does it do
This service provides thr APIs for supporting MuTiming Online Trading.

## Main functions
- Checkout
- ... more functions to be added later

## Quick Start
#### Step 1: Download the source
You can download the source zip or clone it from the github (https://github.com/bobzhouwei/MuTiming)

#### Step 2: Package it
- Run "mvn clean package" 
(Make sure you have the maven installed. If not, download maven via (http://maven.apache.org/download.cgi) 
and follow the instructions to make sure maven be installed properly.)
- After that, you can get the "mutiming-web.jar" under "mutiming-web\target" folder

#### Step 3: Deploy it
- Run "java -jar mutiming-web.jar" 
(Make sure you have the JDK 8 installed. If not, download via https://www.oracle.com/technetwork/java/javase/downloads/index.html 
and follow the instructions to install and configure it.)
- After that, you should see the SpringBoot welcome screen and Application started prompt, that means the application deployed successfully.
(If not, please contact me via: bobo_shu888@hotmail.com)
- Please be aware that this kind deploy is just a temporary solution to do local debug or trial. 
If you want to run it as a persistent service, you should deploy it on your web server.

#### Step 4: Run it
##### Use Swagger
- I embedded the Swagger UI to make it easier to try out the API. Open the url (http://localhost:8080/swagger-ui.html) on your browser.
- You can find the "checkout" endpoint when expanding the "trade-controller". 
- Than you can "Try it out". Fill in the request body with json format, like ["001"] or ["001","002"], then Execute it.
You can see the result at the Response boy.
 ##### Use postman
 - Post request body with json format to the endpoint address - http://localhost:8080/checkout

## Application architecture
##### I use Spring Boot and Domain-Driven Design for this project. So the project structure is:
    - mutiming-online
        - mutiming-common   // the common layer, put the common functions like config/cache/third party utilities/common utities
        - mutiming-domain   // the domain layer, define the repository interfaces and the service functions here
        - mutiming-entity   // define the models like domain objects and value objects. You can also put persistant object, constants/enums here.
        - mutiming-infrastructure   // the infrastructure layer, do implement the interfaces defined in domain layer.
        - mutiming-web      // the application entry point
            - configuration // put log/authentication/cache/async configurations and other configurations like Swagger/ThreadPool config here
                - log       // customized the log output for each service.Log contaisn: uri/interval/requestHead/requestBody/responseHead/responseBody, which provides clear information and help the debug/monitor.
            - controller    // the controllers

## The approaches and further improvements
#### Approaches
- I use configure file to store the watch price list and load it into memory after the first get. 
- For each call of the "checkout" method, I loop the list and put them into a map, the key is the watch Id and the value is the purchase quantity.
- Then I loop the map and lookup the watch price list, to get the unit price, discount criteria, and calculate the total price and total discount. 
- The final price is total price minus total discount.
#### Notes
- I modified the response body a little compare to the Coding challenger, I expanded it with a response head with code and message. The main purpose is to be able to show more information via the response.
- If the watch price list is empty or something wrong, the response returns error code and error messages to figure out the issue, not just returns ZERO.
- If any of the purchasing watch Id is not listed in the watch sale list, the response will also returns error code and error messages. I think at this situation, returns error code and error messages is reasonable for a e-trading system. The trade should not be proceeded if an item has no price, instead of just skip or ignore it which could cause major issues.
- I made a max item quantity check, currently set to 100000000 which should be a reasonable size for the business, to prevent the overflow and response issues. 
#### Further improvements
- Put the configuration (like the price list, and max item quantity) to an online configuration platform (like Apollo, Nacos, etc.) to make it easy to be maintenance and be updated.
- Put the price list onto a database is another way to enable it persistence and able to interact with other module/system. A cache (like Redis) need to be applied to handle the query of price list. The cache should be updated regularly or right after any changes in to database.
- As a trial project, I did not care much about the security and rate limit. To be an formal e-commerce API, need to introduce the authentication check, gateway, load balance, rate limit, system monitor and alert, etc. To make it robust. 
- Current the implement of discount strategy is weak, which can just handle one kind of discount strategy. Further improvement can separate it as a discount module to be able to handle more kinds of discount strategies. 
