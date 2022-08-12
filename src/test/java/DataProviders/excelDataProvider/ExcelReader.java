package DataProviders.excelDataProvider;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    private String excelFilePath;
    private XSSFSheet sheet;
    private XSSFWorkbook book;

    private String sheetName;

    public ExcelReader(String excelFilePath, String sheetName) {
        this.excelFilePath = excelFilePath;
        this.sheetName = sheetName;
        try {
            File file = new File(excelFilePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            this.book = new XSSFWorkbook(fileInputStream);
            this.sheet = book.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Not such file" + e);
        } catch (IOException e) {
            throw new RuntimeException("Invalid file format" + e);
        }
    }

    private String cellToString(XSSFCell cell){
        Object result = null;
        CellType type = cell.getCellType();
        switch (type){
            case NUMERIC:
                result = (int)cell.getNumericCellValue();
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "";
                break;
            default:
                try {
                    throw new Exception("Error when cell value reading");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
        return result.toString();
    }

    private int xlsxCountColumn(){
        return sheet.getRow(0).getLastCellNum();
    }

    private int xlsxCountRow(){
        return sheet.getLastRowNum() + 1;
    }

    public String[][] xlsxDataProvider() throws IOException {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        this.book = new XSSFWorkbook(fileInputStream);
        this.sheet = book.getSheet(sheetName);
        int columnNum = xlsxCountColumn();
        int rowNum = xlsxCountRow();
        String[][] data = new String[rowNum - 1][columnNum];
        for (int i = 1; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                XSSFRow row = this.sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i - 1][j] = value;
                if (value == null) {
                    System.out.println("Empty cells");
                }
            }
        }
        return data;
    }
}
