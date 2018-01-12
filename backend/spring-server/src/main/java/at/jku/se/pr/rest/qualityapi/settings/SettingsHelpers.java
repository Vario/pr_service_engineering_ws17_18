package at.jku.se.pr.rest.qualityapi.settings;

import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Updates;
import io.swagger.model.Setting;
import io.swagger.model.SettingsId;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.elemMatch;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.not;

public class SettingsHelpers {
    public static UUID getSettingsForApi(UUID apiId){
        MongoDBRequest collection = new MongoDBRequest("settings");
        List<Document> results = collection.find(
                new Document().append("used-in", apiId)
        );

        if(results.size() == 1)
            return (UUID) results.get(0).get("id");
        else
            return null;
    }

    public static Setting getSettingForSettingsId(UUID settingsId){
        MongoDBRequest collection = new MongoDBRequest("settings");
        List<Document> results = collection.find(
                new Document().append("id", settingsId)
        );

        Document d;
        if(results.size() == 1) {
            d =  results.get(0);
        }
        else {
            Setting defaultSetting = new Setting();
            defaultSetting.setName("default");
            defaultSetting.setRules(new ArrayList<String>());
            return defaultSetting;
        }

        Setting setting = new Setting();
        setting.setId((UUID) d.get("id"));
        setting.setName((String) d.get("name"));
        setting.setRules((List<String>) d.get("rules"));
        return setting;
    }
}
