# DanceSchool
Dance School Project (Spring Cloud Netflix)

# Architecture

![Architecture](https://github.com/davsuapas/DanceSchool/blob/master/Architecture.jpg)

- Eureka
- Spring Cloud Configuration (git)
- Hystrix Dashboard
- Turbine Stream
- Zuul
- Ribbon
- Event sourcing and CQRS
- Open Feign
- Spring Cloud Stream (Kafka)
- Spring MVC
- Spring AOP
- Spring Data Rest
- Spring JPA (Hibernate)
- Spring Data MongoDb
- Spring Security
- Spring Security (oauth2)
- Spring OAuth2 CLI
- MySql
- Mongo DB
- Kafka
- Spring Cloud Contract
- Spring Cloud Pipeline (Kubernete)
- Kubernete

# Projects

- https://github.com/DanceSchoolMicroservices -> Configuration
- https://github.com/davsuapas/DanceSchool-EurekaSchool
- https://github.com/davsuapas/DanceSchool-ZuulSchool
- https://github.com/davsuapas/DanceSchool-TurbineStreamSchool
- https://github.com/davsuapas/DanceSchool-HystrixDashboardSchool
- https://github.com/davsuapas/DanceSchool-ConfigurationSchool
- https://github.com/davsuapas/DanceSchool-SecuritySchool
- https://github.com/davsuapas/DanceSchool-ClassCustomer
- https://github.com/davsuapas/DanceSchool-ClassCustomerStubrunner
- https://github.com/davsuapas/DanceSchool-ClassCustomerView
- https://github.com/davsuapas/DanceSchool-ClassroomSchool
- https://github.com/davsuapas/DanceSchool-CustomerSchool
- https://github.com/davsuapas/DanceSchool-WebSchool
- https://github.com/davsuapas/DanceSchool-CoreSchool
- https://github.com/davsuapas/DanceSchool-CloudPipeline

# Articles

- [Microservices security with Spring Cloud and how to integrate oauth2 with spring security roles](https://davidsuarez-architecture-development.blogspot.com/2018/09/microservices-security-with-spring.html)

# Work without Kubernete (localhost)

- Install MySql in localhost
- Install Kafka in localhost
- Install MongoDB in localhost
- Add '127.0.1.1	school-security' in /etc/hosts
- Clone all repository including this one at the same root (except https://github.com/DanceSchoolMicroservices)
- Create Username into MySql called schoolservice and password 1234
- Create Database into MySql called school
- Execute run.sh and wait (minimal 12 Gb memory)
- Launch browser at http://localhost:8080 (User: admin, Password: password or User: user, Password: password)
