package eu.mrndesign.matned.hibernate.demo;

import eu.mrndesign.matned.hibernate.demo.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDao {
    public void saveOrUpdate(Student student){
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // instrukcja która służy do zapisywania w bazie
            session.saveOrUpdate(student);
            transaction.commit();
        }
        catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void select(){
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            // instrukcja która służy do zapisywania w bazie
            List<Student> result = session.createQuery("FROM Student").getResultList();
            for (Student el : result) {
                System.out.println(el.toString());
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }
}
