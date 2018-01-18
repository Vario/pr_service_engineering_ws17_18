package at.jku.se.pr.rest.qualityapi.comparison;

import java.util.ArrayList;
import java.util.List;

public class EndpointChanges {
    String endpoint = "";
    String description = "";
    List<Change> parameter;
    List<Change> returntype;

    public EndpointChanges(String endpoint, String description) {
        this.parameter = new ArrayList<Change>();
        this.returntype = new ArrayList<Change>();
        this.endpoint = endpoint;
        this.description = description;
    }

    public void print() {
        System.out.println(this.endpoint);
        System.out.println(this.description);

        System.out.println("Parameter:");
        for(Change e : this.parameter) {
            System.out.println(e.change);
            System.out.println(e.description);
        }

        System.out.println("Returntype:");
        for(Change e : this.returntype) {
            System.out.println(e.change);
            System.out.println(e.description);
        }
    }

    public void addParameter(Change p) {
        this.parameter.add(p);
    }

    public void addReturnType(Change p) {
        this.returntype.add(p);
    }
}
