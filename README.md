# Simple Library API :book:

Repository with the content of the 5-parts Spring Boot tutorial available [HERE](https://dev.to/franciscotis/creating-a-rest-api-using-spring-boot-tests-documentation-part-01-2262)

### System routes

| **HTTP Method** |    **/**    |
|:---------------:|:-----------:|
|       GET       |    books    |
|       GET       | /books/{id} |
|       POST      |    /books   |
|       PUT       | /books/{id} |
|      DELETE     | /books/{id} |



### Tests

To run tests, you should run the following:

```
\.mvnw test

```


### Swagger Documentation

After initializing the server, you should access the url http://localhost:8080/swagger-ui/index.html to see the documentation.
