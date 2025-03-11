curl 'http://localhost:8080/dining-review/restaurants'
curl -X POST -H "Content-type: application/json" -d '{ "name":"La moma", "zipcode": 36043}' 'http://localhost:8080/dining-review/restaurants'