package Test;
import java.io.File;
import java.util.Scanner;

class ReadFromFile {
	public static void main(String[] args) {
		try {
		File file = new File("test.txt");
		
        Scanner sc = new Scanner(file);
        
        System.out.println("Reading File using Scanner :");
        while(sc.hasNextLine()) {
        	System.out.println(sc.nextLine());
        }
        	
        } catch(Exception e){
            e.getStackTrace();
        	
        }
	}

}
