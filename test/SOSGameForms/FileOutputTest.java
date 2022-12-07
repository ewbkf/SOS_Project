package SOSGameForms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FileOutputTest {
    @Test
    public void FileOpensCorrectly(){
        FileOutput testFile = new FileOutput();
        assertTrue(testFile.wasTheFileCreated());
        assertTrue(testFile.deleteFile());
    }

    @Test
    public void WritingAndReadingLineCorrectly(){
        FileOutput testFile = new FileOutput();
        String inputText = "Hello World!";
        String outputText;

        testFile.AddLineToGameLog(inputText);
        outputText = testFile.firstLineOfFile();

        assertEquals(inputText, outputText);
        assertTrue(testFile.deleteFile());
    }
}