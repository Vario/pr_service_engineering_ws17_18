package at.jku.se.pr.rest.qualityapi.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MongoDBRequest {
    private MongoClient mongo;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBRequest(String collectionName){
        this.mongo = new MongoClient( "localhost" , 27017 );
        this.database = mongo.getDatabase("restquality");
        this.collection = database.getCollection(collectionName);
    }

    public void insert(Document document){
        this.collection.insertOne(document);
    }

    public FindIterable<Document> find(Bson bson) {
        return this.collection.find(bson);
    }

    public List<Document> find(Document document) {
        FindIterable<Document> findIterable = this.collection.find(document);
        Iterator iterator = findIterable.iterator();
        List<Document> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add((Document) iterator.next());
        }
        return result;
    }


    public void update(Bson id, Bson update){
        UpdateResult res = this.collection.updateOne(id, update);
        System.out.println("matched: " + res.getMatchedCount());
        System.out.println("modified: " + res.getModifiedCount());
    }
}
