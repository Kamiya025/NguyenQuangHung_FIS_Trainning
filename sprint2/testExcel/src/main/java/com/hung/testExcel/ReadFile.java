package com.hung.testExcel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadFile {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public static void main( String[] args )
    {

        try {
            List<Transaction> transactions = loadTransaction("Account_Enquiry.xlsx");
            for (Transaction transaction: transactions)
                System.out.println(transactions);
        } catch (IOException e) {

        }
    }

    public static List<Transaction> loadTransaction(String excelFilePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() < 9) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Transaction transaction = new Transaction();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        transaction.setExecutionDate(LocalDate.parse((String)getCellValue(cell),formatter));
                        break;
                    case 1:
                        transaction.setValidDate(LocalDate.parse((String)getCellValue(cell),formatter));
                        break;
                    case 2:
                        transaction.setDescription((String) getCellValue(cell));
                        break;
                    case 3:
                        transaction.setDebit((double) getCellValue(cell));
                        break;
                    case 4:
                        transaction.setCrebit((double) getCellValue(cell));
                        break;
                    case 5:
                        transaction.setBalance((double) getCellValue(cell));
                        break;
                    case 6:
                        transaction.setBeneficiaryAccount((String) getCellValue(cell));
                        break;
                    case 7:
                        transaction.setBeneficiaryName((String) getCellValue(cell));
                        break;
                    case 8:
                        transaction.setCode((String) getCellValue(cell));
                        break;
                    case 9:
                        break;

                    default:
                        break;
                }

            }
            transactions.add(transaction);
        }

        workbook.close();
        inputStream.close();

        return transactions;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
