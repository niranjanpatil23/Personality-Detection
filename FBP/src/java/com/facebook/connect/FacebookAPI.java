/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.connect;

import com.facebook.connect.pojo.FacebookPojo;
import com.facebook.connect.pojo.Feed;

/**
 *
 * @author Admin
 */
public class FacebookAPI {

    private Feed fd;
    private FacebookPojo fp;

    /**
     * @return the fd
     */
    public Feed getFd() {
        return fd;
    }

    /**
     * @param fd the fd to set
     */
    public void setFd(Feed fd) {
        this.fd = fd;
    }

    /**
     * @return the fp
     */
    public FacebookPojo getFp() {
        return fp;
    }

    /**
     * @param fp the fp to set
     */
    public void setFp(FacebookPojo fp) {
        this.fp = fp;
    }

}
