import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    public static Session getSession() {
        if(sessionFactory == null || sessionFactory.isClosed()) {
            serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            // Create MetadataSources
            MetadataSources sources = new MetadataSources(serviceRegistry);
            // Create Metadata
            Metadata metadata = sources.getMetadataBuilder().build();
            // Create SessionFactory
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory.getCurrentSession();
    }

    public static void closeSession() {
        if(sessionFactory != null && !sessionFactory.isClosed())
            sessionFactory.close();
    }

}
