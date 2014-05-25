
import java.io.IOException;
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
    
    private StopWord stopObject;
    
    public Lemmatizer(String []args) {
        this.args = args;
        this.init();
    }
    
    private void init() {
        this.getInputParams();
        this.removeStopWords();
    }
    
    private void getInputParams() {
        int inputSize = this.args.length;
        
        if(inputSize != 2) {
//            throw new InputValidation(InputValidation.PARAM_NUMBERS);
        }
        this.inputFileName = args[0];
        this.outputFileName = args[1];
    }
    
    private void removeStopWords() {
        this.stopObject = new StopWord();
        try {
            this.stopObject.removeWords(this.inputFileName);
        } catch (IOException ex) {
            Logger.getLogger(Lemmatizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void main(String []args) {
//        StopWord stop = new StopWord();
        
        Lemmatizer lemObject = new Lemmatizer(args);

    }
    
}
