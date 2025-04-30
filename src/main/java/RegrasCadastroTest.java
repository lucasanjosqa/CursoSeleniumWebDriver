import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class RegrasCadastroTest {
    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Parameterized.Parameter(value = 0)
    public String nome;
    @Parameterized.Parameter(value = 1)
    public String sobrenome;
    @Parameterized.Parameter(value = 2)
    public String sexo;
    @Parameterized.Parameter(value = 3)
    public List<String> comidas;
    @Parameterized.Parameter(value = 4)
    public String[] esportes;
    @Parameterized.Parameter(value = 5)
    public String msg;

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

    @Parameterized.Parameters
    public static Collection<Object[]> getCollection(){
        return Arrays.asList(new Object[][]{
                {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
                {"Lucas", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
                {"Lucas", "Anjos", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
                {"Lucas", "Anjos", "Masculino", Arrays.asList("Carne", "Vegetariano"),  new String[]{}, "Tem certeza que voce eh vegetariano?"},
                {"Lucas", "Anjos", "Masculino", Arrays.asList("Carne"),  new String[]{"Corrida", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
        });
    }

    @Test
    public void deveValidarRegras(){
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if(sexo.equals("Masculino")){ page.setSexoMasculino(); }
        if(sexo.equals("Feminino")){page.setSexoFeminino();}
        if(comidas.contains("Carne")) page.setComidaCarne();
        if(comidas.contains("Pizza")) page.setComidaFavoritaPizza();
        if(comidas.contains("Vegetariano")) page.setComidaVegetariano();
        for (String esporte : esportes) {
            page.setEsporte(esporte);
        }
        page.cadastrar();
        System.out.println(msg);
        Assert.assertEquals(msg, dsl.alertaObterTextoAlertaEAceita());
    }
}
