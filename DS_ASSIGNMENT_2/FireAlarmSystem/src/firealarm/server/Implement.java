/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firealarm.server;

import firealarm.client.FireSensorClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;



/**
 *
 * @author Sutharsan
 */

public class Implement extends UnicastRemoteObject implements FireSensorService, Runnable {
    public Implement() throws RemoteException{
        super();
       //// this.smoke =  a.nextInt(10)is
       this.smoke = new Random().nextInt(10);
       this.CO2 = new Random().nextInt(10);
       // this.CO2 = b.nextInt(10);
    }
    
  private static final long serialVersionUID = 1L;
    public volatile int smoke;
    public volatile int CO2;
    public int floor;
    public int room;
    public boolean activity;
    
    private ArrayList<FireSensorClient> list = new ArrayList<FireSensorClient>();
  
    
   @Override
     public void run()
    {
        Random r = new Random();
       for(;;)
        {
            try
            {
                //Sleep for a random amount of time
                int duration = r.nextInt() % 10000 + 200;
                //Check to see if negative, if so, reverse
                if(duration < 0)
                {
                    duration = duration * -1;
                    Thread.sleep(duration);
                }
            }
            catch(InterruptedException ie)
            {
                System.out.println(ie);
            }
            //Notify registered listeners
            notifySensor();
        }
   }
    private void notifySensor()
    {
        for(FireSensorClient fireclient:list)
        {
            try
            {
                fireclient.smokechange(smoke);
                fireclient.CO2change(CO2);
                fireclient.activitychange(activity);
            }
            catch(RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void addSensor(FireSensorClient fireclient) throws RemoteException
    {
        System.out.println("Adding Sensor - " + fireclient);
        list.add(fireclient);
    }
    
    @Override
    public int getSmokeLevel() throws RemoteException
    {
	return smoke;
    }

    @Override
    public int getCO2Level() throws RemoteException 
    {
	return CO2;
    }

    @Override
    public int getFloorNumber() throws RemoteException
    {
	return floor;
    }

    @Override
    public int getRoomNumber() throws RemoteException 
    {
        return room;
    }

    @Override
    public boolean getActivity() throws RemoteException {
        return true;
    }

  
   
 }

 

