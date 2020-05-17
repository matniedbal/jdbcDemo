package eu.mrndesign.matned.hibernate.demo;

import eu.mrndesign.matned.hibernate.demo.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

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

//    HQL
//    public List<Student> getAll(){
//        List result = new LinkedList<>();
//        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
//        try(Session session = sessionFactory.openSession()) {
//
//            result = session.createQuery("FROM Student").getResultList();
//        }
//        catch (HibernateException e){
//            e.printStackTrace();
//        }
//        return result;
//    }

 public List<Student> getAll(){
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try(Session session = sessionFactory.openSession()) {

            //zapytania i klauzula where
            CriteriaBuilder cb = session.getCriteriaBuilder();
            //obiekt reprezentacja tabeli
            CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);
            //reprezentacja tabeli sql
            Root<Student> rootTable = criteriaQuery.from(Student.class);
            //zapytanie
            criteriaQuery.select(rootTable);
            result.addAll(session.createQuery(criteriaQuery).list());

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return result;
    }

    public Optional<Student> findById(Long id){
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Student.class, id));
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(Student student){
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        }
        catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

}
