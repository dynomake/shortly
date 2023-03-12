package uk.suuft.shortly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class  ShortlyApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ShortlyContextConfiguration.class);

		SpringApplication.run(ShortlyApplication.class, args);
	}

}
