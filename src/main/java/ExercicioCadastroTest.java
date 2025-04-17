import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ExercicioCadastroTest {
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
    public void ExercicioCadastroCompleto(){
        WebElement nome = driver.findElement(By.id("elementosForm:nome"));
        nome.sendKeys("Lucas");

        WebElement sobrenome = driver.findElement(By.id("elementosForm:sobrenome"));
        sobrenome.sendKeys("Anjos");

        WebElement sexo = driver.findElement(By.id("elementosForm:sexo:0"));
        sexo.click();

        WebElement comidaFavorita = driver.findElement(By.id("elementosForm:comidaFavorita:2"));
        comidaFavorita.click();

        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Corrida");

        WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(escolaridade);
        combo.selectByVisibleText("Superior");

        WebElement botaoCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
        botaoCadastrar.click();

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Lucas"));
        Assert.assertEquals("Sobrenome: Anjos", driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: Corrida", driver.findElement(By.id("descEsportes")).getText());
    }
}
