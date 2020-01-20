package com.thinktag.persist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinktag.persist.service.SearchService;
import com.thinktag.persist.service.TypeLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("api/search")
    @ResponseBody
    String search(@RequestParam String type, @RequestParam String attribute, @RequestParam String value) throws JsonProcessingException {
        return TypeLookup.writeJsonList(searchService.search(type, attribute, value));
    }

    @GetMapping("api/searchWildCard")
    @ResponseBody
    String searchWildCard(@RequestParam String type, @RequestParam String attribute, @RequestParam String value) throws JsonProcessingException {
        return TypeLookup.writeJsonList(searchService.searchWildcard(type, attribute, value));
    }

    @PostMapping("api/buildSearchIndex")
    void buildIndex() throws InterruptedException {
        searchService.buildIndex();
    }
}
