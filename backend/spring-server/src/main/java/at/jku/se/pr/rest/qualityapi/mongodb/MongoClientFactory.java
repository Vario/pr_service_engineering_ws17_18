package at.jku.se.pr.rest.qualityapi.mongodb;

import com.mongodb.MongoClient;

public class MongoClientFactory {
    private static MongoClient mongoClient = null;

    private MongoClientFactory(){}

    public static MongoClient getMongoClient() {
        if(mongoClient == null){
            mongoClient = new MongoClient( "localhost" , 27017 );
        }
        return mongoClient;
    }
}
