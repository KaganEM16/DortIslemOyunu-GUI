package dortIslem;

import javax.swing.*;

public class SureAyarla implements Runnable 
{
	JLabel sureEtiketi;
	int sure = Oyun.sure;
	
    public SureAyarla(JLabel sureEtiketi)
    {
        this.sureEtiketi = sureEtiketi;
    }

    @Override
    public void run() 
    {
        while(true) {
        	sureEtiketi.setText("Kalan Sureniz: " + sure + " saniye.");
        	sure--;
        	try {
        		Thread.sleep(1000);
        	}catch(Exception ex) {}
        	if(sure <= -1) {
        		sure = Oyun.sure;
        		break;
        	}        	
        }        
    }
}

