package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelData {

	public static Object[][] retrieveExcelData(String excelPath, String sheetPath) {
		try (FileInputStream file = new FileInputStream(new File(excelPath));
				XSSFWorkbook excelWorkbook = new XSSFWorkbook(file)) {

			XSSFSheet sheet = excelWorkbook.getSheet(sheetPath);
			int rowsNumber = sheet.getPhysicalNumberOfRows();
			int colsNumber = sheet.getRow(0).getPhysicalNumberOfCells();
			Object[][] cellValues = new Object[rowsNumber - 1][colsNumber];

			DataFormatter dataFormatter = new DataFormatter();
			for (int r = 1; r < rowsNumber; r++) {
				XSSFRow row = sheet.getRow(r);
				if (row == null) {
					break;
				}
				for (int c = 0; c < colsNumber; c++) {
					cellValues[r - 1][c] = dataFormatter.formatCellValue(row.getCell(c));
				}
			}

			return cellValues;

		} catch (IOException e) {
			// Manejar la excepción apropiadamente
			e.printStackTrace();
			return new Object[0][0];
		}
	}
}
