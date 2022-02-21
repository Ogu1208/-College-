package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// 음료 반환구 Action
public class TakeCan implements ActionListener {

	JButton getCan;
	
	public TakeCan(JButton getCan) {
		super();
		this.getCan = getCan;
	}


	@Override
	public void actionPerformed(ActionEvent e) {  // 음료 반환구 버튼에서 음료를 가져가면
		getCan.setIcon(new ImageIcon("canreturn.png"));  //기본 검은색 화면으로 설정
		JOptionPane.showMessageDialog(new JFrame(), "감사합니다.");  // 감사합니다 문구 메시지
	}

}
