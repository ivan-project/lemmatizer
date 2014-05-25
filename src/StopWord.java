
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author arkadiusz.gac
 */
public class StopWord {
    
    private ArrayList<String> stopList;
    
    private String stopFileName = "stop_words_2";
    private String stopPath = "files/";
    private String tmpFileName = "haha";
    
    
    public StopWord() {
        this.init();
    }
    
    private void init() {
        this.stopList = new ArrayList<>();
        try {
            this.readStopWordsFromFile();
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(2);
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

    public void removeWords(String inputFileName) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String line;
        while ((line = br.readLine()) != null) {
            line = this.removeStopWords(line);
            line = this.removeChars(line);
            line = this.replaceSpaces(line);
            this.saveToTmp(line);
        }
        br.close();
    }
    
    private String removeStopWords(String line) {
        line = line.toLowerCase();
        for(String pattern : this.stopList) {
            line = line.replaceAll("\\b"+pattern+"\\b", "");
        }
        return line;
    }
    
    private String removeChars(String line) {
        return line.replaceAll("[^\\pL\\pM\\p{Nd}\\p{Nl}\\p{Pc}[\\p{InEnclosedAlphanumerics}&&\\p{So}]]", " ");
    }
    
    private String replaceSpaces(String line) {
        
        line = line.replaceAll("\\s{2,}", " ");
        return line.trim();
    }

    private void saveToTmp(String line) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tmp/"+this.tmpFileName, true)));
        pw.println(line);
        pw.close();
    }
    
    public String getTmpFIleName() {
        return this.tmpFileName;
    }
    
    
}
