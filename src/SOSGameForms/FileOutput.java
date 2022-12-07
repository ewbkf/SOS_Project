package SOSGameForms;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileOutput {
    private File gameLog;
    private FileWriter writer;
    private FileReader reader;
    private BufferedReader bufferedReader;
    private boolean fileCreatedSuccessfully;
    private String fileName;

    public FileOutput(){
        fileName = "SOSGame_" + GetLocalTimeForFileName() + ".txt";

        try {
            gameLog = new File("src/SOSGameForms/GameLogs", fileName);
            writer = new FileWriter("gameLogSOS.txt");
            reader = new FileReader("gameLogSOS.txt");
            bufferedReader = new BufferedReader(reader);

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
            writer.write(line + "\n");
            writer.close();
            System.out.println("Successfully wrote to the file: " + line);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String firstLineOfFile() {
        try {
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
