package hibernate.demo;

import demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestRevision {
    public static void main(String[] args) {

        Student t1 = new Student("Salar1", "Ahmed", "46@a1.com");
        Student t2 = new Student("Salar2", "Ahmed", "467@a12.com");
        Student t3 = new Student("Salar3", "Ahmed", "468@a13.com");
        Student t4 = new Student("Salar4", "Ahmed", "469@a14.com");
        Student t5 = new Student("Salar5", "Ahmed", "460@a1.com");
        Student t6 = new Student("Salar6", "Ahmed", "471@a1.com");

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

        //start a transaction
        session.beginTransaction();

        int[] a = {5,6,7,8,9,10,11};

        int id = 10;
        Student temp = session.get(Student.class, id);
        System.out.println(temp);



        //commit the transaction
        session.getTransaction().commit();
    }
        finally {
        factory.close();
    }


    }
}
