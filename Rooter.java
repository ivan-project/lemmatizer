import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 *
 * @author arkadiusz.gac
 */
public class Rooter {
    
    private MongoClient mongoClient;
    private DB mongoDB;
    private DBCollection coll;
    BasicDBObject whereQuery = new BasicDBObject();
    DBObject findResult;

    
    private String inputTmpFilename;
    private String outputFileName;
    
    private PrintWriter pw; 

    
    public Rooter() throws MyException {
        this.init();
    }
    
    private void init() throws MyException {
        try {
            this.mongoClient = new MongoClient( "localhost" , 27017 );
        } catch (UnknownHostException ex) {
            throw new MyException("Error while connecting to database");
        }
        this.mongoDB = mongoClient.getDB("dict");
    }

    public void startProcess(String inputTmpFilename, String outputFileName) throws MyException {
        this.inputTmpFilename = inputTmpFilename;
        this.outputFileName = outputFileName;
        Charset ch = Charset.forName("UTF-8");
        
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(this.inputTmpFilename), ch));
        } catch (FileNotFoundException ex) {
            throw new MyException("Error while opening temporary file while getting core");
        }
        
        try {
            this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFileName, true)));
        } catch (IOException ex) {
            throw new MyException("Error while opening output file");
        }
        
        String line;
        
        try {
            while ((line = br.readLine()) != null) {
                this.getRoot(line);
            }
        } catch (IOException ex) {
            throw new MyException("Error while reading temporary file");
        }
        
        try {
            this.pw.close();
            br.close();
        } catch (IOException ex) {
            throw new MyException("Error while closing output or temporary file while getting core");
        }
        
        
        this.removeTmp();
    }

    private void getRoot(String line) {
        String newLine = "";
        String useCol;
        String[] splitedLine = line.split(" ");
        Arrays.sort(splitedLine);
        

        for(String sLine : splitedLine) {
            useCol = String.valueOf(sLine.toCharArray()[0]);
            whereQuery.clear();
            whereQuery.put("word", sLine);
            this.findResult = this.mongoDB.getCollection(useCol).findOne(whereQuery);
            newLine += this.findResult.get("core")+" ";
        }
        newLine = newLine.trim();
        this.pw.println(newLine);
    }

    private void removeTmp() {
        File file = new File(this.inputTmpFilename);
        if(!file.delete()) {
            MyLogger.log("deleteTemporary.log", "Failed to remove temporary file "+this.inputTmpFilename);
        }
    }
    
}
