import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

class ReportCreator {

    // Internal objects for creating/modifying excel file
    private XSSFWorkbook workbook;
    private XSSFSheet spreadsheet;
    private XSSFRow row;

    // Constructor which creates a new workbook/spreadsheet every time
    ReportCreator()
    {
        workbook = new XSSFWorkbook();
        spreadsheet = workbook.createSheet("Automation Report");
    }

    void createHeader()
    {
        // Titles of columns of our excel table
        String [] titles = {"Test case #", "Years of experience", "Expected bonus", "Actual bonus", "Result"};

        // Creating the top row (header of teable)
        row = spreadsheet.createRow(0);

        // Applying bold style for the header cells
        CellStyle style = workbook.createCellStyle();
        Font font =  workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);

        int cellCounter = 0;
        for(String title : titles)
        {
            // Putting titles to cells with the style defined above
            row.createCell(cellCounter).setCellValue(title);
            spreadsheet.autoSizeColumn(cellCounter);
            row.getCell(cellCounter).setCellStyle(style);
            cellCounter++;
        }
    }

    void writeTestCaseResult(int testNumber, int years, double expBonus, double actBonus, String result)
    {
        // Creating a new row for a test and putting appropriate data in each column
        row = spreadsheet.createRow(testNumber);
        row.createCell(0).setCellValue(testNumber);
        row.createCell(1).setCellValue(years);
        row.createCell(2).setCellValue(expBonus);
        row.createCell(3).setCellValue(actBonus);
        row.createCell(4).setCellValue(result);

        CellStyle style = workbook.createCellStyle();

        // Set cell foreground colour depends on test result: green or red
        if(Objects.equals(result, "PASSED")) style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        else style.setFillForegroundColor(IndexedColors.RED.getIndex());

        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        row.getCell(4).setCellStyle(style);
        spreadsheet.autoSizeColumn(4);
    }

    void calculateSummary()
    {
        // Create style for the summary row: bigger bold font, aligned center
        CellStyle summaryStyle = workbook.createCellStyle();
        Font font =  workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)14);
        summaryStyle.setFont(font);
        summaryStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        // Title for the summary row
        row = spreadsheet.createRow(9);
        row.createCell(0).setCellValue("% of success:");
        row.getCell(0).setCellStyle(summaryStyle);

        // Merging cells for columns 0..3
        spreadsheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 3));

        // Defining cell with formula for calculating % of success for whole test run
        String coverageFormula = "COUNTIF(E2:E9,\"PASSED\")/(COUNTIF(E2:E9,\"PASSED\")+COUNTIF(E2:E9,\"FAILED\"))";
        Cell finalResult = row.createCell(4);
        finalResult.setCellType(Cell.CELL_TYPE_FORMULA);
        finalResult.setCellFormula(coverageFormula);

        // Displaying the cell with test run coverage in the % format
        summaryStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));
        row.getCell(4).setCellStyle(summaryStyle);
    }

    void saveReport(String name)
    {
        /* Opening FileOutput stream and passing our workbook object's data
        which needs to be saved to the Excel-file*/
        try(FileOutputStream out = new FileOutputStream(new File(name + ".xlsx"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}