# This is a comment
FROM tomcat:8.0-jre8


LABEL maintainer="Caleb Kirschbaum"

#Adds a war file if it already exists
ADD target/FrontController.war /usr/local/tomcat/webapps

#What port to listen on
EXPOSE 8080

CMD ["catalina.sh", "run"]
