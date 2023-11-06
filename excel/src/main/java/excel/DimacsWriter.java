package excel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DimacsWriter {

//    public static void main(String[] args) {
//        List<String> res = new ArrayList<>();
//        // ここにresに任意の数値を追加するコードを書いてください
//
//        // DIMACSファイルに書き込む
//        writeDimacsFile(res, "C:\\\\Users\\\\ietar\\\\OneDrive\\\\デスクトップ\\\\研究室\\\\卒論データ/vend.dimacs");
//    }

    public static void writeDimacsFile(List<String> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // データをファイルに書き込む
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }

            // DIMACSファイルの最終行に数値を追加
            //writer.write("c 数値を追加する行");
            //writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
