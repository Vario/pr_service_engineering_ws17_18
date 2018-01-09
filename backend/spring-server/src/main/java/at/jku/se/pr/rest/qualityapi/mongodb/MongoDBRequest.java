package at.jku.se.pr.rest.qualityapi.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.and;

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

    /* Adds a given property to a field and creates the field if it does not exists
    * Properties:
    * String idName: The name of the id of the record, where the array is a part of
    * Object idValue: The id of the record
    * String fieldName: The name of the array there a value should be added
    * Document fieldValue: The value that should be added to the array
    * */
    public void createAndAddToSet(String idName, Object idValue, String fieldName, Document fieldValue){
        FindIterable<Document> results = this.find(
                and(
                        Filters.eq(idName, idValue),
                        Filters.exists(fieldName)
                )
        );

        Iterator iterator = results.iterator();
        if(iterator.hasNext() == false){
            ArrayList<Document> docs = new ArrayList<>();
            docs.add(fieldValue);
            this.update(
                    Filters.eq(idName, idValue),
                    Updates.set(fieldName, docs)
            );
        } else {
            this.update(
                    Filters.eq(idName, idValue),
                    Updates.addToSet(fieldName, fieldValue)
            );
        }
    }
}
