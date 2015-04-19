/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author raynak
 */
@JMSDestinationDefinition(name = "jms/BooksMessageBean", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "BooksMessageBean")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BooksMessageBean")
})
public class BooksMessageBean implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    
    @PersistenceContext(unitName = "BookShelf-ejbPU")
    private EntityManager em;
    
    
    
    public BooksMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
              if (message instanceof ObjectMessage) {
            msg = (ObjectMessage) message;
            Book e = (Book) msg.getObject();
            save(e);            
                 }
        } 
        catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } 
        catch (Throwable te) {
            te.printStackTrace();
        }
    }
    

    public void save(Object object) {
        em.persist(object);
    }
    
}
