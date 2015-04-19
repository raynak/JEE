/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author raynak
 */
@Singleton
@LocalBean
@WebListener
public class SessionManagerBean implements HttpSessionListener {

    private static int countUsers = 0;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        countUsers++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        countUsers--;
    }

    /**
     * Return the number of users connnected during the current session
     * @return the number of users connnected during the current session
     */
    public int getActiveSessionCount(){
        return countUsers;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
