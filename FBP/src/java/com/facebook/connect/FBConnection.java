package com.facebook.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FBConnection {

    public static final String FB_APP_ID = "100123217048446";
  //   public static final String FB_APP_ID = "1680135788893700";
    public static final String FB_APP_SECRET = "e9463d7e90dba1f4e1cd35502ba9724d";
   //  public static final String FB_APP_SECRET = "bbdd5e2bbe5f5e5f440f76610ba30d33";


    public static final String REDIRECT_URI = "http://localhost:8080/FBP/facebookredirect.jsp";
    
    static String accessToken ="";

    public String getFBAuthUrl() {
        String fbLoginUrl = "";
        try {
            fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
                    + FBConnection.FB_APP_ID + "&redirect_uri="
                    + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
                    + "&scope=email";
        } catch (UnsupportedEncodingException e) {
        }
        return fbLoginUrl;
    }

    public String getFBGraphUrl(String code) {
        String fbGraphUrl = "";
        try {
            fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
                    + "client_id=" + FBConnection.FB_APP_ID + "&redirect_uri="
                    + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
                    + "&client_secret=" + FB_APP_SECRET + "&code=" + code;
        } catch (UnsupportedEncodingException e) {
        }
        return fbGraphUrl;
    }

    public String getAccessToken(String code) {
      if ("".equals(accessToken)) {
            URL fbGraphURL;
            try {
                fbGraphURL = new URL(getFBGraphUrl(code));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid code received " + e);
            }
            URLConnection fbConnection;
            StringBuffer b = null;
            try {
                fbConnection = fbGraphURL.openConnection();
                BufferedReader in;
                in = new BufferedReader(new InputStreamReader(
                        fbConnection.getInputStream()));
                String inputLine;
                b = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    b.append(inputLine).append("\n");
                }
                in.close();
            } catch (IOException e) {
                throw new RuntimeException("Unable to connect with Facebook "
                        + e);
            }

            accessToken = b.toString();
            if (accessToken.startsWith("{")) {
                throw new RuntimeException("ERROR: Access Token Invalid: "
                        + accessToken);
            }
        }
    /*try{
                if (code != null) {
                String requestURL = "https://graph.facebook.com/oauth/access_token?client_id=100123217048446&redirect_uri=http://localhost:8080/FBP/facebookredirect.jsp&client_secret=e9463d7e90dba1f4e1cd35502ba9724d&grant_type=client_credentials&code="+code;
                URL wikiRequest = new URL(requestURL);
                URLConnection connection = wikiRequest.openConnection();  
                connection.setDoOutput(true);  
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                String access_token ="";
                String expiry ="";
                while ((inputLine = in.readLine()) != null){ 
                 String[] tempString  = inputLine.split("&");
                 for(int i=0;i<tempString.length;i++){

                  if(tempString[i].contains("access_token=")){
                   access_token = tempString[i].substring(tempString[i].indexOf("=")+1,tempString[i].length());
                  }else{
                   expiry = tempString[i].substring(tempString[i].indexOf("=")+1,tempString[i].length());
                  }
                 }
                 System.out.println("access       _     token is "+access_token);
                 System.out.println("expiry is "+expiry);
                }}
                
                }catch(Exception e){
                   e.printStackTrace();
   //response.getWriter().println("Error Occured...");
 }*/
 
        return accessToken;
    }
}


/*

 try{
                if (code != null) {
                String requestURL = "https://graph.facebook.com/oauth/access_token?client_id=100123217048446&redirect_uri=http://localhost:8080/FBP/facebookredirect.jsp&client_secret=e9463d7e90dba1f4e1cd35502ba9724d&code="+code;
                URL wikiRequest = new URL(requestURL);
                URLConnection connection = wikiRequest.openConnection();  
                connection.setDoOutput(true);  
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                String access_token ="";
                String expiry ="";
                while ((inputLine = in.readLine()) != null){ 
                 String[] tempString  = inputLine.split("&");
                 for(int i=0;i<tempString.length;i++){

                  if(tempString[i].contains("access_token=")){
                   access_token = tempString[i].substring(tempString[i].indexOf("=")+1,tempString[i].length());
                  }else{
                   expiry = tempString[i].substring(tempString[i].indexOf("=")+1,tempString[i].length());
                  }
                 }
                 System.out.println("access       _     token is "+access_token);
                 System.out.println("expiry is "+expiry);
                }}
                
                }catch(Exception e){
                   e.printStackTrace();
    response.getWriter().println("Error Occured...");
 }
             
*/
// CAABbD8DJM34BANFFaNMUk0KlvO0bWxuQpEEQZA2DCNiBbltNmoeQIQE0iWoaZCVU7VktGdkbGxVaVSMUcFvrctGZAy1nJObQS5o9EKwZApY7IiTZBSBzlTLDZBn6FRHw9xCT0bjug9pD62djZBmcvGZC8ZBTMfgJC5crQ3OSxee0m9b1v21bxOwGKr4mSiZCscI8AZD
// CAABbD8DJM34BAG6OxqHEe4v1y6naASNtaMsYMZCms0ZAql3l5DdDOG3tcRQ41FOa7bbYWCm5iLUguau0SrpgiKFiWlnnaNiwMbZB7T3IMgb4HsEvWEQUBeC9wLZAeGm1sMj88GvcbeLjtC8Y3qegS91kgIwi6GLpIZBZA4ji8bOtVy2aA8hSjozfCwZCPlNmMUZD&expires=5102603
// CAABbD8DJM34BAHduygWLJrjWAIZBSIMYbZBZCgIWAjFiYZAUFEmv3uU2KcgVldzDA0x5rTqZC7Q7NhfFiIYGRES2LtGZBspU8pN1pzm8hLYVrn96MyFoGWnjJ1aOBpV6qDmQPltjT9nqRsPo2jGHRvst8aXwOtZBymuom9CLahj3kwThXsNSaZCWdjdncLlUeogZD