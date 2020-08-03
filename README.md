# Employee Portal REST API Server  

## Run the App with TestCases

    mvn -P runWithTest

## Run the App

    mvn spring-boot:run


## REST APIs
 
### Get all Departments

`GET /api/department/all`

    curl -X GET "http://localhost:8080/api/department/all" -H  "accept: */*"

### Response

     [
	  {
	    "id": 1,
	    "name": "Development and Engineering"
	  },
	  {
	    "id": 2,
	    "name": "Design and Production"
	  },
	  {
	    "id": 3,
	    "name": "Training and Maintenance"
	  },
	  {
	    "id": 4,
	    "name": "Quality control"
	  }
	] 

### Add Department

`POST /api/department/create`

    curl -X POST "http://localhost:8080/api/department/create" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"name\":\"Test\"}"

### Response status code

    201


### Add Employee

`POST /api/employee/create`

    curl -X POST "http://localhost:8080/api/employee/create" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"firstName\":\"Shakti\",\"lastName\":\"Singh\",\"gender\":2,\"dob\":\"2020-08-02T10:22:07.012+00:00\",\"department\":1}"

### Response status code

    201
    

### Get all Employees

`GET /api/employee/all`

    curl -X GET "http://localhost:8080/api/employee/all" -H  "accept: */*"

### Response

	[
	    {
	        "id": 6,
	        "firstName": "Shakti",
	        "lastName": "Singh",
	        "dob": "2020-08-02T10:22:07.012+00:00",
	        "department": {
	            "id": 1,
	            "name": "Development and Engineering"
	        },
	        "gender": 2
	    } 
	]