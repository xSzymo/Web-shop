# Spring web shop project

If you want to check functionality of the project go to All-proper-functionality branch.
There's everything tested by me.<br>
Master branch have had changes in the production code which wasn't tested on live project.<br>
Currently work at test development and move repository static access to services.
<br>

### How to run
-----------------------------------------------------------------------------------------------------------------------
```
git clone https://github.com/xSzymo/Spring-web-shop-project.git
mvn tomcat7:run
```

Default access on ``` http://localhost:8080/WebShop/ ```<br>
Edit application.properties in resources before you start.

### Frameworks :
- Spring Data
- Spring MVC
- Spring Security
- JSP/JSTL 
  <br><br>

### Feature
-----------------------------------------------------------------------------------------------------------------------
ApplicationConfig - file where you can easy config whole application :
 - url
 - project name (you have to change it here and in pom.xml
 - picture path
 - shop email
 - shop password
 - data base name, user name, user password<br>
 
Security administratorSite & account sites, You have to login, otherwise you'll be redirected to login page. <br><br>

### Modules
-----------------------------------------------------------------------------------------------------------------------
Modules : 
  - Administrator site 
    - Users's CRUD with specific role which define access for website
    - Books's CRUD with device for uploading pictures
    - Orders's CRUD with interesting device :) 
    - Address CRUD
    - Categories CRUD
    - Coupon codes CRUD 
  - Login, user reistration 
    - login to account with spring security
    - user registration - no e-mail confirmation
  - Reset password/remember user login
    - reset password - send code for e-mail 
    - reset login - send login for e-mail
  - Click to remember password 
    - If you click to remember-me check-box spring will generate 2 cookies which will authorize your account every time
    - if you click logout while you are logged spring deleted cookies remember-me
    - If cookies are incorrect they be deleted
  - User account 
    - Possibility to change account data like e-mail/password with e-mail authentication 
  - Shop
    - Categories site where you can add items to basket
    - Basket possible to reduce or deleted items 
    - Accept order with diffrent view for anonymous user and logged user. Order is saved in DB and send to user's email.
    <br><br>
  

