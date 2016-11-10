The purpose of this project is to build a CLI to call the go-euro search API. Given a city name (eg: "London") this software calls the go-euro API to get position suggestions. 
The results are stored in a CSV file. The name of the file is set to the "city" + .csv .

1. In order to run the application follow the below steps :
    - **mvn clean package**;
    - **java -jar target/goeuro.jar "city_name"**;

2. Alternative way to run the project from Intellij Idea is :
    - **mvn spring-boot:run -Drun.arguments="city name"**

**Dependencies**:
- idea lombok plugin;
- java version 1.8;
- spring boot plugin;