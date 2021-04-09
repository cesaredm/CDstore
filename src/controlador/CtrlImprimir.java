/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.image.Bitonal;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.CoffeeImageImpl;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.RasterBitImageWrapper;
import com.github.anastaciocintra.output.PrinterOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.print.PrintService;
import samplesCommon.SamplesCommon;

/**
 *
 * @author CESAR DIAZ MARADIAGA
 */
public class CtrlImprimir {

    PrintService printService = PrinterOutputStream.getPrintServiceByName("EPSON TM-T20III Receipt");
    PrinterOutputStream printerOutputStream;
    EscPos escpos;
    Style boldCenter,bold,centrar;
    Bitonal algorithm;
    // creating the EscPosImage, need buffered image and algorithm.
    BufferedImage githubBufferedImage;
    EscPosImage escposImage;
    // this wrapper uses esc/pos sequence: "GS 'v' '0'"
    RasterBitImageWrapper imageWrapper;

    public CtrlImprimir() throws IOException {
        printerOutputStream = new PrinterOutputStream(printService);
        escpos = new EscPos(printerOutputStream);
        boldCenter = new Style(escpos.getStyle())
                .setBold(true).setJustification(EscPosConst.Justification.Center);
        //estilo negrita sin centrar
        bold = new Style(escpos.getStyle()).setBold(true);
        //centrar texto
        centrar = new Style().setJustification(EscPosConst.Justification.Center);
        algorithm = new BitonalThreshold(127);
        githubBufferedImage = SamplesCommon.getImage(SamplesCommon.sampleImages.blessKids);
        escposImage = new EscPosImage(new CoffeeImageImpl(githubBufferedImage), algorithm);
        imageWrapper = new RasterBitImageWrapper();
        imageWrapper.setJustification(EscPosConst.Justification.Center);
    }
}
