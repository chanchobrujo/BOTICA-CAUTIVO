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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import enums.Constans;
import enums.OSname;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.util.Optional; 
import modules.moduleSale;
import util.Headers;

/**
 *
 * @author kpalmall
 */
public class PdfReportSale {
    private moduleSale modulesale;

    public PdfReportSale( ) {
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
        Document doc = new Document();
        
        try { 
            String osname = System.getProperty("os.name").toUpperCase();
            String url = OSname.findUrlByOsName(osname).get().getSrcByPdf();
        
            url = url.concat(codsale).concat(Constans.format_file);
            PdfWriter.getInstance(doc, new FileOutputStream(url));  
            
            Image header = Image.getInstance(Constans.src_image_logo); 
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);  

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Información de venta. \n \n");

            doc.open();
            
            doc.add(header);
            doc.add(parrafo);   
            doc.add(this.detailRenderSale(codsale)); 
            
            doc.close();
            return Optional.of(doc);
        } catch (DocumentException | IOException e) {
            return Optional.empty();
        }
    }
}
