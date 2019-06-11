# Goddess Tribes Backend

## Introduction

The goal of this project is to provide a Travian-like application where the user can set up a Kingdom eqipped with 1 Farm, 1 Mine and 500 unit of Gold. 

## Setting up the environment

To run the application's backend part you need to have the following things set up:

1.	Create a a database schema with the help of a database like MySQL
2.	Create the application.properties file based on the contents of the provided application.properties.example file:

```bash
spring.datasource.url=${TGB_APP_DB_URL}
spring.datasource.username=${TGB_APP_DB_USERNAME}
spring.datasource.password=${TGB_APP_DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=${TGB_APP_HIB_DIAL}
spring.logging.level.org.hibernate.SQL=debug
spring.jpa.show-sql=true
```

3.	You should set up the following environmental variables:
```bash
•	TGB_APP_DB_URL:  the database URL
•	TGB_APP_DB_USERNAME: username for the database
•	TGB_APP_DB_PASSWORD: password for the database
•	TGB_APP_HIB_DIAL : hibarnate dialect
```

## Further source of information
1.	Dynamic API documentation provided and available here: https://safe-taiga-19293.herokuapp.com/swagger-ui.html
2.	Application deployed to Heroku and available here: https://safe-taiga-19293.herokuapp.com

