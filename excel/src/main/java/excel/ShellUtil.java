package excel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShellUtil {
    public static List<String> exec(List<String> commands) {
        List<String> rspList = new ArrayList<String>();
        Runtime run = Runtime.getRuntime();

        try {
            Process proc = run.exec("/bin/bash", null, null);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);

            for (String line : commands) {
                out.println(line);
            }
            out.println("exit");

            String rspLine = "";
            while ((rspLine = in.readLine()) != null) {
                rspList.add(rspLine);
            }

            proc.waitFor();
            in.close();
            out.close();
            proc.destroy();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return rspList;
    }
}
