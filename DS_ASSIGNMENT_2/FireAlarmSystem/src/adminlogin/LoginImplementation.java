/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminlogin;

/**
 *
 * @author Sutharsan
 */
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import adminlogin.LoginInterface;
public class LoginImplementation extends UnicastRemoteObject implements LoginInterface
{
    public LoginImplementation() throws RemoteException
    {
        
    }
    
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean getLogin(String username,String password) throws RemoteException
    {
        boolean found = false;
        try
        {
            if(username.equals("admin") && password.equals("admin123"))
            {
                return found = true;
            }
            else
            {
                return found = false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return found;
    }
}
