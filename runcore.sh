#!/bin/bash

sudo systemctl start mysql
sudo systemctl start kafka
sudo systemctl start mongod

gnome-terminal -x sh -c 'cd ./EurekaSchool && mvn spring-boot:run'
sleep 10
gnome-terminal -x sh -c 'cd ./ConfigurationSchool && mvn spring-boot:run'
sleep 15
gnome-terminal -x sh -c 'cd ./SecuritySchool && mvn spring-boot:run'
gnome-terminal -x sh -c 'cd ./ZuulSchool && mvn spring-boot:run'
