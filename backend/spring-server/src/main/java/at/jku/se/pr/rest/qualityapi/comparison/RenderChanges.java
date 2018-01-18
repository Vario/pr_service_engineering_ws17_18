package at.jku.se.pr.rest.qualityapi.comparison;

import com.deepoove.swagger.diff.SwaggerDiff;
import com.deepoove.swagger.diff.model.ChangedEndpoint;
import com.deepoove.swagger.diff.model.ChangedOperation;
import com.deepoove.swagger.diff.model.ChangedParameter;
import com.deepoove.swagger.diff.model.ElProperty;
import com.deepoove.swagger.diff.model.Endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.models.HttpMethod;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;

public class RenderChanges {
    private List<EndpointChanges> newEndpoints;
    private List<EndpointChanges> missingEndpoints;
    private List<EndpointChanges> changedEndpoints;

    private String swagger1;
    private String swagger2;

    public RenderChanges(String swagger1, String swagger2) {
        this.swagger1 = swagger1;
        this.swagger2 = swagger2;
    }

    public Object render() {
        SwaggerDiff diff = SwaggerDiff.compareV2(this.swagger1, this.swagger2);

        this.newEndpoints = new ArrayList<EndpointChanges>();
        this.getNewEndpoints(diff.getNewEndpoints());

        this.missingEndpoints = new ArrayList<EndpointChanges>();
        this.getMissingEndpoints(diff.getMissingEndpoints());

        this.changedEndpoints = new ArrayList<EndpointChanges>();
        this.getChangedEndpoints(diff.getChangedEndpoints());

        List<List<EndpointChanges>> render = new ArrayList();
        render.add(newEndpoints);
        render.add(missingEndpoints);
        render.add(changedEndpoints);
        Object o = render;
		/*String json1 =  "\"new\":" + new Gson().toJson(newEndpoints);
		String json2 =  "\"removed\":" + new Gson().toJson(missingEndpoints);
		String json3 =  "\"changed\":" + new Gson().toJson(changedEndpoints);
		String json = "{" + json1 + "," + json2 + "," + json3 + "}";*/

        //System.out.println(json);
        return o;
    }

    private void getNewEndpoints(List<Endpoint> endpoints) {
        for (Endpoint endpoint : endpoints) {
            String change = endpoint.getMethod() + " " + endpoint.getPathUrl();
            String description = endpoint.getSummary();
            EndpointChanges e = new EndpointChanges(change, description);
			/*e.addParameter(null);
			e.addReturnType(null);*/
            this.newEndpoints.add(e);
        }
    }

    private void getMissingEndpoints(List<Endpoint> endpoints) {
        for (Endpoint endpoint : endpoints) {
            String change = endpoint.getMethod() + " " + endpoint.getPathUrl();
            String description = endpoint.getSummary();
            EndpointChanges e = new EndpointChanges(change, description);
            this.missingEndpoints.add(e);
        }
    }

    private void getChangedEndpoints(List<ChangedEndpoint> changedEndpoints) {
        for (ChangedEndpoint changedEndpoint : changedEndpoints) {
            String pathUrl = changedEndpoint.getPathUrl();
            Map<HttpMethod, ChangedOperation> changedOperations = changedEndpoint
                    .getChangedOperations();
            for (Map.Entry<HttpMethod, ChangedOperation> entry : changedOperations.entrySet()) {

                ChangedOperation changedOperation = entry.getValue();

                String change = entry.getKey().toString() + " " + changedEndpoint.getPathUrl();
                String description = changedOperation.getSummary();
                EndpointChanges e = new EndpointChanges(change, description);

                if (changedOperation.isDiffParam()) {
                    this.getParameters(changedOperation, e);
                }
                if (changedOperation.isDiffProp()) {
                    this.getReturnTypes(changedOperation, e);
                }
                this.changedEndpoints.add(e);
            }
        }
    }

    private void getParameters(ChangedOperation changedOperation, EndpointChanges e) {
        List<Parameter> addParameters = changedOperation.getAddParameters();
        List<Parameter> delParameters = changedOperation.getMissingParameters();
        List<ChangedParameter> changedParameters = changedOperation.getChangedParameter();;

        for (Parameter param : addParameters) {
            String change = param.getName();
            String description = param.getDescription();
            Change c = new Change("ADDED "+ change, description);
            e.addParameter(c);
        }
        for (ChangedParameter param : changedParameters) {
            List<ElProperty> increased = param.getIncreased();
            for (ElProperty prop : increased) {
                Property property = prop.getProperty();
                String change = prop.getEl();
                String description = property.getDescription();
                Change c = new Change("ADDED " + change, description);
                e.addParameter(c);
            }
        }
        for (ChangedParameter param : changedParameters) {
            boolean changeRequired = param.isChangeRequired();
            boolean changeDescription = param.isChangeDescription();
            if (changeRequired || changeDescription) {
                //sb.append(PRE_LI).append(PRE_CODE).append(li_changedParam(param) + "\n");
                Parameter rightParam = param.getRightParameter();
                Parameter leftParam = param.getLeftParameter();
                String change = "";
                String description = "";
                if (changeRequired) {
                    change = rightParam.getName() + " CHANGED TO " +  (rightParam.getRequired() ? "required" : "not required");
                }
                if (changeDescription) {
                    description = "'" + leftParam.getDescription() + "' CHANGED TO '" + rightParam.getDescription() + "'";
                }
                Change c = new Change(change, description);
                e.addParameter(c);
            }
        }
        for (ChangedParameter param : changedParameters) {
            List<ElProperty> missing = param.getMissing();
            for (ElProperty prop : missing) {
                Property property = prop.getProperty();
                String change = prop.getEl();
                String description = property.getDescription();
                Change c = new Change("REMOVED " + change, description);
                e.addParameter(c);
            }
        }
        for (Parameter param : delParameters) {
            String change = param.getName();
            String description = param.getDescription();
            Change c = new Change("REMOVED " + change, description);
            e.addParameter(c);
        }
    }

    private void getReturnTypes(ChangedOperation changedOperation, EndpointChanges e) {
        List<ElProperty> addProps = changedOperation.getAddProps();
        List<ElProperty> delProps = changedOperation.getMissingProps();
        for (ElProperty prop : addProps) {
            Property property = prop.getProperty();
            String change = prop.getEl();
            String description = property.getDescription();
            Change c = new Change("ADDED " + change, description);
            e.addReturnType(c);
        }
        for (ElProperty prop : delProps) {
            Property property = prop.getProperty();
            String change = prop.getEl();
            String description = property.getDescription();
            Change c = new Change("REMOVED " + change, description);
            e.addReturnType(c);
        }
    }
}
