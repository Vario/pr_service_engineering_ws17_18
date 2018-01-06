package at.jku.se.pr.rest.qualityapi.settings;

import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Updates;
import io.swagger.model.SettingsId;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.elemMatch;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.not;

public class SettingsApiMapper {
    public UUID getSettingsForApi(String apiId){
        MongoDBRequest collection = new MongoDBRequest("settings");
        List<Document> results = collection.find(
                new Document().append("used-in", apiId)
        );

        if(results.size() == 1)
            return (UUID) results.get(0).get("id");
        else
            return null;
    }
}
