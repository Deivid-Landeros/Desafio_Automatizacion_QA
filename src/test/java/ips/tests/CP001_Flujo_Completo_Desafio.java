package ips.tests;

import java.lang.reflect.Method;
import org.testng.annotations.Test;

import ips.utils.JSONReadFromFile;

import static ips.utils.reports.ExtentTestManager.startTest;


public class CP001_Flujo_Completo_Desafio extends BaseTest{

	
	@Test(description="Flujo Completo Desafio")
	public void CP001(Method method) throws InterruptedException {
		
		startTest(method.getName(), "CP001_Flujo_Completo_Desafio.");
		
		String busquedaImac = JSONReadFromFile.readJson("CP001","Busqueda1"); 
		String busquedaIpod = JSONReadFromFile.readJson("CP001","Busqueda2"); ;
		String tipoPago = JSONReadFromFile.readJson("CP001","TipoPago");
		
		homePage.goToIndex().agregarProducto(busquedaImac).agregarProducto(busquedaIpod).
		irCarritoDeCompras(busquedaImac,busquedaIpod); //agrega Imac y Ipod redirecciona al carro de compras
		
		homePage.goToCheckOut().crearUsuarioCheckoutOptions().crearUsuarioAcountDetails("CP001").
		crearUsuarioUsaDireccionActualDelivery().validaCostoDespacho().elegirTipoPago(tipoPago).confirmarOrden();
		
		homePage.goToHistorial().ingresoHistorial().validarPending().ingresarOtraInformacion().validarDatosPago("CP001").logOut(); 
	}

}
