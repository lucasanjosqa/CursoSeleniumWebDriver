import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExercicioCadastroTest {
    private WebDriver driver;
    private CampoTreinamentoPage page;
    private DSL dsl;

    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
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

    @Test
    public void deveValidarNomeObrigatorio(){
        page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAlertaEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio(){
        page.setNome("Lucas");
        page.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAlertaEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        page.setNome("Lucas");
        page.setSobrenome("Anjos");
        page.cadastrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoAlertaEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana(){
        page.setNome("Lucas");
        page.setSobrenome("Anjos");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoAlertaEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso(){
        page.setNome("Lucas");
        page.setSobrenome("Anjos");
        page.setSexoMasculino();
        page.setEsporte("Corrida");
        page.setEsporte("O que eh esporte?");
        page.cadastrar();
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoAlertaEAceita());
    }


}
