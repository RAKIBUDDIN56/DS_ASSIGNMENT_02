/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firealarm.server;

import firealarm.client.FireSensorClient;

/**
 *
 * @author Sutharsan
 */


public interface FireSensorService extends java.rmi.Remote
{
    //register sensor
    public void addSensor(FireSensorClient fireclient) throws java.rmi.RemoteException;
    
    //smoke level returned from client
    public int getSmokeLevel() throws java.rmi.RemoteException;
    
    //CO2 level returned from client
    public int getCO2Level() throws java.rmi.RemoteException;
    
    //floor level returned from client
    public int getFloorNumber() throws java.rmi.RemoteException;
    
    //room level returned from client
    public int getRoomNumber() throws java.rmi.RemoteException;
    
    public boolean getActivity() throws java.rmi.RemoteException;
    
   
}
