package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.integrations.ZallyIntegration;
import io.swagger.model.ApplicationError;
import io.swagger.model.Rule;

import io.swagger.annotations.*;

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

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-11T16:25:58.568Z")

@Controller
public class RulesApiController implements RulesApi {
    ZallyIntegration zallyIntegration = ZallyIntegration.getInstance();

    public ResponseEntity<List<Rule>> rulesGet() {
        List<de.zalando.zally.api.models.Rule> enabledRules = zallyIntegration.getEnabledRules();
        List<Rule> response = new ArrayList<>();
        for (de.zalando.zally.api.models.Rule r : enabledRules){
            Rule rule = new Rule();
            rule.setCode(r.getCode());
            rule.setTitle(r.getTitle());
            rule.setType(r.getType());
            rule.setUrl(r.getUrl());
            response.add(rule);
        }
        return new ResponseEntity<List<Rule>>(response, HttpStatus.OK);
    }

}
