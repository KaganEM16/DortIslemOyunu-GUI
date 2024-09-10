package dortIslem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Oyun extends JFrame implements ActionListener 
{	
	final static int sure = 10;
	JLabel[] etiketler;
	JLabel[] sorular;
	JLabel[] sureler;
	JPanel[] paneller;
	JButton[] butonlar;
	JTextField[] alanlar;
	int[] canlar = {5, 5}; 
	JLabel[] isimler;
	Runnable[] gorev;	
	int sayac = 0;
	int kod, sayi1, sayi2, cevap;
	long baslangic;
	static boolean siraBirde = true;	
	String bosluk = "                         	";
	
    public Oyun(int en, int boy) 
    {
//      Genel Hatlarıyla Pencerenin Hazırlanması:    	
    	super("Dört İşlem Yarışması");
    	this.setLayout(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	this.setSize(en, boy);
    	
//    	Panellerin Hazırlanması
    	paneller = new JPanel[4];
    	for(int i=0 ; i<paneller.length ; i++) {
    		paneller[i] = new JPanel();
    		paneller[i].setSize((i==0 || i==3)? en : en/2 , boy);    		
    		paneller[i].setBackground(Color.BLACK);
    		paneller[i].setLayout(new FlowLayout());    		
    	}
    	this.add(paneller[0]);
    	paneller[2].setBounds(en/2, 0, en/2, boy);
    	
//    	Bilgi Verecek Etiketlerin Hazırlanması
    	etiketler = new JLabel[8];
    	for(int i=0 ; i<etiketler.length ; i++) {
    		if(i==0)
    			etiketler[i] = new JLabel("Dört İşlem Oyununa Hoşgeldiniz");
    		else if(i==1)
    			etiketler[i] = new JLabel("Birinci Oyuncunun Adını Giriniz");//+
    		else if(i==2 || i==3)
    			etiketler[i] = new JLabel("Kalan yanlış cevap hakkınız: " + canlar[i-2]);
    		else if(i==4 || i==5)
    			etiketler[i] = new JLabel("");  //Cevabın doğru yanlışlığının kontrolü.
    		else    			
    			etiketler[i] = new JLabel("Oyun Bitti");
    		etiketAyarla(etiketler[i], (i==0)? 30 : (i==1 || i==6 || i==7)? 28 : 15, Color.WHITE);
    	}
    	
//    	Butonların Hazırlanması
    	butonlar = new JButton[3];
    	for(int i=0 ; i<butonlar.length ; i++) {
    		butonlar[i] = new JButton("Gönder");
    		butonlar[i].setPreferredSize(new Dimension(90, 70));
    		butonlar[i].setBackground(Color.DARK_GRAY);
    		butonlar[i].setForeground(Color.WHITE);
    		butonlar[i].addActionListener(this);
    	}
   
//   	İsimlerin Yazılacağı ve Cevapların Hazırlanacağı 'text' Alanlarının Hazırlanması
    	alanlar = new JTextField[3];
    	for(int i=0 ; i<alanlar.length ; i++) {
    		alanlar[i] = new JTextField();
    		alanlar[i].setPreferredSize((i==0)? new Dimension(200, 68) : new Dimension(80, 68));
    		alanlar[i].setBackground(Color.WHITE);
    	}
    	
//    	Kullanıcının Gireceği İsimlerin Tutulacağı ietiketlerin Hazırlanması
    	isimler = new JLabel[2];
    	for(int i=0 ; i<isimler.length ; i++) {
    		isimler[i] = new JLabel();
    		etiketAyarla(isimler[i], 17, Color.WHITE);
    	}
    	
//    	Soruların Gösterileceği Etiketlerin Hazırlanması
    	sorular = new JLabel[2];
    	for(int i=0 ; i<sorular.length ; i++) {
    		sorular[i] = new JLabel(bosluk);
    		etiketAyarla(sorular[i], 15, Color.WHITE);
    	}
    	
//    	Sürelerin Gösterileceği Etiketlerin Ayarlanması
    	sureler = new JLabel[2];
    	for(int i=0 ; i<sureler.length ; i++) {
    		sureler[i] = new JLabel("Kalan Sureniz: " + sure + " saniye.");
    		etiketAyarla(sureler[i], 15, Color.WHITE);
    	}
    	
//    	Sürelerin Artıp Azalmasını Sağlayacak Görevlerin Ayarlanması
    	gorev = new SureAyarla[2];
    	for(int i=0 ; i<gorev.length ; i++) {
    		gorev[i] = new SureAyarla(sureler[i]); 
    	}
    	
    	paneller[0].add(etiketler[0]);
    	paneller[0].add(etiketler[1]);
    	paneller[0].add(alanlar[0]);
    	paneller[0].add(butonlar[0]);
    	
    	paneller[3].add(etiketler[6]);
    	paneller[3].add(etiketler[7]);
    	
    	for(int i=1 ; i<paneller.length-1 ; i++) {
    		paneller[i].add(isimler[i-1]);
    		paneller[i].add(sorular[i-1]);
    		paneller[i].add(alanlar[i]);
    		paneller[i].add(butonlar[i]);
    		paneller[i].add(sureler[i-1]);    		
    		paneller[i].add(etiketler[i+1]);
    		paneller[i].add(etiketler[i+3]);
    	}
    	sureler[1].setVisible(false);
    	
    	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {    	
	          JButton basilan = (JButton)e.getSource();
	          kod = (int)(Math.random() * 4);
	          sayi1 = (int)(Math.random() * 20);
	          sayi2 = (int)(Math.random() * 20);	          
	          
	          if(basilan == butonlar[0]) {
	        	  if(sayac == 0) {
	        		  isimler[0].setText(alanlar[0].getText() + bosluk);
	        	  }else {
	        		  isimler[1].setText(alanlar[0].getText() + bosluk);
	        		  paneller[0].setVisible(false);
	        		  this.add(paneller[1]);
	        		  this.add(paneller[2]);
	        		  baslangic = System.currentTimeMillis();
	        		  sorular[0].setText(new Sorular(kod,sayi1,sayi2).soru());
	        		  cevap = new Sorular(kod,sayi1,sayi2).cevap();
	        		  alanlar[2].setVisible(false);
	        		  butonlar[2].setVisible(false);
	        		  new Thread(gorev[0]).start();
	        	  }
	        	  alanlar[0].setText("");
	        	  sayac++;
	          }else {
	        	  int k = (siraBirde)? 0 : 1;
	        	  int verilenCevap;
	        	  try {
        			  verilenCevap = Integer.parseInt(alanlar[k + 1].getText());
        		  }catch(Exception ex) {verilenCevap = 1000;}
	        	  long bitis = System.currentTimeMillis();
        		  if((bitis-baslangic)/1000 < sure && verilenCevap == cevap) {
        			  etiketler[k + 4].setText("Zamanında doğru cevap verildi.");
        		  }else {
        			  if(verilenCevap != cevap)
        				  etiketler[k + 4].setText("Maalesef yanlış cevap.");
        			  else
        				  etiketler[k + 4].setText("Süreyi Aştınız.");
        			  canlar[k] -= 1;
        			  etiketler[k + 2].setText("Kalan yanlış cevap hakkınız " + canlar[k]);
        			  if(canlar[k] == 0) {
        				  etiketler[7].setText("Oyunu kazanan: " + isimler[1 - k].getText()); //****
        				  paneller[1].setVisible(false);
        				  paneller[2].setVisible(false);
        				  this.add(paneller[3]);
        			  }
        		  }
        		  alanlar[2 - k].setVisible(true);
        		  butonlar[2 - k].setVisible(true);
        		  alanlar[k + 1].setVisible(false);
        		  butonlar[k + 1].setVisible(false);
        		  baslangic = System.currentTimeMillis();
        		  sorular[k + 0].setText("");
        		  sorular[1 - k].setText(new Sorular(kod,sayi1,sayi2).soru());
        		  cevap = new Sorular(kod,sayi1,sayi2).cevap();
        		  alanlar[1 + k].setText("");
        		  etiketler[5 - k].setText("");
        		  sureler[k].setVisible(false);
        		  sureler[1 - k].setVisible(true);
        		  siraBirde = (siraBirde)? false : true;
        		  new Thread(gorev[1 - k]).start();
	          }
    	}       
    
    public void etiketAyarla(JLabel etiket, int boyut, Color renk) 
    {
        etiket.setForeground(renk);
        etiket.setFont(new Font("Verdana", Font.BOLD, boyut));
        etiket.setHorizontalAlignment(0);
    }
}
