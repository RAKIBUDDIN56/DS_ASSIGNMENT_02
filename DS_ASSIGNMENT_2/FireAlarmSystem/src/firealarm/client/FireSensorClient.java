/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firealarm.client;

/**
 *
 * @author Sutharsan
 */
import java.rmi.RemoteException;

public interface FireSensorClient extends java.rmi.Remote
{
    public void smokechange(int smoke) throws RemoteException;
    public void CO2change(int CO2) throws RemoteException;
    public void activitychange(boolean activity) throws RemoteException;
    public void updateSensor() throws RemoteException;
}
