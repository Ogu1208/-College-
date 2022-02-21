package Action;

// 수금 클래스
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.util.regex.Pattern;

import Machine.MachineFrame;
import Machine.MachinePanelRight;
import Machine.MachineMain;
import Person.Admin;

public class ActionCollectMoney implements ActionListener{
	JTextField money;
	// 수금 클래스 생성자
	public ActionCollectMoney(JTextField money) {
		super();
		this.money = money;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int Money = Integer.parseInt(money.getText());  // int형으로 변환

		if (Money <= Admin.getTotalMoney()) {
			// 수금할 금액이 총액보다 적으면 수금 가능, 총액에서 -
			Admin.setTotalMoney(Admin.getTotalMoney() - Money);
			MachinePanelRight.totalMoneyLabel.setText("총 매출액 : " + Admin.getTotalMoney());
			JOptionPane.showMessageDialog(new JFrame(), Money + "원을 수금했습니다.");
			
		} 
		
		else {
			// 수금할 금액이 총액보다 많으면 수금 불가능
			JOptionPane.showMessageDialog(new JFrame(), "초과한 금액을 수금할 수 없습니다.\n "
					+ "수금가능금액 : " + Admin.getTotalMoney() + "원");
			
		}
	}
}
