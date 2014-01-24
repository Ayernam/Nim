import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Nim extends Applet implements MouseListener, MouseMotionListener{
        boolean drawLine = false;
        boolean greenPlayerTurn = false;
        Point[] line = new Point[]{new Point(0,0), new Point(0,0)};
        Point[] circle = new Point[]{new Point(150,50), new Point(125,100), new Point(175,100), new Point(100,150), new Point(150,150), new Point(200,150), new Point(75,200), new Point(125,200), new Point(175,200), new Point(225,200), new Point(50,250), new Point(100,250), new Point(150,250), new Point(200,250), new Point(250,250)};
        Color[] color = new Color[]{Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black,Color.black};
        String p1name = "Player one", p2name = "Player two";
        int[] score = new int[]{0,0};
        public void init()
        {
            addMouseListener(this);
            addMouseMotionListener(this);
            resetGame();
        }
        public void paint(Graphics page) {
        	//Set size and background
        	setBackground(Color.white);
			setSize(750, 500);
        	page.setColor(Color.black);
        	page.drawRect(25, 25, 275, 275);

            //Draw circles
            for(int i=0; i<15; i++){
            	page.setColor(color[i]);
            	page.fillOval(circle[i].x, circle[i].y, 25, 25);
            }
            
            //Draw line
            page.setColor(Color.blue);
            if(drawLine){
            	page.drawLine(line[0].x, line[0].y, line[1].x, line[1].y);
            	drawLine = false;
            }
            
    		//Draw table
    		page.setColor(Color.black);
    		page.drawRect(350, 25, 200, 275);
    		page.drawRect(350, 25, 200, 50);
    		page.drawRect(350, 75, 100, 100);
    		page.drawRect(450, 75, 100, 100);
    		page.drawRect(350, 75, 200, 50);
    		page.drawString("Scores", 430, 50);
    		page.drawString(p1name, 370, 100);
    		page.drawString(p2name, 470, 100);
    		page.drawString(String.valueOf(score[0]), 395, 150);
    		page.drawString(String.valueOf(score[1]), 495, 150);
        }

        @Override
        public void mouseDragged(MouseEvent drag) {
        	// TODO Auto-generated method stub                
            if(drag.getX()>25 && drag.getX()<300 && drag.getY()>50 && drag.getY()<300){
                    line[1].x=drag.getX();
                    line[1].y=line[0].y;
            }
            drawLine=true;
            
            for(int i=0; i<15; i++){
            	if((circle[i].x>line[0].x && circle[i].x<line[1].x && circle[i].y<line[0].y && circle[i].y+25>line[0].y) || (circle[i].x<line[0].x && circle[i].x+25>line[1].x && circle[i].y<line[0].y && circle[i].y+25>line[0].y)){
                    color[i]=greenPlayerTurn ? Color.green : Color.red;
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
            if(click.getX()>25 && click.getX()<300 && click.getY()>50 && click.getY()<300){
            	line[0].x=click.getX();
                line[0].y=50*((int) click.getY()/50)+10;
            }
        }

        @Override
        public void mouseReleased(MouseEvent release) {
            // TODO Auto-generated method stub
            if(release.getX()>25 && release.getX()<300 && release.getY()>50 && release.getY()<300){
                line[1].x=release.getX();
                line[1].y=line[0].y;
            }
            drawLine=true;
            repaint();
            turnEnd();
        }
        
        public void turnEnd(){
            int selectedCircles = 0, inPlayCircles = 15;
            for(int i=0; i<15; i++){
                if(color[i]!=Color.black){
                    selectedCircles++;
                    circle[i].x=100000;
                    circle[i].y=100000;
                }
            	if(circle[i].x >= 1000){
            		inPlayCircles--;
            	}
            }
            if(selectedCircles != 0){
            	if(inPlayCircles == 0){
            		endGame();
            	} else {
                    greenPlayerTurn = !greenPlayerTurn;
            	}
            }
        }
        public void endGame(){
        	if(greenPlayerTurn){
        		score[0]++; //Red score
        	} else {
        		score[1]++; //Green score
        	}
        	//JOptionPane.showMessageDialog(this, "Game Over!" + (greenPlayerTurn ? " Green Player is victorious!" : " Red Player is victorious!"));
        	Object[] options = {"New Game","Exit"};
        	int n = JOptionPane.showOptionDialog(this,"Game Over!" + (!greenPlayerTurn ? " Green Player is victorious!" : " Red Player is victorious!") + '\n' + "Would you like to play again?", "Game over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        	System.out.println("gameover");
        	System.out.println(n);
        	if(n == 0){
        		resetGame();
        		repaint();
        	} else {
        		System.exit(0);
        	}
        }
        
        public void resetGame(){
        	for(int i = 0; i < 15; i++){
    			color[i] = Color.black;
    		}
    		circle = new Point[]{new Point(150,50), new Point(125,100), new Point(175,100), new Point(100,150), new Point(150,150), new Point(200,150), new Point(75,200), new Point(125,200), new Point(175,200), new Point(225,200), new Point(50,250), new Point(100,250), new Point(150,250), new Point(200,250), new Point(250,250)};
    		p1name = JOptionPane.showInputDialog("What is player one's name?", p1name);
    		p2name = JOptionPane.showInputDialog("What is player two's name?", p2name);
        }
}
