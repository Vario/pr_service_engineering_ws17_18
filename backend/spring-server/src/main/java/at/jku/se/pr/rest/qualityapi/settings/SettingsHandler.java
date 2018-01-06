package at.jku.se.pr.rest.qualityapi.settings;

import de.zalando.zally.api.models.Rule;
import de.zalando.zally.api.models.SupportedRulesResponse;
import io.swagger.model.Setting;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SettingsHandler {
    private String zallyUri = "http://localhost:8000";
    private List<Rule> enabledZallyRules;

    /*
        Constructors
    */
    public SettingsHandler(String zallyUri){
        this.zallyUri = zallyUri;
    }

    /*
        Private Helper Methods
    */
    private void updateEnabledZallyRules(){
        enabledZallyRules = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        List<Rule> supportedZallyRules =
                restTemplate.getForObject(
                        String.format("{}/supported-rules", this.zallyUri),
                        SupportedRulesResponse.class
                ).getSupportedRules();

        for(Rule r : supportedZallyRules){
            if(r.getIsActive()){
                enabledZallyRules.add(r);
            }
        }
    }

    /*
        Public Methods
    */
    /* Produces a list of rules to ignore for the zally api for a given Setting */
    public List<String> getRuleIgnoreList(Setting setting){
        updateEnabledZallyRules();
        ArrayList<String> ignoreList = new ArrayList<>();

        List<String> enabledRules = setting.getRules();


        for(Rule r : enabledZallyRules){
            if(!enabledRules.contains(r.getCode()))
                ignoreList.add(r.getCode());
        }
        return ignoreList;
    }
}
