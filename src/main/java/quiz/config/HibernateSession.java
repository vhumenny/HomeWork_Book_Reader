package quiz.config;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import quiz.entity.Question;
import quiz.entity.Topic;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class HibernateSession {
    private static SessionFactory sessionFactory;
    private static final Properties properties = new Properties();
    private final static String URL = "db.url";
    private final static String USERNAME = "db.username";
    private final static String PASSWORD = "db.password";
    private final static String DRIVER = "db.driver";
    private final static String DIALECT = "db.dialect";
    private final static String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private final static String SHOW_SQL = "db.show_sql";

    static {
        loadProperties();
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.setProperty(Environment.DRIVER, properties.getProperty(DRIVER));
            configuration.setProperty(Environment.URL, properties.getProperty(URL));
            configuration.setProperty(Environment.USER, properties.getProperty(USERNAME));
            configuration.setProperty(Environment.PASS, properties.getProperty(PASSWORD));
            configuration.setProperty(Environment.DIALECT, properties.getProperty(DIALECT));
            configuration.setProperty(Environment.HBM2DDL_AUTO, properties.getProperty(HIBERNATE_HBM2DDL_AUTO));
            configuration.setProperty(Environment.SHOW_SQL, properties.getProperty(SHOW_SQL));

            configuration.addAnnotatedClass(Topic.class);
            configuration.addAnnotatedClass(Question.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void loadProperties() {
        try (var inputstream = PropertiesLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inputstream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
