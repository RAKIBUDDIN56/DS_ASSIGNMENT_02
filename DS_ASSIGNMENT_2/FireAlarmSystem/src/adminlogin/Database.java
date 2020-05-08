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
class Database {
    private int sid,floor,smoke,co2;
    private String room,status;
    
    public Database(int sid,int floor,int smoke,int co2,String room,String status){
        this.sid = sid;
        this.smoke = smoke;
        this.co2 = co2;
        this.floor = floor;
        this.room = room;
        this.status = status;
    }
    
    public int getSid(){
        return sid;
    }
    
    public int getFloor(){
        return floor;
    }
    
    public int getSmoke(){
        return smoke;
    }
    
    public int getCo2(){
        return co2;
    }
    
    public String getRoom(){
        return room;
    }
    
    public String getStatus(){
        return status;
    }
}
