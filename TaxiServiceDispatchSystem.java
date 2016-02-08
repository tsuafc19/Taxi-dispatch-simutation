
/**
 * 
 * Author: Carlens Faustin
 * 
 * Date last modified: February 25, 2013
 */




import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;


public class TaxiServiceDispatchSystem  extends JFrame implements ActionListener{
  int request=1;
  
  StatisticDialog stats;
  JMenuBar myMenuBar;
  JMenu  simulationMenu,settingsMenu,adminMenu;
  JMenuItem startItem, stopItem, rateItem, statsItem;
  JList incomingList, downtownList,airportList,northList,southList,eastList,westList;
  JLabel incomingLabel, downtownLabel, airportLabel, northLabel, southLabel, eastLabel, westLabel;
  JButton dispatchButton;
  Timer myTimer;
  DispatchCenter model;
  public TaxiServiceDispatchSystem(String title) {
    super(title);
    model=new DispatchCenter();
    stats=new StatisticDialog("Statistic");
    stats.setVisible(false);
    myMenuBar = new JMenuBar();
    this.setJMenuBar(myMenuBar);
    //setting up the simulation
    simulationMenu= new JMenu("Simulation");
    simulationMenu.setMnemonic('T');
    startItem = new JMenuItem("Start Clients Coming");
    startItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        myTimer.start(); // Handle the selection of this item from the menu
        
        
      }
    });
    
    stopItem = new JMenuItem("Stop Clients From Coming");
    stopItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        myTimer.stop();  // Handle the selection of this item from the menu
      }
    });
    simulationMenu.add(startItem);
    simulationMenu.add(stopItem);
    
    
    //setting up the settings
    settingsMenu= new JMenu("Settings");
    settingsMenu.setMnemonic('S');
    rateItem = new JMenuItem("Simulation Rate...");
    rateItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        while(true){
          
          try{
            
            String in = JOptionPane.showInputDialog("Iterations Per Second (1 to 20)");
            request= Integer.parseInt( in);
            
            if(request<1||request>=21){
              
              JOptionPane.showMessageDialog(null, "Pease Choose a Number From 1 to 20.","Warning", JOptionPane.ERROR_MESSAGE);
            }else{
              
              break;
            }
          }
          catch(NumberFormatException nfe){
            
            JOptionPane.showMessageDialog(null, "Pease Choose a Number From 1 to 20.","Warning", JOptionPane.ERROR_MESSAGE);
            
          }
        }
        
        
        
      }
    });
    settingsMenu.add(rateItem);
    
    //setting up the admin
    adminMenu= new JMenu("Admin");
    adminMenu.setMnemonic('A');
    statsItem= new JMenuItem("Statistics");
    statsItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        stats.setVisible(true); // Handle the selection of this item from the menu
      }
    });
    adminMenu.add(statsItem);
    
    //adding the JMenus to the MenuBar
    myMenuBar.add(simulationMenu);
    myMenuBar.add(settingsMenu);
    myMenuBar.add(adminMenu);
    
    GridBagLayout layout = new GridBagLayout();
    getContentPane().setLayout(layout);
    GridBagConstraints constraints = new GridBagConstraints();
    
    //CREATING THE BUTTON
    
    //dispatch button
    dispatchButton= new JButton("Dispatch");
    dispatchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        model.sendTaxiForRequest(model.cr.get(0));
        
        update();
      }
    });
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(dispatchButton, constraints);
    getContentPane().add(dispatchButton);
    
    // CREATING THE LABELS
    
    //incoming label
    incomingLabel= new JLabel("Incoming");
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(incomingLabel, constraints);
    getContentPane().add(incomingLabel);
    
    //downtown label
    downtownLabel= new JLabel("Downtown");
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(downtownLabel, constraints);
    getContentPane().add(downtownLabel);
    
    //airport label
    airportLabel= new JLabel("Airport");
    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(airportLabel, constraints);
    getContentPane().add(airportLabel);
    
    //north label
    northLabel= new JLabel("North");
    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(northLabel, constraints);
    getContentPane().add(northLabel);
    
    //south label
    southLabel= new JLabel("South");
    constraints.gridx = 4;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(southLabel, constraints);
    getContentPane().add(southLabel);
    
    //east label
    eastLabel= new JLabel("East");
    constraints.gridx = 5;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(eastLabel, constraints);
    getContentPane().add(eastLabel);
    
    //west label
    westLabel= new JLabel("West");
    constraints.gridx = 6;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 0;
    constraints.weighty = 0;
    layout.setConstraints(westLabel, constraints);
    getContentPane().add(westLabel);
    
    
    
    
    //CREATIING THE LISTS
    
    //incoming list
    incomingList= new JList(model.cr.toArray());
    JScrollPane scrollPane = new JScrollPane(incomingList,
                                             ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                             ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 15;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(scrollPane, constraints);
    getContentPane().add(scrollPane);
    
    //downtown list
    downtownList= new JList(model.areas.get("Downtown").toArray());
    JScrollPane scrollPane1 = new JScrollPane(downtownList,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 10;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(scrollPane1, constraints);
    getContentPane().add(scrollPane1);
    
    
    //airport list
    airportList= new JList(model.areas.get("Airport").toArray());
    JScrollPane scrollPane2 = new JScrollPane(airportList,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    constraints.gridx = 2;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 10;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(scrollPane2, constraints);
    getContentPane().add(scrollPane2);
    
    //north list
    northList= new JList(model.areas.get("North").toArray());
    JScrollPane scrollPane3 = new JScrollPane(northList,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    constraints.gridx = 3;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 10;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(scrollPane3, constraints);
    getContentPane().add(scrollPane3);
    
    //south list
    southList= new JList(model.areas.get("South").toArray());
    JScrollPane scrollPane4 = new JScrollPane(southList,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    constraints.gridx = 4;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 10;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(scrollPane4, constraints);
    getContentPane().add(scrollPane4);
    
    //east list
    eastList= new JList(model.areas.get("East").toArray());
    JScrollPane scrollPane5 = new JScrollPane(eastList,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    constraints.gridx = 5;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 10;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(scrollPane5, constraints);
    getContentPane().add(scrollPane5);
    
    //west list
    westList= new JList(model.areas.get("West").toArray());
    JScrollPane scrollPane6 = new JScrollPane(westList,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    constraints.gridx = 6;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.weightx = 10;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    layout.setConstraints(scrollPane6, constraints);
    getContentPane().add(scrollPane6);
    
    
    
    myTimer = new Timer(1000,new ActionListener() {
      @SuppressWarnings("unchecked")
      public void actionPerformed(ActionEvent e) {
        
        ArrayList<Taxi> a=new ArrayList<Taxi>(model.theTaxis.values());
        for(int i=0;i<request;i++){
          
          model.createRandomRequest();
        }
        
        for(Taxi t: a)
        {
          if(t.getAvailable()==false)
            t.decreaseEstimatedTimeToDest();
        }
        
        update();
      }
    });
    
    
    update();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1000, 450);
  }
  
  public void setData(){
    
    stats.updateData(model.getTrip(),model.lowest(),model.highest());
    
    
  }
  
  public void update(){
    
    incomingList.setListData(model.cr.toArray());
    airportList.setListData(model.areas.get("Airport").toArray());
    downtownList.setListData(model.areas.get("Downtown").toArray());
    northList.setListData(model.areas.get("North").toArray());
    southList.setListData(model.areas.get("South").toArray());
    eastList.setListData(model.areas.get("East").toArray());
    westList.setListData(model.areas.get("West").toArray());
    setData();
    
  }
  
  
  public void actionPerformed(ActionEvent e){
    
  }
  
  public static void main(String[] args) {
    new TaxiServiceDispatchSystem("Taxi Service Dispatching System ").setVisible(true);
  }
  
}
