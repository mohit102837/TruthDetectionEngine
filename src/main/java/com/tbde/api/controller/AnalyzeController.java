package com.tbde.api.controller;

import com.tbde.api.dto.AnalyzeTextRequest;
import com.tbde.api.service.AnalyzeTextService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/analyze")
public class AnalyzeController {

    private final AnalyzeTextService service;
    @PostMapping
    public Object analyze(@Valid @RequestBody AnalyzeTextRequest req) {
        return service.analyze(req);
    }


    public AnalyzeController(AnalyzeTextService service) {
        this.service = service;
    }



}
