package Machine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Action.AddCanFrame;
import Action.AddCoinFrame;
import Action.ActionPwChange;
import Action.ActionCollectMoney;
import Can.CanArray;
import Coin.CoinArray;
import Person.Admin;


public class MachinePanelRight extends JPanel implements ActionListener {

	JTextField adminPass, changePW, collectMoney;
	JPanel canAdminPanel, moneyAdminPanel, moneyTotalPanel, PWPanel, collectPanel;
	JButton btnAdminIn, btnAddCanStart, btnAddCan;
	JLabel label;
	String password;
	DefaultTableModel canModel;
	public static JLabel totalMoneyLabel, PWLabel, collectLabel;
	public static JTable canTable, moneyTable;

	public MachinePanelRight(String password) {
		// 우측 관리자 판넬
		this.password = password;
		
		setPreferredSize(new Dimension(280, 630)); //윈도우창 크기 설정

		label = new JLabel("관리자모드 - 비밀번호를 입력해주세요");  // 우측판넬 기본값
		label.setVisible(true);  // visible true로 초기화

		// ------------ <음료재고 관리 판넬> ------------ //
		canAdminPanel = new JPanel(new BorderLayout());

		String[] canColName = { "음료이름", "재고", "개당 판매가격" };  // 테이블 목록
		canModel = new DefaultTableModel(canColName, 0); // JTable의 목록을 수정
		canTable = new JTable(canModel);  // JTable
		JScrollPane canScrollPanel = new JScrollPane(canTable);  // 스크롤
		canScrollPanel.setPreferredSize(new Dimension(230, 150));

		btnAddCan = new JButton("음료추가");  // 음료추가 버튼
		btnAddCan.addActionListener(new AddCanFrame(canTable));  // 음료추가 버튼 액션

		// 음료재고 관리 판넬 레이아웃 위치 설정
		canAdminPanel.add(new JLabel("음료관리"), BorderLayout.WEST);
		canAdminPanel.add(btnAddCan, BorderLayout.EAST);
		canAdminPanel.add(canScrollPanel, BorderLayout.SOUTH);
		canAdminPanel.setVisible(false);  // 기본값은 안보임

		// 음료이름|재고|개당판매가격 표 출력
		for (int i = 0; i < CanArray.canList.size(); i++) {
			String arr[] = { CanArray.canList.get(i).getCanName(),
					Integer.toString(CanArray.canList.get(i).getCanNum()),
					Integer.toString(CanArray.canList.get(i).getCanPrice()) };
			canModel.addRow(arr);
		}

		// ------------ <잔돈 관리 판넬> ------------ //
		moneyAdminPanel = new JPanel(new BorderLayout());

		String[] moneyColName = { "동전 종류", "남은 갯수", };// 테이블 목록
		DefaultTableModel moneyModel = new DefaultTableModel(moneyColName, 0);// JTable의 목록을 수정
		moneyTable = new JTable(moneyModel);
		JScrollPane moneyScrollPanel = new JScrollPane(moneyTable); // 스크롤
		moneyScrollPanel.setPreferredSize(new Dimension(230, 150));

		JButton btnAddMoney = new JButton("잔돈추가"); // 잔돈추가 버튼
		btnAddMoney.addActionListener(new AddCoinFrame(moneyTable));  // 잔돈추가 버튼 액션
		
		// 잔돈 관리 판넬 레이아웃 설정
		moneyAdminPanel.add(new JLabel("잔돈관리"), BorderLayout.CENTER);
		moneyAdminPanel.add(btnAddMoney, BorderLayout.EAST);
		moneyAdminPanel.add(moneyScrollPanel, BorderLayout.SOUTH);
		moneyAdminPanel.setVisible(false);   // 기본값은 안보임

		moneyModel = (DefaultTableModel) moneyTable.getModel();

		// 동전 종류 | 남은 갯수 출력
		for (int i = 0; i < CoinArray.coinList.size(); i++) {
			String arr[] = { CoinArray.coinList.get(i).getCoinName(),
					Integer.toString(CoinArray.coinList.get(i).getCoinNum()) };
			moneyModel.addRow(arr);
		}

		// ------------ <총 매출액 판넬> ------------ //
		moneyTotalPanel = new JPanel(new BorderLayout());
		totalMoneyLabel = new JLabel("총 매출액 : " + Admin.getTotalMoney());
		moneyTotalPanel.add(totalMoneyLabel);
		moneyTotalPanel.setVisible(false);  // 기본값은 안보임
		

		// ------------ <비밀번호 변경 판넬> ----------- //
		PWPanel = new JPanel(new BorderLayout());  // 전체 판넬
		
		JPanel PW1 = new JPanel();  // 안내문 판넬
		JPanel PW2 = new JPanel();  // 입력부분 판넬
		PWLabel = new JLabel("(특수문자 및 숫자 하나 이상 포함, 8자리 이상)");  // 안내 라벨
		changePW = new JTextField(10);  // 비밀번호 입력 JTextField(수정가능)
		changePW.setText("ex) Ogu1208!");  // 예시 설정
		//changePW.setEditable(true);
		JButton PWButton = new JButton("비밀번호 변경");
		
		
		changePW.addActionListener(new ActionPwChange(changePW));  // 입력텍스트필드 액션
		PWButton.addActionListener(new ActionPwChange(changePW));  // 비밀번호 변경 버튼 액션

		PW1.add(PWLabel);  // PW1은 안내
		PW2.add(changePW);  //PW2는 변경부분
		PW2.add(PWButton);
		
		// 비밀번호 변경 판넬 레이아웃 설정
		PWPanel.add(PW2, BorderLayout.NORTH);
		PWPanel.add(PW1, BorderLayout.SOUTH);
		
		PWPanel.setVisible(false);  // 기본값은 안보임
		
		// ------------ <수금 판넬> ----------- //
		collectPanel = new JPanel(new BorderLayout()); // 전체 판넬
		
		JPanel collect1 = new JPanel(); // 안내문 판넬
		JPanel collect2 = new JPanel(); // 입력부분 판넬
		collectLabel = new JLabel("수금할 금액을 입력하세요(원)");
		collectMoney = new JTextField(10);
		//changePW.setEditable(true);
		JButton collectButton = new JButton("수금");
		
		
		collectMoney.addActionListener(new ActionCollectMoney(collectMoney)); // 입력텍스트필드 액션
		collectButton.addActionListener(new ActionCollectMoney(collectMoney)); // 수금 버튼 액션

		collect1.add(collectLabel); // collect1은 안내
		collect2.add(collectMoney); // collect2는 수금
		collect2.add(collectButton);
		
		// 레이아웃 설정
		collectPanel.add(collect2, BorderLayout.NORTH);
		collectPanel.add(collect1, BorderLayout.SOUTH);
		
		collectPanel.setVisible(false); // 기본값은 안보임
		
		// ------------ <관리자 접속 판넬> ----------- //
		JPanel inAdminPanel = new JPanel();  // 큰 틀

		adminPass = new JTextField(10);  // 비밀번호 텍스트field
		btnAdminIn = new JButton("접속");
		adminPass.addActionListener(this);  // 비밀번호 텍스트field dortus
		
		btnAdminIn.addActionListener(this);  // 접속 버튼 액션

		inAdminPanel.add(new JLabel("관리자"));
		inAdminPanel.add(adminPass);
		inAdminPanel.add(btnAdminIn);

		// 관리자 판넬에 각 판넬들 추가
		add(inAdminPanel);
		add(canAdminPanel);
		add(label);
		add(moneyAdminPanel);
		add(moneyTotalPanel);
		add(PWPanel);
		add(collectPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 관리자 접속 비밀번호확인
				if(!canAdminPanel.isVisible()){  // canAdminPanel이 보이지 않을 경우(많은 것들 중 canAdminpanel을 기준으로 함)
					if(adminPass.getText().equals(Admin.password)){  // 비밀번호가 일치할 경우
						
						label.setVisible(false);
						// 안보였던 판넬들 보이도록 변경
						canAdminPanel.setVisible(true);
						moneyAdminPanel.setVisible(true);
						moneyTotalPanel.setVisible(true);
						PWPanel.setVisible(true);
						collectPanel.setVisible(true);
						
						btnAdminIn.setText("접속해제");
						adminPass.setText("");
						adminPass.setVisible(false);
						
					} 
					else if(adminPass.getText().equals("")) {  // 비밀번호를 입력하지 않았을 경우
						JOptionPane.showMessageDialog(new JFrame(), "비밀번호를 입력해주세요");
					} 
					else {  // 비밀번호가 틀렸을 경우
						JOptionPane.showMessageDialog(new JFrame(), "비밀번호가 틀렸습니다!");
					}
				} else if(canAdminPanel.isVisible()){ // canAdminPanel이 보일 경우
					
					// 각 판넬들을 보이지 않도록 변경
					label.setVisible(true);
					canAdminPanel.setVisible(false);
					moneyAdminPanel.setVisible(false);
					moneyTotalPanel.setVisible(false);
					PWPanel.setVisible(false);
					collectPanel.setVisible(false);
					
					btnAdminIn.setText("접속");
					adminPass.setVisible(true);
				}
	}

}
