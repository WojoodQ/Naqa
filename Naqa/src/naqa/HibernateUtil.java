/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naqa;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
/**
 *
 * @author Asus
 */
public class HibernateUtil {
        private static final SessionFactory sessionFactory;
    
    static {
        try {
             // loads configuration and mappings
            Configuration configuration = new Configuration().configure("/naqa/hibernate.cfg.xml");
            ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
           
            //pojo classes
            configuration.addAnnotatedClass(user.class); 
            configuration.addAnnotatedClass(monthlyconsumption.class);
            configuration.addAnnotatedClass(storge.class);
            configuration.addAnnotatedClass(beforeSession.class); 
            configuration.addAnnotatedClass(afterSession.class); 
            configuration.addAnnotatedClass(DailySuppliesModel.class);
            configuration.addAnnotatedClass(date.class);
            configuration.addAnnotatedClass(NotificationModel.class);
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

