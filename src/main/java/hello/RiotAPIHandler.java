package hello;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import org.bson.BsonDocument;
import org.bson.Document;

public class RiotAPIHandler {
	
private final static String API_Key="";
private final static String SummonerName="GedyHD";

public static void main(String[] args) throws Exception {
	
	ConnectionHandler conHandler= new ConnectionHandler();
	String URL="https://eune.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/" + SummonerName+"?api_key="+API_Key;
	String Record=conHandler.sendGet(URL);
	Document document=Document.parse(Record);
	MongoClient mongoClient = new MongoClient("192.168.30.129",27017);
	MongoDatabase db = mongoClient.getDatabase("RiotAPI");
	//String name=(String) document.get(0);
	//fillDBTest(db);
	db.getCollection("SummonerIDs").insertOne(document.append("_id", SummonerName.toLowerCase()));
	FindIterable<Document> queryResult=db.getCollection("SummonerIDs").find(new Document(SummonerName.toLowerCase()+".name",SummonerName));
	for (Document d:queryResult) {
		Document D =(Document)d.get(SummonerName.toLowerCase());
		System.out.println(D.get("name"));
	}
	
	
	mongoClient.close();
	}
	
}
