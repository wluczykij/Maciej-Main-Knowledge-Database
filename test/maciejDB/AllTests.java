package maciejDB;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( value = { 
		BookTest.class,
		MainBookLibraryTest.class,
		MyUtilitiesTest.class
		})
public class AllTests {

}
