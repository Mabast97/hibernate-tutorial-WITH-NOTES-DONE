package hibernate.demo;

import demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {  // use the session object to save java object

            // create a student object
            Student tempStudent1 = new Student("Mabast1", "Hashm1", "M1@yahoo.com");
            Student tempStudent2 = new Student("Mabast2", "Hashm2", "M2@yahoo.com");
            Student tempStudent3 = new Student("Mabast3", "Hashm3", "M3@yahoo.com");

            //start a transaction
            session.beginTransaction();

            // save the student object
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            //commit the transaction
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
