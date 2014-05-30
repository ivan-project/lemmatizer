/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arek
 */
public class MyException extends Exception{
    
    public MyException(String msg) {
        super(msg);
        
        MyLogger.log("exceptions.log", msg, this);
    }
}
