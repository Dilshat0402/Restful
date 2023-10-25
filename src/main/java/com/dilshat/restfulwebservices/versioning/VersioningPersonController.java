package com.dilshat.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    //URI VERSIONING
    @GetMapping("/V1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Dima Nagiev");
    }

    //URI VERSIONING
    @GetMapping("/V2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Dima", "Nagiev"));
    }

    //REQUEST PARAMETER VERSIONING
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new PersonV1("Dima Nagiev");
    }

    //REQUEST PARAMETER VERSIONING
    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Dima", "Nagiev"));
    }

    //(CUSTOM) HEADERS VERSIONING
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader(){
        return new PersonV1("Dima Nagiev");
    }

    //(CUSTOM) HEADERS VERSIONING
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader(){
        return new PersonV2(new Name("Dima", "Nagiev"));
    }

    //MEDIA TYPE VERSIONING
        @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader(){
        return new PersonV1("Dima Nagiev");
    }

    //MEDIA TYPE VERSIONING
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader(){
        return new PersonV2(new Name("Dima", "Nagiev"));
    }


}
