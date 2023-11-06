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
    public static void main(String[] args) {
        String filePath = "C:\\\\Users\\\\ietar\\\\OneDrive\\\\デスクトップ\\\\研究室\\\\卒論データ/senni3.xlsx"; // エクセルファイルのパスを指定してください
        int[] targetColumnNumbers = {2, 4, 6,8}; // 取得したい列番号の配列を指定してください

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // シートのインデックスを指定して取得

            int lastRowNum = sheet.getLastRowNum();

           List<List<String>> rowList = new ArrayList<>();
            
            for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row != null) {
                    // 指定された列番号のデータを取り込む
                	List<String> colList = new ArrayList<>();
                    for (int targetColumnNumber : targetColumnNumbers) {
                        Cell cell = row.getCell(targetColumnNumber - 1); // 列番号は0から始まるため、-1する

                        if (cell != null) {
                        	colList.add(cell.toString());
                            // System.out.print(cell.toString() + "\t");
                        } else {
                            // System.out.print("\t"); // セルが存在しない場合は空白を表示
                        }
                    }
                    // System.out.println(); // 次の行への改行
                    rowList.add(colList);
                    
                }
            }

            List<String> res = new ArrayList<String>();
            for(List<String> col : rowList) {
            	for(int j=0;j<col.size()-1;j++) {
                	res.add(toStr(col.get(j),col.get(j+1)));
            	}
            }
            
            // output
            for(String s:res) {
            	System.out.println(s);
            }
            
            DimacsWriter.writeDimacsFile(res, "C:\\\\\\\\Users\\\\\\\\ietar\\\\\\\\OneDrive\\\\\\\\デスクトップ\\\\\\\\研究室\\\\\\\\卒論データ/vend.dimacs");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    1 2→-6 0
//    2 3→-6 0
//    1 3→6 0
//    7 1→-6 0
//    7 8→-6 0
//    8 9→-6 0
//    9 1→-6 0
//    3 4→2 0
//    4 1→2 0
//    3 5→4 0
// 	  5 7→4 0
//    3 6→5 0
//    6 7→5 0
    static public String toStr(String s1,String s2) {
    	switch (Integer.parseInt(s1+s2)){
    	  case 12:
    	  case 23:
    	  case 13:
    	  case 71:
    	  case 78:
    	  case 89:
    	  case 91:
    		  return "6 0";
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


