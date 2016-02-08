/**
 * 
 * Author: Carlens Faustin
 * 
 * Date last modified: February 25, 2013
 */


public class Taxi {
  
  private int  plateNumber;
  private boolean  available;
  private String  destination;
  private int  estimatedTimeToDest;
  
  public Taxi (int plate) {
    plateNumber = plate;
    available = true;
    destination = "";
    estimatedTimeToDest = 0;
  }
  
  public int getPlateNumber(){
    return plateNumber;
  }
  
  public boolean getAvailable(){
    return available;
  }
  
  public String getDestination(){
    return destination;
  }
  
  public int getEstimatedTimeToDestination(){
    return estimatedTimeToDest;
  }
  
  public void setAvailable(boolean avail){
    available = avail;
  }
  
  public void setDestination(String d){
    destination = d;
  }
  
  public void setEstimatedTimeToDest(int t){
    estimatedTimeToDest = t;
  }
  
  public void decreaseEstimatedTimeToDest () {
    if(estimatedTimeToDest<0)
      return;
    estimatedTimeToDest--;
    if(estimatedTimeToDest==0)
      available=true;
  }
  
  //
  public boolean equals(Object x){
    
    if (!(x instanceof Taxi))
      return false;
    
    return plateNumber==((Taxi)x).getPlateNumber();
    
  }
  
  public String toString() {
    if (available)
      return plateNumber + " (available)";
    return plateNumber + "(" + estimatedTimeToDest + ")";
  }
  
  
}
