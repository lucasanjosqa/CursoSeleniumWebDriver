import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExercicioCadastroTest {
    private WebDriver driver;
    private CampoTreinamentoPage page;


    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void ExercicioCadastroCompleto(){
        page.setNome("Lucas");
        page.setSobrenome("Anjos");
        page.setSexoMasculino();
        page.setComidaFavoritaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("Corrida");
        page.cadastrar();

        Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
        Assert.assertTrue(page.obterNomeCadastro().endsWith("Lucas"));
        Assert.assertEquals("Sobrenome: Anjos", page.obterSobrenomeCadastro());
        Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Comida: Pizza", page.obterComidaFavoritaCadastro());
        Assert.assertEquals("Escolaridade: superior", page.obterEscolaridade());
        Assert.assertEquals("Esportes: Corrida", page.obterEsporte());
    }

}
