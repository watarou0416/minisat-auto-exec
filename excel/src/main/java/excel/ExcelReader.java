package excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private final static String SENI_EXCEL_PATH = "../data/senni3.xlsx";
    private final static int[] SENI_EXCEL_TARGET_COL = { 2, 4, 6, 8 };

    private final static String SENI_DIMACS_PATH = "../data/all.txt";

    private final static String REAL_SENI_DIMACS_PATH = "../data/real-all.txt";

    public static void readSeniExcel() {
        try (FileInputStream fileInputStream = new FileInputStream(SENI_EXCEL_PATH);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            List<List<String>> rowList = new ArrayList<>();

            for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row != null) {
                    List<String> colList = new ArrayList<>();
                    for (int targetCol : SENI_EXCEL_TARGET_COL) {
                        Cell cell = row.getCell(targetCol - 1);

                        if (cell != null) {
                            colList.add(cell.toString());

                        }
                    }
                    rowList.add(colList);
                }
            }

            List<String> res = new ArrayList<String>();
            for (List<String> col : rowList) {
                for (int i = 0; i < col.size() - 1; i++) {
                    res.add(toStr(col.get(i), col.get(i + 1)));
                }
            }

            FileUtil.writeFile(res, SENI_DIMACS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readRealSeniExcel(List<Integer> deleteRow) {
        try (FileInputStream fileInputStream = new FileInputStream(SENI_EXCEL_PATH);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            List<List<String>> rowList = new ArrayList<>();

            for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                if (!deleteRow.contains(rowIndex)) {
                    Row row = sheet.getRow(rowIndex);

                    if (row != null) {
                        List<String> colList = new ArrayList<>();
                        for (int targetCol : SENI_EXCEL_TARGET_COL) {
                            Cell cell = row.getCell(targetCol - 1);

                            if (cell != null) {
                                colList.add(cell.toString());

                            }
                        }
                        rowList.add(colList);
                    }
                }

            }

            List<String> res = new ArrayList<String>();

            for (List<String> s : rowList) {
                String sss = "";
                for (String ss : s) {
                    sss = sss + ss + " ";
                }
                res.add(sss);
            }

            FileUtil.writeFile(res, REAL_SENI_DIMACS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1 2→-6 0
    // 2 3→-6 0
    // 1 3→6 0
    // 7 1→6 0
    // 7 8→-6 0
    // 8 9→-6 0
    // 9 1→-6 0
    // 3 4→2 0
    // 4 1→2 0
    // 3 5→4 0
    // 5 7→4 0
    // 3 6→5 0
    // 6 7→5 0
    static public String toStr(String s1, String s2) {
        switch (Integer.parseInt(s1 + s2)) {
            case 13:
            case 71:
                return "6 0";
            case 12:
            case 23:
            case 78:
            case 89:
            case 91:
                return "-6 0";
            case 34:
            case 41:
                return "2 0";
            case 35:
            case 57:
                return "4 0";
            case 36:
            case 67:
                return "5 0";
        }

        return "";
    }
}
