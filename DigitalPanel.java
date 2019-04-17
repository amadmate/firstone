/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe_26;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.geom.GeneralPath;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Peter Heusch
 */
public class DigitalPanel extends javax.swing.JPanel {

    private final Window myparent;
    private Calendar lastTime;
    private int height;
    private int width;
    private int line;
    private int gap;
    private int digitgap;
    private int blockgap;
    private int pointgap;
    private Color foreground;
    private Color background;

    private final int OFFSET_FIRST_HOUR = -40;
    private final int OFFSET_SECOND_HOUR = 150;
    private final int OFFSET_FIRST_MINUTE = 300;
    private final int OFFSET_SECOND_MINUTE = 500;
    private final int OFFSET_FIRST_SECOND = 700;
    private final int OFFSET_SECOND_SECOND = 900;

    /**
     * Creates new form ClockPanel
     */
    public DigitalPanel(Window parent) {
        myparent = parent;
        lastTime = Calendar.getInstance();
        initComponents();

    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        // gc.drawLine(gap, gap, gap, gap);?
        Graphics2D g2d = (Graphics2D) gc;
       
       
        //g2d.rotate(cal.get(Math.toRadians(sec)));
        int size = 300;
        int half = size / 2;
        Dimension dim = getSize();
        g2d.setColor(Color.BLACK);

        GeneralPath p = new GeneralPath();
        p.moveTo(dim.width / 2 - half, dim.height / 2 - half);

        drawDots(g2d);

        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date 
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        drawDigit(splitNumber(hour)[0], g2d, OFFSET_FIRST_HOUR);
        drawDigit(splitNumber(hour)[1], g2d, OFFSET_SECOND_HOUR);
        drawDigit(splitNumber(minute)[0], g2d, OFFSET_FIRST_MINUTE);
        drawDigit(splitNumber(minute)[1], g2d, OFFSET_SECOND_MINUTE);
        drawDigit(splitNumber(second)[0], g2d, OFFSET_FIRST_SECOND);
        drawDigit(splitNumber(second)[1], g2d, OFFSET_SECOND_SECOND);
        
    
        
    }

    private int[] splitNumber(int number) {
        int firstNumber = 0;
        int secondNumber = 0;
        if (number>60) {
            firstNumber=6;
            secondNumber=number-60;
            }else if (number>=50) {
            firstNumber = 5;
            secondNumber = number-50;
        }else if (number>=40) {
            firstNumber = 4;
            secondNumber= number-40;       
        }else if (number >=30) {
            firstNumber = 3;
            secondNumber = number-30;
        }else if (number >= 20) {
            firstNumber = 2;
            secondNumber = number - 20;
        } else if (number >= 10) {
            firstNumber = 1;
            secondNumber = number - 10;
        } else {
            secondNumber = number;
        }
        return new int[]{firstNumber, secondNumber};
    }

    private void drawDots(Graphics2D g1d) {
        // g1d.create(gap, gap, WIDTH, HEIGHT);?
        // g1d.create(gap, gap, WIDTH, HEIGHT);?
        int size = 20;
        int half = size / 2;
        int docdis = 100;
        Dimension dim = getSize();
        g1d.setColor(Color.BLACK);
        g1d.fillOval(dim.width / 3 - half, (dim.height / 3 - half) + docdis, size, size);
        g1d.fillOval(dim.width / 3 - half, dim.height / 3 - half, size, size);
        g1d.fillOval((1000  ) , (dim.height / 3 - half) + docdis, size, size);
        g1d.fillOval((1000), dim.height / 3 - half, size, size);
    }

    private void drawDigit(int digit, Graphics2D g1d, int offset) {
        // irgendwas mit paintComponent(g1d);?

        switch (digit) {
            case 0:   //vertikal
                g1d.fillRect(350 + offset, 290, 15, 100); //linksoben
                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben
                g1d.fillRect(350 + offset, 405, 15, 100);//linksunten
                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben

                g1d.fillRect(365 + offset, 505, 100, 15);//mitteunten
                repaint();
                break;
            case 1:

                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben

                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor

                repaint();
                break;
            case 2:

                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben
                g1d.fillRect(350 + offset, 405, 15, 100);//linksunten

                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben
                g1d.fillRect(365 + offset, 390, 100, 15);//mittemitte
                g1d.fillRect(365 + offset, 505, 100, 15);//mitteunten
                repaint();
                break;
            case 3:

                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben

                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben
                g1d.fillRect(365 + offset, 390, 100, 15);//mittemitte
                g1d.fillRect(365 + offset, 505, 100, 15);//mitteunten
                repaint();
                break;
            case 4:
                g1d.fillRect(350 + offset, 290, 15, 100); //linksoben
                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben

                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor

                g1d.fillRect(365 + offset, 390, 100, 15);//mittemitte

                repaint();
                break;
            case 5:
                g1d.fillRect(350 + offset, 290, 15, 100); //linksoben

                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben
                g1d.fillRect(365 + offset, 390, 100, 15);//mittemitte
                g1d.fillRect(365 + offset, 505, 100, 15);//mitteunten
                repaint();
                break;
            case 6:
                g1d.fillRect(350 + offset, 290, 15, 100); //linksoben

                g1d.fillRect(350 + offset, 405, 15, 100);//linksunten
                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben
                g1d.fillRect(365 + offset, 390, 100, 15);//mittemitte
                g1d.fillRect(365 + offset, 505, 100, 15);//mitteunten
                repaint();
                break;
            case 7:

                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben

                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben

                repaint();
                break;
            case 8:
                g1d.fillRect(350 + offset, 290, 15, 100); //linksoben
                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben
                g1d.fillRect(350 + offset, 405, 15, 100);//linksunten
                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben
                g1d.fillRect(365 + offset, 390, 100, 15);//mittemitte
                g1d.fillRect(365 + offset, 505, 100, 15);//mitteunten

                repaint();
                break;

            case 9:
                g1d.fillRect(350 + offset, 290, 15, 100); //linksoben
                g1d.fillRect(465 + offset, 290, 15, 100); //rechtsoben

                g1d.fillRect(465 + offset, 405, 15, 100); //rechtsunten
                //hor
                g1d.fillRect(365 + offset, 275, 100, 15);//mitte oben
                g1d.fillRect(365 + offset, 390, 100, 15);//mittemitte
                g1d.fillRect(365 + offset, 505, 100, 15);//mitteunten
                repaint();
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
