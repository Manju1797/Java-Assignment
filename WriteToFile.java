package Test;
import java.io.FileWriter;   
import java.io.IOException;  

public class WriteToFile {
  public static void main(String[] args) {
    try {
      FileWriter myWriter = new FileWriter("input.txt");
      myWriter.write("Files in Java might be tricky!!!!!!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}