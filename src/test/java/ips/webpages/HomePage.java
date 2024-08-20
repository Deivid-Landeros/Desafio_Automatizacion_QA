package ips.webpages;

import static ips.utils.listeners.TestListener.registrarPaso;

import org.openqa.selenium.WebDriver;

import ips.utils.JSONReadFromFile;
import ips.utils.logs.Log;

public class HomePage extends BasePage{
	
	/**
     * Constructor
     */
    public HomePage(WebDriver driver) {
    	super(driver);
    }

    /**
     * Variables
     */


    
   //Go to Pagina Index
    public Index goToIndex() {
    Log.info("Entrando a la web Index..");
    driver.get(JSONReadFromFile.readJson("url"));
    return new Index(driver);
    }
    
    //Go to Checkout
    public CheckOut goToCheckOut() {
        Log.info("Entrando a la web Check Out..");
        return new CheckOut(driver);
    }
    
    //Go to Historial
    public Historial goToHistorial() {
        Log.info("Entrando a la web Historial..");
        return new Historial(driver);
    }
    
    //Go to Compra
    public Compra goToCompra() {
        Log.info("Entrando a la web Compra..");
        return new Compra(driver);
    }
}
