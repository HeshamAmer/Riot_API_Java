package hello;

import org.bson.Document;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class RiotAPIHandler {

	private static String API_Key;
	private static String SummonerName;
	private static String MongoIP;
	private static int MongoPort;
	private static ConnectionHandler conHandler;

	public static String getMongoIP() {
		return MongoIP;
	}

	public static void setMongoIP(String mongoIP) {
		MongoIP = mongoIP;
	}

	public static int getMongoPort() {
		return MongoPort;
	}

	public static void setMongoPort(int mongoPort) {
		MongoPort = mongoPort;
	}

	public static String getAPI_Key() {
		return API_Key;
	}

	public static void setAPI_Key(String aPI_Key) {
		API_Key = aPI_Key;
	}

	public static String getSummonerName() {
		return SummonerName;
	}

	public static void setSummonerName(String summonerName) {
		SummonerName = summonerName;
	}

	void InsertIntoMongoDB() throws Exception {


		String URL = "https://eune.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/" + SummonerName + "?api_key="
				+ API_Key;
		String Record = conHandler.sendGet(URL);
		Document document = Document.parse(Record);
		MongoClient mongoClient = new MongoClient(MongoIP, MongoPort);
		MongoDatabase db = mongoClient.getDatabase("RiotAPI");
		// String name=(String) document.get(0);
		// fillDBTest(db);
		// db.getCollection("SummonerIDs").insertOne(document.append("_id",
		// SummonerName.toLowerCase()));
		FindIterable<Document> queryResult = db.getCollection("SummonerIDs")
				.find(new Document(SummonerName.toLowerCase() + ".name", SummonerName));
		for (Document d : queryResult) {
			Document D = (Document) d.get(SummonerName.toLowerCase());
			System.out.println(D.get("name"));
		}

		mongoClient.close();
	}

	public static ConnectionHandler getConHandler() {
		return conHandler;
	}

	public static void setConHandler(ConnectionHandler conHandler) {
		RiotAPIHandler.conHandler = conHandler;
	}

}
