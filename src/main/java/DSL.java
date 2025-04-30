import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DSL {
    private final WebDriver driver;

    public DSL(WebDriver driver){
        super();
        this.driver = driver;
    }

    public void escreve(String id_campo, String texto){
        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obterValorCampo(String id_campo){
        //Assert.assertEquals("Lucas", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }

    public void clicarRadio(String id){
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String id){
        return driver.findElement(By.id(id)).isSelected();
    }

    public void clicarCheckbox(String id){
        driver.findElement(By.id(id)).click();
    }

    public void selecionarCombo(String id, String valor){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String alertaObterTextoAlertaEAceita() {
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        alert.accept();
        return texto;
    }

    public List<WebElement> obterOpcoesSelecionadas(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getAllSelectedOptions();
    }

    public void clicarBotao(String id){
        driver.findElement(By.id(id)).click();
    }

    public void clicarLink(String link){
        driver.findElement(By.linkText(link)).click();
    }

    public String obterTexto(By by){
        return driver.findElement(by).getText();
    }

    public String obterTexto(String id){
        return obterTexto(By.id(id));
    }

}
