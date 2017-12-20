package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by afuri on 30/09/2017.
 */
@Component
public class Worker {

    private static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
    private static DesiredCapabilities desiredCaps = null;
    private static WebDriver driver ;
    private static String to = "victor.ads75@gmail.com";
    private static String from = "victor.ads75@gmail.com";
    private static String host = "localhost";//or IP address
    private static void initPhantomJS(){
        if(desiredCaps == null) {
            desiredCaps = new DesiredCapabilities();
            desiredCaps.setJavascriptEnabled(true);
            desiredCaps.setCapability("takesScreenshot", false);
            desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "./bin/phantomjs");
            desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "User-Agent", USER_AGENT);

            ArrayList<String> cliArgsCap = new ArrayList();
            cliArgsCap.add("--web-security=false");
            cliArgsCap.add("--ssl-protocol=any");
            cliArgsCap.add("--ignore-ssl-errors=true");
            cliArgsCap.add("--webdriver-loglevel=OFF");

            desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        }
        driver = new PhantomJSDriver(desiredCaps);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    private static void sendMail(String text) {

        //Get the session object
        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);

        //compose the message
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("RAZER - AVAILABILITYT");
            message.setText(text);

            // Send message
            Transport t = session.getTransport("smtps");
            t.connect("smtp.gmail.com", from, "Shutting123");
            t.sendMessage(message, message.getAllRecipients());
            System.out.println("message sent successfully....");

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    private static int i = 0;

    @Async
    @Scheduled(initialDelay = 2000, fixedRate = 3600000)
    public static void work() {
        try {
            System.out.println("INIT GONNA WORK\n");
            System.setProperty("phantomjs.page.settings.userAgent", USER_AGENT);
            String baseUrl = "https://zvault.razerzone.com/zSilver";
            System.out.println("\n + " + i++ + " HOURS WORKING\n");
            System.gc();
            initPhantomJS();
            driver.get(baseUrl);
            WebElement signInButton = driver.findElement(By.id("ssoLogin"));
            signInButton.click();

            WebDriverWait wait = new WebDriverWait(driver, 30);
            // We wait for the ajax call to fire and to load the response into the page
            Thread.sleep(800);
            WebElement email = driver.findElement(By.id("loginStep3_razerId"));
            email.clear();
            email.sendKeys("afuri@hotmail.fr");

            Thread.sleep(1000);

            WebElement pass = driver.findElement(By.id("loginStep3_password"));
            pass.clear();
            pass.sendKeys("Shutting123");

            WebElement form = driver.findElement(By.xpath("//div[@class='fx-280']/button[@type='submit']"));
            form.click();
            // We wait for the ajax call to fire and to load the response into the page
            Thread.sleep(10000);

            // acceder a la page des rewards
            driver.get("https://zvault.razerzone.com/zSilver#zSilverItem");
            Thread.sleep(5000);

            // On affiche les rewards
            WebElement catMouseDiv = driver.findElement(By.xpath("//button[@class='cat-mouse']"));
            System.out.println(catMouseDiv.getText());
            catMouseDiv.click();
            Thread.sleep(5000);

            WebElement sourisDiv = driver.findElement(By.xpath("//h4[contains(text(),'Razer Lancehead Tournament Edition')]"));
            sourisDiv.click();

            Thread.sleep(5000);
            WebElement frenchDiv = driver.findElement(By.xpath("//i[@class='zSilverFlag FR']"));
            System.out.println(frenchDiv.getText());
            frenchDiv.click();

            Thread.sleep(5000);

            WebElement availability = driver.findElement(By.xpath("//div[@class='orderDetailDesc']//p[@class='ng-binding']"));
            if (!availability.getText().contains("VOUCHERS OUT OF STOCK")) {
                System.out.println("YOU CAN BUY IT");
                sendMail("Hello, this is you in a bot trying to reach you at " + Instant.now());
            } else {
                System.out.println("NO STOCK");
            }
            driver.quit();
        }catch (Exception ie) {
            System.out.println("Got an error fetchhing data");
        }
    }

}
