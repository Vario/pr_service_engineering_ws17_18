package at.jku.se.pr.rest.qualityapi.files;

import at.jku.se.pr.rest.qualityapi.exceptions.MultipleResultsException;
import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import org.bson.Document;

import java.util.List;
import java.util.UUID;

public class FileHelpers {
    public static UUID getApiIdForApiTitle(String title){
        MongoDBRequest collection = new MongoDBRequest("files");
        List<Document> results = collection.find(
                new Document().append("title", title)
        );

        if(results.size() == 0){
            return null;
        } else {
            return (UUID) results.get(0).get("api-id");
        }
    }

    public static UUID getApiIdForFileId(UUID fileId){
        MongoDBRequest collection = new MongoDBRequest("files");
        List<Document> results = collection.find(
                new Document().append("file-id", fileId)
        );

        if(results.size() == 0){
            return null;
        } else {
            return (UUID) results.get(0).get("api-id");
        }
    }

    public static UUID getApiIdForFileIds(List<UUID> fileIds) throws MultipleResultsException {
        UUID ret = null;
        for (UUID fileId : fileIds){
            if(ret == null){
                ret = getApiIdForFileId(fileId);
            } else if(!ret.equals(getApiIdForFileId(fileId))){
                throw new MultipleResultsException();
            }
        }
        return ret;
    }

    public static Object getSwaggerDocForFileId(UUID fileId){
        MongoDBRequest collection = new MongoDBRequest("files");
        List<Document> results = collection.find(
                new Document().append("file-id", fileId)
        );

        if(results.size() == 0){
            return null;
        } else {
            return results.get(0).get("swagger");
        }
    }
}
