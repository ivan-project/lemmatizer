import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author arkadiusz.gac
 */
public class StopWord {
    
    private ArrayList<String> stopList;
    
    private String stopFileName = "stop_words";
    private String stopPath = "files/";
    private String tmpFileName = "";
    private PrintWriter pw; 
    
    
    public StopWord() throws MyException {
        this.init();
    }
    
    private void init() throws MyException {
        this.stopList = new ArrayList<>();
        this.readStopWordsFromFile();
    }
    
    private void readStopWordsFromFile() throws MyException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.stopPath+this.stopFileName));
        } catch (FileNotFoundException ex) {
            throw new MyException("Error while opening stop words from file");
        }
        
        String line;
        try {
            while ((line = br.readLine()) != null) {
                this.stopList.add(line);
            }
        } catch (IOException ex) {
            throw new MyException("Error while reading stop words from file while processing stop words");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                throw new MyException("Error while closing stop words from file while processing stop words");
            }
        }
    }

    public void removeWords(String inputFileName) throws MyException {
        this.createTmpFileName(inputFileName);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(inputFileName));
        } catch (FileNotFoundException ex) {
            throw new MyException("Error while opening input file while processing stop words");
        }
        try {
            this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.tmpFileName, true)));
        } catch (IOException ex) {
            throw new MyException("Error while opening temporary file while processing stop words");
        }
        
        String line;
        try {
            while ((line = br.readLine()) != null) {
                line = this.removeStopWords(line);
                line = this.removeChars(line);
                line = this.removeNumbers(line);
                line = this.replaceSpaces(line);
                
                this.saveToTmp(line);
            }
        } catch (IOException ex) {
            throw new MyException("Error while reading from input file");
        }
        try {
            this.pw.close();
            br.close();
        } catch (IOException ex) {
            throw new MyException("Error while closing input or temporary file");
        }
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
        this.pw.println(line);
    }
    
    public String getTmpFIleName() {
        return this.tmpFileName;
    }

    private String removeNumbers(String line) {
        return line.replaceAll("\\d", " ");
    }

    private void createTmpFileName(String inputFileName) {
        Path path = Paths.get(inputFileName);
        Date date = new Date();
        this.tmpFileName = "tmp/"+path.getFileName().toString()+"_"+date.getTime();
    }
    
    
}
