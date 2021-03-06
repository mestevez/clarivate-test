# clarivate-test
Test project for clarivate job application.

# Run application
The following commands allows running, or start, the application.

## Windows
```
mvnw.cmd spring-boot:run
```

## Linux/MacOS
```
./mvnw spring-boot:run
```

# Junit Test
There is a set of Junit scripts for testing the main functionalities.
They can be executed with the following commands:

## Windows
```
mvnw.cmd test
```

## Linux/MacOS
```
./mvnw test
```

# Execution
Once the application is started, it can be accessed via a simple HTTP request. 
The easiest way to do so is using CURL commands. 

## CURL
 
### Logging In
Application access is secured by session key authentication. 
So, the first step in order to execute further requests is getting a session key.
It can be achieved by the executing the following curl command: 
```
curl --header "Content-Type: application/json" --data "{\"username\":\"mestevez\",\"password\":\"secret\"}" -v localhost:8080/login
```

The command above should response with something like:
```
...

Bearer  eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjbGFyaXZhdGVKV1QiLCJzdWIiOiJtZXN0ZXZleiIsImlhdCI6MTYwNzg4MjQwNCwiZXhwIjoxNjA3ODgzMDA0fQ.fI7aF0-2WmWmvHQl_fIZCFn3SxotaPwEdcnuQsCMPG0
* Connection #0 to host localhost left intact
```

Notice that "Bearer eyJh..." corresponds with session key, which must be sent in further request as a header parameter.

> Do not forget include "Bearer " when copying the key.

### Adding scores
The addScores REST method can be executed like this: 
```
curl --header "Authorization: {sessionKey}" -X PUT -v localhost:8080/level/3/score/2999
```

> Replace {sessionKey} by the token obtained before.
### Getting highest scores
```
curl --header "Authorization: {sessionKey}" -v localhost:8080/level/3/score?filter=highestscore
```
