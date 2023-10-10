package com.ecommerce.automation.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class ExcelDataReader {

    public static final String TEST_DATA_XLSX = "TestData.xlsx";

    public static String[][] readData(String sheetName) {
        return readData(TEST_DATA_XLSX, sheetName);
    }

    public static String[][] readData(String fileName, String sheetName) {
        String[][] data = null;
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(resource.toURI())));
            XSSFSheet sheet = workbook.getSheet(sheetName);
            String urlSheet = sheet.getSheetName();
            if (StringUtils.equals(urlSheet, "URL")) {
                data = new String[1][1];

                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 1; j++) {
                        XSSFCell cell = sheet.getRow(i).getCell(j);
                        data[i][j] = cell != null ? cell.getStringCellValue() : "";
                    }
                }
            }
            if (StringUtils.equals(urlSheet, "Login")) {
                data = new String[1][2];

                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 2; j++) {
                        XSSFCell cell = sheet.getRow(i).getCell(j);
                        data[i][j] = cell != null ? cell.getStringCellValue() : "";
                    }
                }
            }

            if (StringUtils.equals(urlSheet, "item-selection")) {
                int rows = 2, columns = 8;
                data = new String[rows - 1][columns];

                for (int i = 1; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        XSSFCell cell = sheet.getRow(i).getCell(j);
                        data[i - 1][j] = cell != null ? cell.getStringCellValue() : "";
                    }
                }
            }

            if (StringUtils.equals(urlSheet, "shipping-address")) {
                int rows = 2, columns = 8;
                data = new String[rows - 1][columns];

                for (int i = 1; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        XSSFCell cell = sheet.getRow(i).getCell(j);
                        data[i - 1][j] = cell != null ? cell.getStringCellValue() : "";
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public static String getPath() {
        return Thread.class.getClassLoader().getParent().getName();
    }
}
