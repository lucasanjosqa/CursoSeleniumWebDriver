import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteExercicioCadastro {

    @Test
    public void ExercicioCadastroCompleto(){
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

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


    }
}
