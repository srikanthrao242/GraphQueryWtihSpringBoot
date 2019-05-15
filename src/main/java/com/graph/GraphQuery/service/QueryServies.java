package com.graph.GraphQuery.service;

import com.graph.GraphQuery.model.QueryResult;
import org.apache.jena.query.*;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
public class QueryServies {

    private static HashMap<String,String> queries = new HashMap<>();
    private static String service = "https://dbpedia.org/sparql";

    static {
        String query1 = "select distinct ?Concept  where {[] a ?Concept} LIMIT 100";
        queries.put("query1",query1);
        String query2 = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX res: <http://dbpedia.org/resource/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT DISTINCT ?uri ?string \n" +
                "WHERE {\n" +
                "\t?uri rdf:type dbo:Film .\n" +
                "        ?uri dbo:starring res:Julia_Roberts .\n" +
                "        ?uri dbo:starring res:Richard_Gere.\n" +
                "\tOPTIONAL {?uri rdfs:label ?string . FILTER (lang(?string) = 'en') }\n" +
                "}";
        queries.put("query2",query2);
        String query3 = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX res: <http://dbpedia.org/resource/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT DISTINCT ?uri ?string \n" +
                "WHERE {\n" +
                "\tres:Wikipedia dbo:author ?uri .\n" +
                "        OPTIONAL { ?uri rdfs:label ?string . FILTER (lang(?string) = 'en') }\n" +
                "}";
        queries.put("query3",query3);
    }


    public QueryResult executeSelectQuery(String queryName){
        try{
            QueryExecution e = QueryExecutionFactory.sparqlService(service,queries.get(queryName));
            ResultSet result = e.execSelect();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(outputStream, result);
            return new QueryResult("success",new String(outputStream.toByteArray(), StandardCharsets.UTF_8));
        }catch (Exception exc){
            return new QueryResult("error","");
        }
    }


}
