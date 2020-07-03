import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EditTicket {


    WebDriver driver = null;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login-form-password")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        driver.findElement(By.xpath("//*[@data-issue-key='WEBINAR-11962']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addCommentToTicket() {


        driver.findElement(By.xpath("//*[@data-issue-key='WEBINAR-11962']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("footer-comment-button")).click();
        driver.findElement(By.id("comment")).sendKeys("My comment");
        driver.findElement(By.id("issue-comment-add-submit")).click();
//            Thread.sleep(5000);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
//            boolean commentPresent = wait.until(presenceOfElementLocated(By.className("issue-data-block activity-comment twixi-block  expanded focused"))).isDisplayed();
//        assertEquals(commentPresent, true);
        // assertTrue(driver.findElement(By.className("issue-data-block activity-comment twixi-block  expanded focused"))).isEnabled();

    }


    @Test
    public void delCommentToTicket() {

        driver.findElement(By.xpath("//*[@data-issue-key='WEBINAR-11962']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@class='issue-data-block activity-comment twixi-block  expanded'][last()]//child::*[@title='Delete']")).click();
        //$x("//*[@class='issue-data-block activity-comment twixi-block  expanded'][last()]//child::*[@title='Delete']")
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("comment-delete-submit")).click();

        ////*[@id="comment-19625"]/div[2]/div/div/text()[2]
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
