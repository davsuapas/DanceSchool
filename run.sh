#!/bin/bash

sudo systemctl start mysql
sudo systemctl start kafka
sudo systemctl start mongod

gnome-terminal -x sh -c 'cd ../DanceSchool-Eureka && mvn spring-boot:run'
sleep 10
gnome-terminal -x sh -c 'cd ../DanceSchool-Configuration && mvn spring-boot:run'
sleep 15
gnome-terminal -x sh -c 'cd ../DanceSchool-Security && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-TurbineStream && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-HystrixDashboard && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-Zuul && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-Customer && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-Classroom && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-ClassCustomer && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-ClassCustomerView && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ../DanceSchool-Web && mvn spring-boot:run'

