package ru.yandex.ajwar.listener;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Ar on 25.01.2016.
 */
public class HibernateListener implements ServletContextListener{

    private Configuration config;
    private SessionFactory factory;
    /**Относительный путь к настройкам Hibernate*/
    private String path = "/hibernate.cfg.xml";
    private static Class clazz = HibernateListener.class;

    public static final String KEY_NAME = clazz.getName();

    public void contextDestroyed(ServletContextEvent event) {
        //
    }

    public void contextInitialized(ServletContextEvent event) {

        try {
            /**Инициализация фреймворка Hibernate*/
            URL url = HibernateListener.class.getResource(path);
            config = new Configuration().configure(url);
            factory = config.buildSessionFactory();

            /**сохранние  SessionFactory Hibernate в контексте сервлета*/
            event.getServletContext().setAttribute(KEY_NAME, factory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}