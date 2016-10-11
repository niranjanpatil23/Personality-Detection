/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.connect.pojo;

/**
 *
 * @author Admin
 */
public class FacebookPojo
{
    private String id;
    private String email;

    private String name;

    private Feed feed;
    
    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Feed getFeed ()
    {
        return feed;
    }

    public void setFeed (Feed feed)
    {
        this.feed = feed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", email = "+email+", name = "+name+", feed = "+feed+"]";
    }
}