# AMLKYC

### Prerequisite

  - Java 8
  - Apache Maven – 3.6.x
  - Mysql Community Server
  - Ganache-cli (npm install -g ganache-cli)


### MySQL Script
  >  Script location 	 - AMLKYC\Java\amlkyc\src\main\resources\amlkyc.sql
  >  Importing SQL from unix cmd line 
    ```sh
    $ mysql -u username -p amlkyc <  amlkyc.sql
    ```
### Building jar file
After cloning the git repository, navigate to the directory amlkyc 
    ```
	$ mvn clean install 
	 ```
This will build the jar file required for deployment.
### Spring Boot Configuration
Spring boot application properties are saved in **application.properties**
**Location** -– Create a config folder in the location where the .jar is present. Inside the config folder place the application.properties file with the fields appropriately populated.
- MYSQL Database Credentials
     Provide the username and password of your MYSQL 
    - **spring.datasource.username=userName**
    - **spring.datasource.password=Password**
    
- Blockchain 
    - Run ganache-cli\testrpc locally
    - Provide the coinbase Private Key like below in application.properties eg:coinbasePrivateKey=0xc16aa7e3d318aa8edb5af2838e7a8a4dd5f8e8430c3931aa812d3f1c4426da67

