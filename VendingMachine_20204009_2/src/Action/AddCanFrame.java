package Action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Can.CanArray;

public class AddCanFrame extends JFrame implements ActionListener{

	JCheckBox canCheck;
	JButton canOk, canClose;
	JTable canTable;
	DefaultTableModel canModel;
	
	public AddCanFrame(JTable canTable){		
		this.canTable = canTable;
	}
	
	public void addCanFrame(JTable canTable){		
		this.canTable = canTable;
		
		List<JCheckBox> canCheckList = new ArrayList<JCheckBox>();  // 체크박스
		List<JComboBox> canSelectList = new ArrayList<JComboBox>();  // 콤보박스
		
		setTitle("음료 추가 팝업");
		setSize(320, 300);
		setLocation(200,200);
		
		// title
		JLabel label = new JLabel("음료를 채우세요");
		JPanel toPanel = new JPanel();
		toPanel.add(label);
		add(toPanel, BorderLayout.NORTH);
		
		// 체크박스 추가
		JPanel checkPanel = new JPanel();
		JButton chechAll = new JButton("전체10개추가");
		chechAll.setPreferredSize(new Dimension(120, 10));
		chechAll.addActionListener(new ActionSelectAll(canCheckList));
		
		String[] canSelectNum = {"10개","15개","20개"};  // 음료 재교 추가 개수 목록
		
		for(int i=0; i<CanArray.canList.size();i++){
			JPanel checkEach = new JPanel();
			canCheck = new JCheckBox(CanArray.canList.get(i).getCanName()); // 음료 이름 체크박스
			JComboBox<String> canSelectBox = new JComboBox();  // 음료 재고 추가 개수 목록을 콤보박스로 보여줌
			for(int j=0; j<canSelectNum.length; j++){
				canSelectBox.addItem(canSelectNum[j]);
			}
			
			//판넬에 각 요소 넣어주기
			canCheckList.add(canCheck);
			canSelectList.add(canSelectBox);

			checkEach.add(canCheck);
			checkEach.add(canSelectBox);
			canSelectBox.setVisible(false);
			
			checkPanel.add(checkEach);
			canCheck.addActionListener(new ActionCheck(canSelectList));
			checkEach.setPreferredSize(new Dimension(160, 30));
		}
		
		
		// 10개추가, 콤보박스 추가 레이아웃 설정
		add(chechAll, BorderLayout.WEST);  
		add(checkPanel, BorderLayout.CENTER);
		
		//추가, 취소 버튼 아래에 배치, 레이아웃 설정
		JButton btnAdd = new JButton("추가");
		JButton btnCancle = new JButton("취소");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnCancle);
		add(buttonPanel, BorderLayout.SOUTH);
		btnAdd.addActionListener(new ActionAddCan(this, chechAll, canCheckList, canSelectList));
		btnCancle.addActionListener(new ActionCancle(this));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addCanFrame(canTable);
	}
}
