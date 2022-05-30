## RESTFul Web Services


### spring-boot
Study Spring Boot Features ! Happy Learning !!

### RestFul Web Services

* Representational State Transfer ( REST )
    * Platform Independent, Loosely coupled & Interoperable
    * REST mainly rests on following three -
        * Resource : Resources are the fundamental elements. A resource is an object with a type, associated data, relationships to other resources, and a set of methods that operate on it. Every resource has unique identifier on web platform, which is called as URI ( Universal Resource Identifier). In REST world, we use nouns to identify type of resource.
        * Representation
            * JSON
            * XML
        * Verbs
            * GET : **retrieve an existing resource**
            * POST : **create a new entry of resource**
            * PUT : **modify an existing resource**
            * DELETE : **remove an existing resource** 
            
#### API Specifications

- Retrieve all BusinessUnits   				- GET /businessunits
- Create BusinessUnit        				- POST /businessunits
- Retrieve BusinessUnit   					- GET /businessunits/{id}
- Update BusinessUnit						- PUT /businessunits/{id}
- Remove BusinessUnit   	 					- DELETE /businessunits/{id}

- Retrieve all employees for single BusinessUnit  		- GET /businessunits/{businessUnitId}/employees
- Create employee for BusinessUnit  					- POST /businessunits/{businessUnitId}/employees
- Retrieve details of employee for BusinessUnit			- GET /businessunits/{businessUnitId}/employees/{employeeId}


#### Testing

* H2 Database Testing
    * Open http://localhost:8080/restful-web-services/h2-console 
    * Use URL to validate DB jdbc:h2:file:./data/restful-web-services
* API Testing using CURL
    * Retrieve all BusinessUnits : **curl -s -i http://localhost:8080/restful-web-services/api/businessunits**
    * Create BusinessUnit : **curl -s -H "Content-Type: application/json" -i -X POST -d '{ "businessUnitName": "AEG" }' http://localhost:8080/restful-web-services/api/businessunits**
    * Retrieve BusinessUnit : **curl -s -i http://localhost:8080/restful-web-services/api/businessunits/3**
    * Update BusinessUnit : **curl -s -H "Content-Type: application/json" -i -X PUT -d '{ "businessUnitId": 3, "businessUnitName": "AEG_UPDT" }' http://localhost:8080/restful-web-services/api/businessunits/3**
    * Remove BusinessUnit : **curl -s -H "Content-Type: application/json" -i -X DELETE http://localhost:8080/restful-web-services/api/businessunits/3**
    * Retrieve all employees for single BusinessUnit : **curl -s -i http://localhost:8080/restful-web-services/api/businessunits/2/employees**
    * Create employee for BusinessUnit : **curl -s -H "Content-Type: application/json" -i -X POST -d '{"firstName":"Jack","lastName":"Sparrow","gender":"M","email":"jack.sparrow@users.noreply.github.com","birthDate":"1977-12-03","joiningDate":"1999-04-15","grade":"Developer","salary":20000}' http://localhost:8080/restful-web-services/api/businessunits/2/employees**
    * Retrieve details of employee for BusinessUnit : **curl -s -i http://localhost:8080/restful-web-services/api/businessunits/2/employees/870820**
    
### Exception Handling

global exception handling

### Run as Docker Container

* Build Image
   * **docker build -t restful-web-service .**
* Run Container 
   * **docker run --name restful-web-services -p 8080:8080 -v ${PWD}/container-logs:/logs --rm -d restful-web-service**
   * logs will be copied from container to ${PWD}/container-logs
   * or we can check with **docker container logs restful-web-service**    
            
