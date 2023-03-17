package modulos.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.log.Log;
import paginas.LoginPage;

import java.time.Duration;

public class LoginTest {
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
    @DisplayName("Digitar usuario e senha invalidos")
    public void testDigitarUsuarioESenhaInvalidos (){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("a")
                .submeterfomularioDeLoginInvalido()
                .capturarMensagemDeInconsistencia();

        Assertions.assertEquals("Falha ao fazer o login", mensagemApresentada);
    }

    @Test
    @DisplayName("Digitar apenas o usuário")
    public void testDigitarApenasOUsuario(){
       String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("admin")
                .submeterfomularioDeLoginInvalido()
                .capturarMensagemDeInconsistencia();

        Assertions.assertEquals("Falha ao fazer o login", mensagemApresentada);
    }

    @Test
    @DisplayName("Digitar login e senha validos")
    public void testDigitarLoginESenhaValidos(){
         new LoginPage(navegador)
                 .informarOUsuario("admin")
                 .informarASenha("admin")
                 .submeterFormularioDeLogin()
                 .validarLoginComSucesso();
    }

    @Test
    @DisplayName("Digitar usuário e senha em branco")
    public void testDigitarUsuarioESenhaEmBranco(){
        String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario(" ")
                .informarASenha(" ")
                .submeterfomularioDeLoginInvalido()
                .capturarMensagemDeInconsistencia();

        Assertions.assertEquals("Falha ao fazer o login", mensagemApresentada);
    }

    @AfterEach
    public void afterEach(){
        navegador.close();
    }
}
