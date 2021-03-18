#Stocky - Virtual Stock Market#
A real-time stock brokerage and exchange site

WPL Final Project README
CS 6314.502
Professor Balakrishna
Team: Elkmé
Zongzhe Li, Yuncheng Gao, Tianhang Zhu
December 6th, 2019

Our project was developed under MacOS using Eclipse 64 bit for Java EE, which contains Tomcat v9.0.29. We also used MySQL community server as the backend database. To replicate the same environment on other Operating Systems, users might need the corresponding commands for the tools installation. The following instructions were tested only on the MacOS.

To run the project, user needs to install the following:
MySQL DMG Archive on https://dev.mysql.com/downloads/mysql/, 
Eclipse For Java EE on https://www.eclipse.org/downloads/packages/. 
For convenience of viewing database, downloading MySQLWorkbench is recommended, which is on https://dev.mysql.com/downloads/workbench/. For JAR files, we have “java-json.jar”, “spymemcached-2.10.3.jar” and “mysql-connector-java-8.0.18.jar”.

The data dump file, jar files and web application code are all included in our project folder.

In order to view the database in a GUI environment, first start MySQL database server and set up a password, then start MySQLWorkbench and connect to the MySQL server by selecting the local host with the correct port number. Inside workbench, select “Data Import” and choose our data set. Check server status in the “Administration” tab, which should indicate the server status to be “running”.

For setting up Eclipse, first create a new project named “stocky”. Then under that project, create 3 dynamic web projects and give them the same names as ours: “stocky”, “webApplication” and “stockExchange”. Then select their Tomcat servers to all be v9.0. Copy all our files to the projects created. “java-json.jar” is on Java Build Path of all three dynamic web projects. “spymemcached-2.10.3.jar” is only in “stocky”. “java-database.jar” is in “stockExchange” and “webApplication”. If everything is setup correctly, change the three server ports in Eclipse’ “Servers” perspective. Server 1 is for “stocky” on port 8080, 8009, 8005 and 8443. Server 2 is for “webApplication” on port 8180, 8109, 8105 and 8543. Server 3 is for “stockExchange” on port 8280, 8209, 8205 and 8543. Server.xml and Web.xml require some changes for SSL encryption and compression, in which compression can be enabled  by simply replicating our Server.xml and Web.xml for each of the three Tomcat servers. And for SSL we just need to add the pre-generated .keystore file in Eclipse’ Tomcat folder under /conf folder for all three servers (notice that one might need to enable hidden files viewing in the OS settings). The three SSL ports (8443, 8543 and 8643) should be usable after the success of configuring the above settings.

Since the database server is password protected, simply changing password strings from “DBConnection.java” in all three projects to your own password that you set up for MySQL database access. 

For setting up memcached server, download homebrew on Mac Terminal. Use homebrew to install memcached server. Use command “brew install memcached” to install. Copy the file “homebrew.mxcl.memcached.plist” into “~/Library/LaunchAgents/”. Then install Lunchy for convenient launch method. Type “gem install lunchy” to install and type “lunchy start memcached” to launch memcached server. “lunchy stop memcached” to stop memcached server. Reference for installing memcached is here: https://gist.github.com/tomysmile/ba6c0ba4488ea51e6423d492985a7953

Go back to Eclipse, save all files, right click those Tomcat server and click “Start” to start all three Tomcat servers. Now the web application Stocky should be able to run on the local host via port 8080 (our server is configured to automatically redirect any non-SSL request to the secure port 8443 at the front end, thus you will see the port changes to 8443 on the URL bar). For instance, one can enter localhost:8080/stocky and it should be directed to https://localhost:8443/stocky.

It is recommended to use Google Chrome to access the web application, and the corresponding CORS policy needs to be changed either manually, or one can use an extension such as “Moesif CORS” on Chrome to bypass such restriction. Otherwise there might be an error stating “Blocked by CORS policy: The 'Access-Control-Allow-Origin'
” when accessing the dataTable.
