package string;

public class StringTest {
	public static void main(String[] args) {
		System.out.println(" powerfulCode ".trim() == "powerfulCode".trim() 
				? "0" : "1");
		
		System.out.println(new String("breakYourLimits").intern()
                == new String("breakYourLimits").intern() ? "8" : "9");
		
		
		/**
		 * trim() method create new object via forcing new keyword 
		 * Result : false
		 */
		System.out.println(" duke".trim() == "duke".trim());
		System.out.println("Hello");
		System.out.println("Hello World");
		
	}
}
