
package com.EX2.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    public ExcelReader() {}

    /** Reads a sheet and returns rows (excluding header) as Object[][] of Strings. */
    public static Object[][] readSheet(String excelPath, String sheetName) throws IOException {

        try (FileInputStream in = new FileInputStream(excelPath);
             XSSFWorkbook wb = new XSSFWorkbook(in)) {

            XSSFSheet sheet = wb.getSheet(sheetName);
            if (sheet == null) throw new IllegalArgumentException("Sheet not found: " + sheetName);

            XSSFRow header = sheet.getRow(0);
            if (header == null) throw new IllegalStateException("Header row (0) is empty");
            int cols = header.getLastCellNum();

            int lastRow = sheet.getLastRowNum();        // index of last defined row
            Object[][] data = new Object[lastRow][cols];

            for (int r = 1; r <= lastRow; r++) {        // data starts after header
                XSSFRow row = sheet.getRow(r);
                for (int c = 0; c < cols; c++) {
                    XSSFCell cell = (row == null) ? null
                        : row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    data[r - 1][c] = cellToString(cell); // store as String
                }
            }
            return data;
        }
    }

    private static String cellToString(XSSFCell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:  return cell.getStringCellValue();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:  return cell.getStringCellValue();
                    case NUMERIC: return String.valueOf(cell.getNumericCellValue());
                    case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
                    default:      return "";
                }
            default: return "";
        }
    }
}