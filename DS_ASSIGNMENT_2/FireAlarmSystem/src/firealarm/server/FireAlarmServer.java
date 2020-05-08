/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firealarm.server;

/**
 *
 * @author Sutharsan
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import adminlogin.LoginImplementation;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import adminlogin.LoginInterface;
public class FireAlarmServer extends UnicastRemoteObject
{
    private static final long serialVersionUID = 1L;
    public FireAlarmServer() throws RemoteException{
        super();
    }
    
    public static void main(String[]args)
    {
        try
        {
            Registry reg = LocateRegistry.createRegistry(2999);
            LoginInterface li = new LoginImplementation();
            reg.rebind("Login", li);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
         System.setProperty("java.security.policy","file:allowall.policy");
         
        try
        {
             Registry reg = LocateRegistry.createRegistry(3999);
             FireSensorService fs = new Implement();
             reg.rebind("FireServer", fs);
             System.err.println("Loading Sensor server....");
             System.err.println("Server is ready");
            
           
            
            Thread thread = new Thread((Runnable) fs);
            thread.start();
        }
        catch(Exception e)
        {
            System.out.println("Error - " + e);         
        }
    }
}
