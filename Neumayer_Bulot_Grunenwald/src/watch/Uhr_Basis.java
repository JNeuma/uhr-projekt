package watch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPanel;



public class Uhr_Basis extends JPanel implements Runnable {

 
    private static final long serialVersionUID = 302743725004371394L; 
    protected Point centerPoint    = new Point(250, 250); 
    protected Point secondPoint_a  = new Point(0,0);
    //private   Point secondPoint_b  = new Point(0,0);	//Stummel Sekunden
    private   Point minutePoint    = new Point(0,0);
    private   Point hourPoint      = new Point(0,0);        
    BasicStroke basicstroke;
    //private   BasicStroke STROKE_3 = new BasicStroke(3);   //Dicke Ziffernblatt
    private   BasicStroke STROKE_5 = new BasicStroke(5);   //Dicke Minuten- und Stundenzeiger    
    private   Thread thread;
    protected Graphics2D g2d;
   // private   Color colorSecondHand;
    private int xM;
    private int yM;
    private int lengthSecondHand;
    private int lengthMinuteHand;
    private int lengthHourHand;
    private int lengthFactor;
    protected int angleSec;
    protected int angleMin;
    protected int angleHou;
    protected GregorianCalendar heute;
    JPanel p = new JPanel();
    protected String str_sec;
    protected int stateUhrBasis;
    



   
    public Uhr_Basis() {   	
    	thread = new Thread(this);
    	thread.start();
    	lengthSecondHand = 23;
    	lengthMinuteHand = 19;
    	lengthHourHand   = 12;
    	basicstroke   = new BasicStroke(5);  
    	lengthFactor  = 10;
    	stateUhrBasis = 1;
    } 
    
    public void set_stateUhrBasis(int state){
    	this.stateUhrBasis = state;    	
    }  
         
    
    public void paintComponent(Graphics g) {	    
	    super.paintComponent(g);
	    
	    Dimension d = getSize();
		int w = d.width;
		int h = d.height;	
		lengthFactor = w/50;

		xM = w/2;
		yM = h/2;
	    g2d = (Graphics2D) g;	    

        
        // Striche bei 3, 6, 9 und 12 Uhr  
        int r = h/2;
    	//int r = 245;
        ((Graphics2D) g).setStroke(new BasicStroke(18));
        for (int i=0;i<4;i++) {
        	g.drawLine(
        			xM+(int)((r-17)*Math.cos(Math.toRadians(90*i))),
        			xM+(int)((r-17)*Math.sin(Math.toRadians(90*i))),
        			xM+(int)(r*Math.cos(Math.toRadians(90*i))),
        			xM+(int)(r*Math.sin(Math.toRadians(90*i))));
        }      
        
	    // background
	    g2d.setColor(Color.BLACK);
	    g2d.fillOval(0, 0, 500, 470);
	 
	    // border
	    g2d.setColor(Color.WHITE);
	    //g2d.setStroke(STROKE_3);
	    g2d.drawOval(0, 0, w, h);
	    	    
	    // minutes
	    g2d.setStroke(STROKE_5);
	    g2d.drawLine(xM, yM, minutePoint.x, minutePoint.y);
	 
	    // hours
	    g2d.setStroke(STROKE_5);
	    g2d.drawLine(xM, yM, hourPoint.x, hourPoint.y);
	 
	    // seconds
	    g2d.setStroke(basicstroke);
	    g2d.drawLine(xM, yM, secondPoint_a.x, secondPoint_a.y);
	    
	}
 
    @Override
    public void run() {
 
	    while (true) {
	
	        heute = new GregorianCalendar();

	        try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        
	        secondPoint_a.x = ((int) ( lengthSecondHand * (lengthFactor) * Math.sin(Math.toRadians(angleSec))) + xM);
	        secondPoint_a.y = ((int) (-lengthSecondHand * (lengthFactor) * Math.cos(Math.toRadians(angleSec))) + yM);	 

	        
	        angleMin = (360 / 60) * heute.get(Calendar.MINUTE);	
	        minutePoint.x = ((int) ( lengthMinuteHand * (lengthFactor) * Math.sin(Math.toRadians(angleMin))) + xM);
	        minutePoint.y = ((int) (-lengthMinuteHand * (lengthFactor) * Math.cos(Math.toRadians(angleMin))) + yM);

	        
	        angleHou = (int) (((360 / 12) * heute.get(Calendar.HOUR_OF_DAY)) + (((double) (30d / 60d)) * heute.get(Calendar.MINUTE)));	 
	        hourPoint.x = ((int) ( lengthHourHand * (lengthFactor) * Math.sin(Math.toRadians(angleHou))) + xM);
	        hourPoint.y = ((int) (-lengthHourHand * (lengthFactor) * Math.cos(Math.toRadians(angleHou))) + yM);
	 
	        if (stateUhrBasis==1)
	        	repaint();
	    }
    }
}