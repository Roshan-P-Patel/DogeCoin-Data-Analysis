import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class WinOrLose{
  ArrayList<String> dL;
    ArrayList<String> pL;
    ArrayList<String> oL;
    ArrayList<String> hL;
    ArrayList<String> lL;
    
  public WinOrLose(ArrayList<String> d, ArrayList<String> p, ArrayList<String> o, ArrayList<String> h, ArrayList<String> l){
    dL=d;
    pL=p;
    oL=o;
    hL=h;
    lL=l;
  }
  public double getOpen(String dat){
    int ind=dL.indexOf(dat);
    double d= Double.parseDouble(oL.get(ind));
    return d;
  }
  public double getClose(String dat){
    int ind=dL.indexOf(dat)+1;
    double d;
    if(oL.get(ind)!=null){
    d= Double.parseDouble(oL.get(ind));}
    else{
      d=0;
    }
    return d;
  }
  public double diff(String dat){
    return( getClose(dat)-getOpen(dat));
    
  }

  public void countDayWinVsLose(){
    int wins=0;
    int lose=0;
    for(int i=1;i<dL.size()-1;i++){
      if(this.diff(dL.get(i))>0){
        wins++;
      }
      else{
        lose++;
      }
    }
  if(wins>lose){
    System.out.println("There was "+Integer.toString(wins)+" winning days and only "+Integer.toString(lose)+" losing days, meaning that there were "+Integer.toString(wins-lose)+" more wins than loses");
  }
    else{
      System.out.println("There was "+Integer.toString(lose)+" losing days and only "+Integer.toString(wins)+" winning days, meaning that there were "+Integer.toString(lose-wins)+" more wins than loses");
      
    }
    
  }
  
}

class avChange{
ArrayList<String> dL;
    ArrayList<String> pL;
    ArrayList<String> oL;
    ArrayList<String> hL;
    ArrayList<String> lL;
    
  public avChange(ArrayList<String> d, ArrayList<String> p, ArrayList<String> o, ArrayList<String> h, ArrayList<String> l){
    dL=d;
    pL=p;
    oL=o;
    hL=h;
    lL=l;
  }
  public double getOpen(String dat){
    int ind=dL.indexOf(dat);
    double d= Double.parseDouble(oL.get(ind));
    return d;
  }
  public double getClose(String dat){
    int ind=dL.indexOf(dat)+1;
    double d;
    if(oL.get(ind)!=null){
    d= Double.parseDouble(oL.get(ind));}
    else{
      d=0;
    }
    return d;
  }
  public double diff(String dat){
    return getClose(dat)-getOpen(dat);
    
  }
  public void findAvs(){
    double days=0;
    double total=0;
    for(int i=1;i<dL.size()-1;i++){
      total=total+this.diff(dL.get(i));
      days++;
  }

  System.out.println("The average change by day is: "+(total/days));
}}

class Main {
  public static void main(String[] args) {
    String csvFile = "doge.csv"; // Replace with your CSV file path
    String delimiter = ","; // Replace with the appropriate delimiter used in your CSV file

    ArrayList<String> dateLis = new ArrayList<>();
    ArrayList<String> priceLis = new ArrayList<>();
    ArrayList<String> openLis = new ArrayList<>();
    ArrayList<String> highLis = new ArrayList<>();
    ArrayList<String> lowLis = new ArrayList<>();
    ArrayList<String> volLis = new ArrayList<>();
    ArrayList<String> changeLis = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(delimiter);
        if (values.length >= 7) {
          dateLis.add(values[0]);
          priceLis.add((values[2]));
          openLis.add(values[3]);
          highLis.add(values[4]);
          lowLis.add(values[5]);
          volLis.add(values[6]);
          //changeLis.add(values[6]);
          }
      }
    } catch (IOException e) {
        e.printStackTrace();
        }

    //System.out.println(Double.valueOf(priceLis.get(10)));
        WinOrLose wol=new WinOrLose(dateLis, priceLis, openLis, highLis, lowLis);
    System.out.println("Comparison test: check to see if there are more winning days or losing days");
    wol.countDayWinVsLose();

    avChange ac= new avChange(dateLis, priceLis, openLis, highLis, lowLis);

    System.out.println("Quantitative test: calculate the average change in price per day.");

    ac.findAvs();
  }
}