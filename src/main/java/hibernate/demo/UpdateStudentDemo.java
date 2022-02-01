package hibernate.demo;

import demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;
            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("Getting student with id: "+studentId);

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating Student");
            myStudent.setFirstName("Helloww");

            // commit the transaction, it will automatically update the data in the database without need to say .save() or .update()
            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("update emails for all the students : ");

            session.createQuery("update Student set email='testing@gmial.com'").executeUpdate();

            session.getTransaction().commit();


            System.out.println("Done");
        }
        finally {
            factory.close();
        }

    }

}
