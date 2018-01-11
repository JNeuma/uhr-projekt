package watch;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Uhr_GoMenue extends Uhr_Start implements ActionListener{
	
	Uhr_Basis b;
		
	public Uhr_GoMenue(Uhr_Basis b){			
		super();	
		this.b=b;
		digitalButton.addActionListener(this);
	}
		
	public void actionPerformed(ActionEvent e) {		
		if (e.getActionCommand().equals("Digital")){
			System.out.println("Digital-button gedrückt");
			new Uhr_Digital(b);
			setVisible(true);
		}				
	}
}
