package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import Can.CanArray;
import Coin.CoinArray;

public class ActionCheck implements ActionListener {
	List<JComboBox> selectList;
	
	public ActionCheck(List<JComboBox> selectList) {
		super();
		this.selectList = selectList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JCheckBox c = (JCheckBox)obj;
		
		if(selectList.size()>=5){	
			if(c.isSelected()){
				for(int i=0;i<selectList.size();i++){
					if(CanArray.canList.get(i).getCanName().equals(c.getLabel())){
						selectList.get(i).setVisible(true);
					}
				}
			} else {
				for(int i=0;i<selectList.size();i++){
					if(CanArray.canList.get(i).getCanName().equals(c.getLabel())){
						selectList.get(i).setVisible(false);
					}
				}
			}
			
			if(c.isSelected()){
				for(int i=0;i<selectList.size();i++){
					if(CoinArray.coinList.get(i).getCoinName().equals(c.getLabel())){
						selectList.get(i).setVisible(true);
					}
				}
			} else {
				for(int i=0;i<selectList.size();i++){
					if(CoinArray.coinList.get(i).getCoinName().equals(c.getLabel())){
						selectList.get(i).setVisible(false);
					}
				}
			}
			
		} 
		
		
	}

}
