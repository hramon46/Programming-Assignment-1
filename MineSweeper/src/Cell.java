import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;



public class Cell implements ActionListener{
	private JButton button;
	private Board board;
	private int value;
	private int id;
	private boolean notChecked;



	public Cell(Board board){
		button = new JButton();
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(20,20));
		button.setMargin(new Insets(0,0,0,0));
		this.board = board;
		notChecked = true;

	}

	public JButton getButton() {
		return button;
	}


	public int getValue() {
		return value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void displayValue(){
		if(value==-1){
			button.setBackground(Color.BLACK);

		}
		else if(value!=0){
			button.setText(String.valueOf(value));
			button.setBackground(Color.GRAY);
		}
	}

	public void checkCell(){
		button.setEnabled(false);
		displayValue();
		notChecked = false;
		if(value == 0) 
			board.scanForEmptyCells();
		button.setBackground(Color.GRAY);
		if(value == -1){ 
			board.fail();
			JOptionPane.showMessageDialog(null, "GameOver. YOU LOSE", "Game Over", 0);
		}


	}

	public void incrementValue(){
		value++;
	}

	public boolean isNotChecked(){
		return notChecked;
	}

	public boolean isEmpty(){
		return isNotChecked() && value==0;
	}

	public void reveal(){
		displayValue();
		button.setEnabled(false);
	}



	public void actionPerformed(ActionEvent e) {
		checkCell();
	}






}