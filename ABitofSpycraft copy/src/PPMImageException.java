// throws file exception in constructor and in writeimage() 
public class PPMImageException extends Exception {
	
	public PPMImageException(String ierrormessage) {
		
		super(ierrormessage);
	}

}
