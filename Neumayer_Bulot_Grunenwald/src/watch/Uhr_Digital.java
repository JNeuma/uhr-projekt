package watch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Uhr_Digital extends JFrame {
	
	Uhr_Basis b;	
	JTextField hr          = new JTextField("Stunden    ");		
	JTextField min         = new JTextField("Minuten    ");
	JTextField sec         = new JTextField("Sekunden   ");
	JButton    startStop   = new JButton("stop");	
	static int stateUhrDigital    = 1;
	GetTimeThread  t = new GetTimeThread();

	
	private class GetTimeThread extends Thread{
		public void run() {
			while (true) {
				
		        try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		        
				if (stateUhrDigital==1){					
					System.out.println("Uhr_Digital()-> state1");
					hr.setText (""  + (b.heute.get(Calendar.HOUR_OF_DAY)));
					
					hr.setForeground(Color.BLUE);
					//hr.setBorder (BorderFactory.createLineBorder ((Color.BLUE), 1));
					hr.setBorder(javax.swing.BorderFactory.createEmptyBorder());
					min.setText(":"  + (b.heute.get(Calendar.MINUTE)));
					min.setForeground(Color.BLUE);
					//min.setBorder (BorderFactory.createLineBorder ((Color.BLUE), 1));
					min.setBorder(javax.swing.BorderFactory.createEmptyBorder());
					sec.setText(":" + (b.heute.get(Calendar.SECOND)));
					sec.setForeground(Color.BLUE);
					//sec.setBorder (BorderFactory.createLineBorder ((Color.BLUE), 1));
					sec.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //Border ausblenden
					startStop.setText("stop");	
					startStop.setBackground(Color.RED);
				}
				if (stateUhrDigital==0){
					System.out.println("Uhr_Digital()-> state0");					
					hr.setForeground(Color.RED);
					hr.setBorder(javax.swing.BorderFactory.createEmptyBorder());					
					min.setForeground(Color.RED);
					min.setBorder(javax.swing.BorderFactory.createEmptyBorder());					
					sec.setForeground(Color.RED);
					sec.setBorder(javax.swing.BorderFactory.createEmptyBorder());					
					startStop.setText("start");	
					startStop.setBackground(Color.GREEN);
				}	
			}
		}		
	}	

	
	
	Uhr_Digital(Uhr_Basis ub){
		
		super();
		this.b = ub;
		
		startStop.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				System.out.println("startStop-Button gedrückt in Uhr_Digital() state: " + t.getState());				
					
					if (startStop.getText().equals("start")){
						stateUhrDigital=1;
					}
					if (startStop.getText().equals("stop")){
						stateUhrDigital=0;
					}
				}
		});		
		
		
		
		JPanel p = new JPanel();			
		p.setLayout(new FlowLayout());
		
		hr.setEditable(false);
		min.setEditable(false);
		sec.setEditable(false);
				
	    Font f = new Font("Courier", Font.BOLD,24);
	    hr.setFont(f);	    
	    min.setFont(f);	    
	    sec.setFont(f);	    

		p.add(hr ,FlowLayout.LEFT);
		p.add(min,FlowLayout.CENTER);
		p.add(sec,FlowLayout.RIGHT);
				
		JPanel stst = new JPanel();	
		stst.add(startStop);

		p.add(stst);

		setSize(620,120);		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width  - this.getSize().width) / 2;
	    int y = (d.height - this.getSize().height) / 2;
	    //setLocation(x-550, y-215); 
	    setLocation(x-565, y-25); 
	    
	    add(p);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(true);
	    setVisible(true);
		
		t.start();
	}
}
