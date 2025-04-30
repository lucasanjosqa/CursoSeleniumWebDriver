import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
    private final DSL dsl;

    public CampoTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }

    public void setNome(String nome){
        dsl.escreve("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escreve("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino(){
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setSexoFeminino(){
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    public void setComidaFavoritaPizza(){
        dsl.clicarCheckbox("elementosForm:comidaFavorita:2");
    }

    public void setComidaCarne(){
        dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
    }

    public void setComidaVegetariano(){
        dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor){
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }

    public void setEsporte(String valor){
        dsl.selecionarCombo("elementosForm:esportes", valor);
    }

    public void cadastrar(){
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro(){
        return dsl.obterTexto("resultado");
    }

    public String obterNomeCadastro(){
        return (dsl.obterTexto(By.id("descNome")));
    }

    public String obterSobrenomeCadastro(){
        return (dsl.obterTexto(By.id("descSobrenome")));
    }

    public String obterSexoCadastro(){
        return (dsl.obterTexto(By.id("descSexo")));
    }

    public String obterComidaFavoritaCadastro(){
        return (dsl.obterTexto(By.id("descComida")));
    }

    public String obterEscolaridade(){
        return (dsl.obterTexto(By.id("descEscolaridade")));
    }

    public String obterEsporte(){
        return (dsl.obterTexto(By.id("descEsportes")));
    }
}
