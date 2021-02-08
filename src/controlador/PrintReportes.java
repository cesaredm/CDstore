/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * PrintReportes.java
 * 
 * 
 */
package controlador;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.image.Bitonal;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.CoffeeImageImpl;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.RasterBitImageWrapper;
import com.github.anastaciocintra.output.PrinterOutputStream;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;
import samplesCommon.SamplesCommon;

/**
 *
 * @author CESAR DIAZ MARADIAGA
 */
public class PrintReportes {

    //Ticket attribute content
    private String contentTicketDiario = ""
            + "{{nombreTienda}}           \n"
            + "REPORTE DEL DIA {{fecha}}      \n"
            + "\n"
            + "Efctivo de apertura caja           {{base}}\n"
            + "Ingreso por ventas en efectivo     {{ventasE}}\n"
            + "Ingreso por ventas con tarjeta     {{ventasT}}\n"
            + "Ingresos por abonos en efectivo    {{pagosE}}\n"
            + "Ingresos por abonos con tarjeta    {{pagosT}}\n"
            + "Ingresos de efectivo               {{ingresosE}}\n"
            + "Creditos                           {{creditos}}\n"
            + "Egresos de efectivo de caja        {{egresos}}\n"
            + "===========================================\n"
            + "Total efectivo en caja             {{existCaja}}\n"
            + "Total Bancos                       {{bancos}}\n"
            + "Total vendido                      {{totalV}}\n"
            + "-------------------------------------------\n"
            + "Dólares   ${{DV}} * {{PDV}}  {{TCDV}}\n"
            + "Dólares   ${{DC}} * {{PDC}}  {{TCDC}}\n"
            + "Córdobas                   C${{cordobas}}\n"
            + "..\n\n\n\n\n";
    //DV = dolaresVendidos DC = dolaresComprados PDV = precioDolarVenta PDC = precioDolarCompra TCDV = total de cordobas por dolares vendidos TCDC = total de cordobas por dolares comprados
    private String ticketTotalV = ""
            + "{{nombreTienda}}            \n"
            + "\n"
            + "Fecha Inicio      Fecha Final      Total Vendido\n"
            + "\n"
            + "{{datos}}"
            + "\n\n\n\n\n";
    private String contentTicketGlobal = ""
            + "{{nombreTienda}}           \n"
            + "REPORTE GENERAL GLOBAL      \n"
            + "\n"
            + "Ingreso por ventas en efectivo   {{ventasE}}\n"
            + "Ingreso por ventas con tarjeta   {{ventasT}}\n"
            + "Ingresos por abonos en efectivo  {{pagosE}}\n"
            + "Ingresos por abonos con tarjeta  {{pagosT}}\n"
            + "Ingresos de efectivo             {{ingresosE}}\n"
            + "Creditos                         {{creditos}}\n"
            + "Egresos de efectivo de caja      {{egresos}}\n"
            + "============================================\n"
            + "Total efectivo en caja           {{existCaja}}\n"
            + "Total Bancos                     {{bancos}}\n"
            + "Total vendido                    {{totalV}}\n"
            + "..\n\n\n\n\n";

    //bussines Intelligense
    private String BI = ""
            + "{{nombreTienda}}            \n"
            + "Productos mas vendidos o solicitados\n"
            + "Fecha: {{fecha1}} Hasta {{fecha2}}\n"
            + "-----------------------------------------\n"
            + "N°   Nombre                     Vendido\n"
            + "-----------------------------------------\n"
            + "{{producto}}\n"
            + "\n\n\n\n\n";

    //TICKET DE ABONOS DE CREDITOS
    private String ReciboPago = ""
            + "{{tienda}}\n"
            + "COMPROBANTE DE PAGO\n"
            + "N° Pago: {{idPago}}\n"
            + "FECHA : {{fecha}}\n"
            + "CLIENTE: {{cliente}}\n"
            + "N° CREDITO: {{credito}}\n"
            + "TOTAL CREDITO: {{totalCredito}}\n"
            + "MONTO DE PAGO :{{monto}}\n"
            + "SALDO: {{saldo}}\n\n\n\n"
            + "___________________________________________\n"
            + "               Firma Vendedor\n\n\n"
            + "___________________________________________\n"
            + "               Firma Cliente\n\n\n";
    //constructor

    PrintReportes() {

    }

    public void llenarTicketPago(String tienda, String idPago, String fecha, String cliente, String credito, String totalCredito, String monto, String saldo) {
        this.ReciboPago = this.ReciboPago.replace("{{tienda}}", tienda);
        this.ReciboPago = this.ReciboPago.replace("{{fecha}}", fecha);
        this.ReciboPago = this.ReciboPago.replace("{{cliente}}", cliente);
        this.ReciboPago = this.ReciboPago.replace("{{credito}}", credito);
        this.ReciboPago = this.ReciboPago.replace("{{monto}}", monto);
        this.ReciboPago = this.ReciboPago.replace("{{idPago}}", idPago);
        this.ReciboPago = this.ReciboPago.replace("{{totalCredito}}", totalCredito);
        this.ReciboPago = this.ReciboPago.replace("{{saldo}}", saldo);
        //System.out.println(this.ReciboPago);
    }

