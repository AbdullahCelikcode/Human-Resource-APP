
# HR-Management-App

This project is to improve the skills of my mentees and to give them new perspectives. This is an application designed to streamline human resource operations within an organization. It offers comprehensive features to manage personnel information, track employee details, and facilitate leave management efficiently

## Tech Stack
**Framework**
* Core
    * Spring 
        * Spring Boot
        * Spring Web
        * Spring Mail
        * Spring Validation


**3rd Party Dependencies**
* sql2o


**Database**
* MySQL


**Language**
* Java 17


**Software Development Process**
* Git
* GitHub


**APIs Interaction Platform**
* Postman

## Before Running 
Run the SQL scripts
```bash
 /path/to/Human-Resource-RESTAPI/src/main/resources/db/database.sql
 /path/to/Human-Resource-RESTAPI/src/main/resources/db/database2.sql
```
## Installation and Running the Project

Clone the Repository

```bash
  git clone https://github.com/AbdullahCelikcode/Human-Resource-RESTAPI
```

Build and Install the Project

```bash
  ./mvnw clean install
```



Run the Spring Boot Application

```bash
 ./mvnw spring-boot:run
```

## Postman Collection

This repository contains the [Postman](https://documenter.getpostman.com/view/27272819/2s9YkrcL2f) collection.


## Routes List:

### Employee (/api)

| Method     | URL                               | Action                                                      |
|------------|-----------------------------------|-------------------------------------------------------------|
| `POST`     | `/employee`                       | `App\Http\Controller\EmployeeController@createEmployee`     |
| `PUT`      | `/employee/{id}`                  | `App\Http\Controller\EmployeeController@updateEmployee`     |
| `PUT`      | `/employee/password/{id}`         | `App\Http\Controller\EmployeeController@updatePassword`     |
| `GET`      | `/employees`                      | `App\Http\Controller\EmployeeController@getEmployees`       |
| `GET`      | `/daily`                          | `App\Http\Controller\EmployeeController@getEmployeesOnLeave`|


### Leave (/api/leave)

| Method     | URL                               | Action                                                      |
|------------|-----------------------------------|-------------------------------------------------------------|
| `POST`     |                                   | `App\Http\Controller\LeaveController@createLeave`           |
| `PUT`      | `/status`                         | `App\Http\Controller\LeaveController@updateStatus`          |
| `GET`      | `/employee/{employeeId}`          | `App\Http\Controller\LeaveController@getLeaves`             |
| `GET`      | `/pending`                        | `App\Http\Controller\LeaveController@getPendingLeaves`      |
| `GET`      | `/approved`                        | `App\Http\Controller\LeaveController@getApprovedLeaves`    |
| `GET`      | `/rejected`                        | `App\Http\Controller\LeaveController@getRejectedLeaves`    |   

### Auth (/api/auth)

| Method     | URL                               | Action                                                      |
|------------|-----------------------------------|-------------------------------------------------------------|
| `GET`      | `/login`                          | `App\Http\Controller\AuthController@login`                  |

  
