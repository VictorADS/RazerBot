import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.Message;


// PhantomJS

@EnableScheduling
@Component
@EnableAutoConfiguration
public class Article4 {

	public static void main(String[] args) {
		try {
			new AnnotationConfigApplicationContext(Article4.class);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
