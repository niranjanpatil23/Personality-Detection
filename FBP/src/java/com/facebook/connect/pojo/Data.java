/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.connect.pojo;

import org.json.JSONObject;

/**
 *
 * @author Admin
 */
                 
public class Data
{
    private String id;

    private String message;

    private String story;

    private String created_time;
    
    
    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
       this.message = message;
       /* if(this.message != null) {
            StringBuilder sb = Sentiment.getScoring(message);
            try {
                JSONObject json = new JSONObject(new JSONObject(sb+"").getString("result"));

                   massageSentiment = json.getString("sentiment");;
                   massageScore = Double.parseDouble(json.getString("confidence"));
            }
            catch(Exception e) {
            }
        }*/
        
    }
    
    
    /*public String getStory ()
    {
        return story;
    }

    public void setStory (String story)
    {
        this.story = story;
    }

    public String getCreated_time ()
    {
        return created_time;
    }

    public void setCreated_time (String created_time)
    {
        this.created_time = created_time;
    }*/

    @Override
    public String toString()
    {
        return "[id = "+id+", message = "+message+"]";// story = "+story+", created_time = "+created_time+"]";
    }
}