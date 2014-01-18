import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Nim extends Applet implements MouseListener, MouseMotionListener{
		long turnEndC = 0;
        boolean drawLine = false;
        boolean greenPlayerTurn = false;
        Point[] line = new Point[]{new Point(0,0), new Point(0,0)};
        Point[] circle = new Point[]{new Point(150,50), new Point(125,100), new Point(175,100), new Point(100,150), new Point(150,150), new Point(200,150), new Point(75,200), new Point(125,200), new Point(175,200), new Point(225,200), new Point(50,250), new Point(100,250), new Point(150,250), new Point(200,250), new Point(250,250)};
        Color[] color = new Color[]{Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black};
        public void init()
        {
            addMouseListener(this);
            addMouseMotionListener(this);
            
        }
        public void paint(Graphics page)
        {        
                //Add listeners
            
            //Set size and background
                setBackground(Color.white);
                setSize(500, 500);
                
                //Draw board
                page.setColor(Color.black);
                page.drawRect(25, 25, 275, 275);
                //Draw circles
                for(int i=0; i<15; i++){
                        page.setColor(color[i]);
                        page.fillOval(circle[i].x, circle[i].y, 25, 25);
                }
                
                /*
                page.fillOval(150, 50, 25, 25);
                for(int i=125; i<=175; i+=50){
                        page.fillOval(i, 100, 25, 25);
                }
                for(int i=100; i<=200; i+=50){
                        page.fillOval(i, 150, 25, 25);
                }
                for(int i=75; i<=225; i+=50){
                        page.fillOval(i, 200, 25, 25);
                }
                for(int i=50; i<=250; i+=50){
                        page.fillOval(i, 250, 25, 25);
                }
                */
                
                //Draw line
                page.setColor(Color.blue);
                if(drawLine){
                        page.drawLine(line[0].x, line[0].y, line[1].x, line[1].y);
                }
                
        }

        @Override
        public void mouseDragged(MouseEvent drag) {
                // TODO Auto-generated method stub                
                if(drag.getX()>25 && drag.getX()<300 && drag.getY()>25 && drag.getY()<300){
                        line[1].x=drag.getX();
                        line[1].y=line[0].y;
                }
                drawLine=true;
                
                for(int i=0; i<15; i++){
                        if((circle[i].x>line[0].x && circle[i].x<line[1].x && circle[i].y<line[0].y && circle[i].y+25>line[0].y) || (circle[i].x<line[0].x && circle[i].x>line[1].x && circle[i].y<line[0].y && circle[i].y+25>line[0].y)){
                                color[i]= greenPlayerTurn ? Color.green: Color.red;
                        } else {
                                color[i]=Color.black;
                        }
                }
                
                repaint();
        }

        @Override
        public void mouseMoved(MouseEvent drag) {
                // TODO Auto-generated method stub

        }

        @Override
        public void mouseClicked(MouseEvent click) {
                // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mousePressed(MouseEvent click) {
                // TODO Auto-generated method stub
                if(click.getX()>25 && click.getX()<300 && click.getY()>25 && click.getY()<300){
                        line[0].x=click.getX();
                        line[0].y=50*((int) click.getY()/50)+10;
                }
        }

        @Override
        public void mouseReleased(MouseEvent release) {
                // TODO Auto-generated method stub
                if(release.getX()>25 && release.getX()<300 && release.getY()>25 && release.getY()<300){
                        line[1].x=release.getX();
                        line[1].y=line[0].y;
                }
                drawLine=true;
                repaint();
                turnEnd();
        }
        
        public void turnEnd(){
        	System.out.println(greenPlayerTurn);
        	System.out.println(turnEndC++);
                //int[] circ = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                for(int i=0; i<15; i++){
                        if(color[i]!=Color.black){
                                circle[i].x=100000;
                                circle[i].y=100000;
                        }
                }
                drawLine = false;
                greenPlayerTurn = !greenPlayerTurn;
        }
}
