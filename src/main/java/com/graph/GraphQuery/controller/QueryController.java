package com.graph.GraphQuery.controller;

import com.graph.GraphQuery.service.QueryServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @Autowired
    private QueryServies queryServies;

    @PostMapping (path = "/grpahQuery/{queryName}" , produces="application/json")
    public String retrieveCoursesForStudent(@PathVariable String queryName) {
        return queryServies.executeSelectQuery(queryName).toString();
    }


}
