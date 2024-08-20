package ips.tests;

import java.lang.reflect.Method;
import org.testng.annotations.Test;

import ips.utils.JSONReadFromFile;

import static ips.utils.reports.ExtentTestManager.startTest;


public class CP003_PuntoExtra2 extends BaseTest{

	
	@Test(description="Punto Extra Apple VS Samsung")
	public void CP003(Method method) throws InterruptedException {
		
		startTest(method.getName(), "CP003_Punto Extra Apple VS Samsung");
		
		String apple = JSONReadFromFile.readJson("CP003","Busqueda1"); 
		String samsung = JSONReadFromFile.readJson("CP003","Busqueda2");

		homePage.goToIndex().ComparaProducto(apple).ComparaProducto(samsung).IrComparador();
	}

}
