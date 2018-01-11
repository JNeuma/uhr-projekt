package watch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Uhr_Start extends JFrame implements MouseMotionListener {
 
	protected static JLabel label;
	public static JButton digitalButton;
	
	Uhr_Start() { 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 550);
        setLayout(new BorderLayout());
        add(getPanel(), BorderLayout.SOUTH);
        digitalButton  = new JButton("Digital");
    }
 
	
	

    public static void main(String[] args) {  	
    	Uhr_Basis clock = new Uhr_Basis_x();
	    JFrame frame    = new Uhr_GoMenue(clock);
	    frame.setSize(500, 550);
	    
		JPanel    mainPanel   = new JPanel();		
		mainPanel.setLayout(new BorderLayout());
		
		
		JPanel northPanel = new JPanel();	
		northPanel.add("Center",digitalButton);	
		
		mainPanel.add("North",northPanel);	
		mainPanel.add("Center", clock);
		
		System.out.println("Uhr_Start()-> sec: " + clock.heute.get(Calendar.SECOND));		
	 
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width  - frame.getSize().width) / 2;
	    int y = (d.height - frame.getSize().height) / 2;
	    frame.setLocation(x, y);    
	    frame.add(mainPanel);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	    frame.setVisible(true);
    }
        
    protected JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        Icon icon = new ImageIcon( "D://PC//Software//Java//Kurs1618OOP//playground//src//eu//de//tnd//juo//seitenverhaeltnisFenster//lock.gif" );
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(15,15));
        label.addMouseMotionListener(this);
        panel.setSize(30, 30);
        panel.add(label, BorderLayout.EAST);
        return panel;
    }
    
    public void mouseDragged(MouseEvent e) {
        int x = e.getXOnScreen()/2;
        int y = e.getYOnScreen()/2;
        y = x+50;
        this.setSize(x,y);
    }
 
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouse moved!");
    } 
}