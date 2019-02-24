# Pull base image
#From maven:3.3-jdk-8

#RUN mkdir /home/new
#WORKDIR /home/new
#ADD target/WebShop.war /home/new

# Pull base image
From tomcat:8-jre8

# Copy to images tomcat path
ADD target/WebShop.war /usr/local/tomcat/webapps/