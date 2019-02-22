# Pull base image
From tomcat:8-jre8

# Copy to images tomcat path
ADD target/WebShop.war /usr/local/tomcat/webapps/