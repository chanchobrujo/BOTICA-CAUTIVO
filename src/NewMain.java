
import services.saleService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// = new saleService()
/**
 *
 * @author kpalmall
 */
public class NewMain {
    private saleService saleService;

    /**
     * @param args the command line arguments
     */ 
    
    public NewMain(){
        saleService = new saleService();
        
    }
    
    public void run() {
        saleService.newSale(1, 0.5);
        saleService.addLine(4, 2); 
        saleService.addLine(4, 5); 
        saleService.addLine(4, 1); 
        System.err.println(this.saleService.viewDetails().getId());
        System.err.println(this.saleService.viewDetails().getCart().size());
        System.err.println(this.saleService.viewDetails().getTotal());
    }
    
    public static void main(String[] args) {
        NewMain NewMain = new NewMain();
        NewMain.run();
        // TODO code application logic here
    }
    
}
