#!/bin/bash

sudo systemctl start mysql
sudo systemctl start kafka
sudo systemctl start mongod

gnome-terminal -x sh -c 'cd ./EurekaSchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./ConfigurationSchool && mvn spring-boot:run'
sleep 35
gnome-terminal -x sh -c 'cd ./SecuritySchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./TurbineStreamSchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./HystrixDashboardSchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./ZuulSchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./CustomerSchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./ClassroomSchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./ClassCustomerSchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./ClassCustomerViewSchool && mvn spring-boot:run'

