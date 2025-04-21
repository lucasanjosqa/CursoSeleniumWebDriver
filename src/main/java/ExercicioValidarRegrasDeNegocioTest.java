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
    private DSL dsl;

    @Before
    public void inicializa() {
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void exercicioValidarRegrasDeNegocio() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Assert.assertEquals("Nome eh obrigatorio", dsl.obterTextoAlerta());

        dsl.escreve("elementosForm:nome", "Lucas");

        // Sobrenome
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.obterTextoAlerta());

        dsl.escreve("elementosForm:sobrenome", "Anjos");

        // Sexo
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));

        // Não posso dizer que sou vegetariano e como carne
        dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
        dsl.clicarBotao("elementosForm:cadastrar");
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.obterTextoAlerta());
        dsl.clicarCheckbox("elementosForm:comidaFavorita:3");

        //  Não posso marcar algum esporte e depois dizer o que eh algum esporte?
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        dsl.clicarBotao("elementosForm:cadastrar");
        String alertTextEsporte = dsl.obterTextoAlerta();
        Assert.assertEquals("Voce faz esporte ou nao?", alertTextEsporte);
        combo.deselectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
    }
}
