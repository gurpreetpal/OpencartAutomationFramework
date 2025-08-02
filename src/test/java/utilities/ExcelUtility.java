package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	String path;
	
	public ExcelUtility(String path) throws IOException {
		this.path = path;
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
	}



	public int getRowCount(String sheetName) throws IOException {
		
		sheet = workbook.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return totalRows;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int totalCell = row.getLastCellNum();
		workbook.close();
		fis.close();
		return totalCell;
		
	}
	
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			
			data = "";
		}
		workbook.close();
		fis.close();
		return data;

	}
	
}
