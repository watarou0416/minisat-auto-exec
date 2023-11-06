import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class minisat {

    public static void main(String[] args) throws IOException, InterruptedException {
        // DIMACSファイルを読み込む
        File file = new File("C:\\\\Users\\\\ietar\\\\OneDrive\\\\デスクトップ\\\\研究室\\\\卒論データ/vend.dimacs");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // minisatを実行する
        String command = "C:\\\\\\\\Users\\\\\\\\ietar\\\\\\\\OneDrive\\\\\\\\デスクトップ\\\\\\\\研究室\\\\\\\\卒論データ/vend.dimacs";
        Process process = Runtime.getRuntime().exec(command);

        // minisatの出力を読み込む
        BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = outputReader.readLine()) != null) {
            System.out.println(line);
        }

        // minisatの終了を待つ
        process.waitFor();
    }
}
