goEuro is a simple console app returning a list of suggestion based on a city name you provide.
All the suggestion is then listed into "city name".csv file.


1. In order to run the application do the following :
    - **mvn clean package**;
    - **java -jar target/goeuro.jar "city_name"**;

2. Alternative way to run the project from Intellij Idea is :
    - **mvn spring-boot:run -Drun.arguments="city name"**

**Dependencies**:
- idea lombok plugin;
- java version 1.8;
- spring boot plugin;