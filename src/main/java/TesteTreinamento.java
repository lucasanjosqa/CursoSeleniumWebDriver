import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteTreinamento {

    @Test
    public void testeTextField(){
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
        Assert.assertEquals("Lucas", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Anjos");
        driver.close();
    }

    @Test
    public void deveInteragirComTextArea() {
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Texto TextArea");
        Assert.assertEquals("Texto TextArea", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
        driver.close();
    }
}
