import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author arek
 */
public abstract class LemmatizerExceptions extends Exception{
    private Integer excType;
    private Integer code;
    
    public LemmatizerExceptions(Integer code, String msg) {
        super(msg);
        this.excType = excType;
        this.code = code;
        this.log();
    }
    
    private void log() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("logs/exception.log", true)));
        } catch (Exception ex) {
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        pw.println(dateFormat.format(cal.getTime())+" "+this+" (code: "+this.code+")");
        this.printStackTrace(pw);
        pw.println();
        pw.close();
    }
    
}

class ExceptionCodes {
    
    static Integer InputValidation = 1;
}

class InputValidation extends LemmatizerExceptions {
    protected Integer excType = ExceptionCodes.InputValidation;
    protected static HashMap<Integer, String> msgList;
    
    static Integer PARAM_NUMBERS = 1;
    
    static {
        msgList = new HashMap<Integer, String>();
        msgList.put(InputValidation.PARAM_NUMBERS, "Wrong parameters number");
    }

    public InputValidation(Integer code) {
        super(code, InputValidation.msgList.get(code));
    }
}
