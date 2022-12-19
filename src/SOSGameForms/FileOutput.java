package SOSGameForms;

import java.io.*;
import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class FileOutput {
    private File gameLog;
    private boolean fileCreatedSuccessfully;
    private String fileName;

    public FileOutput(){
        fileName = "SOSGame_" + GetLocalTimeForFileName() + ".txt";
        try {
            gameLog = new File("src/SOSGameForms/GameLogs", fileName);


            if (gameLog.createNewFile()) {
                System.out.println("File created: " + gameLog.getName());
                fileCreatedSuccessfully = true;
            } else {
                System.out.println("File already exists.");
                fileCreatedSuccessfully = false;
            }
        } catch (IOException e) {
            fileCreatedSuccessfully = false;
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(3);
        }
    }

    //Adds an entry to the gameLog, no need to include a newline character
    public void AddLineToGameLog(String line) {
        try {
            FileWriter writer = new FileWriter(gameLog, true);
            writer.write(line);
            writer.flush();
            writer.close();
            System.out.println("Successfully wrote to the file: " + line);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String firstLineOfFile() {
        try {
            FileReader reader;
            BufferedReader bufferedReader;
            reader = new FileReader("gameLogSOS.txt");
            bufferedReader = new BufferedReader(reader);
            String line;
            line = bufferedReader.readLine();
            reader.close();
            bufferedReader.close();
            System.out.println("Successfully read from the file: " + line);
            return line;
        }
        catch (IOException e){
            System.out.println("Error reading line");
            return "Error reading line";
        }
    }

    CharBuffer extractGameLog() throws FileNotFoundException {
        CharBuffer buf = null;
        CharBuffer.allocate(1000);

        try (FileReader reader = new FileReader(gameLog)) {
            reader.read(buf);
        }
        catch (IOException e) {
            System.out.println("Error reading line");
            return buf;
        }
        return buf;
    }

    private String GetLocalTimeForFileName(){
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy-HH.mm.ss");
        fileName = currentDateAndTime.format(formatter);
        return fileName;
    }

    //really just for testing below this point.
    public boolean wasTheFileCreated(){ return fileCreatedSuccessfully; }
    public boolean deleteFile(){ return gameLog.delete(); }
}

