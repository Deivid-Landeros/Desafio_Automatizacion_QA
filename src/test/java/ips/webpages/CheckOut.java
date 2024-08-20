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

public class CheckOut extends BasePage{
	
	// -- fc=File Chooser tf=Text Field cb=Combo Box rb=Radio Button	
	
	@FindBy (xpath ="/html/body/div[2]/div/div/h1")
	WebElement tagCheckout;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div[2]/div/div/div[2]/div[1]/input")
	WebElement tfEmailLogin;

	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div[2]/div/div/div[2]/input")
	WebElement btnLogin;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div[2]/div/div/div[1]/div[1]/label")
	WebElement rbRegister;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div[2]/div/div/div[1]/div[2]/label")
	WebElement rbGuest;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div[2]/div/div/div[1]/input")
	WebElement btnContinueNewCostumer;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/fieldset[1]/legend") //Your Personal Details
	WebElement tagPersonaldetails;
	
	//------------Formulario
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/fieldset[1]/div[2]/input") 
	WebElement tfNombre; 
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/fieldset[1]/div[3]/input") 
	WebElement tfApellido; 
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/fieldset[1]/div[4]/input") 
	WebElement tfEmailForm; 
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/fieldset[1]/div[5]/input") 
	WebElement tfTelefono;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/fieldset/div[2]/input") 
	WebElement tfDireccion;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/fieldset/div[4]/input") 
	WebElement tfCiudad;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/fieldset/div[5]/input") 
	WebElement tfCodigoPostal;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/fieldset/div[6]/select") 
	WebElement cbPais;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/fieldset/div[7]/select") 
	WebElement cbRegion;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/fieldset[2]/div[1]/input") 
	WebElement tfContrasenia;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[1]/fieldset[2]/div[2]/input") 
	WebElement tfConfirmaContrasenia;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[4]/div/input[1]") 
	WebElement chkPrivacidad;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[4]/div/input[2]") 
	WebElement btnContinuaForm;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[1]")  
	WebElement tagEmailRep;
	
// ------------- detalles de facturacion
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div/form/div[5]/div/input")
	WebElement btnContinueFactura;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div/form/div[1]/label") 
	WebElement rbUsarDireccionActualFactura;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div/form/div[3]/label")  
	WebElement rbUsarDireccionDiferenteFactura;
	
//------------- detalles de delivery
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[3]/div[2]/div/form/div[5]/div/input")
	WebElement btnContinueDelivery;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[3]/div[2]/div/form/div[1]/label") 
	WebElement rbUsarDireccionActualDelivery;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[3]/div[2]/div/form/div[3]/label")  
	WebElement rbUsarDireccionDiferenteDelivery;
	
//--------------------- Metodo de delivery (validacion de costo de delivery y comentarios)
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[4]/div[2]/div/div[1]/label")  
	WebElement rbCostoEnvio;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[4]/div[2]/div/p[4]/textarea")  
	WebElement tfComentariosDelivery;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[4]/div[2]/div/div[2]/div/input")  
	WebElement btnContinueMethod;	
	
// ----------------------- Tipo de Pago
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[1]/label")  
	WebElement rbPagoTransferencia;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[2]/label")  
	WebElement rbEfectivo;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[5]/div[2]/div/p[3]/textarea")  
	WebElement tfComentariosPago;
		
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[3]/div/input[2]")  
	WebElement btnContinuePago;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[3]/div/input[1]")  
	WebElement chkCondiciones;
	
//----------------------- Confirma La orden
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div[6]/div[2]/div/div[2]/div/input")
	WebElement btnConfirmarOrden;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/h1")  
	WebElement tagConfirmaOrden;
	
	@FindBy (xpath = "/html/body/div[2]/div/div/div/div/a")  
	WebElement btnContinueTerminoOrden;
	
	
		

////////////////------------------------------------------------------------
	
