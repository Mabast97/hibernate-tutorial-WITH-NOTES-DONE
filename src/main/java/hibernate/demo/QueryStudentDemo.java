package hibernate.demo;

import demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {  // use the session object to save java object

            //start a transaction
            session.beginTransaction();

            // Query Students
            List<Student> theStudents = session.createQuery("from Student").list();

            // Display all the students
            displayStudents(theStudents);


            theStudents = session.createQuery("from Student s where s.firstName='Mabast'").list();
            System.out.println("\nFirst name with Mabast : ");
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.firstName LIKE 'Mab%'").list();
            System.out.println("\nFirst name Contains :  (Mab) : ");
            displayStudents(theStudents);

            //commit the transaction
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

}
