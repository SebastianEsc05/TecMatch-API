package potronet.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final String PERSISTENCE_UNIT_NAME = "tecmatch";
    private static EntityManagerFactory emf;

    private JpaUtil(){}

    public static EntityManager getEntityManager(){
        if(emf == null){
            synchronized (JpaUtil.class){
                if(emf == null){
                    emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                }
            }
        }
        return emf.createEntityManager();
    }
    public static void close(){
        if(emf != null && emf.isOpen()){
            emf.close();
        }
    }
}
