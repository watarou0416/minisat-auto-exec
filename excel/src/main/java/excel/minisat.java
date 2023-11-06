package excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class minisat {

    public static void main(String[] args) {
        // Minisatの実行コマンド（パスが通っている必要があります）
        String minisatCommand = "minisat";

        // DIMACSファイルのパス
        String dimacsFilePath = "C:\\\\Users\\\\ietar\\\\OneDrive\\\\デスクトップ\\\\研究室\\\\卒論データ/vend.dimacs";

        // Minisatを実行する
        runMinisat(minisatCommand, dimacsFilePath);
    }

    public static void runMinisat(String minisatCommand, String dimacsFilePath) {
        try {
            // Minisatを起動する外部プロセスを構築
            ProcessBuilder processBuilder = new ProcessBuilder(minisatCommand + " " +  dimacsFilePath);
            Process process = processBuilder.start();

            // Minisatの標準出力を読み取り、表示する
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // プロセスの終了を待機
            int exitCode = process.waitFor();
            System.out.println("Minisatプロセスの終了コード: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
