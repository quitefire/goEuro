The purpose of this project is to build a CLI to call the go-euro search API. Given a city name (eg: "London") this software calls the go-euro API to get position suggestions. 
The results are stored in a CSV file. The name of the file is set to the "city" + .csv .


### How to build

This project requires [Maven](https://maven.apache.org/) 3.x , [java](https://java.com/en) 1.8+ and Lombok plugin for Intellij Idea

```bash
$ git clone https://github.com/quitefire/goEuro.git
$ cd ./go-euro
$ mvn clean package
```



### How to run

Build the project and do the following: 

```bash
$ java -jar target/goeuro.jar "city name"

Alternative way to run the project from Intellij Idea is :
$ mvn spring-boot:run -Drun.arguments="city name"

$ cat(unix)/type(windows) "city name".csv
  _id,name,type,latitude,longitude
  376217,Berlin,location,52.524370,13.410530
  448103,Berlingo,location,45.502980,10.043660
  425332,Berlingerode,location,51.457750,10.238400
  425326,Bernau bei Berlin,location,52.679820,13.587080
  314826,Berlin Tegel,airport,52.554800,13.289030
  314827,Berlin Schönefeld,airport,52.388726,13.518087
  334196,Berlin Hbf,station,52.525589,13.369548
  333977,Berlin Ostbahnhof,station,52.510972,13.434567
```

