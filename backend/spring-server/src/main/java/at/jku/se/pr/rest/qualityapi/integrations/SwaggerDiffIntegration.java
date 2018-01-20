package at.jku.se.pr.rest.qualityapi.integrations;

import com.deepoove.swagger.diff.SwaggerDiff;
import com.deepoove.swagger.diff.model.ChangedEndpoint;
import com.deepoove.swagger.diff.model.ChangedOperation;
import com.deepoove.swagger.diff.model.ChangedParameter;
import com.deepoove.swagger.diff.model.ElProperty;
import com.deepoove.swagger.diff.model.Endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.model.Change;
import io.swagger.model.ChangeItem;
import io.swagger.models.HttpMethod;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;

public class SwaggerDiffIntegration {
    private SwaggerDiff diff;

    private String swagger1;
    private String swagger2;

    public SwaggerDiffIntegration(String swagger1, String swagger2) {
        this.swagger1 = swagger1;
        this.swagger2 = swagger2;
    }

    public HashMap<String,List<Change>> render() {
        this.diff = SwaggerDiff.compareV2(this.swagger1, this.swagger2);

        HashMap<String,List<Change>> changes = new HashMap<>();

        changes.put("new", renderNewRemovedEndpoints(diff.getNewEndpoints()));
        changes.put("removed", renderNewRemovedEndpoints(diff.getMissingEndpoints()));
        changes.put("changed", getChangedEndpoints(diff.getChangedEndpoints()));

        return changes;
    }

    private List<Change> renderNewRemovedEndpoints(List<Endpoint> endpoints) {
        List<Change> ret = new ArrayList<>();

        for (Endpoint endpoint : endpoints) {
            Change change = new Change();
            change.setDescription(endpoint.getSummary());
            change.setEndpoint(endpoint.getMethod() + " " + endpoint.getPathUrl());
            ret.add(change);
        }

        return ret;
    }

    private List<Change> getChangedEndpoints(List<ChangedEndpoint> endpoints) {
        List<Change> changedEndpoints = new ArrayList<>();

        for (ChangedEndpoint changedEndpoint : endpoints) {
            String pathUrl = changedEndpoint.getPathUrl();
            Map<HttpMethod, ChangedOperation> changedOperations = changedEndpoint
                    .getChangedOperations();
            for (Map.Entry<HttpMethod, ChangedOperation> entry : changedOperations.entrySet()) {

                ChangedOperation changedOperation = entry.getValue();

                Change e = new Change();
                e.setEndpoint(entry.getKey().toString() + " " + changedEndpoint.getPathUrl());
                e.setDescription(changedOperation.getSummary());

                if (changedOperation.isDiffParam()) {
                    e = getParameters(changedOperation, e);
                }
                if (changedOperation.isDiffProp()) {
                    e = getReturnTypes(changedOperation, e);
                }
                changedEndpoints.add(e);
            }
        }

        return changedEndpoints;
    }

    private Change getParameters(ChangedOperation changedOperation, Change e) {
        List<Parameter> addParameters = changedOperation.getAddParameters();
        List<Parameter> delParameters = changedOperation.getMissingParameters();
        List<ChangedParameter> changedParameters = changedOperation.getChangedParameter();

        for (Parameter param : addParameters) {
            String change = param.getName();
            String description = param.getDescription();

            ChangeItem c = new ChangeItem();
            c.setChange("ADDED "+ change);
            c.setDescription(description);
            e.addParameterItem(c);
        }
        for (ChangedParameter param : changedParameters) {
            List<ElProperty> increased = param.getIncreased();
            for (ElProperty prop : increased) {
                Property property = prop.getProperty();

                String change = prop.getEl();
                String description = property.getDescription();

                ChangeItem c = new ChangeItem();
                c.setChange("ADDED " + change);
                c.setDescription(description);
                e.addParameterItem(c);
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
                ChangeItem c = new ChangeItem();
                c.setChange(change);
                c.setDescription(description);
                e.addParameterItem(c);
            }
        }
        for (ChangedParameter param : changedParameters) {
            List<ElProperty> missing = param.getMissing();
            for (ElProperty prop : missing) {
                Property property = prop.getProperty();
                String change = prop.getEl();
                String description = property.getDescription();

                ChangeItem c = new ChangeItem();
                c.setChange("REMOVED " + change);
                c.setDescription(description);
                e.addParameterItem(c);
            }
        }
        for (Parameter param : delParameters) {
            String change = param.getName();
            String description = param.getDescription();

            ChangeItem c = new ChangeItem();
            c.setChange("REMOVED " + change);
            c.setDescription(description);
            e.addParameterItem(c);
        }

        return e;
    }

    private Change getReturnTypes(ChangedOperation changedOperation, Change e) {
        List<ElProperty> addProps = changedOperation.getAddProps();
        List<ElProperty> delProps = changedOperation.getMissingProps();
        for (ElProperty prop : addProps) {
            Property property = prop.getProperty();
            String change = prop.getEl();
            String description = property.getDescription();

            ChangeItem c = new ChangeItem();
            c.setChange("ADDED " + change);
            c.setDescription(description);
            e.addReturntypeItem(c);
        }
        for (ElProperty prop : delProps) {
            Property property = prop.getProperty();
            String change = prop.getEl();
            String description = property.getDescription();

            ChangeItem c = new ChangeItem();
            c.setChange("REMOVED " + change);
            c.setDescription(description);
            e.addReturntypeItem(c);
        }

        return e;
    }
}
