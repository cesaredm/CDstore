/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author CESAR DIAZ MARADIAGA
 */
public class FacturacionTest {
    
    public FacturacionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getProducto method, of class Facturacion.
     */
    @Test
    public void testGetProducto() {
        System.out.println("getProducto");
        Facturacion instance = new Facturacion();
        String[] expResult = {"1122","12q","1.0","Prensapelo","75.00","1.00","Córdobas","75.00"};
        String[] result = instance.getProducto();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getStock method, of class Facturacion.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        Facturacion instance = new Facturacion();
        float expResult = 0.0F;
        float result = instance.getStock();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonedaVenta method, of class Facturacion.
     */
    @Test
    public void testGetMonedaVenta() {
        System.out.println("getMonedaVenta");
        Facturacion instance = new Facturacion();
        String expResult = "";
        String result = instance.getMonedaVenta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrecioDolar method, of class Facturacion.
     */
    @Test
    public void testSetPrecioDolar() {
        System.out.println("setPrecioDolar");
        float precio = 0.0F;
        Facturacion instance = new Facturacion();
        instance.setPrecioDolar(precio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GuardarFactura method, of class Facturacion.
     */
    @Test
    public void testGuardarFactura() {
        System.out.println("GuardarFactura");
        int caja = 0;
        Date fecha = null;
        String nombreComprador = "";
        String credito = "";
        String pago = "";
        String iva = "";
        String total = "";
        Facturacion instance = new Facturacion();
        instance.GuardarFactura(caja, fecha, nombreComprador, credito, pago, iva, total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DetalleFactura method, of class Facturacion.
     */
    @Test
    public void testDetalleFactura() {
        System.out.println("DetalleFactura");
        String factura = "";
        String producto = "";
        String precio = "";
        String cantidad = "";
        String total = "";
        Facturacion instance = new Facturacion();
        instance.DetalleFactura(factura, producto, precio, cantidad, total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of BusquedaGeneralProductoVender method, of class Facturacion.
     */
    @Test
    public void testBusquedaGeneralProductoVender() {
        System.out.println("BusquedaGeneralProductoVender");
        String buscar = "";
        Facturacion instance = new Facturacion();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.BusquedaGeneralProductoVender(buscar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of BuscarPorCategoria method, of class Facturacion.
     */
    @Test
    public void testBuscarPorCategoria() {
        System.out.println("BuscarPorCategoria");
        String categoria = "";
        Facturacion instance = new Facturacion();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.BuscarPorCategoria(categoria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of BuscarPorMarca method, of class Facturacion.
     */
    @Test
    public void testBuscarPorMarca() {
        System.out.println("BuscarPorMarca");
        String marca = "";
        Facturacion instance = new Facturacion();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.BuscarPorMarca(marca);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FormasPago method, of class Facturacion.
     */
    @Test
    public void testFormasPago() {
        System.out.println("FormasPago");
        Facturacion instance = new Facturacion();
        DefaultComboBoxModel expResult = null;
        DefaultComboBoxModel result = instance.FormasPago();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ObtenerIdFactura method, of class Facturacion.
     */
    @Test
    public void testObtenerIdFactura() {
        System.out.println("ObtenerIdFactura");
        Facturacion instance = new Facturacion();
        String expResult = "";
        String result = instance.ObtenerIdFactura();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ObtenerFormaPago method, of class Facturacion.
     */
    @Test
    public void testObtenerFormaPago() {
        System.out.println("ObtenerFormaPago");
        String pago = "";
        Facturacion instance = new Facturacion();
        String expResult = "";
        String result = instance.ObtenerFormaPago(pago);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Vender method, of class Facturacion.
     */
    @Test
    public void testVender() {
        System.out.println("Vender");
        String id = "";
        String cantidad = "";
        Facturacion instance = new Facturacion();
        instance.Vender(id, cantidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ActualizarFactura method, of class Facturacion.
     */
    @Test
    public void testActualizarFactura() {
        System.out.println("ActualizarFactura");
        int caja = 0;
        String id = "";
        Date fecha = null;
        String nombreComprador = "";
        String credito = "";
        String pago = "";
        String iva = "";
        String total = "";
        Facturacion instance = new Facturacion();
        instance.ActualizarFactura(caja, id, fecha, nombreComprador, credito, pago, iva, total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ActualizarDetalle method, of class Facturacion.
     */
    @Test
    public void testActualizarDetalle() {
        System.out.println("ActualizarDetalle");
        String id = "";
        String producto = "";
        String precio = "";
        String cant = "";
        String total = "";
        Facturacion instance = new Facturacion();
        instance.ActualizarDetalle(id, producto, precio, cant, total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPorCodBarra method, of class Facturacion.
     */
    @Test
    public void testObtenerPorCodBarra() {
        System.out.println("obtenerPorCodBarra");
        String codBarra = "12q";
        Facturacion instance = new Facturacion();
        instance.obtenerPorCodBarra(codBarra);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of monedaVentaProducto method, of class Facturacion.
     */
    @Test
    public void testMonedaVentaProducto() {
        System.out.println("monedaVentaProducto");
        String id = "";
        Facturacion instance = new Facturacion();
        instance.monedaVentaProducto(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ActualizarDevolucion method, of class Facturacion.
     */
    @Test
    public void testActualizarDevolucion() {
        System.out.println("ActualizarDevolucion");
        int id = 0;
        float iva = 0.0F;
        float total = 0.0F;
        Facturacion instance = new Facturacion();
        instance.ActualizarDevolucion(id, iva, total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarDetalle method, of class Facturacion.
     */
    @Test
    public void testEliminarDetalle() {
        System.out.println("eliminarDetalle");
        int id = 0;
        Facturacion instance = new Facturacion();
        instance.eliminarDetalle(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTotalFacturaSeleccionada method, of class Facturacion.
     */
    @Test
    public void testObtenerTotalFacturaSeleccionada() {
        System.out.println("obtenerTotalFacturaSeleccionada");
        int id = 0;
        Facturacion instance = new Facturacion();
        float expResult = 0.0F;
        float result = instance.obtenerTotalFacturaSeleccionada(id);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