	JSONReadFromFile utils;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public CheckOut(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	public CheckOut crearUsuarioCheckoutOptions() {
		Log.info("Creando Nuevo usuario");
		wait.until(ExpectedConditions.visibilityOf(tagCheckout));
		registrarPaso(obtenerHora()+" Ingreso de a CheckOut");
		
		rbRegister.click();
		btnContinueNewCostumer.click();
		return this;
	}
	
	public CheckOut crearUsuarioAcountDetails(String NPrueba) throws InterruptedException {
		Log.info("Ingreso Datos de Cuenta Nueva");
		wait.until(ExpectedConditions.visibilityOf(tagPersonaldetails));
		Actions actions = new Actions(driver); 
		actions.moveToElement(btnContinuaForm).perform(); // Posicionamiento de página 
		registrarPaso(obtenerHora()+" Ingreso a modulo de Datos");
		
		// Extrae datos del Json
		
		 String  Nombre, Apellido, Email, Telefono, Direccion, Ciudad, CodigoPostal, Pais, Region, Contrasenia, ConfirmaContrasenia;
		 
		 Nombre=JSONReadFromFile.readJson(NPrueba,"Nombre");
		 Apellido=JSONReadFromFile.readJson(NPrueba,"Apellido");
		 Email=JSONReadFromFile.readJson(NPrueba,"Email");
		 Telefono=JSONReadFromFile.readJson(NPrueba,"Telefono");
		 Direccion=JSONReadFromFile.readJson(NPrueba,"Direccion");
		 Ciudad=JSONReadFromFile.readJson(NPrueba,"Ciudad");
		 CodigoPostal=JSONReadFromFile.readJson(NPrueba,"CodigoPostal");
		 Pais=JSONReadFromFile.readJson(NPrueba,"Pais");
		 Region=JSONReadFromFile.readJson(NPrueba,"Region");
		 Contrasenia=JSONReadFromFile.readJson(NPrueba,"Contrasenia");
		 ConfirmaContrasenia=JSONReadFromFile.readJson(NPrueba,"ConfirmaContrasenia");
		 
		// Llena Formulario
		 
		 tfNombre.sendKeys(Nombre);
		 tfApellido.sendKeys(Apellido);
		 tfEmailForm.sendKeys(Email);
		 tfTelefono.sendKeys(Telefono);
		 tfDireccion.sendKeys(Direccion);
		 tfCiudad.sendKeys(Ciudad);
		 tfCodigoPostal.sendKeys(CodigoPostal);
		 Select paisDropdown = new Select(cbPais);
		 paisDropdown.selectByVisibleText(Pais);     //selecciona en el Combo Box el pais
		 Thread.sleep(3000);
		 
		 Select regionDropdown = new Select(cbRegion); // selecciona en el Combo Box la Region
		 regionDropdown.selectByVisibleText(Region);
		 tfContrasenia.sendKeys(Contrasenia);
		 tfConfirmaContrasenia.sendKeys(ConfirmaContrasenia);
		 chkPrivacidad.click();
		 
		 registrarPaso(obtenerHora()+" Llenado de Formulario");
		 btnContinuaForm.click();
		 Thread.sleep(2000);
	
		 try {
		    if (tagEmailRep.isDisplayed()) {
		       String actualText = tagEmailRep.getText();
		       Assert.assertFalse(actualText.contains("Warning: E-Mail Address is already registered"), "El mail está siendo usado actualmente, intentar con otro");
		        }
		    } catch (NoSuchElementException e) {
		    	Log.info("Datos Correctamente Validados");
		    }
		
		return this;
	}
		
		public CheckOut crearUsuarioUsaDireccionActualFacturacion() {
			Log.info("Aceptando la Dirreccion Actual de Facturacion");
			wait.until(ExpectedConditions.visibilityOf(btnContinueFactura));
			registrarPaso(obtenerHora()+" Aceptando la Dirreccion Actual de Facturacion");
			
			rbUsarDireccionActualFactura.click();
			btnContinueFactura.click();
			return this;
		}
		
		public CheckOut crearUsuarioUsaDireccionActualDelivery() {
			Log.info("Aceptando la Dirreccion Actual de Delivery");
			wait.until(ExpectedConditions.visibilityOf(btnContinueDelivery));
			registrarPaso(obtenerHora()+" Aceptando la Dirreccion Actual de Delivery");
			
			rbUsarDireccionActualDelivery.click();
			btnContinueDelivery.click();
			return this;
		}
		
		public CheckOut validaCostoDespacho() {
			Log.info("Validando Costo de Despacho");
			wait.until(ExpectedConditions.visibilityOf(rbCostoEnvio));
			registrarPaso(obtenerHora()+" Costo de Despacho");
			
			String actualText = rbCostoEnvio.getText();
		    Assert.assertTrue(actualText.contains("Flat Shipping Rate - $5.00"), "El Costo de despacho es distinto a: Flat Shipping Rate - $5.00 ");
		    Log.info("Costo de despacho Valido");
			
		    rbCostoEnvio.click();
		    btnContinueMethod.click();
			return this;
		}
		
		public CheckOut elegirTipoPago(String TipoPago) {
			Log.info("Elegir Tipo de Transferencia");
			wait.until(ExpectedConditions.visibilityOf(btnContinuePago));
			
			if (TipoPago.equals("Transferencia")) {
			    rbPagoTransferencia.click();
			} else if (TipoPago.equals("Efectivo")) {
			    rbEfectivo.click();
			} else {
			    // Si TipoPago es distinto de "Efectivo" o "Transferencia"
			    Log.info("El valor de pago: "+TipoPago+"  especificado es inválido, se continua con Transferencia");
			}
			
			registrarPaso(obtenerHora()+" Aceptando Tipo de Pago:  "+TipoPago);
			chkCondiciones.click();
			btnContinuePago.click();
			return this;
		}
	
		public CheckOut confirmarOrden() {
			Log.info("Inicio de Confirmación de Orden");
			wait.until(ExpectedConditions.visibilityOf(btnConfirmarOrden));
			registrarPaso(obtenerHora()+" Información sobre Orden");
			
			btnConfirmarOrden.click();
			wait.until(ExpectedConditions.visibilityOf(btnContinueTerminoOrden));
			String actualText =tagConfirmaOrden.getText();
			if (actualText.contains("Your order has been placed")) {
				Log.info("La orden se procesó Correctamente");

			}else {
			    Log.info("La orden no se procesó correctamente");
			}
			
			registrarPaso(obtenerHora()+" Estatus Final de la Orden");
			btnContinueTerminoOrden.click();
			return this;
		}
	
		
	
}
