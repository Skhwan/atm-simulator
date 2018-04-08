# ATM Simulator
This is a Spring Boot project simulating ATM machine's dispensing logic

### Technologies
* [Maven](https://maven.apache.org/) - Software project management
* [Spring Boot](https://projects.spring.io/spring-boot/) - Quickly create stand-alone, production-grade Spring based Applications
* [H2](http://www.h2database.com/html/main.html) - A Java in-memory database providing JDBC API
* [Mockito](http://site.mockito.org/) - Write beautiful tests with a clean & readable with simple API

### Prerequisites
* [ATM Simulator Website](https://github.com/Skhwan/atm-simulator-react)
* Maven - you can easily install it on MAC OS X by 
```
$ brew install maven
```

### Running project on MAC OS X
* Clone and go to the project by running command
```
$ git clone git@github.com:Skhwan/atm-simulator.git
$ cd atm-simulator
```

* Then prepare project dependencies with
```
$ mvn clean install
```

* Run project using command
```
$ mvn spring-boot:run
```

The project should start on port 8080

### How to test the API
Go to [frontend atm-simulator project](https://github.com/Skhwan/atm-simulator-react) and run the frontend side first and use it to test ATM Simulator
The Website should look like this
![React test-tool website](/src/main/resources/image/test-tool-sample.png?raw=true)


OR


You can request dispensing money via [Postman](https://www.getpostman.com) or by opening the following URL in a browser
```
http://localhost:8080/atm-simulator/dispense?amount=<AMOUNT>
```
while \<AMOUNT\> indicates money you want to dispense from ATM Simulator
The response should look like
```
{
    "responseCode": "0",
    "responseDesc": "SUCCESS",
    "responseStatus": "SUCCESS",
    "responseBody": [
        {
            "type": "ONE_HUNDRED",
            "value": 100,
            "amount": 1
        },
        {
            "type": "FIFTY",
            "value": 50,
            "amount": 1
        }
    ]
}
```
###### Possible API response
* success
```
{
    "responseCode": "0",
    "responseDesc": "SUCCESS",
    "responseStatus": "SUCCESS",
    "responseBody": [
        {
            "type": "ONE_HUNDRED",
            "value": 100,
            "amount": 1
        },
        {
            "type": "FIFTY",
            "value": 50,
            "amount": 1
        }
    ]
}
```

* Insufficient balance - Total balance of ATM Simulator is less then needed amount
```
{
    "responseCode": "1",
    "responseDesc": "Remaining balance less than dispensed amount",
    "responseStatus": "FAIL",
    "responseBody": []
}
```

* Invalid amount - Needed amount is less than minimum value of the note in ATM Simulator(which, in this case, is 20)
```
{
    "responseCode": "1",
    "responseDesc": "Amount less than min amount",
    "responseStatus": "FAIL",
    "responseBody": []
}
```

* Insufficient note - ATM Simulator doesn't have enough notes needed by requested amount or the simulator cannot find appropriate combination of notes to fulfill requested amount
```
{
    "responseCode": "1",
    "responseDesc": "Insufficient note number. Try dispensing a different amount.",
    "responseStatus": "FAIL",
    "responseBody": []
}
```

###### Possible Web response
* Please enter dispensed amount - The user doesn't enter amount when clicking dispense button
* Amount less than min amount - Requested amount is less than minimum amount
* Remaining balance less than dispensed amount - Total balance of ATM Simulator is less than requested amount
* Insufficient note number. Try dispensing a different amount. - ATM Simulator doesn't have enough notes needed by requested amount or the simulator cannot find appropriate combination of notes to fulfill requested amount
* Out of service - The backend server isn't started
