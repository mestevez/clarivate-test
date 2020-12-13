# clarivate-test
Test project for clarivate application

# CURL Testing

## Loging In
curl --header "Content-Type: application/json" --data '{"username":"mestevez","password":"secret"}' -v localhost:8080/login

## Adding scores
curl -X PUT -v localhost:8080/level/3/score/2999

## Getting highest scores
curl -v localhost:8080/level/3/score?filter=highestscore
