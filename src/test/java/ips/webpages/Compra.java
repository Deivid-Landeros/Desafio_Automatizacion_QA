package ips.webpages;

import static ips.utils.listeners.TestListener.registrarPaso;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

public class Compra extends BasePage{
	
	// -- fc=File Chooser tf=Text Field cb=Combo Box rb=Radio Button
	
//---- HP
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/ul[2]/li[2]")
	WebElement btnEspecificaciones;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[2]/table/tbody[1]/tr/td[2]")
	WebElement tagMemory;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div[1]/div/input")
	WebElement calDelivery;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/input[1]")
	WebElement tfCantidad;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/button")
	WebElement btnAgregarCarro;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[3]/form/div[2]/div/input")
	WebElement tfNombreReview;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[3]/form/div[3]/div/textarea")
	WebElement tfReview;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[3]/form/div[4]/div/textarea")
	WebElement tfReview2;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[2]/div[3]/p/a[2]")
	WebElement btnEscribeReview;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[3]/form/div[4]/div/input[3]")
	WebElement rbRanking3;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[3]/form/div[2]") 
	WebElement tagReviewMensaje;												   
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[3]/form/div[5]/div/button")				
	WebElement btnContinueReview;
	
	@FindBy (xpath ="/html/body/div[2]/div/div/div/div[1]/div/div[3]/form/div[6]/div/button")				
	WebElement btnContinueReview2;
	

////////////////------------------------------------------------------------
	
	JSONReadFromFile utils;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public Compra(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	public Compra completarDatosProductoHP(String Nprueba) throws InterruptedException {
		String producto = JSONReadFromFile.readJson(Nprueba,"BusquedaHP");
		Log.info("Agregando " +producto);
		wait.until(ExpectedConditions.visibilityOf(btnAgregarCarro)); // espera hasta 10 segundos al encontrar el elemento				
		registrarPaso(obtenerHora()+" Vista de producto:  "+producto);
		
		//delivery el dia de mañana
		LocalDate maniana = LocalDate.now().plusDays(1); //calcula dia de mañana
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //formato dia mañana
		String fechaFormateada = maniana.format(formato);
		calDelivery.clear();
		calDelivery.sendKeys(fechaFormateada);
		
		//cantidad de equipos
		String cantidad = JSONReadFromFile.readJson(Nprueba,"CantidadBusquedaHP");
		tfCantidad.clear();
		tfCantidad.sendKeys(cantidad);
		
		//valida especificacion Ram
		btnEspecificaciones.click();
		wait.until(ExpectedConditions.visibilityOf(tagMemory));
		String Ram = JSONReadFromFile.readJson(Nprueba,"CantidadRam");
		String actualText = tagMemory.getText();
		if (actualText.equals(Ram)) {
			Log.info("El valor de la Ram es correcto: "+Ram);
		}else {
		    Log.info("El valor de la Ram No es correcto: "+Ram);
		}		
		registrarPaso(obtenerHora()+" Detalles del producto");		
		
	    return this;
	}
	
	public Compra reviewTresEstrellas(String Nprueba) throws InterruptedException {
		Log.info("Iniciando Review");
		wait.until(ExpectedConditions.visibilityOf(btnEscribeReview));
		btnEscribeReview.click();
		wait.until(ExpectedConditions.visibilityOf(btnContinueReview));
		
		Actions actions = new Actions(driver); 
		actions.moveToElement(btnContinueReview).perform(); // muestra el review
		registrarPaso(obtenerHora()+" Mostrando el review");
		
		String nombre = JSONReadFromFile.readJson("CP001","Nombre");
		String reviewInvalido = JSONReadFromFile.readJson(Nprueba,"EscribeReviewMalo");
		String reviewValido = JSONReadFromFile.readJson(Nprueba,"EscribeReviewBueno");
		
		tfNombreReview.sendKeys(nombre);
		tfReview.sendKeys(reviewInvalido);
		rbRanking3.click();
		registrarPaso(obtenerHora()+" Review a enviar");
		btnContinueReview.click();
		Thread.sleep(2000);
		String tagInfo = tagReviewMensaje.getText();
		

		if (tagInfo.contains("Warning")) {
			Log.info("Review Incorrecta");
			registrarPaso(obtenerHora()+" la review no es correcta");
			tfReview2.clear();
			tfReview2.sendKeys(reviewValido);
			btnContinueReview2.click();
			Thread.sleep(3000);
			tagInfo = tagReviewMensaje.getText();
		}
		
		if (tagInfo.contains("Thank you for your review")) {
			Log.info("Carga de Review Correcta");
			registrarPaso(obtenerHora()+" la review es correcta");
		}
		
		return this;
	}
	
	public Compra agregarACarro() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(btnAgregarCarro));
		Log.info("Agrega Producto al Carro");
		registrarPaso(obtenerHora()+" Agrega Producto al carro");
		btnAgregarCarro.click();
		
		return this;
		
	}


	
	
	
	
}
