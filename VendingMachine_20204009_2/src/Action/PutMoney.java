package Action;

// 금액 투입 클래스 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Can.CanArray;
import Machine.MachinePanelRight;
import Person.Admin;

public class PutMoney implements ActionListener {

	JTextField putMoneytext, takeMoneytext;
	List<JButton> blist;

	// 금액 투입 클래스 생성자
	public PutMoney(JTextField putMoneytext, JTextField takeMoneytext, List<JButton> blist) {
		super();
		this.putMoneytext = putMoneytext;
		this.takeMoneytext = takeMoneytext;
		this.blist = blist;
	}
	
	public PutMoney(int i, int count) {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 정규식 조건문
		if (putMoneytext.getText().equals("")) {
			JOptionPane.showMessageDialog(new JFrame(), "돈을 넣지 않았습니다.");
		} 
		else if (Pattern.matches("^[0-9]*$", putMoneytext.getText())) {

			boolean pattern;

			if (Integer.parseInt(putMoneytext.getText()) < 100) {
				// 100원 이하면
				pattern = Pattern.matches("[0,5]?0$", putMoneytext.getText());
			} 
			else if (Integer.parseInt(putMoneytext.getText()) <= 5000) {
				//5000원 이하면
				pattern = Pattern.matches("[1-9]((\\d){0,2}[0,5])?0$", putMoneytext.getText());
			} 
			else {
				// 투입 가능 금액 초과면
				pattern = false;
			}

			if (pattern == false) { // 투입 가능 금액 초과면
				JOptionPane.showMessageDialog(new JFrame(), "5000원 이하로 투입할 수 있습니다.");
			} else {

				takeMoneytext.setText(String
						.valueOf(Integer.parseInt(takeMoneytext.getText()) + Integer.parseInt(putMoneytext.getText())));
				for (int i = 0; i < blist.size(); i++) {
					if (blist.get(i).getLabel().equals(CanArray.canList.get(i).getCanName())) {
						if(CanArray.canList.get(i).getCanNum() == 0){  // 재고가 없으면
							blist.get(i).setForeground(new Color(255, 255, 255));
							blist.get(i).setBackground(new Color(204, 61, 61)); // 빨간색
						}
						if (CanArray.canList.get(i).getCanPrice() <= Integer.parseInt(takeMoneytext.getText())
								&& CanArray.canList.get(i).getCanNum() > 0) {  // 재고가 있고 투입금액이 충분하면
							blist.get(i).setForeground(new Color(255, 255, 255));
							blist.get(i).setBackground(new Color(20, 175, 100));
						}
					}
				}
				
				// 총매출액 다시 Admin에서 설정 후 총 RightPanel에 총매출액 다시 세팅
				Admin.setTotalMoney(Admin.getTotalMoney() + Integer.parseInt(putMoneytext.getText()));
				MachinePanelRight.totalMoneyLabel.setText("총 매출액 : " + Admin.getTotalMoney());

				putMoneytext.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "숫자 형식으로 입력하세요");
		}
	}
}
