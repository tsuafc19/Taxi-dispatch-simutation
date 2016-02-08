
/**
 * 
 * Author: Carlens Faustin
 * 
 * Date last modified: February 25, 2013
 */


import javax.swing.JDialog;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;


public class StatisticDialog extends JFrame  {
  
  DispatchCenter model;
  JLabel fromTo, fromdowntownLabel, fromairportLabel, fromnorthLabel, fromsouthLabel, fromeastLabel, fromwestLabel,
    todowntownLabel, toairportLabel, tonorthLabel, tosouthLabel, toeastLabel, towestLabel;
  JTextField[][] travels;
  JPanel textPanel;
  
  
  
  public StatisticDialog(String title) {
    super(title);
    
    
    
    GridBagLayout layout = new GridBagLayout();
    getContentPane().setLayout(layout);
    GridBagConstraints constraints = new GridBagConstraints();
    
    
    //downtown label
    fromdowntownLabel= new JLabel("Downtown");
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(fromdowntownLabel, constraints);
    getContentPane().add(fromdowntownLabel);
    
    //airport label
    fromairportLabel= new JLabel("Airport");
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(fromairportLabel, constraints);
    getContentPane().add(fromairportLabel);
    
    //north label
    fromnorthLabel= new JLabel("North");
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(fromnorthLabel, constraints);
    getContentPane().add(fromnorthLabel);
    
    //south label
    fromsouthLabel= new JLabel("South");
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(fromsouthLabel, constraints);
    getContentPane().add(fromsouthLabel);
    
    //east label
    fromeastLabel= new JLabel("East");
    constraints.gridx = 0;
    constraints.gridy = 5;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(fromeastLabel, constraints);
    getContentPane().add(fromeastLabel);
    
    //west label
    fromwestLabel= new JLabel("West");
    constraints.gridx = 0;
    constraints.gridy = 6;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(fromwestLabel, constraints);
    getContentPane().add(fromwestLabel);
    
    // the dropoff LOCATIONS
    // downtown label
    todowntownLabel= new JLabel("Downtown");
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(todowntownLabel, constraints);
    getContentPane().add(todowntownLabel);
    
    //airport label
    toairportLabel= new JLabel("Airport");
    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(toairportLabel, constraints);
    getContentPane().add(toairportLabel);
    
    //north label
    tonorthLabel= new JLabel("North");
    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(tonorthLabel, constraints);
    getContentPane().add(tonorthLabel);
    
    //south label
    tosouthLabel= new JLabel("South");
    constraints.gridx = 4;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(tosouthLabel, constraints);
    getContentPane().add(tosouthLabel);
    
    //east label
    toeastLabel= new JLabel("East");
    constraints.gridx = 5;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(toeastLabel, constraints);
    getContentPane().add(toeastLabel);
    
    //west label
    towestLabel= new JLabel("West");
    constraints.gridx = 6;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(towestLabel, constraints);
    getContentPane().add(towestLabel);
    
    //from/to label
    fromTo= new JLabel("FROM/TO");
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(fromTo, constraints);
    getContentPane().add(fromTo);
    
    //panel that contains the jtextfields
    textPanel=new JPanel();
    textPanel.setLayout(new GridLayout(6, 6, 5, 5));
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 6;
    constraints.gridheight = 6;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(textPanel, constraints);
    getContentPane().add(textPanel);
    
    
    travels=new JTextField[6][6];
    for (int i=0;i<6;i++){
      for(int j=0;j<6;j++){
        
        travels[i][j]=new JTextField();
        travels[i][j].setText("0");
        travels[i][j].setEditable(false);
        textPanel.add(travels[i][j]);
      }
    }
    
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setSize(500, 300);
  }
  
  
  public void updateData( int[][]trips,int low,int high){
    
    for(int i=0;i<6;i++){
      for(int j=0;j<6;j++){
        travels[i][j].setBackground(Color.gray);
        travels[i][j].setText(""+trips[i][j]);
        if(trips[i][j]==low)
          
          travels[i][j].setBackground(Color.red);
        if(trips[i][j]==high)
          
          travels[i][j].setBackground(Color.green);
        
      }
    }
  }
  
  public static void main(String[] args) {
    new StatisticDialog("Statistic ").setVisible(true);
  }
  
  
}
