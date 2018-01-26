package at.jku.se.pr.rest.qualityapi.integrations;

import at.jku.se.pr.rest.qualityapi.models.SwaggerDiffRequest;
import org.springframework.web.client.RestTemplate;

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
}
