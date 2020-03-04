import java.io.*;

class PPMImage {
	
	File image;
	
//	instantiates the variables being used in readImage() 
//	since readImage()i is private they have to be outside inorder to be accessed by the other methods
	private String magicNum;
    private String widthHeight;
    private String maxValue;
    
    private int picWidth;
    private int picHeight;
    
    private char[][][] raster;
    
    
//    constructor and throws exception if the input image 
//    passes to readImage() if exception isn't thrown
    public PPMImage(File image) throws Exception {

    	
    	if (!image.exists()) {
    		throw new FileNotFoundException("Image does not exist!");
    	}
    	
    	String imageName = image.getName();
    	String imageEx = ".ppm";
    	String substr = imageName.substring(imageName.length() - 4);
//    	checks to see if the substring of the input image ends with .ppm image
    	if ((substr.equals(imageEx))) {
        	this.image =  image;
    	}
    	
    	else {
    		throw new PPMImageException("Image input file is not a .ppm file");
    	}

		
		readImage();
	}
// gets the input file
    private void readImage() {
    	try {
			DataInputStream in = new DataInputStream(new FileInputStream(this.image));

			//	reads the first 3 line		
			magicNum = in.readLine();
			widthHeight = in.readLine();
            maxValue = in.readLine();
            
//            splits the width and height if the bc in.readLine()  had it all together
            String[] widthHeightSplit = widthHeight.split(" ");

            picWidth = Integer.parseInt(widthHeightSplit[0]);
            picHeight = Integer.parseInt(widthHeightSplit[1]);

//            instatiates the 3d array
            raster = new char[picHeight][picWidth][3];
            
            
// 			for loops to get the rest of the characters in the image and store them in the array
            for (int i = 0; i < picWidth; i++) {
                for (int j = 0; j < picHeight; j++) {
                    raster[j][i][0] = (char) in.read();
                    raster[j][i][1] = (char) in.read();
                    raster[j][i][2] = (char) in.read();
                }
                
            }
            
        	in.close();
            
		} catch (IOException ex) {
            ex.printStackTrace();
        }
    	
    }
    
//    creates the output file
    public void writeImage(File filename) throws Exception{
    	
    	
//    	has the same throw exceptions as in the constructor to see in output file name is a .ppm 
    	String imageName = filename.getName();
    	String imageEx = ".ppm";
    	
    	
    	if ((imageName.length() < imageEx.length())) {
    		throw new PPMImageException("Image output file is not a .ppm file");
    	}

    	String substr = imageName.substring(imageName.length() - 4);
    	if ((substr.equals(imageEx))) {


	        try {
				DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
				
	            out.writeBytes(magicNum + "\n");
	            out.writeBytes(widthHeight + "\n");
	            out.writeBytes(maxValue + "\n");
	            
	            for (int i = 0; i < picWidth; i++) {
	                for (int j = 0; j < picHeight; j++) {
	                    out.write(raster[j][i][0]);
	                    out.write(raster[j][i][1]);
	                    out.write(raster[j][i][2]);
	                }
	                
	            }
				
	        	out.close();
	            
			} 
	        catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        
	    }
    	
    	else {
    		throw new PPMImageException("Image output file is not a .ppm file");
    	}
    	
    }
    
//    hides a message in the image that can't be seen by th ehuman eye
    public void hideData(String message) {

    	message +='\0';
    	String bits = "";
    	char test;
    	char mask;
    	
//    	stores message bits in  string
//    	gets each character in the message
    	for (int i = 0; i < message.length(); i++) {
    		
    		test = message.charAt(i);
    		
//    		gets the 8 bits of the character and then goes to the outer loop again
    		for (int n = 8; n > 0; n--) {
    			
    			mask = (char) (1 << (n - 1));
    			
    			if ((test & mask) == 0) {
    				bits += 0;
    			}
    			else {
    				bits += 1;
    			}
    		}
    	}
    	int count = 0;

//		0000 0001 (checks if the bit is on)
		char maskOn = (1 << 0);
//		1111 1110 (checks if the bit is off)
		char maskOff = (char) ~(1 << 0);
    	
        for (int i = 0; i < picWidth; i++) {
            for (int j = 0; j < picHeight; j++) {
            	for (int n = 0; n < 3; n++) {
            		
//            		turs lbs off
            		if(count < bits.length()) {
            			if ((bits.charAt(count) & maskOn)  == 0) {
            				raster[j][i][n] = (char) (raster[j][i][n] & maskOff);
            				
            			}
//            			turns lsb on
            			else {
            				raster[j][i][n] = (char) (raster[j][i][n] | maskOn);
            			}

            		}else
            			break;
            		
            		count++;

            	}

            }
            
        }
      
    	
    }
    
//    recovers the hidden message in the image
    public String recoverData() {
    	
    	String store = "";
    	
    	char convert;
    	
    	String recoverMessage = "";
    	
// checks to see the status of the lsb
    	char mask = 1 << 0;;
    	
        for (int i = 0; i < picWidth; i++) {
            for (int j = 0; j < picHeight; j++) {
            	for (int n = 0; n < 3; n++) {
//            		if the lsb is off store it in the string
            		if ((raster[j][i][n] & mask) == 0) {
            			store += '0';  
            		}
            		
            		else {
            			store += '1';
            		}
            		
//            		every 8 bits convert the bits to it's character
            		if (store.length() == 8)  {
            			convert = (char) Integer.parseInt(store, 2);
            			
//            			if the bits when converted equals 0 then terminate and return the message
            			if (convert == 0) {
            				
            				return recoverMessage;
            				
            			}
            			
            			recoverMessage += convert;
            			
            			store = "";
            		}
            		
            	}


            }
            
        }
      
    	
    	return recoverMessage;
    }
   
}
