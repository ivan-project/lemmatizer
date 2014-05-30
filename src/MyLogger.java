
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author arkadiusz.gac
 */
public class MyLogger {
    
    protected static final Logger logger=Logger.getLogger("MYLOG");
    
    public static void log(String fileName, String msg, Exception e){
        logger.setUseParentHandlers(false);
        FileHandler fh = null;
        try {
            fh = new FileHandler("logs/"+fileName,true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.log(Level.SEVERE, msg, e);
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally{
            if(fh!=null)fh.close();
        }
    }
    
    public static void log(String fileName, String msg){
        logger.setUseParentHandlers(false);
        FileHandler fh = null;
        try {
            fh = new FileHandler("logs/"+fileName,true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.log(Level.INFO, msg);
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally{
            if(fh!=null)fh.close();
        }
    }
}
