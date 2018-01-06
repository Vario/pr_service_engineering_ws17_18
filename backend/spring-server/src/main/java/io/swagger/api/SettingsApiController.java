package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import io.swagger.model.ApplicationError;
import io.swagger.model.Setting;
import io.swagger.model.SettingsCreationRequest;
import io.swagger.model.Setting;
import io.swagger.model.SettingsListItem;

import io.swagger.annotations.*;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.*;
import javax.validation.Valid;

import static com.mongodb.client.model.Filters.eq;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-05T17:09:31.244Z")

@Controller
public class SettingsApiController implements SettingsApi {
    MongoDBRequest collection = new MongoDBRequest("settings");

    public ResponseEntity<Setting> settingsPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody SettingsCreationRequest settings) {
        // TODO: Input Validation (name (does already exist?), rules: does rule exist?)
        Setting response = new Setting();
        response.setId(UUID.randomUUID());
        response.setName(settings.getName());
        response.setRules(settings.getRules());

        MongoDBRequest request = new MongoDBRequest("settings");
        request.insert(new Document()
                .append("id", response.getId())
                .append("name", settings.getName())
                .append("rules", settings.getRules())
                .append("used-in", new ArrayList<>())
        );
        return new ResponseEntity<Setting>(response, HttpStatus.OK);
    }


    public ResponseEntity<List<SettingsListItem>> settingsGet() {
        ArrayList<SettingsListItem> response = new ArrayList<>();

        List<Document> results = collection.find(new Document());
        for (Document r : results) {
            SettingsListItem item = new SettingsListItem();
            item.setId((UUID) r.get("id"));
            item.setName((String) r.get("name"));
            response.add(item);
        }

        return new ResponseEntity<List<SettingsListItem>>(response, HttpStatus.OK);
    }

    public ResponseEntity<Setting> settingsIdGet(@ApiParam(value = "Settings ID",required=true ) @PathVariable("id") UUID id) {
        List<Document> results = collection.find(new Document().append("id", id));
        switch (results.size()){
            case 0:
                return new ResponseEntity<Setting>(HttpStatus.NOT_FOUND);
            case 1:
                Setting response = new Setting();
                Document d = results.get(0);
                response.setId((UUID) d.get("id"));
                response.setName((String) d.get("name"));
                response.setRules((List<String>) d.get("rules"));
                return new ResponseEntity<Setting>(response, HttpStatus.OK);
            default:
                System.out.println("Too many results (>1) for a unique id: {}".format(String.valueOf(results.size())));
                return new ResponseEntity<Setting>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Setting> settingsIdPut(@ApiParam(value = "Settings ID",required=true ) @PathVariable("id") UUID id,
        @ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody SettingsCreationRequest settings) {

        List<Document> results = collection.find(new Document().append("id", id));
        if (results.size() == 0)
            return new ResponseEntity<Setting>(HttpStatus.NOT_FOUND);

        long updateCount = collection.update(eq("id", id), new Document("$set", new Document()
                .append("id", id)
                .append("name", settings.getName())
                .append("rules", settings.getRules())
        ));

        Setting response = new Setting();
        response.setId(id);
        response.setName(settings.getName());
        response.setRules(settings.getRules());
        if(updateCount == 0)
                return new ResponseEntity<Setting>(response, HttpStatus.OK);
        else
                return new ResponseEntity<Setting>(response, HttpStatus.CREATED);
    }
}
