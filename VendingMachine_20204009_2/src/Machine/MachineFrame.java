package Machine;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Can.Can;
import Can.CanArray;
import Coin.Coin;
import Coin.CoinArray;
import Person.Admin;

public class MachineFrame extends JFrame {
	// 자판기의 전체적인틀
	public MachineFrame(String password){
		Admin.setPassword(password);
		// 음료 초기화
		CanArray.canList.add(new Can("물", 5, 450));
		CanArray.canList.add(new Can("커피", 5, 500));
		CanArray.canList.add(new Can("이온음료", 5, 550));
		CanArray.canList.add(new Can("고급커피", 5, 700));
		CanArray.canList.add(new Can("탄산음료", 5, 750));
		// 잔돈 초기화
		CoinArray.coinList.add(new Coin("1000",10));
		CoinArray.coinList.add(new Coin("500",10));
		CoinArray.coinList.add(new Coin("100",10));
		CoinArray.coinList.add(new Coin("50",10));
		CoinArray.coinList.add(new Coin("10",10));
		
		// 윈도우 이벤트 등록 및 설정
		setTitle("자판기 관리 프로그램");
		setPreferredSize(new Dimension(650,630));
		setLocation(400,150);
		WindowListener win = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		};
		addWindowListener(win);

		// 자판기 왼쪽 판넬과 오른쪽 판넬 구성
		Container contentPanel = getContentPane();
		
		JPanel panelRight = new MachinePanelRight(password);
		JPanel panelLeft = new MachinePanelLeft();
		
		contentPanel.add(panelRight,BorderLayout.EAST);
		contentPanel.add(panelLeft,BorderLayout.CENTER);

		pack();
		setVisible(true);
		
	}
}
