/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table.Pdf_Report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;  
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable; 
import enums.Constans; 
import java.io.IOException; 
import java.util.Optional; 
import model.PdfParams.ParamsFont;
import modules.moduleSale;
import render.table.Pdf_Report.PdfGenerator.PDFInit;
import util.Headers;

/**
 *
 * @author kpalmall
 */
public class PdfReportTableSale {
    private moduleSale modulesale;
    private PDFInit PDFInit;

    public PdfReportTableSale( ) {
        PDFInit = new PDFInit();
        modulesale = new moduleSale(0.0);
    } 
    
    private PdfPTable detailRenderSale(String codsale) {
        PdfPTable tablaCliente = new PdfPTable(4);
        for (String element : Headers.headres_detail) tablaCliente.addCell(element);

        this.modulesale.findAllDetails(codsale).stream()
                .forEach(element -> {
                    tablaCliente.addCell(element.getProduct());
                    tablaCliente.addCell(Constans.empty + element.getProduct_price());
                    tablaCliente.addCell(Constans.empty + element.getQuantity());
                    tablaCliente.addCell(Constans.empty + element.get_import());
                });

        return tablaCliente;
    }
    
    public Optional<Document> generatedPdfSale(String codsale){  
        try { 
            Document doc = PDFInit.initializeDocument(codsale);  
            
            ParamsFont paramsFont = ParamsFont.builder()
                    .fontsize(14)
                    .fontf(Font.BOLD)
                    .fontcolor(BaseColor.BLUE)
                    .fontname("Console").build();

            doc.open();
            
            doc.add(this.PDFInit.addHeaderImage(Constans.src_image_logo, 650, 1000, Chunk.ALIGN_CENTER));
            doc.add(this.PDFInit.addParagraph("INFORMACIÓN DE LA VENTA.", paramsFont, Paragraph.ALIGN_CENTER));   
            doc.add(this.detailRenderSale(codsale));  
            
            doc.close();
            return Optional.of(doc);
        } catch (DocumentException | IOException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