    public void llenarTicketDiario(String NombreTienda, String fecha, String base, String ventasEfectivo, String ventasT, String pagosE, String pagosT, String ingresosE, String creditos, String egreso, String existCaja, String bancos, String totalV, String dolaresVendidos, String dolaresComprados, String PDC, String PDV, String TCDV, String TCDC, String cordobas) {
        this.contentTicketDiario = this.contentTicketDiario.replace("{{nombreTienda}}", NombreTienda);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{fecha}}", fecha);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{base}}", base);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{ventasE}}", ventasEfectivo);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{ventasT}}", ventasT);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{pagosE}}", pagosE);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{pagosT}}", pagosT);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{ingresosE}}", ingresosE);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{creditos}}", creditos);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{egresos}}", egreso);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{existCaja}}", existCaja);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{bancos}}", bancos);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{totalV}}", totalV);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{DV}}", dolaresVendidos);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{DC}}", dolaresComprados);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{PDV}}", PDV);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{PDC}}", PDC);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{TCDC}}", TCDC);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{TCDV}}", TCDV);
        this.contentTicketDiario = this.contentTicketDiario.replace("{{cordobas}}", cordobas);
        //System.out.println(this.contentTicketDiario);
    }

    public void llenarTicketTotalV(String[] datos, String tienda) {
        StringBuffer a = new StringBuffer("");
        for (int i = 0; i < datos.length; i++) {
            a.append(datos[i]);
        }
        this.ticketTotalV = this.ticketTotalV.replace("{{nombreTienda}}", tienda);
        this.ticketTotalV = this.ticketTotalV.replace("{{datos}}", a);
        // System.out.println(this.ticketTotalV);
    }

    //bussines 
    public void BIP(String tienda, String fecha1, String fecha2, String[] P) {
        StringBuffer Producto = new StringBuffer("");
        int N = 0;
        for (int i = 0; i < P.length; i++) {
            Producto.append(P[i] + "\n");
        }
        this.BI = this.BI.replace("{{nombreTienda}}", tienda);
        this.BI = this.BI.replace("{{fecha1}}", fecha1);
        this.BI = this.BI.replace("{{fecha2}}", fecha2);
        this.BI = this.BI.replace("{{producto}}", Producto);
        //System.out.println(this.BI);
    }
    
    public void llenarTicketGlobal(String NombreTienda, String ventasEfectivo, String ventasT, String pagosE, String pagosT, String ingresosE, String creditos, String egreso, String existCaja, String bancos, String totalV) {
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{nombreTienda}}", NombreTienda);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{ventasE}}", ventasEfectivo);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{ventasT}}", ventasT);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{pagosE}}", pagosE);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{pagosT}}", pagosT);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{ingresosE}}", ingresosE);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{creditos}}", creditos);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{egresos}}", egreso);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{existCaja}}", existCaja);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{bancos}}", bancos);
        this.contentTicketGlobal = this.contentTicketGlobal.replace("{{totalV}}", totalV);
        // System.out.println(this.contentTicketGlobal);
    }

    public void print(String TipoReport, String printerName) {
        //this call is slow, try to use it only once and reuse the PrintService variable.
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        try {
            PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
            EscPos escpos = new EscPos(printerOutputStream);

            /*CODIGO PARA INCLUIR IMAGEN A LA IMPRESION
            Bitonal algorithm = new BitonalThreshold(127); 
            // creating the EscPosImage, need buffered image and algorithm.
            BufferedImage  githubBufferedImage;
            githubBufferedImage = SamplesCommon.getImage(SamplesCommon.sampleImages.github);
            EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(githubBufferedImage), algorithm);
            
            // this wrapper uses esc/pos sequence: "GS 'v' '0'"
            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
            
            imageWrapper.setJustification(EscPosConst.Justification.Center);
            escpos.write(imageWrapper, escposImage).feed(1);*/
            
            switch (TipoReport) {
                case "Diario": {
                    escpos.writeLF(contentTicketDiario)
                    .feed(2).cut(EscPos.CutMode.FULL);
                }
                break;
                case "TotalV": {
                    escpos.writeLF(ticketTotalV)
                    .feed(2).cut(EscPos.CutMode.FULL);
                }
                break;
                case "Global": {
                    escpos.writeLF(contentTicketGlobal)
                    .feed(2).cut(EscPos.CutMode.FULL);
                }
                break;
                case "BI": {
                    escpos.writeLF(BI)
                    .feed(2).cut(EscPos.CutMode.FULL);
                }
                break;
                case "Pago": {
                    escpos.writeLF(ReciboPago)
                    .feed(2).cut(EscPos.CutMode.FULL);
                }
                break;
            }

            escpos.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
