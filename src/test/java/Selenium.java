import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

public class Selenium {
    public static void main(String args[])
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions co=new ChromeOptions();
        //co.addArguments("--headless");
        co.addArguments("--disable-gpu");
        co.addArguments("--incognito");
        WebDriver driver=new ChromeDriver(co);
        driver.get("https://magento.softwaretestingboard.com/");
        WebElement women= driver.findElement
                (By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/women.html\"]//following::span"));
        WebElement tops=driver.findElement
                (By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/women/tops-women.html\"]"));
        WebElement hoodies= driver.findElement
                (By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/women/tops-women.html\"]//following::span[text()=\"Hoodies & Sweatshirts\"]"));
        Actions actions=new Actions(driver);
        actions.moveToElement(women).perform();
        actions.moveToElement(tops).perform();
        actions.click(hoodies).perform();
        WebElement gear= driver.findElement
                (By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/gear.html\"]//following::span"));
        WebElement fitnessEquipment=driver.findElement
                (By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/gear/fitness-equipment.html\"]"));
        actions.moveToElement(gear).perform();
        actions.click(fitnessEquipment).perform();
        WebElement sortBy=driver.findElement(By.cssSelector("#sorter"));
        Select select=new Select(sortBy);
        System.out.println("Options available in sorter are"+ Arrays.toString(select.getOptions().stream().map(option->option.getText()).toArray()));
        select.selectByValue("price");
        ((JavascriptExecutor)driver).executeScript("scroll(0,100)");
        driver.findElement(By.xpath("//a[contains(@href,\"sprite-yoga-companion\")]")).click();
        WebElement reviews= driver.findElement(By.cssSelector("[id*=tab-label-reviews-title]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",reviews);
        reviews.click();
        ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
        WebElement stars=driver.findElement(By.xpath("//label[@title=\"1 star\"]"));
        WebElement nickname=driver.findElement(By.cssSelector("[id*=nickname]"));
        WebElement summary=driver.findElement(By.cssSelector("[id*=summary_field]"));
        WebElement review_field=driver.findElement(By.cssSelector("[id*=review_field]"));
        stars.click();
        nickname.sendKeys("AA");
        summary.sendKeys("Pretty good");
        review_field.sendKeys("Completely happy with the product quality and color");
        WebElement submit=driver.findElement(By.cssSelector("[class*=submit]"));
        submit.click();
        driver.close();







    }
}
