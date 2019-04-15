
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class Draw extends JComponent {
private Image imaage;
private Graphics2D g2;

private int curX, curY, oldY, oldX;

public Draw() {
	setDoubleBuffered(false);
	addMouseListener(new MouseAdapter() {	

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			oldX = e.getX();
			oldY = e.getY();
		}
		 
	});
	
	addMouseMotionListener(new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			curX = e.getX();
			curY=e.getY();
			if (g2 != null) {
				g2.drawLine(oldX, oldY, curY, curX);
				repaint();
				oldX = curX;
				oldY = curY;
				
			}
		}
	});
	
	
	
	
}
protected void paintCom(Graphics g) {
	if (imaage ==null) {
		imaage = createImage(getSize().width, getSize().height);
		g2 = (Graphics2D) imaage.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		clear();
	}
	g.drawImage(imaage,0,0,null);
	
}


public void clear() {
	g2.setPaint(Color.white);
	g2.fillRect(0, 0, getSize().width, getSize().height);
	g2.setPaint(Color.black);
	repaint();
}









}
