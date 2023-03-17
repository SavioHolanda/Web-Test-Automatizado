package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    private WebDriver navegador;

    public ListaDeProdutosPage(WebDriver navagador){
        this.navegador = navagador;
    }

    public String validarLoginComSucesso(){
        return navegador.findElement(By.linkText("Boas vindas, admin!")).getText();
    }

    public FormularioDeAdicaoDeProdutoPage acessarFormularioAdicaoNovoProduto(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        return new FormularioDeAdicaoDeProdutoPage(navegador);
    }

    public String capturarMensagemApresetada(){
        return navegador.findElement((By.cssSelector(".toast.rounded"))).getText();
    }

    public LoginPage botaoDeLogoff(){
        navegador.findElement(By.linkText("Sair")).click();

        return new LoginPage(navegador);
    }

    public FormularioDeEdicaoDoProdutoPage clicarNoProdutoParaEditar(){
        navegador.findElement(By.linkText("TesteEditarProduto")).click();
        return new FormularioDeEdicaoDoProdutoPage(navegador);
    }
}
