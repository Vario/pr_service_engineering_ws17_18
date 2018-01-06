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

    public List<Document> findIterableToList(FindIterable<Document> findIterable){
        Iterator iterator = findIterable.iterator();
        List<Document> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add((Document) iterator.next());
        }
        return result;
    }

    public void insert(Document document){
        this.collection.insertOne(document);
    }

    public FindIterable<Document> find(Bson bson) {
        return this.collection.find(bson);
    }

    public List<Document> find(Document document) {
        return this.findIterableToList(
                this.collection.find(document)
        );
    }


    public long update(Bson id, Bson update){
        UpdateResult res = this.collection.updateOne(id, update);
        return res.getModifiedCount();
    }

    public long updateMany(Bson id, Bson update){
        UpdateResult res = this.collection.updateMany(id, update);
        return res.getModifiedCount();
    }
}
