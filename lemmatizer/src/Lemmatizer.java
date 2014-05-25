
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arkadiusz.gac
 */
public class Lemmatizer {
    
    private String inputFileName;
    private String outputFileName;
    
    private String []args;
    
    public Lemmatizer(String []args) {
        this.args = args;
        this.init();
    }
    
    private void init() {
        try {
            this.validateInputParams();
        } catch (InputValidation ex) {
            System.exit(1);
        }
    }
    
    private void validateInputParams() throws InputValidation {
        int inputSize = this.args.length;
        
        if(inputSize != 2) {
            throw new InputValidation(InputValidation.PARAM_NUMBERS);
        }
    }
    
    
    
    public static void main(String []args) {
//        StopWord stop = new StopWord();
        
        Lemmatizer lemObject = new Lemmatizer(args);

    }
    
}
