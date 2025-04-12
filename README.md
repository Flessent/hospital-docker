# Hospital Management
### Injection of dependance isn't a new way in software development. We provided here a small project about how the injection was done before Spring: https://github.com/Flessent/Injection_of_Dependence_without_Spring
### Injection of dependance with Spring but yet Using an xml config file in which beans are created and also using setter : https://github.com/Flessent/ioc-spring

## In this Github, project using Injection of dependance through Annotation (@Autowired or Setter): 
 - https://github.com/Flessent/Sprachschule-Projekt
 - https://github.com/Flessent/ioc-spring

## This is a very small application using Spring and Postgres 12 in order to manage a hospital.
The goal of this small project is 
### 1-to show how to properly manage OneToOne, ManytoOne and ManyToMany relationship using Spring MVC and Spring data.
### 2 -Applying Injection of dependance using the recommended approach which is the injection through the constructor (not @Autowired)
## 3- Avoid StarkoverflowException(common in ManyToMany or ManyToOne relationship) with help of Jakson library 
## 4-Security part comes soon. However the management of ROLE, password and username are done  using Bcrypt which is a way of hashing password with spring security.
