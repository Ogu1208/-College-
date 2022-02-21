package Action;

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
// 비밀번호 변경 클래스
public class ActionPwChange implements ActionListener{
	
	JTextField password;
	// 비밀번호 변경 생성자
	public ActionPwChange(JTextField password) {
		super();
		this.password = password;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (password.getText().equals("")) {
			// 아무것도 입력하지 않았을 경우
			JOptionPane.showMessageDialog(new JFrame(), "바꾸실 비밀번호를 입력해주세요");
		} 
		else if(password.getText().length() < 8) {
			//8자리 이상 입력하지 않았을 경우
			JOptionPane.showMessageDialog(new JFrame(), "8자리 이상 입력해주세요");
		}
		
		else {
			// 8자리 이상이고
			boolean pattern = Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", password.getText());
			if(pattern == false) {  // 정규식 조건에 부합하지 않으면
				// 특수문자나숫자가 포함되지 않으면 포함되지 않았음을 알림
				JOptionPane.showMessageDialog(new JFrame(), "특수문자나 숫자를 하나 이상 포함해 주세요");
			}
			else {
				// 8자리 이상이고 정규식 조건에 부합하면
				Admin.setPassword(password.getText());  // 비밀번호를 변경
				JOptionPane.showMessageDialog(new JFrame(),   // 변경했음을 알림
						password.getText() + " 로 변경했습니다");
			}
			
		}
	}
}
