# secureapibyjwttoken
Demo project for Secure Restful api using spring boot security and JWT token

# JWT (Json Web Token)
<p> more details  <a href="https://jwt.io/"> here </a> </p>

# Spring Boot Security
<p> more details <a href="https://spring.io/guides/topicals/spring-security-architecture/">here </a> </p>

# Run the application
<p> <b> mvn clean install </b></p>
<p> java -jar << jar-name >> </p>

# Test the api on the postman

<h4>Step-1: Run the endpoint for signup with username and password:</h4>

![alt tag](https://github.com/sendkumaranil/secureapibyjwttoken/blob/develop/signup.png)

<h4>Step-2: Run the endpoint for login with username and password:</h4>

![alt tag](https://github.com/sendkumaranil/secureapibyjwttoken/blob/develop/rename.png)

<p> Once login successfull you will get Authrization token in response header.

<h4>Step-3: Run the post endpoint for save task and pass the authorization token to the header </h4>

![alt tag](https://github.com/sendkumaranil/secureapibyjwttoken/blob/develop/addtask.png)
