# LMS_IKub_Intership

This is a Rest Api Spring Boot Library Management System that manages books and users!

**Author**: Artur Molla <br />
**Version**: 1.0.0

---
## Overview
This project is Rest API application that serves as an Library
management system. Managing three types of users (Admin,Secretary and Student) 
and different books on the library.

#### Registration, Login with (JWT)
After registration, users will wait for his accout activation.After activation he can login
and proceed as per his/her role and authorities granted.
Admin after login he/her can have access on managing the users, activating them and deleting.
Secretary after login can have access on creating, viewing, updating and deleting books 
from inventory.

#### Database Schema

![Schema](https://github.com/ictTuri/LMS_IKub_Internship/blob/main/img/diagram.png?raw=true)

---
#### Database Schema Shortened Explanation
A user :
* can have one or more roles
* has an unique email
* has an unique username

A book :
* has a title
* title is unique

A Rezervation :
* Has a student and book
* Has rezervation date and return date

---
#### Operation Explained
_User_ Admin :
* Access to all users and user-roles relations
* Access to create, read, update and delete to users and user-roles
* Can not delete a role user relation if it is the only one for the user
* Can add new user-role relations 
* Can not delete book idf it is taken/rezerved
* Access to activate deactivated user 
* Can soft delete and hard delete users
* Can add roles to his account and have additional accesses
* Have Crud Access on Rezervation Table

_User_ Secretary:
* Access to create, read, update and delete books
* Can not add a book if the book already exist
* Can not delete book idf it is taken/rezerved
* Have Crud Access on Rezervation Table
* Can Close a rezervation 

_User_ Student
* After activation user has access to view books
* Can view books by id and all
* Can rezerve a book that is not taken already

---
#### Maven Testing
* Run Maven Clean and Then Maven Test
* Main Profile set to "mongo" or "sql"
* Mongo Repository Tested along with Sql Repository
* 80% code coverage on tests

---
#### Technical details on testing
_Testing_ runs on 2 profiles:
* "mongo":
* It test the mongo repository operations
* It initializes an embedded mongo database 
* It is not transactional so the tests are made having that in mind
* If test does not run in your end / check their order as the data get saved from one to another

* "test":
* It test the sql repository operations
* It initializes an embedded H2 database
* It is transactional so the tests are made having that in mind

* Controller and Service tests runs on both profiles !
---
#### Controllers 

* Book-Controller Operations

![Schema](https://github.com/ictTuri/LMS_IKub_Internship/blob/main/img/bookController.PNG?raw=true)

---
* Register-Controller Operations

![Schema](https://github.com/ictTuri/LMS_IKub_Internship/blob/main/img/registerController.PNG?raw=true)

---
* Rezervation-Controller Operations

![Schema](https://github.com/ictTuri/LMS_IKub_Internship/blob/main/img/rezervationController.PNG?raw=true)

---
* Users-Controller Operations

![Schema](https://github.com/ictTuri/LMS_IKub_Internship/blob/main/img/userController.PNG?raw=true)

---
* User-Roles-Controller Operations

![Schema](https://github.com/ictTuri/LMS_IKub_Internship/blob/main/img/userRoleController.PNG?raw=true)

---

---
## Architecture
This application is created using Spring Boot 2.4  <br />
* Languages*: JAVA, SQL<br />
* Tools*: STS Spring Tool SUite, Sonarlint, Postgresql and H2 for testing, Jpa Hibernate, Lombok<br />
JUnit, Logger, Spring Security Jwt<br />
* Type of Application*: Rest Api <br />