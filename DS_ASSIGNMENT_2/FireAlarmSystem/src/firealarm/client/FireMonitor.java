/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firealarm.client;

import firealarm.server.FireSensorService;
import firealarm.server.Implement;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sutharsan
 */

public class FireMonitor extends UnicastRemoteObject implements FireSensorClient,Runnable{
    private static final long serialVersionUID = 1L;
    private int count = 0;
     static int readingsmoke;
     
    int newSmoke= 0;
    int newCo2 = 0;
    String activity ;
     
    public FireMonitor() throws RemoteException{
    	super();
    }
    public static void main(String[] args) throws MalformedURLException, RemoteException{
           System.setProperty("java.security.policy", "file:allowall.policy");
//           Implement implement = new Implement();
//           implement.updateSensor();

    ArrayList<Integer> datalist =new ArrayList<>();
    
      try{
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firealarm", "root" , "");
            String query="SELECT sensorID FROM firealarm.sensor";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            
          //  System.out.println(rs);
//         
            while(rs.next())       {
            //    System.out.println(rs.getInt("sensorID"));ta,
             datalist.add(rs.getInt("sensorID"));
            }                  
//            
               
      }
      catch(Exception e){
          e.printStackTrace();
      }
     
    
    
        try{
               Registry reg=LocateRegistry.getRegistry("127.0.0.1",2000);
               FireSensorService sensor=(FireSensorService)reg.lookup("FireServer");
           
               for(int i = 0 ; i <= datalist.size(); i++){
                   System.out.println("SensorID :"+ datalist.get(i));
                  
              readingsmoke = sensor.getSmokeLevel();
               System.out.println("Smoke Reading : " + readingsmoke);
            
               int readingCO2 = sensor.getCO2Level();
               System.out.println("CO2 Reading : " + readingCO2);
            
               int readingfloor = sensor.getFloorNumber();
	       System.out.println("Floor : " + readingfloor);
            
               int readingroom = sensor.getRoomNumber();
	       System.out.println("Room : " + readingroom);
			
	       boolean readingactiviy = sensor.getActivity();
	       System.out.println("Status : " + readingactiviy);
            
            //A client object created
           // FireMonitor monitor = new FireMonitor();
            
           sensor.addSensor( new FireMonitor());
           new FireMonitor().run();
      
               }
        } catch (RemoteException re) {
            System.out.println(re);
	} catch (NotBoundException nbe) {
            System.out.println(nbe);
	}
    }
    
    @Override
    public void run(){
       for(;;){
          
         
            try{
               Thread.sleep(1000);
               this.smokechange(count);
               
               this.CO2change(count);
               this.activitychange(true);
              this.updateSensor();
            }
            
            catch(InterruptedException ie){
               System.out.println(ie);
            } catch (RemoteException ex) {
               Logger.getLogger(FireMonitor.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
   
    @Override
    public void smokechange(int smoke) throws RemoteException{
        Random random = new Random();
         newSmoke = random.nextInt(11);
        if(newSmoke >= 5){
           SendMailSSL se = new SendMailSSL();
            String[]  ab = null;
            se.main(ab);
        System.err.println("\nSmoke change event : " +newSmoke);
        activity = "Activated";
        }
        else{
             System.out.println("\nCO2 change event : " + newSmoke);
             activity = "Deactivated";
        }
       
    }
    
    @Override
    public void CO2change(int CO2) throws RemoteException{
        Random random = new Random();
         newCo2 = random.nextInt(11);
        if(newCo2 >= 5 ){
           System.err.println("\nCO2 change event : " + newCo2);
           activity = "Activated";
        }
        else{
           System.out.println("\nCO2 change event : " + newCo2);
            activity = "Deactivated";
        }
    }
    
    @Override
   public void activitychange(boolean activity) throws RemoteException {
      
	System.out.println("\nActivity change event : " + activity);

   }
   
    @Override
    public void updateSensor() throws RemoteException {
         try{
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firealarm", "root" , "");
                String query = "update sensor set smokeLevel = ? , CO2Level = ?, status = ?  where sensorID = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
               
               
                preparedStmt.setInt(1,newSmoke );
                preparedStmt.setInt(2,newCo2 );
                 preparedStmt.setString(3,activity );
                   preparedStmt.setInt(4,101);
              

                // execute the java preparedstatement
                preparedStmt.executeUpdate();
         
             }
        catch(Exception e){
        e.printStackTrace();
        }        
 }

    
}
