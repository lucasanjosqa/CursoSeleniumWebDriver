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
    private DSL dsl;

    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void ExercicioCadastroCompleto(){
        dsl.escreve("elementosForm:nome", "Lucas");
        dsl.escreve("elementosForm:sobrenome", "Anjos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:2");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto(By.id("descNome")).endsWith("Lucas"));
        Assert.assertEquals("Sobrenome: Anjos", dsl.obterTexto(By.id("descSobrenome")));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto(By.id("descSexo")));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto(By.id("descComida")));
        Assert.assertEquals("Escolaridade: superior", dsl.obterTexto(By.id("descEscolaridade")));
        Assert.assertEquals("Esportes: Corrida", dsl.obterTexto(By.id("descEsportes")));
    }
}
