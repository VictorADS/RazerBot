package blog.article1;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebScraper {

	public static void main(String[] args) {
		
	    String searchQuery = "iphone 6s" ;
		String baseUrl = "https://zvault.razerzone.com/zSilver" ;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			//String searchUrl = baseUrl + "search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
			HtmlPage page = client.getPage(baseUrl);
			
			DomElement loginButton = page.getElementById("ssoLogin") ;
			if(loginButton == null){
				System.out.println("No items found !");
			}else{
				loginButton.click();
				Thread.sleep(10000);
			}
		} catch(Exception e){
			e.printStackTrace();
		}

	}

}
