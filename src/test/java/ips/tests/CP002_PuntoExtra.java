package ips.tests;

import java.lang.reflect.Method;
import org.testng.annotations.Test;

import ips.utils.JSONReadFromFile;

import static ips.utils.reports.ExtentTestManager.startTest;


public class CP002_PuntoExtra extends BaseTest{

	
	@Test(description="Flujo Punto Extra HP")
	public void CP002(Method method) throws InterruptedException {
		
		startTest(method.getName(), "CP002_Punto Extra HP");
		
		//Se utilizan los usuarios creados en la Prueba Anterior CP001 para seguir un Flujo
		String usuario = JSONReadFromFile.readJson("CP001","Email"); 
		String pass = JSONReadFromFile.readJson("CP001","Contrasenia");
		
		String productoHP = JSONReadFromFile.readJson("CP002","BusquedaHP");
        					
		homePage.goToIndex().loginUsuario(usuario, pass).mostrarProducto(productoHP);
		homePage.goToCompra().completarDatosProductoHP("CP002").reviewTresEstrellas("CP002").agregarACarro();

	}
}
