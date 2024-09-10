package dortIslem;

public class Sorular {
    int kod;
    int sayi1;
    int sayi2;
    
    public Sorular(int kod, int sayi1, int sayi2) {
        if (kod < 0 || kod > 4) {
            throw new IllegalArgumentException("Kod 1 ile 4 arasında olmalıdır. Ancak Kod şu anda " + kod);
        }
        this.kod = kod;
        this.sayi1 = sayi1;
        this.sayi2 = sayi2;
    }

    public String soru() {
        String soru;
        switch (kod) {
            case 0:
                soru = "" + sayi1 + " + " + sayi2 + " = ? ";
                break;
            case 1:
                soru = "" + sayi1 + " - " + sayi2 + " = ? ";
                break;
            case 2:
                soru = "" + sayi1 + " * " + sayi2 + " = ? ";
                break;
            case 3:
            	if(isInteger(sayi1, sayi2))
            		soru = "" + sayi1 + " / " + sayi2 + " = ? ";
            	else 	
            		soru = "" + sayi1 + " + " + sayi2 + " = ? ";            		
                break;
            default:
                soru = "Geçersiz işlem";
                break;
        }
        soru += "                         ";
        return soru;
    }

    public int cevap() {
        int cevap;
        switch (kod) {
            case 0:
                cevap = sayi1 + sayi2;
                break;
            case 1:
                cevap = sayi1 - sayi2;
                break;
            case 2:
                cevap = sayi1 * sayi2;
                break;
            case 3:
            	if(isInteger(sayi1, sayi2))
            		cevap = sayi1 / sayi2;
            	else
            		cevap = sayi1 + sayi2;
            	break;
            default:
                throw new IllegalArgumentException("Geçersiz kod");
        }
        return cevap;
    }
    
    private boolean isInteger(int sayi1, int sayi2) {
    	int kalan;
    	try {
    		kalan = sayi1 % sayi2;
    	}catch(Exception ex) {
    		return false;
    	}
    	if(kalan == 0)
    		return true;
    	else
    		return false;
    }
}
