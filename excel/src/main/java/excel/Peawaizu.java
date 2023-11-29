package excel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Peawaizu {

    // テキストファイルから指定範囲の行の値を取得
    public static String[] readLines(String filePath, int startLine, int endLine) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] values = new String[endLine - startLine + 1];
            int lineCount = 0;
            String line;

            // 行を読み込んで指定範囲の行の値を配列に格納
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount >= startLine && lineCount <= endLine) {
                    values[lineCount - startLine] = line;
                }
            }

            return values;
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0]; // エラーが発生した場合は空の配列を返す
        }
    }

    // dimacsファイルから最後の行を取得
    public static String readLastLine(String filePath) {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            long length = file.length();
            if (length == 0) {
                return null; // ファイルが空の場合はnullを返す
            } else {
                file.seek(length - 1); // ファイルの最後の位置に移動

                // 改行文字を探す
                while (file.getFilePointer() > 0) {
                    char c = (char) file.read();
                    if (c == '\n') {
                        break;
                    }
                    file.seek(file.getFilePointer() - 2); // 2バイト戻る
                }

                // 最後の行を取得
                return file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // エラーが発生した場合はnullを返す
        }
    }

    // 取得した値をdimacsファイルの一番下に書き込み
    public static void appendToDimacsFile(String filePath, String[] values) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // 取得した値をdimacsファイルに書き込み
            for (String value : values) {
                writer.write(value);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
