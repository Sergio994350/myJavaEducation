import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Entity;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        String hql1 = "From " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hql1, PurchaseList.class).getResultList();

        for (int i = 0; i < purchaseList.size(); i++) {

            String hql2 = "From " + Course.class.getSimpleName() + " Where name = '" + purchaseList.get(i).getCourseName() + "' ";
            String hql3 = "From " + Student.class.getSimpleName() + " Where name = '" + purchaseList.get(i).getStudentName() + "' ";
            List<Course> purchaselistsCourseName = session.createQuery(hql2).getResultList();
            List<Student> purchaselistsStudentName = session.createQuery(hql3).getResultList();
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setCourseId(purchaselistsCourseName.get(0).getId());
            linkedPurchaseList.setStudentId(purchaselistsStudentName.get(0).getId());
            session.save(linkedPurchaseList);
            for (Student e : purchaselistsStudentName) {
                System.out.println(e.getId());
            }
        }
        transaction.commit();
        sessionFactory.close();
    }
}