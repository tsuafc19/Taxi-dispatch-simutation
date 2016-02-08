

/**
 * File: DispatchCenter.java
 * Author: Carlens Faustin
 * 
 * Date last modified: February 25, 2013
 */



import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;



public class DispatchCenter {
  LinkedList<ClientRequest> taxiRequests=new LinkedList<ClientRequest>();
  HashMap<Integer, Taxi>   theTaxis;
  HashMap<String, ArrayList<Taxi>> areas;
  private int [][] tripNumbers;//to store the #
  ArrayList<ClientRequest> cr=new ArrayList<ClientRequest>();
  
// the string of areas
  private static final String[] areaList = {"Downtown","Airport","North","South","East","West"};
  
//this 2d array represent the time from a location to another
//for example in the area list north is at index 2
//if we want to know the duration from north to north we
//would look at times[2][2] and that would be 10
  private static final int[][] times={{10, 40, 20, 20, 20, 20},
    {40, 10, 40, 40, 20, 60},
    {20, 40, 10, 40, 20, 20},
    {20, 40, 40, 10, 20, 20},
    {20, 20, 20, 20, 10, 40},
    {20, 60, 20, 20, 40, 10}};
  @SuppressWarnings("unchecked")
  public DispatchCenter() {
    
    
    theTaxis = new HashMap<Integer, Taxi>(50);
    areas = new HashMap<String,ArrayList<Taxi>>(6);
    
    //to initializes the taxis HashMap to contain 50 taxis
    //with random (unique) plate numbers ranging from 100 to 999.
    
    for (int i=0;i<50;i++){
      int plate =100 + (int)(Math.random() * 900);
      while(theTaxis.containsKey(new Integer(plate))){
        plate =100 + (int)(Math.random() * 900);
      }
      theTaxis.put(new Integer (plate),new Taxi(plate));
      
    }
    
    Object[] taxiPlates= (theTaxis.keySet()).toArray();
    int taxpos=0;
    
    for (int i=0;i<6;i++){
      areas.put(areaList[i],new ArrayList<Taxi>());
      int jmax=8;
      if (i>=4){
        jmax=9;
      }
      for (int j=0;j<jmax;j++){
        if(taxpos<taxiPlates.length){
          Taxi t= theTaxis.get(((Integer)taxiPlates[taxpos++]).intValue());
          t.setDestination(areaList[i]);
          addTaxi(t,areaList[i]);
        }
        
      }
      
    }
    
    tripNumbers=new int [6][6] ;
    
    
  }
  
  public String[] getAreaList(){
    return areaList;
  }
  
  //rerurn the duration of the trip from one location to another
  public static int getDuration(String debut, String arrive){
    int begin=areaPosition(debut);
    int dest=areaPosition(arrive);
    
    
    if ((begin >= 0 && begin < times.length )&& (dest >= 0 && dest < times[begin].length))
      return (times[begin][dest]);
    else
      return -1;
  }
  
  
  // takes the area and returns its position in the areaList array
  //if not found return -1
  public static int areaPosition(String s){
    //linear search for the string
    int result=-1;
    for (int i = 0; i < areaList.length; i++){
      
      if(s.equals(areaList[i]))
        result=i;
      if (i == 6)
        result= -1;
    }
    return result;
  }
  
  
  //when give the position it returns the area
  public static String intToArea(int index)
  {
    if (index >= 0 && index < areaList.length)
      return (areaList[index]);
    else
      return ("Area doesn't exist");
  }
  
  
  //return taxi hashmap
  public HashMap getTaxiHash(){
    return theTaxis;
  }
  //rught area hashmap
  public HashMap getAreaHash(){
    return areas;
  }
  
  
  //initialize array of taxi with new plate
  public void addTaxi(Taxi t, String s) {
    
    
    
    //check if taxi already exist
    
    if(!areas.get(s).contains(t)){
      
      areas.get(s).add(t);
    }
    
  }
  
  //this will get all the available taxis in the hasmap area
  //basicly check if string s matches a area name athen to get this areas array of taxi
  //and from that list to take all the available ones and add them to a new array and return it
  public ArrayList<Taxi> availableTaxisInArea(String s){
    
    ArrayList<Taxi> notBusy= new ArrayList<Taxi>();
    
    for( Taxi t: areas.get(s)){
      if(t.getAvailable()){
        notBusy.add(t);
      }
    }
    return  notBusy;
  }
  
  
  // this method returns an array of all taxis not available
  public ArrayList<Taxi> getBusyTaxis() {
    
    ArrayList<Taxi> busy= new ArrayList<Taxi>();
    
    String s;
    for (int i=0;i<areaList.length;i++){
      s=areaList[i];
      for (Taxi t :areas.get(s)){
        if(t.getAvailable()==false){
          busy.add(t);
        }
      }
    }
    return  busy;
  }
  
  
  public void  sendTaxiForRequest(ClientRequest c) {
    Taxi t=getATaxi(c.getPickupLocation());
    
    if(t==null){
      System.out.println("Currently,there's no available taxi");
      
    }
    else{
      //update the # of trips for those pair location
      tripNumbers(c.getPickupLocation(),c.getDropoffLocation());
      //set the estimated time of travel for the taxi
      t.setEstimatedTimeToDest(getDuration(t.getDestination(),c.getPickupLocation())+getDuration(c.getPickupLocation(),c.getDropoffLocation()));
      //remove taxi from the location we found it
      areas.get(t.getDestination()).remove(t);
      //add it to the the new area
      addTaxi(t,c.getDropoffLocation());
      t.setAvailable(false);
      t.setDestination(c.getDropoffLocation());
      cr.remove(c);
    }
  }
  
  
//return taxi in the current area if no taxi found in this area
//it looks though other location for the first available taxi
  private Taxi getATaxi(String s){
    
    Taxi t = null;
    ArrayList currentArea;
    
    //check for taxis in same area
    currentArea = availableTaxisInArea(s);
    if (!currentArea.isEmpty())
    {
      //return the first taxi
      t = (Taxi)currentArea.get(0);
    }
    //find taxis in the other areas
    else
    {
      String[] a = areaList;
      
      //loop through the areas to find a taxi
      for (int i = 0; i < a.length; i++)
      {
        currentArea = availableTaxisInArea(a[i]);
        if (!currentArea.isEmpty())
        {
          //return the first taxi
          t = (Taxi)currentArea.get(0);
          break;
        }
      }
    }
    
    return t;
  }
  
  
  
  
  public int[][] tripNumbers(String a,String b){
    
    int x=areaPosition(a);
    int y=areaPosition(b);
    
    
    tripNumbers[x][y]+=1;
    
    
    return tripNumbers;
  }
  
//return the trip 2d array
  public int[][] getTrip(){
    
    return tripNumbers;
    
  }
  
//it creates random areas for the new client
  public ClientRequest createRandomRequest(){
    
    int a=(int)(Math.random() * ((5) + 1));
    int b=(int)(Math.random() * ((5) + 1));
    
    ClientRequest c= new ClientRequest(intToArea(a),intToArea(b));
    
    cr.add(c);
    return c;
    
  }
  
  public int lowest(){
    int result=1000;
    int a;
    for(int i=0;i<6;i++){
      for(int j=0;j<6;j++){
        a=tripNumbers[i][j];
        if(a<result)
          result=a;
      }
    }
    return result;
  }
  
  public int highest(){
    int result=0;
    int a;
    for(int i=0;i<6;i++){
      for(int j=0;j<6;j++){
        a=tripNumbers[i][j];
        if(a>result)
          result=a;
      }
    }
    return result;
  }
  
  
  
}
