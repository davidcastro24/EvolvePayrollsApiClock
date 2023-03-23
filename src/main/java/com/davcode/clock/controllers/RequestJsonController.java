package com.davcode.clock.controllers;

import com.davcode.clock.mappers.RequestJson;
import com.davcode.clock.services.RequestJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/req-json")
public class RequestJsonController {

    private final RequestJsonService requestJsonService;

    @Autowired
    public RequestJsonController(RequestJsonService requestJsonService) {
        this.requestJsonService = requestJsonService;
    }

    @PostMapping
    public void addUserAndEmployee(@RequestBody RequestJson requestJson){
        requestJsonService.addUserAndEmployee(requestJson);
    }
}
