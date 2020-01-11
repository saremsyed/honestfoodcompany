package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static XSSFSheet workSheet;

	private static XSSFWorkbook workBook;

	private static XSSFCell cell;

	public Object[][] getTableArray(String fileName, String sheetName) throws Exception {

		String[][] tabArray = null;

		try {

			FileInputStream excelFile = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/" + fileName);

			workBook = new XSSFWorkbook(excelFile);
			workSheet = workBook.getSheet(sheetName);

			int startRow = 1;
			int startCol = 1;
			int ci, cj;

			int totalRows = workSheet.getLastRowNum();
			Row row = workSheet.getRow(0);

			int totalCols = row.getLastCellNum();
			tabArray = new String[totalRows][totalCols - 1];

			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols - 1; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
				}
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		return (tabArray);

	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {
			cell = workSheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}

	}

}