package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import paginas.ListaDeProdutosPage;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;
    @BeforeEach
    public void beforeEach(){
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromeDriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        this.navegador = new ChromeDriver(options);
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }
    @Test
    @DisplayName("Nao e permitido registrar um produto igual a zero")
    public void testNaoEPermitidoResgistrarProdutoComValorIgualAZero(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("Vermelho,Azul,Branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresetada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor maior que 7.000,00")
    public void testNaoEPermitidoResgistrarProdutoComvalorAciomadeseteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto,azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresetada();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de 0,01 a 7.000,00")
    public void testPossoAdicionarProdutosComValorDeUmCentavoASeteMilReais(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macnook Pro")
                .informarValorDoProduto("30000")
                .informarCoresDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresetada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produto que estejam no limite de 7.000,00")
    public void testPossoAdicionarProdutoQueEstejamNoLimiteDeSeteMilReais(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Teclado")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("azul")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresetada();
        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Fazer logoff do sistema")
    public void testFazerLogoffDoSistema(){
        new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .botaoDeLogoff();
    }

    @Test
    @DisplayName("Editar um produto com dados valido")
    public void testEditarUmProdutoComDadosValdidos(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("TesteEditarProduto")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("azul")
                .submeterFormularioDeAdicaoComSucesso()
                .voltarPagaAPaginaListaDeProdutos()
                .clicarNoProdutoParaEditar()
                .informarAlteracaoDoNomeDoProduto("TesteFoiEditado")
                .informarAlteracaoDoValorDoProduto("1000")
                .informarAlteracaoDasCoresDoProduto("Vermelho, azul")
                .submeterFormularioDeEdicaoComSucesso()
                .capturarMensagemApresetada();

       Assertions.assertEquals("Produto alterado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Editar um produto com dados invalidos")
    public void testEditarUmProdutoComDadosInvalidos(){
        String mensagemApresentada;
        mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("TesteEditarProduto")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("azul")
                .submeterFormularioDeAdicaoComSucesso()
                .voltarPagaAPaginaListaDeProdutos()
                .clicarNoProdutoParaEditar()
                .informarAlteracaoDoNomeDoProduto("TesteFoiEditado")
                .informarAlteracaoDoValorDoProduto("0")
                .informarAlteracaoDasCoresDoProduto("Vermelho, azul")
                .submeterFormularioDeEdicaoComErro()
                .capturarMensagemApresetada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @AfterEach
    public void afterEach(){
        navegador.close();
    }
}