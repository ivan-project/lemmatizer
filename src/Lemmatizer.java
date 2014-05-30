
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
    private Rooter rooterObject;
    
    public Lemmatizer(String []args) throws MyException {
        this.args = args;
        this.init();
    }
    
    private void init() throws MyException {
        this.getInputParams();
        this.removeStopWords();
        this.getRootOfWord();
    }
    
    private void getInputParams() throws MyException {
        int inputSize = this.args.length;
        
        if(inputSize != 2) {
            throw new MyException("Nieprawidłowa liczba parametrów");
        }
        this.inputFileName = args[0];
        this.outputFileName = args[1];
    }
    
    private void removeStopWords() throws MyException {
        this.stopObject = new StopWord();
        this.stopObject.removeWords(this.inputFileName);
    }
    
    private void getRootOfWord() throws MyException {
        this.rooterObject = new Rooter();
        this.rooterObject.startProcess(this.stopObject.getTmpFIleName(), this.outputFileName);
    }
    
    
    
    public static void main(String []args) {
        try {
            Lemmatizer lemObject = new Lemmatizer(args);
        } catch (MyException ex) {
            System.err.println("Something goes wrong check tmp/exceptions.log");
            System.exit(0);
        }
    }
    
}
