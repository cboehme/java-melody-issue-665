#!/usr/bin/env bash

cd jdbc-application
mvn org.wildfly.plugins:wildfly-maven-plugin:deploy

cd ../java-melody-application
mvn org.wildfly.plugins:wildfly-maven-plugin:deploy

curl -I http://localhost:8080/jdbc-application-1.0.0-SNAPSHOT
echo "Should return HTTP status code 200"

mvn org.wildfly.plugins:wildfly-maven-plugin:undeploy
mvn org.wildfly.plugins:wildfly-maven-plugin:deploy

curl -I http://localhost:8080/jdbc-application-1.0.0-SNAPSHOT
echo "Should return HTTP status code 500"

mvn org.wildfly.plugins:wildfly-maven-plugin:undeploy

cd ../jdbc-application
mvn org.wildfly.plugins:wildfly-maven-plugin:undeploy
