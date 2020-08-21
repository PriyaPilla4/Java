/**
StoreImages class stores images and has methods for adding images, and Action listener

@author Priya Pilla
@version 1.0

COP2253	Project #: 6
File Name: StoreImages.java
*/
package ppillaproj6;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class StoreImages implements ActionListener {
	
	String backOfCard = "back-of-card.png";
	String[] imageList = {"Ada-Lovelace.jpg", "ADM-Grace-Hopper.jpg", "Alan-Turing.jpg", "Bjarne-Straustroup.jpg","Dennis-Ritchie.jpg", "James-Gosling.jpg", "Joe-ORourke.jpg", "Steve-Wozniak.jpg", "Ada-Lovelace.jpg", "ADM-Grace-Hopper.jpg", "Alan-Turing.jpg", "Bjarne-Straustroup.jpg","Dennis-Ritchie.jpg", "James-Gosling.jpg", "Joe-ORourke.jpg", "Steve-Wozniak.jpg" };
	ImageIcon[] icon = new ImageIcon[16];
	Image[] newimg = new Image[16];
	Image[] img = new Image[16];
	ImageIcon[] iconB = new ImageIcon[16];
	Image[] newimgB = new Image[16];
	Image[] imgB = new Image[16];
	JButton[] button = new JButton[16];
	ImageIcon tempIcon = new ImageIcon();
	int j = 0;
	int b = 0;
	int temp = 0;
	 // Construct the JFrame object
    JFrame frame = new JFrame();
    GridLayout flow = new GridLayout(4,4);
    Timer timer = new Timer();
    
    /**
    Method adds default image to cards
    @return void
    */
    
    public void addBackOfCard() {
    	
    	for(int i = 0; i < 16; i++) {	
			icon[i] = new ImageIcon(backOfCard);
			img[i] = icon[i].getImage();
			newimg[i] = img[i].getScaledInstance( 115, 115,  java.awt.Image.SCALE_SMOOTH ) ;  
			icon[i] = new ImageIcon( newimg[i] );
			button[i] = new JButton(icon[i]);
			button[i].addActionListener(this);
    	}
    }
	
    /**
    Method adds computer scientist images to cards
    @return void
    */
	public void addImages() {
		
		 for(int i = 0; i < 16; i++) {
				
			iconB[i] = new ImageIcon(imageList[i]);
			imgB[i] = iconB[i].getImage();
			newimgB[i] = imgB[i].getScaledInstance( 115, 115,  java.awt.Image.SCALE_SMOOTH ) ;  
			iconB[i] = new ImageIcon( newimgB[i] );
			
		}
	}
	
	 /**
    Method creates JFrame
    @return void
    */
	
	public void frame() {
		
		for(int k = 0; k < 16; k++) {
		 	 frame.add(button[k]); 
		   }
	
	      frame.setLayout(flow);
	      frame.setSize(500, 500);
	      frame.setTitle("Where is your favorite Computer Scientist?");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	      
	}

	 /**
    Method performs action listener
    @return void
    */

	@Override
	public void actionPerformed(ActionEvent e) {

		for(int h = 0; h < 16; h++) {
			
			JButton sourceEvent = (JButton) e.getSource();
			
			if(sourceEvent.equals( button[h])) {

					if(imageList[temp].equals(imageList[h]) && (!button[temp].equals(button[h]))) {
						
						button[h].setIcon(iconB[h]);
						JOptionPane.showMessageDialog(null, "Match!");
						
						//timer delay
						
							try {
							    TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException ie) {
							    Thread.currentThread().interrupt();
							}
							
							button[h].setVisible(false);
							button[temp].setVisible(false);
						
					}else {
						if(button[h].getIcon().equals(icon[h])) {
							
							button[h].setIcon(iconB[h]);
					
						}else {
							
							button[h].setIcon(icon[h]);
						}
					}
				
				tempIcon = icon[h];
				temp = h;
				
				break;
			}
		}
		
	}

}

