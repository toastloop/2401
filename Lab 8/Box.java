
/**
 *
 * @author makbar
 */
public class Box {
  private double width, height, length;
  
  Box(double w, double h, double l){
    width=w;
    height=h;
    length=l;
  }
  
  private double getVolume(){
    return width*height*length;
  }
    
  public int compareTo(Box o){
    double myVol = this.getVolume();
    double thatVol = o.getVolume();
    if (myVol>thatVol)
      return 1;
    else if (myVol<thatVol)
      return -1;
    else
      return 0;
  }
  
  public String toString(){
    return "Width: "+width+
           "\theight: "+height+
            "\tlength: "+length+
            "\tVolume: "+getVolume();
  }

          
}
