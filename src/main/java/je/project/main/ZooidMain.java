package je.project.main;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ZooidMain {

	public static void main(String[] args) {
		
	}
	
	public void star() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		SqlSessionFactory ssf = (SqlSessionFactory) ctx.getBean("sqlSessionFactory");
		SqlSession sqlSession = ssf.openSession();
		
		
		
//		sqlSession.getMapper(type);
	}

}
