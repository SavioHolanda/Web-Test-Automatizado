package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    private WebDriver navegador;

    public ListaDeProdutosPage(WebDriver navagador){
        this.navegador = navagador;
    }

    public FormularioDeAdicaoDeProdutoPage acessarFormularioAdicaoNovoProduto(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        return new FormularioDeAdicaoDeProdutoPage(navegador);
    }

    public String capturarMensagemApresetada(){
        return navegador.findElement((By.cssSelector(".toast.rounded"))).getText();
    }
}
