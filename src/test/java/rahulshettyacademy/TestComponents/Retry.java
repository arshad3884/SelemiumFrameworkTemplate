package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count =0;
	int maxTry=1;
	@Override
	public boolean retry(ITestResult result) {
		// If we need to rerun a flaky or failed tests
		if (count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
