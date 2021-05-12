/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
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
public class ProductosTest {
    
    public ProductosTest() {
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
     * Test of isExiste method, of class Productos.
     */
    @Test
    public void testIsExiste() {
        System.out.println("isExiste");
        Productos instance = new Productos();
        boolean expResult = true;
        boolean result = instance.isExiste();
        assertEquals(expResult, result);
    }

    /**
     * Test of setExiste method, of class Productos.
     */
    @Test
    public void testSetExiste() {
        System.out.println("setExiste");
        boolean existe = false;
        Productos instance = new Productos();
        instance.setExiste(existe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecioMinimo method, of class Productos.
     */
    @Test
    public void testGetPrecioMinimo() {
        System.out.println("getPrecioMinimo");
        Productos instance = new Productos();
        float expResult = 0.0F;
        float result = instance.getPrecioMinimo();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Guardar method, of class Productos.
     */
    @Test
    public void testGuardar() {
        System.out.println("Guardar");
        String codigoBarra = "";
        String nombre = "";
        String precioCompra = "";
        String monedaCompra = "";
        String precioVenta = "";
        String monedaVenta = "";
        String precioMinimo = "";
        Date fechaVencimiento = null;
        String stock = "";
        String categoria = "";
        String laboratorio = "";
        String ubicacion = "";
        String descripcion = "";
        float utilidad = 0;
        Productos instance = new Productos();
        instance.Guardar(codigoBarra, nombre, precioCompra, monedaCompra, precioVenta, monedaVenta, precioMinimo, fechaVencimiento, stock, categoria, laboratorio, ubicacion, descripcion, utilidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Actualizar method, of class Productos.
     */
    @Test
    public void testActualizar() {
        System.out.println("Actualizar");
        String id = "";
        String codigoBarra = "";
        String nombre = "";
        String precioCompra = "";
        String monedaCompra = "";
        String precioVenta = "";
        String monedaVenta = "";
        String precioMinimo = "";
        Date fechaVencimiento = null;
        String stock = "";
        String categoria = "";
        String laboratorio = "";
        String ubicacion = "";
        String descripcion = "";
        float utilidad = 0;
        Productos instance = new Productos();
        instance.Actualizar(id, codigoBarra, nombre, precioCompra, monedaCompra, precioVenta, monedaVenta, precioMinimo, fechaVencimiento, stock, categoria, laboratorio, ubicacion, descripcion,utilidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Eliminar method, of class Productos.
     */
    @Test
    public void testEliminar() {
        System.out.println("Eliminar");
        String id = "";
        Productos instance = new Productos();
        instance.Eliminar(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Consulta method, of class Productos.
     */
    @Test
    public void testConsulta() {
        System.out.println("Consulta");
        String buscar = "";
        Productos instance = new Productos();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.Consulta(buscar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MostrarCategorias method, of class Productos.
     */
    @Test
    public void testMostrarCategorias() {
        System.out.println("MostrarCategorias");
        String nombre = "";
        Productos instance = new Productos();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.MostrarCategorias(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MostrarMarca method, of class Productos.
     */
    @Test
    public void testMostrarMarca() {
        System.out.println("MostrarMarca");
        String nombre = "";
        Productos instance = new Productos();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.MostrarMarca(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ObtenerIdMarca method, of class Productos.
     */
    @Test
    public void testObtenerIdMarca() {
        System.out.println("ObtenerIdMarca");
        String nombre = "";
        Productos instance = new Productos();
        String expResult = "";
        String result = instance.ObtenerIdMarca(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ObtenerIdCategoria method, of class Productos.
     */
    @Test
    public void testObtenerIdCategoria() {
        System.out.println("ObtenerIdCategoria");
        String nombre = "";
        Productos instance = new Productos();
        String expResult = "";
        String result = instance.ObtenerIdCategoria(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AgregarProductoStock method, of class Productos.
     */
    @Test
    public void testAgregarProductoStock() {
        System.out.println("AgregarProductoStock");
        String id = "";
        String cantidad = "";
        Productos instance = new Productos();
        instance.AgregarProductoStock(id, cantidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MinimoStock method, of class Productos.
     */
    @Test
    public void testMinimoStock() {
        System.out.println("MinimoStock");
        String categoria = "";
        float cantidad = 0.0F;
        Productos instance = new Productos();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.MinimoStock(categoria, cantidad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GenerarReporteStockMin method, of class Productos.
     */
    @Test
    public void testGenerarReporteStockMin() throws Exception {
        System.out.println("GenerarReporteStockMin");
        String categ = "";
        float cantidad = 0.0F;
        Productos instance = new Productos();
        instance.GenerarReporteStockMin(categ, cantidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inversion method, of class Productos.
     */
    @Test
    public void testInversion() {
        System.out.println("inversion");
        Productos instance = new Productos();
        float expResult = 0.0F;
        float result = instance.inversion();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inversionCordobas method, of class Productos.
     */
    @Test
    public void testInversionCordobas() {
        System.out.println("inversionCordobas");
        Productos instance = new Productos();
        float expResult = 0.0F;
        float result = instance.inversionCordobas();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inversionDolar method, of class Productos.
     */
    @Test
    public void testInversionDolar() {
        System.out.println("inversionDolar");
        Productos instance = new Productos();
        float expResult = 0.0F;
        float result = instance.inversionDolar();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ExitsCodBarra method, of class Productos.
     */
    @Test
    public void testExitsCodBarra() {
        System.out.println("ExitsCodBarra");
        String codBarra = "12q";
        Productos instance = new Productos();
        instance.ExitsCodBarra(codBarra);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of precioMinimo method, of class Productos.
     */
    @Test
    public void testPrecioMinimo() {
        System.out.println("precioMinimo");
        String id = "";
        Productos instance = new Productos();
        instance.precioMinimo(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
