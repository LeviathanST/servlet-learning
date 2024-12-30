#!/bin/bash

OS_NAME=$(uname -o)

if [ $OS_NAME != "GNU/Linux " ]; then
	echo Generating WAR file...
	mvn clean package
	clear
	echo Generate WAR successfull!

	echo Move WAR file to Tomcat server
	sudo mv ./target/test-1.war /var/lib/tomcat10/webapps/
	echo Move successfully!
	clear

	echo Restart tomcat service
	systemctl restart tomcat10.service
	echo Restart successfully!

	echo Progress is done!
else
	echo Your OS is not supported
fi
