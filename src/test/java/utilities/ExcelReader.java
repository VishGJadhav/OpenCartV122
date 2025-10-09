package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;

	static String path;

	public ExcelReader(String path) {
		this.path = path;
	}

	public static int getRowCount(String xlsheet) throws IOException {
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		int rownumber = sheet.getLastRowNum();
		wb.close();
		fis.close();
		return rownumber;
	}

	public static int getCellCount(String xlsheet, int rownum) throws IOException {
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellCount;
	}

	public static String getCellData(String xlsheet, int rownum, int column) throws IOException {

		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);
		DataFormatter formtter = new DataFormatter();
		String data;
		try {

			data = formtter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fis.close();
		return data;
	}
}
