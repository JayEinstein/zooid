package je.jdbc.sql;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpring {
	public static ApplicationContext startSpring() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		return context;
	}
}
