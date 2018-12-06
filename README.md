
# Prerequisites

•	Java 8
•	Apache Maven – 3.6.x
•	MySQL Community Server
•	ganache-cli (npm install -g ganache-cli)

**Blockchain** 
Run ganache-cli\testrpc locally.

**MySQL Script**
Script location 	 - AMLKYC\Java\amlkyc\src\main\resources\amlkyc.sql
Importing SQL from Unix command line 

    mysql -u username -p amlkyc <  amlkyc.sql
**Spring Boot Configuration**

**MYSQL Database Credentials**
Provide the username and password of your MYSQL 
**spring.datasource.url**=*jdbc:mysql://localhost:3306/amlkyc?useSSL=false*
**spring.datasource.username**=*userName*
**spring.datasource.password**=*Password*
where **amlkyc** refers to the name of the schema.

**Blockchain Configuration**
Provide the ganache-cli  coinbase private key like below in **application.properties**
***coinbasePrivateKey**=0xc16aa7e3d318aa8edb5af2838e7a8a4dd5f8e8430c3931aa812d3f1c4426da67*

**Building Jar File**
After cloning the git repository, navigate to the directory amlkyc 

    mvn clean install 
This will build the jar file required for deployment.

Note- Spring boot application properties are saved in **application.properties** file.
Create a **config** folder in the same location, **.jar** file built from previous step is present. Inside the config folder place the **application.properties** file with the fields appropriately populated.

Run `java -jar  amlkyc-0.0.1-SNAPSHOT.jar` to start the application.

