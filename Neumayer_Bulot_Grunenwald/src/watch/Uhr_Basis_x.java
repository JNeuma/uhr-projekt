package watch;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Uhr_Basis_x extends Uhr_Basis{
	
    protected JTextField txt_sec;
    private int    i_sec;
	
	Uhr_Basis_x(){
		
	    txt_sec = new JTextField("",3);
	    txt_sec.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //Border ausblenden
	    txt_sec.setEditable(false);
	    Font f = new Font("Courier", Font.BOLD,36);
	    txt_sec.setFont(f);	
	    
	    
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());	
		p.add(txt_sec);	
	}
		
	//zeichnet zusätzliche grafische Elemente in Uhr_Basis()
    public void paintComponent(Graphics g) {	    
	    super.paintComponent(g);
	    	    	   
	    //zeichnet Sekunden-Textfeld
        i_sec = heute.get(Calendar.SECOND);
	    angleSec = (360 / 60) * i_sec;       
	    Integer int_sec = new Integer(i_sec);
	    str_sec = int_sec.toString();
    }
}
