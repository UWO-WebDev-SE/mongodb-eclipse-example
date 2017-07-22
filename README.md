# mongodb-eclipse-example
An example of how to connect to a MongoDB database using Java.

To run this project, clone it and open it in Eclipse. There should be no need for additional configuration outside the **config.properties** file.

## Mongo-Java Driver Information
Most major releases are available for free download. You can visit the archive [here](http://central.maven.org/maven2/org/mongodb/mongo-java-driver). The source code of the driver is also hosted on GitHub and is found [here](https://github.com/mongodb/mongo-java-driver).

## Configuration Information
The **config.properties** file contains the configuration needed for the application to connect to the MongoDB. Here is the sample used in the repository:
```
# Server configuration
server=127.0.0.1
port=27017
authenticationDatabase=students

# User configuration
username=YourUsername
password=YourPassword
database=YourDatabase
```
Details about each of these properties are provided below.

* **server:** The IP address or domain name of the server you are connecting to. If you are employing a tunnel to connect to your database, this should always be `localhost` or `127.0.0.1`.
* **port:** The port on the given server to connect to. [MongoDB's default port is 27017](https://docs.mongodb.com/manual/reference/default-mongodb-port), so you should not change this property when trying to connect to a UW Oshkosh MongoDB.
* **authenticationDatabase:** The internal database to be used by the MongoDB server to verify the given user's credentials. Students should not change this property when trying to connect to a UW Oshkosh MongoDB.
* **username and password:** The username and password of the user who will be used to interact with the given MongoDB database.
* **database:** The database to be interacted with. The user whose credentials are provided must have access to this database, otherwise you will be unable to connect.
