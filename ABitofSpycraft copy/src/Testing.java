import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Testing {

	public static void main(String[] args) {
		
		
		
		
		
    	String testing = "S*";
    	
    	String bits = "";
    	
    	
//    	int bytes = 0;
    	
    	for (int i = 0; i < testing.length(); i++) {
    		char test = testing.charAt(i);
//    		char test = 149;
    		int bytes = (int)test;
    		
    		for (int n = 8; n > 0; n--) {
    			
    			char mask = (char) (1 << (n - 1));
    			
    			if ((test & mask) == 0) {
    				bits += 0;
    			}
    			else {
    				bits += 1;
    			}
    		}
    	}

//      System.out.println(bits);
      
      

//      for (int i = 0; i < picWidth; i++) {
//          for (int j = 0; j < picHeight; j++) {
//              raster[j][i][0];
//              raster[j][i][1];
//              raster[j][i][2];
//          }
//          
//      }
    	
    	int count = 0;
    	
    	String str = "";
    	
    	for (int j = 0; j < 3; j++) {
    		
            for (int i = 0; i < 3; i++) {
            	
            	str += bits.charAt(count);
            	
            	count++;
                
            }
    	}
    	
      System.out.println(str);
      
      
      
//    System.out.println("[" + (int)raster[1][0][0] + "]");
		
		
		
		
		
        
//        File input = new File("bridge.ppm"); //
////        File output = new File("testing.ppm");
//
//        try{
//
//            DataInputStream in = new DataInputStream(new FileInputStream(input));
//            
//            String magicNum = in.readLine();//
//			String widthHeight = in.readLine();//
//            String maxValue = in.readLine();//
//            
//            String[] widthHeightSplit = widthHeight.split(" ");//
//
//            int picWidth = Integer.parseInt(widthHeightSplit[0]);//
//            int picHeight = Integer.parseInt(widthHeightSplit[1]);//
//
//            char[][][] raster = new char[picHeight][picWidth][3];//
//
//            for (int i = 0; i < picWidth; i++) {//
//                for (int j = 0; j < picHeight; j++) {
//                    raster[j][i][0] = (char) in.read();
//                    raster[j][i][1] = (char) in.read();
//                    raster[j][i][2] = (char) in.read();
//                }
//                
//            }
//            
//            System.out.print(raster[0][0][0]);
//            
//
//            //
////            DataOutputStream out = new DataOutputStream(new FileOutputStream(output));
////            
////            out.writeBytes(magicNum + "\n");
////            out.writeBytes(widthHeight + "\n");
////            out.writeBytes(maxValue + "\n");
////            
////            for (int i = 0; i < picWidth; i++) {
////                for (int j = 0; j < picHeight; j++) {
////                    out.write(raster[j][i][0]);
////                    out.write(raster[j][i][1]);
////                    out.write(raster[j][i][2]);
////                }
////                
////            }
//            in.close();//
////            out.close();
//        } 
//
//        catch (IOException ex) {
//            ex.printStackTrace(); }
////		String str = "hello";
////		byte[] byteArr = str.getBytes();
////		// print the byte[] elements
////		System.out.println("String to byte array: " + Arrays.toString(byteArr));
////        
	}

}
