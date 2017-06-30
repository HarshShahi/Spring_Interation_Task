# Spring_Interation_Task

**Task:**

There will be a series of files placed into the directory (C:\SITA_TEST_TASK\IN) with a number on each line.  Attached are some example files (invalid and valid).  

We would like the application to automatically process these files by polling that folder for new files every 5 seconds. If a new file is found, then the application should sum all the numbers in the file and create a new file containing the resulting value in the directory (C:\SITA_TEST_TASK\OUT). 

The output file should have the same name as the input file with .OUTPUT appended to the end of the filename. 

When the input file is successfully processed it should be moved to the following directory (C:\SITA_TEST_TASK\PROCESSED) with .PROCESSED appended to the end of the file name. 

If an error occurs while processing the input file then the input file should be moved into the following directory (C:\SITA_TEST_TASK\ERROR) with .ERROR appended to the end of the filename.


**Dependencies:**

spring 4.3.7.RELEASE
spring-integration 4.3.8.RELEASE
junit 4.12
log4j 1.2.17
JDK 1.6 and above
Tomcat 1.7
Maven repository to download dependencies:


**Build, Deployment & Testing:**

- Run 'mvn clean install' from command propmpt.

- Create below directory structure in C:\ drive,

    'C:\SITA_TEST_TASK\IN'
  
- Deploy the war file in Tomcat,

     Place the war (SitaTestTask.war) file in webapps folder of Tomcat and run startup.bat.
  
- You can provide input file (only '.txt') in 'C:\SITA_TEST_TASK\IN' to process the file.

- Log file will be available on <Tomcat_Home>\logs\SitaTestTaskLogs\sitaTestTask.log


**Process Flow:**

On server startup the application get deployed and inbound-channel starts polling for message in from 'C:\SITA_TEST_TASK\IN' directory in interval of 5 secconds then sends to routerChannel.

One Handler is configured at routerChannel which validates and further sends the valid message to processingChannel and invalid message to errorChannel. outbound-channel-adapter at errorChannel is responsible to generate the output in C:\SITA_TEST_TASK\ERROR directory with .ERROR extension. processingChannel has configured recipient-list-router which sends same message to two channels,

1.	outputProcessChannel
2.	processedChannel

Service-activator has been configured at outputProcessChannel channel which performs the sum task and sends newly prepared message to outputChannel. outbound-channel-adapter on outputChannel generates with desired extension (.OUTPUT) also result, which is copied at C:\SITA_TEST_TASK\OUT directory. On processedChannel we have outbound-channel-adapter which is responsible to generate the output in C:\SITA_TEST_TASK\PROCESSED directory with desired extension (.PROCESSED).
