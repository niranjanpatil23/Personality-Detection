/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.connect;

import com.facebook.connect.pojo.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.connect.pojo.FacebookPojo;
import com.facebook.connect.pojo.Feed;
import com.facebook.connect.pojo.*;
public class FBGraph {

    private final String accessToken;

   
            

    public FBGraph(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFBGraph() {
        String graph = null;
        try {
            
             //System.out.println("........................................"+accessToken);
            //String g = "https://graph.facebook.com/me?" + accessToken;
            //String g = "https://graph.facebook.com/v2.4/me?fields=id,name,email,feed&" + accessToken;    
             // correct String g = "https://graph.facebook.com/v2.5/me?fields=id,name,email,feed.limit(100)&access_token=CAACEdEose0cBAKD22w57dwojNIB52XQzW0JAfgxj808fkfBeY3F2owtWwLZAdvNwuW0xd5MLIAj4q1E97ugKRuetZCdck3ZCcIkGUl4jSX9ub78fT2QGTup7uVxpjmDZCDwRe2uOb6Csj9leFJdSpu3E45f7PQZAEkGYu8S3Mmyqgd9GnTxlRz9tZCg4UsaRPl9NZA6B0wKFwZDZD";
           // String g = "https://graph.facebook.com/v2.5/me?fields=id,name,email,feed.limit(100)&access_token=CAACEdEose0cBAP03bMbRTu97C0CEZAMRZA3g3JZBjZCp5MYb32zMzbTiFHn3ZBwwVr0ZBI1mWouMItr846kaZBxXf5pCq9alq5ZCMF6UViInlu0xFSrZA7sT9M2HQLJpMlLuq69rf5B8ARIxNpZB1JW8IzB9i7R4dFZAYpbKqnPjMx5U9cCBkoMCt6rLM3qA7aWV7I8enqmXNUKGAZDZD&expires=5178456";
          //co String g = "https://graph.facebook.com/v2.5/me?fields=id,name,email,feed.limit(100)&access_token=CAACEdEose0cBAOd5O1ZCARX0MCcW5qchuQZBostcjePpD3H2YoNE6j5HgnZAJMaeZCeWa0FdauaIObc8AlAhopmtlFJ4YbmchcZAOqmKnNxAlUCeRumzDy9cN6ZAudhYiQEoOsNObeKRV7QD2szF7cZAX4zPdZABpeOcawkb9BKDBADQ9grlvyYodIZBiF9fWhioZAbhXZCUAoIRgZDZD";
       //String g = "https://graph.facebook.com/v2.5/me?fields=id,name,email,feed.limit(100)&"+ accessToken;
         //   String g = "https://graph.facebook.com/v2.5/me?fields=id,name,email,feed &" + accessToken;
//"https://graph.facebook.com/v2.5/me?fields=id,name,email,feed.limit(100)&" + accessToken;
      String g = "https://graph.facebook.com/v2.4/me?fields=id,name,email,feed.limit(100)&"+accessToken;   
            
            
            
      //      String g = "https://graph.facebook.com/v2.5/me?fields=id,name,email,feed.limit(20)&access_token=CAACEdEose0cBAHxacZCLWuFc1R7Vy75nGZCUbOjMreT6RVJSShI7ueTfgqZAJ8xZCbeuMENoLqVAvxp32sZBOZA2HMu61067wAahaGzTRRY8AuFj9uNxjmy7ehCFCWY6nobRHIvCx3xzUVN1a9hDZA2wRvtZAcrs3GJUFUoAZCFZBSALI1wVcAZAamZCZAYV4QLB94LyCZBHgdgDN6gQZDZD";
            System.out.println("........................................");
            System.out.println(g);
            System.out.println("........................................");

            URL u = new URL(g);
            URLConnection c = u.openConnection();
            StringBuffer b;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    c.getInputStream()))) {
                String inputLine;
                b = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    b.append(inputLine).append("\n");
                }
            }
            graph = b.toString();
            //System.out.println(graph);
        } catch (Exception e) {
            throw new RuntimeException("ERROR in getting FB graph data. " + e);
        }
        return graph;
    }

    public FacebookPojo getFcp() {
        return fcp;
    }

    public void setFcp(FacebookPojo fcp) {
        this.fcp = fcp;
    }

    public Feed getFd() {
        return fd;
    }

    public void setFd(Feed fd) {
        this.fd = fd;
    }

    private FacebookPojo fcp;
    private Feed fd;
    
    public void getGraphData(String fbGraph) {

        try {
             JSONObject json = new JSONObject(fbGraph);
               System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>");
            System.out.println(fbGraph);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>");
            fcp = new FacebookPojo();
            fcp.setId(json.getString("id"));
            fcp.setName(json.getString("name"));
            fcp.setEmail(json.getString("email"));
           // fcp.setFeed(json.getString("fd"));
            
        
        
          
           
           
           JSONObject json2 = new JSONObject(json.getString("feed"));
           JSONArray jarray = json2.getJSONArray("data");
          

            fd = new Feed();
            Data[] dt = new Data[jarray.length()];

            for (int i = 0; i < jarray.length(); i++) {
                try {
                    JSONObject jsoninnner = jarray.getJSONObject(i);                   
                   dt[i] = new Data();
                   dt[i].setMessage(jsoninnner.getString("message"));
                   dt[i].setId(jsoninnner.getString("id"));
                 //  dt[i].setStory(jsoninnner.getString("story"));
                 //  dt[i].setCreated_time(jsoninnner.getString("created_time"));                    
                } catch (Exception e) {                    
                    System.out.println(" "+ e.getMessage());
                }
            }
            fd.setData(dt);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR in parsing FB graph data. " + e);
        }
    }
}
// CAABbD8DJM34BAPJMOMWp38ZBZC7ZCzoW72FB59MvCmWT7DPDK7feK4NNOXyT4FEqj5SA6AvPoLubM8TLezpppiInLoIlIWXssR69fkVMsssFY9ExcJZAGo2x1UIOBvqtYZAAQ5zppdA4n7jOfq3C8zImz665dHJ2Ix2RAvf55R4vdsZBFD0VPS21NRfcpVA44ZD&expires=5183984
// CAABbD8DJM34BACVlNV37e1mHhWVz44BQBS9RKcaASDR6ZBQf30Wg0kSSTapE3hPqQQOhH7g9OgUWecE1jv1ZB4DFPYFHGlJYdcEfDF9O2iieuViVwPo6iW7Y0lLfuoQZBclRNGX1e7UfC7Q5t3xziigJi1dZCN1ehAQq21yHXlnLZCcZBHtZBoiun6XI3QV4NkZD&expires=5182030