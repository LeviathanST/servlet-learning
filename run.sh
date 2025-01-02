#!/bin/bash

OS_NAME=$(uname -o)

if [ $OS_NAME != "GNU/Linux " ]; then
	if [ ! -d ./webapp/WEB-INF ]; then
		echo Make webapp/WEB-INF dir
		mkdir -p webapp/WEB-INF
	fi
	if [ ! -e ./webapp/WEB-INF/schema.db ]; then
		echo Generate db
		sqlite3 schema.db < sql/schema.sql
		sqlite3 schema.db < sql/seed.sql

		echo Move schema.db to WEB-INF
		mv schema.db webapp/WEB-INF
	fi

		echo Generating WAR file...
		mvn clean package
		clear

		echo Move WAR file to Tomcat server
		sudo mv ./target/test-1.war /var/lib/tomcat10/webapps/
		clear

		echo Restart tomcat service
		systemctl restart tomcat10.service
		clear

	echo Progress is done!
else
	echo Your OS is not supported
fi
