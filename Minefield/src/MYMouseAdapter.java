import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MYMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	private int id;
	private Component button;
	private int value;
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			GamePanel myPanel = (GamePanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame)c;
		GamePanel myPanel = (GamePanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);
	
		
		switch (e.getButton()) {
		case 1:		//Left mouse button


		if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
			//Had pressed outside
			//Do nothing
		} 
		else {
			if ((gridX == -1) || (gridY == -1)) {
				//Is releasing outside
				//Do nothing
			} 
			else {
				if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
					//Released the mouse button on a different cell where it was pressed
					//Do nothing
				} 
				else {
					//Released the mouse button on the same cell where it was pressed
					if ((gridX == 0) || (gridY == 0)) {
						//On the left column and on the top row... do nothing
						Color newColor = null;
						switch (generator.nextInt(2)) {
						case 0:
							newColor = Color.GRAY;
							break;

						case 1:
							newColor = Color.BLACK;
							break;
						}
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
						myPanel.repaint();
					} 
					else {
						//On the grid other than on the left column and on the top row:
						Color newColor = null;
						switch (generator.nextInt(2)) {
						case 0:
							newColor = Color.GRAY;
							break;

						case 1:
							newColor = Color.BLACK;
							break;
						}
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
						myPanel.repaint();
					}
				}
			}
		}
		myPanel.repaint();
		break;
		case 3:		//Right mouse button
			if ((myPanel.mouseDownGridX == 0) || (myPanel.mouseDownGridY == 0)) {

				if ((gridX <= 9) || (gridY <= 9)) {

					Color newColor = null;
					newColor = Color.RED;


					for(int i= 1;i < 9; i++){
						for(int j=1; j<9; j++){
							myPanel.colorArray[i][j] = newColor;
							myPanel.repaint();
						}
					}
				}
			}

			myPanel.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public Color randomColor(Color color){

		switch (generator.nextInt(5)) {
		case 0:
			color = Color.GRAY;
			break;

		case 1:
			color = Color.BLACK;
			break;
		}
		return color;
	}
	 public int getId() {
	        return id;
	    }
	 public void displayValue(){
	        if(value==-1){
//	            button.setText("\u2600");
	            button.setBackground(Color.BLACK);
	        }else if(value!=0){
	            ((GamePanel) button).setToolTipText(String.valueOf(value));
	            button.setBackground(Color.GRAY);
	            }
	    }
	 public void reveal(){
	        displayValue();
	        button.setEnabled(false);
	    }
	 public void setValue(int value) {
	        this.value = value;
	    }
}