package primeiraAplicacao;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class BDMongo {
		
		Mongo mongo = null;
		DB dbMongo = null;
		DBCollection collection = null;
		
		public void connectMongo(){
			try{
				mongo = new Mongo("localhost", 27017);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public boolean isMongoConnected(){
			if(mongo != null){
				dbMongo = mongo.getDB("listaDeCompras");
				collection = dbMongo.getCollection("item");
				return true;
			}else{
				return false;
			}
		}
		
		//checking for database
		public void testingDatabase(){
			System.out.println("Databases:");
			List<String> dbs = mongo.getDatabaseNames();
			for (String dbset : dbs){
				System.out.println(dbset);
			}
		}
		
		//checking for collections in the database
		public void testingCollections(){
			System.out.println("Collections:");
			Set<String> collections = dbMongo.getCollectionNames();
			for(String coll: collections){
				System.out.println(coll);
			}
		}
	
		//create and insert data
		public void insterItemMongo(String nome, int quantidade){
			BasicDBObject document = new BasicDBObject();
			document.put("nome",nome);
			document.put("quantidade", quantidade);
			
			collection.insert(document);
		}
		public void insertItemSimultMongo(String nome, int quantidade, String nome2, int quantidade2){
			this.insterItemMongo(nome, quantidade);
			this.insterItemMongo(nome2, quantidade2);
		}
		
		
		public void removeItemMongo(String nome){
			BasicDBObject document = new BasicDBObject();
			document.put("nome", nome);
			collection.remove(document);
			
		}
		public void removeItemSimultMongo(String nome, String nome2){
			this.removeItemMongo(nome);
			this.removeItemMongo(nome2);
		}
		
		public void listCollectionItems(){
			System.out.println("\nData of collection");
			BasicDBObject searchQuery = new BasicDBObject();
			
			DBCursor cursor = collection.find(searchQuery);
			
			while (cursor.hasNext()){
				System.out.println(cursor.next());
			}
		}
		/*
		 * //data of collection are output
		System.out.println("\nData of collection");
		BasicDBObject searchQuery = new BasicDBObject();
		
		DBCursor cursor = collection.find(searchQuery);
		
		while (cursor.hasNext()){
			System.out.println(cursor.next());
		}
		
		
		
		//update
		BasicDBObject query = new BasicDBObject();
		query.put("nome", "Feijão");
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("nome", "Feijão-Updated");
		
		BasicDBObject updatedObj = new BasicDBObject();
		updatedObj.put("$set", newDocument);
		
		collection.update(query, updatedObj);
		
		
		
		// data of collection are output
		System.out.println("\nData of collection:");
		searchQuery = new BasicDBObject();
		
		cursor = collection.find(searchQuery);
		
		
		while (cursor.hasNext()){
			System.out.println(cursor.next());
		}
		 */
		
		
}