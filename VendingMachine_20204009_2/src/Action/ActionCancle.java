package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

// ActionCacle Å¬·¡½º 
public class ActionCancle implements ActionListener {	
	JFrame frame;	
	public ActionCancle(JFrame frame){
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
	}
	
}
