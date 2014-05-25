
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author arkadiusz.gac
 */
public class StopWord {
    
    private ArrayList<String> stopList;
    
    private String stopFileName = "stop_words";
    private String stopPath = "files/";
    
    
    public StopWord() {
        this.init();
    }
    
    private void init() {
        try {
            this.readStopWordsFromFile();
        } catch (IOException ex) {
            System.err.println("Error"+ex);
//            Logger.getLogger(StopWord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readStopWordsFromFile() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.stopPath+this.stopFileName));
        String line;
        while ((line = br.readLine()) != null) {
           this.stopList.add(line);
        }
        br.close();
    }
    
    public ArrayList<String> getStopList() {
        return stopList;
    }
    
}
