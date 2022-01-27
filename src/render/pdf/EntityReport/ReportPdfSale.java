/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.pdf.EntityReport;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;  
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable; 
import Constans.Enums.AlertMessage;
import Constans.Constan; 
import java.io.IOException;  
import model.Messages.Message;
import model.ModelSale;
import model.PdfParams.ParamsFont;
import modules.moduleSale; 
import render.pdf.PDFInit;
import util.Commons;

import Constans.Headers.HeadersTableSwing;
import Constans.Headers.HeadersWidthSizePdf;

/**
 *
 * @author kpalmall
 */
public class ReportPdfSale {
    private moduleSale modulesale;
    private PDFInit PDFInit;

    public ReportPdfSale( ) {
        PDFInit = new PDFInit();
        modulesale = new moduleSale(0.0);
    } 
    
    private PdfPTable TableRenderSale(ModelSale modelSale) throws DocumentException {
        PdfPTable tbl = new PdfPTable(2);
        tbl.setWidths(HeadersWidthSizePdf.headres_with_report_sale);
        
        tbl.addCell(HeadersTableSwing.headres_sale[1].intern());
        tbl.addCell(modelSale.getUser());
        
        tbl.addCell(HeadersTableSwing.headres_sale[2].intern());
        tbl.addCell(modelSale.getCustomer());
        
        tbl.addCell(HeadersTableSwing.headres_sale[3].intern());
        tbl.addCell(modelSale.getDate());
        
        tbl.addCell(HeadersTableSwing.headres_sale[4].intern());
        tbl.addCell(modelSale.getTime());
        
        tbl.addCell(HeadersTableSwing.headres_sale[6].intern());
        tbl.addCell(modelSale.getSubtotal().toString());
        
        tbl.addCell(HeadersTableSwing.headres_sale[5].intern());
        tbl.addCell(modelSale.getDesc().toString());
        
        tbl.addCell(HeadersTableSwing.headres_sale[7].intern());
        tbl.addCell(modelSale.getTotal().toString());
        return tbl;
    }
    
    private PdfPTable TableRenderCart(String codsale) throws DocumentException {
        PdfPTable tbl = new PdfPTable(4);
        tbl.setWidths(HeadersWidthSizePdf.headres_with_report_detail);
        
        for (String element : HeadersTableSwing.headres_detail) tbl.addCell(element); 
        
        this.modulesale.findAllDetails(codsale).stream()
                .forEach(element -> {
                    tbl.addCell(Commons.setPropertiesProduct(element.getProduct()));
                    tbl.addCell(Constan.empty + element.getProduct_price());
                    tbl.addCell(Constan.empty + element.getQuantity());
                    tbl.addCell(Constan.empty + element.get_import());
                });

        return tbl;
    }
    
    private Paragraph addParagraph(Integer fontsize, Integer font, BaseColor fontcolor, String fontname, String value){
        ParamsFont paramsFont = ParamsFont.builder()
                    .fontsize(fontsize)
                    .fontf(font)
                    .fontcolor(fontcolor)
                    .fontname(fontname).build();
        
        return this.PDFInit.addParagraph(value, paramsFont, Paragraph.ALIGN_CENTER);
    }
    
    public Message generatedPdfSale(ModelSale modelSale){  
        String msg = AlertMessage.PDF_SUCCESS.getValue();
        Boolean iserror = false;
        
        try { 
            Document doc = PDFInit.initializeDocument(modelSale.getId());   
            
            doc.open();
            
            doc.add(this.PDFInit.addHeaderImage(Constan.src_image_logo, 650, 1000, Chunk.ALIGN_CENTER));
            
            doc.add(this.addParagraph(11, Font.BOLD, BaseColor.BLACK, "Console", "INFORMACIÓN DE LA VENTA."));   
            doc.add(this.TableRenderSale(modelSale));  
            
            doc.add(this.addParagraph(10, Font.NORMAL, BaseColor.DARK_GRAY, "Console", "DETALLE DE LA VENTA."));   
            doc.add(this.TableRenderCart(modelSale.getId()));  
            
            doc.close(); 
        } catch (DocumentException | IOException e) { 
            iserror = true;
            msg = e.getMessage();
        }
        
        return new Message(msg, iserror);
    }
}
