package utilsNew;

import org.testng.annotations.DataProvider;
public class LoginDataProvider {

	@DataProvider(name="loginData")
	public Object[][] getData(){
		
		
		return new Object[][] {

			{"invalid_user",   "secret_sauce"},   // invalid username, valid password
			{"standard_user",  "invalid_pass"},    // valid username, invalid password
			{"standard_user",  "secret_sauce"},    // valid username and password

		};
	}
		
		
		
		
		
	
}
