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

public class Historial extends BasePage{
	
	// -- fc=File Chooser tf=Text Field cb=Combo Box rb=Radio Button	
	
	@FindBy (xpath ="/html/body/nav/div/div[2]/ul/li[2]/a/span[1]")
	WebElement btnMiCuenta;
	
	@FindBy (xpath ="/html/body/nav/div/div[2]/ul/li[2]/ul/li[2]/a")
	WebElement btnHistorial;

	@FindBy (xpath ="/html/body/header/div/div/div[1]/div/h1")
	WebElement tagStore;
	
//-----------Cuenta
	
	@FindBy (xpath ="/html/body/div[2]/div/div/h1")
	WebElement tagCuenta;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[2]")
	WebElement tagNombreCuenta;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div[1]/table/tbody/tr/td[4]")
	WebElement tagEstado;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div[1]/table/tbody/tr[1]/td[7]/a")
	WebElement btnVer;
	
// -------- Otra Información
	
	@FindBy (xpath ="/html/body/div[2]/div/div/table[2]/tbody/tr/td[1]")
	WebElement tfDatosPago;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/table[2]/tbody/tr/td[1]")
	WebElement tfDatosTraslado;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/table[1]/thead/tr/td")
	WebElement tagDetalles;

//---------LogOUT
	@FindBy (xpath ="/html/body/nav/div/div[2]/ul/li[2]/ul/li[5]/a")
	WebElement btnLogOut;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/h1")
	WebElement tagLogOut;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div/a")
	WebElement btnContinueLogOut;
	

////////////////------------------------------------------------------------
	
	JSONReadFromFile utils;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public Historial(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	public Historial ingresoHistorial() {
		Log.info("Ingresando a Historial");
		wait.until(ExpectedConditions.visibilityOf(tagStore));
		wait.until(ExpectedConditions.visibilityOf(btnMiCuenta));
		btnMiCuenta.click();
		
		wait.until(ExpectedConditions.visibilityOf(btnHistorial));
		registrarPaso(obtenerHora()+" Entrando a Menu Order History");
		btnHistorial.click();

		return this;
	}
	
	public Historial validarPending() {
		Log.info("Mostrando Historial");
		wait.until(ExpectedConditions.visibilityOf(tagCuenta));
		registrarPaso(obtenerHora()+" Historial de la cuenta");
		
		 try {
			       Assert.assertTrue(tagNombreCuenta.isDisplayed(), "No hay datos en la cuenta ");
			   
			    } catch (NoSuchElementException e) {
			    	Log.info("El elemento 'tagNombreCuenta' no existe, por lo tanto, no hay datos en la cuenta.");
			 }
		 
		 String actualText = tagEstado.getText();
		 Assert.assertTrue(actualText.contains("Pending"),"El status del producto se encuentra en: "+actualText);
		 Log.info("Validación estado Pending del producto");	 

		return this;
	}
	
	public Historial ingresarOtraInformacion() {
		Log.info("Ingresando a Otra Informacion");
		btnVer.click();	
		wait.until(ExpectedConditions.visibilityOf(tagDetalles));
		registrarPaso(obtenerHora()+" Entrando a Menu Order History");

		return this;
	}
	
	public Historial validarDatosPago(String NPrueba) throws InterruptedException {
		Log.info("Validar Datos de direccion de pago vs los creados en Cuenta");
		wait.until(ExpectedConditions.visibilityOf(tagDetalles));
		
		// Extrae datos del Json
		
		 String  Nombre, Apellido, Direccion, Ciudad, CodigoPostal, Pais, Region;
		 
		 Nombre=JSONReadFromFile.readJson(NPrueba,"Nombre");
		 Apellido=JSONReadFromFile.readJson(NPrueba,"Apellido");
		 Direccion=JSONReadFromFile.readJson(NPrueba,"Direccion");
		 Ciudad=JSONReadFromFile.readJson(NPrueba,"Ciudad");
		 CodigoPostal=JSONReadFromFile.readJson(NPrueba,"CodigoPostal");
		 Pais=JSONReadFromFile.readJson(NPrueba,"Pais");
		 Region=JSONReadFromFile.readJson(NPrueba,"Region");

		 
		// Consulta sobre datos
		 
		 String actualText = tfDatosPago.getText();
		 Assert.assertTrue(actualText.contains(Nombre), "No se encontró " + Nombre + " en la dirección de pago");
		 Assert.assertTrue(actualText.contains(Apellido), "No se encontró " + Apellido + " en la dirección de pago");
		 Assert.assertTrue(actualText.contains(Direccion), "No se encontró " + Direccion + " en la dirección de pago");
		 Assert.assertTrue(actualText.contains(Ciudad), "No se encontró " + Ciudad + " en la dirección de pago");
		 Assert.assertTrue(actualText.contains(CodigoPostal), "No se encontró " + CodigoPostal + " en la dirección de pago");
		 Assert.assertTrue(actualText.contains(Pais), "No se encontró " + Pais + " en la dirección de pago");
		 Assert.assertTrue(actualText.contains(Region), "No se encontró " + Region + " en la dirección de pago");
		 
		 Log.info("Los datos de direccion de direccion de pago son los mismos que los ingresados al crear la cuenta");
		 
		return this;
	}
	
	public Historial logOut() throws InterruptedException {
		Log.info("Iniciando Logout");
		wait.until(ExpectedConditions.visibilityOf(btnMiCuenta));
		btnMiCuenta.click();
		wait.until(ExpectedConditions.visibilityOf(btnHistorial));
		registrarPaso(obtenerHora()+" Entrando a Menu para realizar LogOut");
		btnLogOut.click();
		
		wait.until(ExpectedConditions.visibilityOf(tagLogOut));
		Log.info("Logout del Sistema");
		registrarPaso(obtenerHora()+" Logout del Sistema");
		
		btnContinueLogOut.click();
		Thread.sleep(2000);
		registrarPaso(obtenerHora()+" Fin de Ciclo");

		return this;
	}
	
		
	
}
