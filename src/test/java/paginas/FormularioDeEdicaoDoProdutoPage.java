package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FormularioDeEdicaoDoProdutoPage {
    private WebDriver navegador;
    public FormularioDeEdicaoDoProdutoPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public String capturarMensagemApresetada(){
        return navegador.findElement((By.cssSelector(".toast.rounded"))).getText();
    }

    public ListaDeProdutosPage voltarPagaAPaginaListaDeProdutos(){
        navegador.findElement(By.linkText("LISTA DE PRODUTOS")).click();
        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeEdicaoDoProdutoPage informarAlteracaoDoNomeDoProduto(String produtoNome){
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        navegador.findElement(By.id("produtonome")).sendKeys(selectAll);
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }

    public FormularioDeEdicaoDoProdutoPage informarAlteracaoDoValorDoProduto(String produtoValor){
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        navegador.findElement(By.name("produtovalor")).sendKeys(selectAll);
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);

        return this;
    }

    public FormularioDeEdicaoDoProdutoPage informarAlteracaoDasCoresDoProduto(String produtoCores){
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        navegador.findElement(By.id("produtocores")).sendKeys(selectAll);
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeEdicaoComErro(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeEdicaoDoProdutoPage submeterFormularioDeEdicaoComSucesso(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return this;
    }
}
