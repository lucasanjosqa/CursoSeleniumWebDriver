import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
    @Test
    public void deveInteragirComAlertSimples() {
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("alert")).click();
        Alert alerta = driver.switchTo().alert();
        String texto = alerta.getText();
        Assert.assertEquals("Alert Simples", texto);
        alerta.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        driver.close();
    }

    @Test
    public void deveInteragirComAlertConfirmAndCancel() {
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alerta = driver.switchTo().alert();
        alerta.accept();
        String textConfirm = alerta.getText();
        Assert.assertEquals("Confirmado", textConfirm);
        alerta.accept();

        driver.findElement(By.id("confirm")).click();
        alerta.dismiss();
        String textCancel = alerta.getText();
        Assert.assertEquals("Negado", textCancel);
        alerta.accept();
        driver.close();
    }

    @Test
    public void deveInteragirComPrompt() {
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("prompt")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("12");
        alerta.accept();
        Assert.assertEquals("Era 12?", alerta.getText());
        alerta.accept();
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();
    }
}

