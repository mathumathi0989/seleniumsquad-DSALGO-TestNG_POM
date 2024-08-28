package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static Workbook workbook;
    private static Sheet sheet;

    // Load the Excel file
    public static void loadExcel(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load Excel file: " + filePath);
        }
    }

    // Get the number of rows
    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    // Get data from a cell
    public static String getCellData(int row, int col) {
        return sheet.getRow(row).getCell(col).toString();
    }

    // Close the workbook
    public static void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
