/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render.table.Pdf_Report.PdfGenerator;

import com.itextpdf.text.BadElementException; 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import enums.Constans;
import enums.OSname;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.NoArgsConstructor;
import model.PdfParams.ParamsFont;

/**
 *
 * @author kpalmall
 */
@NoArgsConstructor
public class PDFInit {

    private String URL(String nameDoc) {
        String osname = System.getProperty("os.name").toUpperCase();
        String url = OSname.findUrlByOsName(osname).get().getSrcByPdf();

        return url.concat(nameDoc).concat(Constans.format_file);
    }

    public Document initializeDocument(String nameDoc) throws FileNotFoundException, DocumentException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(this.URL(nameDoc)));
        return doc;
    }

    public Image addHeaderImage(String src, float fitWidth, float fitHeight, Integer alignment) throws BadElementException, IOException {
        Image header = Image.getInstance(src);
        header.scaleToFit(fitWidth, fitHeight);
        header.setAlignment(alignment);
        return header;
    }

    public Paragraph addParagraph(String value, ParamsFont ParamsFont, Integer alignment) {
        Paragraph parrafo = new Paragraph();  
        parrafo.setAlignment(alignment);
        parrafo.setFont(ParamsFont.getFont());
        parrafo.add(value.concat("\n \n"));
        return parrafo;
    }
}
