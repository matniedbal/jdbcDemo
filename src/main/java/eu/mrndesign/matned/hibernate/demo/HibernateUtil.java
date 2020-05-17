package eu.mrndesign.matned.hibernate.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory ourSessionFactory;

    //blok statyczny - fragment bloku wykonujący się raz przy starcie klasy
    static {

        try {
            System.out.println("Konfiguruje hibernate");
            //domyślne odwołanie do pliku konfiguracyjnego w formacie podanym w resources
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
//            System.exit(376); // kod błędu - 376 - błąd hibernate
            throw e;
        }
    }

    public static SessionFactory getOurSessionFactory() {
        return ourSessionFactory;
    }
}
