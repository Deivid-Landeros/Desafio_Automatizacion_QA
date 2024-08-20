package ips.webpages;

import static ips.utils.listeners.TestListener.registrarPaso;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ips.utils.JSONReadFromFile;
import ips.utils.logs.Log;

public class Index extends BasePage{
	
	// -- fc=File Chooser tf=Text Field cb=Combo Box rb=Radio Button
	
	@FindBy (xpath ="/html/body/header/div/div/div[1]/div/h1/a")
	WebElement tagInicio;
	
	@FindBy (xpath ="/html/body/header/div/div/div[2]/div/input")
	WebElement tfBusqueda;
	
	@FindBy (xpath ="/html/body/header/div/div/div[2]/div/span/button")
	WebElement btnBusqueda;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[1]/span")
	WebElement btnAddBusqueda;

	@FindBy (xpath ="/html/body/div[2]/div/div/div[3]/div/div/div[2]/div[1]/h4/a")
	WebElement linkBusqueda;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[3]")
	WebElement comparaBusqueda;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/h2")
	WebElement tagBusqueda;
	
	@FindBy (xpath ="/html/body/header/div/div/div[3]/div/button/span")
	WebElement btnIrCarrito;
	
	@FindBy (xpath ="/html/body/div[2]/div[1]")   
	WebElement tagSuccess;
	
	@FindBy (xpath ="/html/body/div[2]/div[1]/a[2]")   
	WebElement tagIrComparador;
	
	@FindBy (xpath ="/html/body/div[2]/div[1]/a[1]")
	WebElement tagProducto;
	
	@FindBy (xpath ="/html/body/div[2]/div[1]/a[1]")
	WebElement tagPrimerElemento;
	
	@FindBy (xpath ="/html/body/div[2]/div[1]/a[1]")
	WebElement tagSegundoElemento;
	
	@FindBy (xpath ="/html/body/header/div/div/div[3]/div/ul/li[2]/div/table/tbody/tr[1]/td[1]")
	WebElement tagSubTotal;
	
	@FindBy (xpath ="/html/body/header/div/div/div[3]/div/ul/li[1]/table/tbody/tr[1]/td[2]")
	WebElement tagCarritoPrimero;
	
	@FindBy (xpath ="/html/body/header/div/div/div[3]/div/ul/li[1]/table/tbody/tr[2]/td[2]")
	WebElement tagCarritoSegundo;
	
	@FindBy (xpath ="/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[2]")
	WebElement btnComprar;

//---------------Login
	@FindBy (xpath ="/html/body/nav/div/div[2]/ul/li[2]/a/span[1]")
	WebElement btnMiCuenta;
	
	@FindBy (xpath ="/html/body/nav/div/div[2]/ul/li[2]/ul/li[2]/a")
	WebElement btnLoginMenu;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div/h2")
	WebElement tagReturnCustomer;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div/form/div[1]/input")
	WebElement fcMailLogin;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div/form/div[2]/input")
	WebElement fcPasswordLogin;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div/form/input")
	WebElement btnLoginInicio;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/h2[1]")
	WebElement tagAccount;
	
	//-------------Comparador
	@FindBy (xpath ="/html/body/div[2]/div/div/table/tbody[3]/tr/td[2]/input")
	WebElement btnAgregarCarroComparador;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/h1")
	WebElement tagComparaProductos;
	

////////////////------------------------------------------------------------
	
