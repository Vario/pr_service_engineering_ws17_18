package at.jku.se.pr.rest.qualityapi.mongodb;

import com.mongodb.MongoClient;

public class MongoClientFactory {
    private static MongoClient mongoClient = null;

    private MongoClientFactory(){}

    public static MongoClient getMongoClient() {
        if(mongoClient == null){
            System.out.println("NEW MONGOCLIENT INSTANCE");
            String mongoDbHostname = System.getenv("JKU_REST_QUALITY_API_MONGO_DB_HOSTNAME");
            if(mongoDbHostname == null)
                mongoDbHostname = "localhost";
            mongoClient = new MongoClient( mongoDbHostname , 27017 );
        }
        return mongoClient;
    }
}
