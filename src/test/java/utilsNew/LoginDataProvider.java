package utilsNew;

import org.testng.annotations.DataProvider;
public class LoginDataProvider {

	@DataProvider(name="loginData")
	public Object[][] getData(){
		
		
		return new Object[][] {
			
			{"username", "test"},
			{"username1", "test1"},
			{"username2", "test2"},
			
		};
	}
		
		
		
		
		
	
}
