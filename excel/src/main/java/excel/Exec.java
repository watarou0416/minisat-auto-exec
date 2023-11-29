package excel;

import java.util.ArrayList;
import java.util.List;

public class Exec {
    public static void main(String[] args) {

        ExcelReader.readSeniExcel();

        List<String> r = new ArrayList<>();

        List<Integer> dr = new ArrayList<>();

        // until SATISFIABLE
        for (int j = 1; j <= 105; j += 3) {

            System.out.println("START");
            String[] res = Peawaizu.readLines("../data/all.txt", j, j + 2);
            for (String s : res) {
                System.out.println(s);
            }

            int count = 0;
            for (int i = 1; i <= 33; i += 4) {

                System.out.println("----------------------------");
                String featureFilePath = "../data/feature.txt";
                String[] values = Peawaizu.readLines(featureFilePath, i, i + 3);
                for (String s : values) {
                    System.out.println(s);
                }

                List<String> cmds = new ArrayList<>();
                cmds.add(
                        "cp /home/kishi/Documents/GitHub/minisat-auto-exec/data/vend.dimacs /home/kishi/Documents/GitHub/minisat-auto-exec/data/tmp"
                                + j + "+" + i + ".dimacs");
                ShellUtil.exec(cmds);

                Peawaizu.appendToDimacsFile("../data/tmp" + j + "+" + i + ".dimacs", res);
                Peawaizu.appendToDimacsFile("../data/tmp" + j + "+" + i + ".dimacs", values);

                cmds = new ArrayList<>();
                cmds.add("minisat ../data/tmp" + j + "+" + i + ".dimacs");
                List<String> execRes = ShellUtil.exec(cmds);
                for (String s : execRes) {
                    System.out.println(s);
                }

                if (!execRes.get(execRes.size() - 1).equals("UNSATISFIABLE")) {
                    r.add(j + "-" + i + "-SATISFIABLE break");
                    break;
                } else {
                    count++;
                }

            }

            if (count == 9) {
                // AL UNSATISFIABLE
                int row = ((j + 2) / 3) + 1;
                dr.add(row);
                r.add(j + "-ALL UNSATISFIABLE which col num : " + row);
            }

        }

        for (String s : r) {
            System.out.println(s);
        }

        ExcelReader.readRealSeniExcel(dr);
    }
}
