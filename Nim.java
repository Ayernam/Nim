import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

public class Nim extends Applet implements MouseListener, MouseMotionListener{
	boolean drawLineOnly = false;
	boolean isFirstDraw = true;
		int circlesPainted, lastCirclesPainted = 0;
                long turnEndC = 0;
                int lastCirclesInPlay = 15,circlesInPlay = 15;
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
        	if(isFirstDraw){
                setBackground(Color.white);
                setSize(500, 500);
                
                //Draw board
                isFirstDraw = false;
        	}
            page.setColor(Color.black);
            page.drawRect(25, 25, 275, 275);
        	//if(!drawLineOnly){
                //Draw circles
                for(int i=0; i<15; i++){
                        page.setColor(color[i]);
                        page.fillOval(circle[i].x, circle[i].y, 25, 25);
                }
                
                //Draw line
                page.setColor(Color.blue);
                if(drawLine){
                        page.drawLine(line[0].x, line[0].y, line[1].x, line[1].y);
                }
        //	} else {
        //		page.setColor(Color.blue);
        //		page.drawLine(line[0].x, line[0].y, line[1].x, line[1].y);
        //		drawLineOnly = false;
        //	}
                
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
                                    color[i]= greenPlayerTurn ? Color.green : Color.red;
                                    circlesPainted++;
                            } else {
                                    color[i]=Color.black;
                            }
                    }
                    if(circlesPainted != lastCirclesPainted){
                    	System.out.println("cirlcesPainted" + circlesPainted);
                    	System.out.println("lastCirclesPainted" + lastCirclesPainted);
                    	lastCirclesPainted = circlesPainted;
                    	drawLineOnly = true;
                    	repaint();
                    }
                	circlesPainted = 0;
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
                int selectedCircles = 0;
                for(int i=0; i<15; i++){
                        if(color[i]!=Color.black){
                                        selectedCircles++;
                                circle[i].x=100000;
                                circle[i].y=100000;
                        }
                }
                int inPlayCircles = 15;
                for(int i = 0; i < 15; i++){
                	if(circle[i].x >= 1000){
                		inPlayCircles--;
                	}
                }
                drawLine = false;
                if(selectedCircles != 0){
                	if(inPlayCircles == 0){
                		endGame();
                	} else {
                        greenPlayerTurn = !greenPlayerTurn;
                        lastCirclesInPlay = circlesInPlay;
                
                	}
                }
        }
        public void endGame(){
        	//JOptionPane.showMessageDialog(this, "Game Over!" + (greenPlayerTurn ? " Green Player is victorious!" : " Red Player is victorious!"));
        	Object[] options = {"New Game","Exit"};
        	int n = JOptionPane.showOptionDialog(this,"Game Over!" + (greenPlayerTurn ? " Green Player is victorious!" : " Red Player is victorious!") + '\n' + "Would you like to play again?", "Game over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        	System.out.println("gameover");
        	System.out.println(n);
        	if(n == 0){
        		for(int i = 0; i < 15; i++){
        			color[i] = Color.black;
        		}
        		circle = new Point[]{new Point(150,50), new Point(125,100), new Point(175,100), new Point(100,150), new Point(150,150), new Point(200,150), new Point(75,200), new Point(125,200), new Point(175,200), new Point(225,200), new Point(50,250), new Point(100,250), new Point(150,250), new Point(200,250), new Point(250,250)};
        		isFirstDraw = true;
        		repaint();
        	} else {
        		System.exit(0);
        	}
        }
}
