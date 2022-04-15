package com.kaiburr.api.api;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Random;



public class MongoConnection {
	
	String uri = "mongodb://root:welcome@localhost:27017/";
    
    public long insertNewServer(String name, String language, String framework ) {
    	
    	Random rand = new Random();
    	long rand_id = rand.nextInt(999999999);
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("servers_list");
            MongoCollection<Document> collection = database.getCollection("servers");
	    	try {
	            InsertOneResult result = collection.insertOne(new Document()
	                    .append("id", rand_id)
	                    .append("name", name)
	                    .append("language", language)
	                    .append("framework", framework));
	
	            System.out.println("Success! Inserted document id: " + result.getInsertedId());
	            return rand_id;
	        } catch (MongoException me) {
	            System.err.println("Unable to insert due to an error: " + me);
	        	return 0;
	        }
    	}
    }
    
    public ArrayList<String> getAllServers() {
    	
    	try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("servers_list");
            MongoCollection<Document> collection = database.getCollection("servers");
            
	    	Bson projectionFields = Projections.fields(
	    			Projections.include("id", "name", "language", "framework"),
	    			Projections.excludeId());
	        
	    	MongoCursor<Document> cursor = collection.find()
	                .projection(projectionFields)
	                .sort(Sorts.ascending("id")).iterator();
	        
	    	ArrayList<String> result = new ArrayList<String>();
	    	try {
	            while(cursor.hasNext()) {
	                result.add(cursor.next().toJson());
	            }
	        } finally {
	            cursor.close();
	        }
	    	return result;
    	}
    }
    
    public String getServer(long id) {
    	try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("servers_list");
            MongoCollection<Document> collection = database.getCollection("servers");

            Bson projectionFields = Projections.fields(
	    			Projections.include("id", "name", "language", "framework"),
	    			Projections.excludeId());
            
            Document doc = collection.find(eq("id", id))
                    .projection(projectionFields)
                    .sort(Sorts.descending("name"))
                    .first();
            
            if (doc == null) {
                System.out.println("No results found.");
                return null;
            } else {
                System.out.println(doc.toJson());
                return doc.toJson();
            }
        }
    }
    
    public boolean updateServer(long id, String name, String language, String framework) {
    	try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("servers_list");
            MongoCollection<Document> collection = database.getCollection("servers");
            
            Document query = new Document().append("id",  id);
            
            Bson updates = Updates.combine(
                    Updates.set("name", name),
                    Updates.addToSet("language", language),
                    Updates.addToSet("framework", framework));
            
            UpdateOptions options = new UpdateOptions().upsert(true);
            
            try {
                UpdateResult result = collection.updateOne(query, updates, options);
                System.out.println("Modified document count: " + result.getModifiedCount());
                System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
                return true;
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
                return false;
            }
        }
    }
    
    public boolean deleteOneServer(long id) {
    	try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Bson query = eq("id", id);
            try {
                DeleteResult result = collection.deleteOne(query);
                System.out.println("Deleted document count: " + result.getDeletedCount());
                return true;
            } catch (MongoException me) {
                System.err.println("Unable to delete due to an error: " + me);
                return false;
            }
        }
    }
    
    @SuppressWarnings("unlikely-arg-type")
	public boolean deleteAllServer() {
    	try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("servers_list");
            MongoCollection<Document> collection = database.getCollection("servers");
            
            BasicDBObject document = new BasicDBObject();

            // Delete All documents from collection Using blank BasicDBObject
            collection.deleteMany(document);
            
            MongoCursor<Document> cursor = collection.find().cursor();
	        
	    	while(cursor.hasNext()) {
	            ((Document) collection).remove(cursor.next());
	        }
	    	return true;
    	}
    }
}
