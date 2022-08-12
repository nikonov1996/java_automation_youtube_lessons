package DataProviders.excelDataProvider;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ExcelDataProviders {
    private static final String EXCEL_FILE_PATH = "/home/dev/GIT/oleh_pendak_java_automation_course/src/main/resources/data.xlsx";
    @DataProvider
    public Object[][] usersFromXlsx() throws IOException {
        ExcelReader excelReader = new ExcelReader(EXCEL_FILE_PATH,"Лист1");
        return excelReader.xlsxDataProvider();
    }

    @DataProvider
    public Object[][] dataFromXlsx() throws IOException {
        return new ExcelReader(EXCEL_FILE_PATH,"Лист2").xlsxDataProvider();
    }
}
