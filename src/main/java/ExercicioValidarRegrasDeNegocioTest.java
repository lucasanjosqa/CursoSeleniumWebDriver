import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ExercicioValidarRegrasDeNegocioTest {
    private WebDriver driver;

    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void ExercicioValidarRegrasDeNegocio(){
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        //  Nome é obrigatório
        Alert alertNome = driver.switchTo().alert();
        String alertTextNome = alertNome.getText();
        Assert.assertEquals("Nome eh obrigatorio", alertTextNome);
        alertNome.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");

        //  Sobrenome é obrigatório
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alertSobrenome = driver.switchTo().alert();
        String alertTextSobrenome = alertSobrenome.getText();
        Assert.assertEquals("Sobrenome eh obrigatorio", alertTextSobrenome);
        alertSobrenome.accept();
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Anjos");

        //  Sexo é obrigatório
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        //  Não posso dizer que sou vegetariano e como carne
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alertComida = driver.switchTo().alert();
        String alertTextComida = alertComida.getText();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alertTextComida);
        alertComida.accept();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        //  Não posso marcar algum esporte e depois dizer o que eh algum esporte?
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alertEsporte = driver.switchTo().alert();
        String alertTextEsporte = alertEsporte.getText();
        Assert.assertEquals("Voce faz esporte ou nao?", alertTextEsporte);
        alertEsporte.accept();
        combo.deselectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        driver.close();
    }
}
