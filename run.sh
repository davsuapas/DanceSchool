#!/bin/bash
# Shell script to open terminals

sudo systemctl start mysql
sudo systemctl start kafka
sudo systemctl start mongod

gnome-terminal -x sh -c 'mvn -f ./EurekaSchool/pom.xml spring-boot:run'
gnome-terminal -x sh -c 'mvn -f ./ZuulSchool/pom.xml spring-boot:run'
gnome-terminal -x sh -c 'mvn -f ./CustomerSchool/pom.xml spring-boot:run'
gnome-terminal -x sh -c 'mvn -f ./ClassroomSchool/pom.xml spring-boot:run'
gnome-terminal -x sh -c 'mvn -f ./ClassCustomerSchool/pom.xml spring-boot:run'
gnome-terminal -x sh -c 'mvn -f ./ClassCustomerViewSchool/pom.xml spring-boot:run'

