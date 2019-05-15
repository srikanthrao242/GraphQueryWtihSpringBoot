package com.graph.GraphQuery.model;


public class QueryResult {

    private String message;
    private String result;

    @Override
    public String toString() {
        if(message.equals("error"))
            return "{\"message\":\"" + message + "\"}" ;
        else
            return "{" +
                "\"message\":\"" + message + '\"' +
                ",\"result\": " + result +
                '}';
    }

    public QueryResult(String message, String result ){
        super();
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
