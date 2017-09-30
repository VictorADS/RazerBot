import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Article4 {
 
	public static void main(String[] args) {

		SpringApplication.run(
				new Object[] { Article4.class }, args);
	}
}