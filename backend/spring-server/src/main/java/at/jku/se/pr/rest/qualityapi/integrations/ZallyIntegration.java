package at.jku.se.pr.rest.qualityapi.integrations;

import de.zalando.zally.api.models.LintingRequest;
import io.swagger.model.LintingResponse;
import de.zalando.zally.api.models.Rule;
import de.zalando.zally.api.models.SupportedRulesResponse;
import io.swagger.model.Setting;
import io.swagger.model.Violation;
import io.swagger.model.ViolationsCount;
import org.bson.Document;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ZallyIntegration {
    private String uri = "http://localhost:8000";
    private List<Rule> enabledRules;

    /*
        Constructors
    */
    public ZallyIntegration(){

    }

    public ZallyIntegration(String uri){
        this.uri = uri;
    }

    /*
        Private Helper Methods
    */
    private void updateEnabledRules(){
        enabledRules = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        List<Rule> supportedZallyRules =
                restTemplate.getForObject(
                        String.format("%s/supported-rules", this.uri),
                        SupportedRulesResponse.class
                ).getSupportedRules();

        for(Rule r : supportedZallyRules){
            if(r.getIsActive()){
                enabledRules.add(r);
            }
        }
    }

    /*
        Public Methods
    */
    /* Produces a list of rules to ignore for the zally api for a given Setting */
    public List<String> getRuleIgnoreList(Setting setting){
        this.updateEnabledRules();
        ArrayList<String> ignoreList = new ArrayList<>();
        List<String> settingRules = setting.getRules();

        for(Rule r : enabledRules){
            if(!settingRules.contains(r.getCode()))
                ignoreList.add(r.getCode());
        }
        return ignoreList;
    }

    /* Requests violations for a given swagger doc on the zally api */
    /*public LintingResponse getViolations(Object swagger, Setting setting){
        LintingRequest request = new LintingRequest();
        request.setApiDefinition(swagger);
        request.setIgnoreRules(getRuleIgnoreList(setting));

        RestTemplate restTemplate = new RestTemplate();
        LintingResponse violation =
                restTemplate.postForObject(
                        String.format("%s/api-violations", this.uri),
                        request,
                        LintingResponse.class
                );
        return violation;
    }*/
    public Object getViolations(Object swagger, Setting setting){
        LintingRequest request = new LintingRequest();
        request.setApiDefinition(swagger);
        request.setIgnoreRules(getRuleIgnoreList(setting));

        RestTemplate restTemplate = new RestTemplate();
        Object violation =
                restTemplate.postForObject(
                        String.format("%s/api-violations", this.uri),
                        request,
                        Object.class
                );
        return violation;
    }

    /*public static Document lintingResponseToDocument(LintingResponse lintingResponse){
        Document doc = new Document();

        /* Message
        doc.append("message", lintingResponse.getMessage());

        /* ViolationsCount Object
        ViolationsCount violationsCount = lintingResponse.getViolationsCount();
        doc.append("violations_count", new Document()
                .append("must", violationsCount.getMust())
                .append("should", violationsCount.getShould())
                .append("may", violationsCount.getMay())
                .append("could", violationsCount.getCould())
                .append("hint", violationsCount.getHint())
        );

        List<Document> violations = new ArrayList<>();
        for(Violation v : lintingResponse.getViolations()){
            violations.add(new Document()
                    .append("title", v.getTitle())
                    .append("description", v.getDescription())
                    .append("violation_type", v.getViolationType())
                    .append("rule_link", v.getRuleLink())
                    .append("paths", v.getPaths())
            );
        }

        return doc;

    }*/
}
