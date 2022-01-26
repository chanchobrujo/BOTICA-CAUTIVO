/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.PdfParams;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kpalmall
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParamsFont {
    private String fontname;
    private Integer fontsize; 
    private Integer fontf; 
    private BaseColor fontcolor;
    
    public Font getFont(){
        return FontFactory.getFont(this.fontname, this.fontsize, this.fontf, this.fontcolor);
    }
}
