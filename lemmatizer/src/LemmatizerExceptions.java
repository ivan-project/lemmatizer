import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @author arek
 */
public abstract class LemmatizerExceptions extends Exception{
    protected Integer excType;
    
    public LemmatizerExceptions(Integer code, String msg) {
        super(msg);
    }
    
    public Integer getExcType() {
        return this.excType;
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
