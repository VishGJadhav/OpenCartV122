package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\opencartdemo.xlsx";

		ExcelReader xlUtil = new ExcelReader(path);

		int totalRow = xlUtil.getRowCount("Sheet1");
		int totalColumn = xlUtil.getCellCount("Sheet1", 1);

		String loginData[][] = new String[totalRow][totalColumn];

		for (int i = 1; i <= totalRow; i++) {
			for (int j = 0; j < totalColumn; j++) {
				loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
}
