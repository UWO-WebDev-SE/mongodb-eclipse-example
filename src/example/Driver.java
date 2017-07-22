package example;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Driver {
	private static Properties config;
		
	private static void loadProperties() {
		try {
			FileInputStream file = new FileInputStream("config.properties");
			config = new Properties();
			config.load(file);
			file.close();
		} catch (Exception e) {
			System.out.println("An error occurred while trying to load config.properties.");
		}
	}
	
	public static void main(String[] args) {
		silenceMongoLog();
		loadProperties();
		
		MongoClient mongoClient = null;
		try {
			mongoClient = getMongoClient();
			
			// Now that we have the connection established, it's time to retrieve our database.			
			MongoDatabase mongoDB = mongoClient.getDatabase(config.getProperty("database"));
			
			// At this point, we can begin interacting with the database. The code below
			// illustrates an example of iterating through a collection.
			MongoCollection<Document> collection = mongoDB.getCollection("YourCollection");
			for (Document document : collection.find()) {
				System.out.println("Found a document: " + document.toString());
			}
		} catch (Exception e) {
			// Handle errors here
		} finally {
			// We must assure the connection gets closed, even in the event of an exception.
			if (mongoClient != null) {
				mongoClient.close();
			}
		}
	}
	
	private static void silenceMongoLog() {
		/* 
		 * This method mutes the MongoDB Driver's log output. If you want to view this output,
		 * simply comment out the call to this method in the main() method.
		 * 
		 * Functionality note: setting the Logger's level to SEVERE tells it to only display
		 * log messages of that level. You can lower the level to loosen the filter.
		*/
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);
	}
	
	private static MongoClient getMongoClient() throws Exception {
		MongoClient client = null;
		try {
			// Set up server connection info.			
			ServerAddress address = new ServerAddress(config.getProperty("server"), Integer.parseInt(config.getProperty("port")));
			
			// Set up connection credentials. Without this step, we cannot make changes to
			// the database.
			MongoCredential credential = MongoCredential.createCredential(
					config.getProperty("username"),
					config.getProperty("authenticationDatabase"),
					config.getProperty("password").toCharArray()
					);
			client = new MongoClient(address, Arrays.asList(credential));
		} catch (Exception e) {
			System.out.println("An error occurred while creating the MongoClient.");
			throw e;
		}
		return client;
	}
}
