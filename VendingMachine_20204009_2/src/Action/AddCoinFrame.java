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

import Coin.CoinArray;


public class AddCoinFrame extends JFrame implements ActionListener{
	JCheckBox coinCheck;
	JButton coinOk, coinClose;
	JTable coinTable;
	DefaultTableModel coinModel;
	public AddCoinFrame (JTable coinTable){
		this.coinTable  = coinTable;
	}
	
	public void addCoinFrame(JTable coinTable){
		this.coinTable = coinTable;
		
		List<JCheckBox> coinCheckList = new ArrayList<JCheckBox>();
		List<JComboBox> coinSelectList = new ArrayList<JComboBox>();
		
		setTitle("잔돈추가팝업");
		setSize(320, 300);
		setLocation(200,200);

		// title
		JLabel label = new JLabel("어떤잔돈이 부족하신가요?");
		JPanel toPanel = new JPanel();
		toPanel.add(label);
		add(toPanel, BorderLayout.NORTH);
		
		// 체크박스 추가
		JPanel checkPanel = new JPanel();
		JButton chechAll = new JButton("전체10개추가");
		chechAll.setPreferredSize(new Dimension(120, 30));
		chechAll.addActionListener(new ActionSelectAll(coinCheckList));
		
		String[] coinSelectNum = {"10개","20개","50개"};  // 잔돈 재교 추가 개수 목록
		
		
		
		for(int i=0; i<CoinArray.coinList.size();i++){
			JPanel checkEach = new JPanel();
			coinCheck = new JCheckBox(CoinArray.coinList.get(i).getCoinName());  // 잔돈 이름 체크박스
			JComboBox<String> coinSelectBox = new JComboBox();  // 잔돈 재고 추가 개수 목록을 콤보박스로 보여줌
			for(int j=0; j<coinSelectNum.length; j++){
				coinSelectBox.addItem(coinSelectNum[j]);
			}
			
			
			coinCheckList.add(coinCheck);
			coinSelectList.add(coinSelectBox);
			
			checkEach.add(coinCheck);
			checkEach.add(coinSelectBox);
			coinSelectBox.setVisible(false);
			
			checkPanel.add(checkEach);
			coinCheck.addActionListener(new ActionCheck(coinSelectList));
			checkEach.setPreferredSize(new Dimension(160, 30));
		}
		
		// 10개추가, 콤보박스 추가 레이아웃 설정
		add(chechAll, BorderLayout.WEST);
		add(checkPanel,BorderLayout.CENTER);
		
		//추가, 취소 버튼 아래에 배치, 레이아웃 설정
		coinOk = new JButton("추가");
		coinClose = new JButton("취소");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(coinOk);
		buttonPanel.add(coinClose);
		add(buttonPanel, BorderLayout.SOUTH);
		coinOk.addActionListener(new ActionAddCoin(this, chechAll, coinCheckList, coinSelectList));
		coinClose.addActionListener(new ActionCancle(this));
	
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		addCoinFrame(coinTable);
	}
}