	JSONReadFromFile utils;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public Index(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	public Index agregarProducto(String producto) throws InterruptedException {
		Log.info("Agregando " +producto);
		wait.until(ExpectedConditions.visibilityOf(tagInicio)); // espera hasta 10 segundos al encontrar el elemento		
		tfBusqueda.sendKeys(producto);		
		registrarPaso(obtenerHora()+" Ingreso de Texto "+producto);
		btnBusqueda.click();		
		
		try {
		wait.until(ExpectedConditions.visibilityOf(btnAddBusqueda));
		}catch (TimeoutException e){
		Log.info("Búsqueda no válida: El Articulo No Fue Encontrado");		
		}

		Actions actions = new Actions(driver); 
        actions.moveToElement(btnAddBusqueda).perform(); // Posicionamiento en Articulo 
        registrarPaso(obtenerHora() + " Articulo a agregar");
        btnAddBusqueda.click();
		Thread.sleep(1000); // tiempo para que muestre el mensaje
		wait.until(ExpectedConditions.visibilityOf(tagSuccess));
		
		String actualText = tagSuccess.getText();
		Assert.assertTrue(actualText.contains("Success"), "El articulo no fue agregado al carrito"); // valida que el articulo sea agregado
		actualText = tagProducto.getText();
		Assert.assertTrue(actualText.contains(producto), "El articulo no fue agregado al carrito"); // 2da validacion de articulo.
		
		actions.moveToElement(tagInicio).perform();   // reposiciona en el inicio
		registrarPaso(obtenerHora()+" Articulo agregado a carrito");
		tfBusqueda.clear();
		
	    return this;
	}
	
	
	public Index irCarritoDeCompras(String articulo1, String articulo2) {
		Log.info("Ingresando al carrito de compras");
		
		Actions actions = new Actions(driver); 
		actions.moveToElement(btnIrCarrito).perform(); //Posicionamiento a el carrito
		btnIrCarrito.click();
		registrarPaso(obtenerHora()+" Mostrando Carrito de Compras");
		
		wait.until(ExpectedConditions.visibilityOf(tagSubTotal));
		
		String actualText = tagCarritoPrimero.getText();
		if (actualText.contains(articulo1)) {
			Log.info( articulo1+" se encuentra en el carrito");			
		}
		
		actualText = tagCarritoSegundo.getText();
		if (actualText.contains(articulo2)) {
			Log.info( articulo2 +" se encuentra en el carrito");			
		}
		
		btnComprar.click();
		return this;
	}
	
	
	public Index loginUsuario(String Usuario, String Pass) throws InterruptedException {
		Log.info("Iniciando Login");
		wait.until(ExpectedConditions.visibilityOf(btnMiCuenta));
		btnMiCuenta.click();
		wait.until(ExpectedConditions.visibilityOf(btnLoginMenu));
		registrarPaso(obtenerHora()+" Entrando a Menu para realizar Login");
		btnLoginMenu.click();
		
		wait.until(ExpectedConditions.visibilityOf(tagReturnCustomer));
		Log.info("Pagina Login del Sistema");
		registrarPaso(obtenerHora()+" Pagina Login del Sistema");
		
		fcMailLogin.sendKeys(Usuario);		
		fcPasswordLogin.sendKeys(Pass);
		btnLoginInicio.click();
		wait.until(ExpectedConditions.visibilityOf(tagAccount));
		registrarPaso(obtenerHora()+" Ingreso a la Cuenta");

		return this;
	}
	
	
	public Index mostrarProducto(String producto) throws InterruptedException {
		Log.info("mostrando " +producto);
		wait.until(ExpectedConditions.visibilityOf(tagInicio)); // espera hasta 10 segundos al encontrar el elemento		
		tfBusqueda.sendKeys(producto);		
		registrarPaso(obtenerHora()+" Ingreso de Texto "+producto);
		btnBusqueda.click();		
		
		try {
		wait.until(ExpectedConditions.visibilityOf(linkBusqueda));
		}catch (TimeoutException e){
		Log.info("Búsqueda no válida: El Articulo No Fue Encontrado");		
		}

		Actions actions = new Actions(driver); 
        actions.moveToElement(linkBusqueda).perform(); // Posicionamiento en Articulo 
        registrarPaso(obtenerHora() + " Articulo a Mostrar");
        linkBusqueda.click();
        tfBusqueda.clear();
        
	    return this;
	}

	public Index ComparaProducto(String producto) throws InterruptedException {
		Log.info("mostrando " +producto);
		wait.until(ExpectedConditions.visibilityOf(tagInicio)); // espera hasta 10 segundos al encontrar el elemento		
		tfBusqueda.sendKeys(producto);		
		registrarPaso(obtenerHora()+" Ingreso de Texto "+producto);
		btnBusqueda.click();		
		
		try {
		wait.until(ExpectedConditions.visibilityOf(comparaBusqueda));
		}catch (TimeoutException e){
		Log.info("Búsqueda no válida: El Articulo No Fue Encontrado");		
		}

		Actions actions = new Actions(driver); 
        actions.moveToElement(linkBusqueda).perform(); // Posicionamiento en Articulo 
        registrarPaso(obtenerHora() + " Articulo a comprar Agregado");
        comparaBusqueda.click();
        tfBusqueda.clear();
        
	    return this;
	}
	
	public Index IrComparador() throws InterruptedException {
		Log.info("Entrando a Comparador de Productos");
		Thread.sleep(1000); // tiempo para que muestre el mensaje
		wait.until(ExpectedConditions.visibilityOf(tagSuccess));
		tagIrComparador.click();
		
		wait.until(ExpectedConditions.visibilityOf(tagComparaProductos));
		Actions actions = new Actions(driver); 
        actions.moveToElement(btnAgregarCarroComparador).perform(); // Posicionamiento de la comparación
        Log.info("Mostrando Productos");
        registrarPaso(obtenerHora()+" Mostrando Comparacion de Productos");
 		
		return this;
	}
	
	
	
}
