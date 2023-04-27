/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author sarra
 */
public class Participants {
    private Evenement event;
    private User user;

    public Participants(Evenement event, User user) {
        this.event = event;
        this.user = user;
    }

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   
    
    
}
