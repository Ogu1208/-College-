package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Coin.CoinArray;
import Machine.MachinePanelRight;
import Person.Admin;
//잔돈 추가 클래스

public class ActionAddCoin extends JFrame implements ActionListener{

   JFrame frame;
   DefaultTableModel coinModel;
   List<JCheckBox> checkList;
   List<JComboBox> selectList;
   JButton checkAll;
   
// 잔돈 추가 클래스 생성자
   public ActionAddCoin(JFrame frame, JButton checkAll, List<JCheckBox> checkList, List<JComboBox> selectList){
      super();
      this.frame = frame;
      this.checkList = checkList;
      this.selectList = selectList;
      this.checkAll = checkAll;
   }


   @Override
   public void actionPerformed(ActionEvent e) {
      // 잔돈 재고 추가
      coinModel =  (DefaultTableModel)MachinePanelRight.moneyTable.getModel();
      if (coinModel.getRowCount() != 0) {
         coinModel.setRowCount(0);
      }
      for(int i=0; i<checkList.size();i++){
         if(checkList.get(i).isSelected()){
            if(selectList.get(i).getSelectedItem().equals("5개")){
            	// 5개 버튼 선택일 경우 재고 5개 추가
               CoinArray.coinList.get(i).setCoinNum(CoinArray.coinList.get(i).getCoinNum() + 5);
            } // 10개 버튼 선택일 경우 재고 10개 추가
            else if(selectList.get(i).getSelectedItem().equals("10개")){
               CoinArray.coinList.get(i).setCoinNum(CoinArray.coinList.get(i).getCoinNum() + 10);
            } // 20개 버튼 선택일 경우 재고 10개 추가
            else if(selectList.get(i).getSelectedItem().equals("20개")){
               CoinArray.coinList.get(i).setCoinNum(CoinArray.coinList.get(i).getCoinNum() + 20);
            }
            checkList.get(i).setSelected(false);
         }
      }
      
      for(int i=0;i<selectList.size();i++){
         selectList.get(i).setVisible(false);
         checkAll.setLabel("전체10개추가");
      }

      for (int i = 0; i < CoinArray.coinList.size(); i++) {
         String arr[] = { CoinArray.coinList.get(i).getCoinName(), Integer.toString(CoinArray.coinList.get(i).getCoinNum())};
         coinModel.addRow(arr);
      }
         
      frame.dispose();
      this.setVisible(false);
   }
}