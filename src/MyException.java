/**
 *
 * @author arkadiusz.gac
 */
public class MyException extends Exception{
    
    public MyException(String msg) {
        super(msg);
        
        MyLogger.log("exceptions.log", msg, this);
    }
}
