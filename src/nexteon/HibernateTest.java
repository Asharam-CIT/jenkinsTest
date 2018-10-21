package nexteon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import nexteon.model.Student;

public class HibernateTest {
	public static void main(String[] args) {
		
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("resources/hibernate.cfg.xml");
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		/*Session session = sessionFactory.openSession();
		
		Student st = new Student();
		st.setName("Krishnan");
		
		session.save(st);
		
		session.close();*/
	}
}
