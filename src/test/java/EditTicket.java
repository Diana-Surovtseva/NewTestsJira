import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String commentText = driver.findElement(By.xpath("//*[@id='issue_actions_container']//child::*[@class='issue-data-block activity-comment twixi-block  expanded focused']//child::*[@class='action-body flooded']")).getText();
        assertTrue(commentText.contains("My comment"));
    }

    @Test

    public void dellCommentFromTicket() {
        driver.findElement(By.xpath("//*[@data-issue-key='WEBINAR-11962']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement elementList = driver.findElement(By.xpath("//*[@class='issue-data-block activity-comment twixi-block  expanded'][last()]//child::*[@title='Delete']"));
        elementList.click();
        String currCommentText = elementList.getText();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("comment-delete-submit")).click();

        String commentText = driver.findElement(By.xpath("//*[@id='issue_actions_container']//child::*[@class='issue-data-block activity-comment twixi-block  expanded']//child::*[@class='action-body flooded']")).getText();
        assertTrue(!commentText.contains(currCommentText));

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
