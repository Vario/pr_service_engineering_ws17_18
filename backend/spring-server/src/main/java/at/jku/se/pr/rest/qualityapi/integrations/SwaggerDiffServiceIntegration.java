package at.jku.se.pr.rest.qualityapi.integrations;

import at.jku.se.pr.rest.qualityapi.models.SwaggerDiffRequest;
import io.swagger.model.Change;
import io.swagger.model.ComparisonReportResponseChanges;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SwaggerDiffServiceIntegration {
    private String comparisonServiceEndpoint;

    public SwaggerDiffServiceIntegration(){
        this.comparisonServiceEndpoint = System.getenv("JKU_REST_QUALITY_API_SWAGGERDIFF_CONNECTION_STRING");
        if(this.comparisonServiceEndpoint == null)
            this.comparisonServiceEndpoint = "http://localhost:9000";
    }

    public Object getDiff(String url1, String url2){
        SwaggerDiffRequest request = new SwaggerDiffRequest();
        request.setOldSpec(url1);
        request.setNewSpec(url2);

        RestTemplate restTemplate = new RestTemplate();
        Object swaggerDiffResponse =
                restTemplate.postForObject(
                        String.format("%s/comparison", this.comparisonServiceEndpoint),
                        request,
                        Object.class
                );
        return swaggerDiffResponse;
    }

    private List<Change> getChanges(List<HashMap> array){
        List<Change> changes = new ArrayList<>();
        for (HashMap h : array){
            Change c = new Change();
            c.setMessage((String) h.get("message"));
            c.setMethod((String) h.get("method"));
            c.setPath((String) h.get("path"));
            c.setRuleId((String) h.get("ruleId"));
            changes.add(c);
        }
        return changes;
    }

    public ComparisonReportResponseChanges getChanges(Object diff){
        ComparisonReportResponseChanges changes = new ComparisonReportResponseChanges();

        HashMap map = (LinkedHashMap) diff;
        List<HashMap> errors = (List) map.get("errors");
        List<HashMap> warnings = (List) map.get("warnings");
        List<HashMap> infos = (List) map.get("infos");

        changes.setErrors(getChanges(errors));
        changes.setWarnings(getChanges(warnings));
        changes.setInfos(getChanges(infos));

        return changes;
    }
}
