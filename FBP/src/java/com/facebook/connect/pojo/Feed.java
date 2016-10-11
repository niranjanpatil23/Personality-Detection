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
public class Feed
{
    private Data[] data;

   

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

 
    @Override
    public String toString()
    {
        return "[data = "+data+"]";
    }
}