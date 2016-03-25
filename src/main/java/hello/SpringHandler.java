package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHandler {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		RiotAPIHandler riotAPIHandler = (RiotAPIHandler) context.getBean("riotAPIHandler");
		riotAPIHandler.InsertIntoMongoDB();

	}
}
