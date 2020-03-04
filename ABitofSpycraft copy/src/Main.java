import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        
        boolean menu = true;
        
        while(menu) {
        	
        	 System.out.println("\nWhat would you like to do?");
             System.out.println("a) Hide a message");
             System.out.println("b) Recover a message");
             System.out.println("c) Exit");
             Scanner scanchoice = new Scanner(System.in);
             System.out.println();
             System.out.print("Enter your selction(a, b, or c): ");
             String choiceentry = scanchoice.nextLine();
        	
            switch(choiceentry.toLowerCase().charAt(0)) {
		        case 'a':
		        	// prepare the input file
		            System.out.print("\nInput File Name: ");
		            String inputFileName = scanchoice.nextLine();
		            System.out.print("\nOutput File Name: ");
		            String outputFileName = scanchoice.nextLine();
		            System.out.print("\nMessage: ");
		            String message = scanchoice.nextLine();
		            PPMImage ppmImage = new PPMImage(new File(inputFileName));
		            ppmImage.hideData(message);
		            ppmImage.writeImage(new File (outputFileName));
		            System.out.print("\n\"" + message + "\"" + " has been hidden in file: " + outputFileName);
		            System.out.println();
		            break;
		        case 'b':
		        	System.out.print("\nFile Name to recover message: ");
		            String recoverFileName = scanchoice.nextLine();
		            PPMImage ppmImage2 = new PPMImage(new File(recoverFileName));
		            String recoverMessage = ppmImage2.recoverData();
		            System.out.print("\n\"" + recoverMessage + "\"" + "has been recovered in file: " + recoverFileName);
		            System.out.println();
		            break;
		        case 'c':
		        	System.exit(0);
		        	break;
            }
        	
        }
        

    }
}