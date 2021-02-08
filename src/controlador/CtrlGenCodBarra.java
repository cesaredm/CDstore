/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
//import com.lowagie.text.pdf.Barcode;
import net.sourceforge.barbecue.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import modelo.Productos;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.output.OutputException;
import samplesCommon.SamplesCommon;
import vista.IMenu;

/**
 *
 * @author CESAR DIAZ MARADIAGA
 */
public class CtrlGenCodBarra extends Productos implements ActionListener, CaretListener{
    IMenu menu;
    public CtrlGenCodBarra(IMenu menu){
        this.menu = menu;
        this.menu.lblAlertaCreacionCodeBarra.setVisible(false);
        this.menu.btnGenerarCodBarra.addActionListener(this);
        this.menu.btnImprimirCodeBarra.addActionListener(this);
        this.menu.btnGenerarCodeBarraAleatorio.addActionListener(this);
        this.menu.txtCrearCodigoBarra.addCaretListener(this);
    }
    
    //crear o generar codigo de barra
    public BufferedImage codeBarra(String code) throws OutputException{
        Barcode barcode = null;
        try {
                barcode = BarcodeFactory.createCode128B(code);
            } catch (BarcodeException ex) {
                Logger.getLogger(CtrlGenCodBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        return BarcodeImageHandler.getImage(barcode);
    }
    
    public void pngCodeBarra(){
        try {
            File f = new File("C:/CodeBarraCDstore/lot4957.png");
            // Let the barcode image handler do the hard work
            
            //Guardar imagen generada con codigo de barra
            //BarcodeImageHandler.savePNG(codeBarra(code), f);
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e + "Error al generar el codigo de barra");
        }
    }
    //imprimir codigo de barra recibe nombre de la impresora y el codigo de barra a imprimir
    public void GenerarCodigoBarra(String printerName, String code, int Ncopias) throws OutputException {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            escpos = new EscPos(new PrinterOutputStream(printService));

            Bitonal algorithm = new BitonalThreshold(127);
            // creating the EscPosImage, need buffered image and algorithm.
            BufferedImage githubBufferedImage;
            //githubBufferedImage = SamplesCommon.getImage(SamplesCommon.sampleImages.bless);
            EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(codeBarra(code)), algorithm);

            // this wrapper uses esc/pos sequence: "GS 'v' '0'"
            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();

            for(int i = 0; i < Ncopias; i++){
                imageWrapper.setJustification(EscPosConst.Justification.Center);
                escpos.feed(2);
                escpos.write(imageWrapper, escposImage).feed(5).cut(EscPos.CutMode.FULL);
            }

            escpos.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menu.btnGenerarCodBarra){
            this.menu.jdGenerarCodeBarra.setSize(770, 150);
            this.menu.jdGenerarCodeBarra.setLocationRelativeTo(null);
            this.menu.jdGenerarCodeBarra.setVisible(true);
        }
        if(e.getSource() == menu.btnImprimirCodeBarra){
            try {
                String code = this.menu.txtCrearCodigoBarra.getText();
                int Ncopias = (int) this.menu.jsNcopias.getValue();
                if(!code.equals("")){
                    GenerarCodigoBarra("LR2000",code, Ncopias);
                    this.menu.txtCrearCodigoBarra.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "El campo de codigo de barra esta vacio..!");
                }
            } catch (OutputException ex) {
                //Logger.getLogger(CtrlGenCodBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == menu.btnGenerarCodeBarraAleatorio){
            //genera un numero al azar sin decimales
            String code = String.valueOf(Math.random()*8+1);
            this.menu.txtCrearCodigoBarra.setText(code.replace(".", ""));
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if(e.getSource() == this.menu.txtCrearCodigoBarra){
            String code = this.menu.txtCrearCodigoBarra.getText();
            ExitsCodBarra(code);
            if(isExiste()){
                this.menu.lblAlertaCreacionCodeBarra.setVisible(true);
                this.menu.btnImprimirCodeBarra.setEnabled(false);
            }else{
                this.menu.lblAlertaCreacionCodeBarra.setVisible(false);
                this.menu.btnImprimirCodeBarra.setEnabled(true);
            }
        }
    }

}
