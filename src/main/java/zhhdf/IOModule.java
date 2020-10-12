package zhhdf;


import java.io.*;

public class IOModule {

    private static String ouptProblems = "Exercises.txt";
    private static String ouptAnswers = "Answers.txt";
    private static BufferedWriter bwP;
    private static BufferedWriter bwA;
    private static BufferedReader brA;
    private static BufferedReader brE;

    public static void outPut(int count, String expression, Boolean problem) {//输出问题文件和答案文件
        try {
            if (problem) {
                if (bwP == null) {
                    bwP = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouptProblems, false)));
                    bwP.write("");
                    bwP.flush();
                    bwP = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouptProblems, true)));
                }
                bwP.append(count + "." + expression);
                bwP.newLine();
                bwP.flush();
            } else {
                if (bwA == null) {
                    bwA = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouptAnswers, false)));
                    bwA.write("");
                    bwA.flush();
                    bwA = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouptAnswers, true)));
                }
                bwA.append(count + "." + expression);
                bwA.newLine();
                bwA.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public static String inPut(String srcPath,Boolean problem) {     //输入问题文件和答案文件返回式子或null
        String data = null;

        try {
            if (problem) {
                if (brE == null) {
                    brE = new BufferedReader(new InputStreamReader(new FileInputStream(srcPath)));
                }
                data = brE.readLine();
            } else {
                if (brA == null) {
                    brA = new BufferedReader(new InputStreamReader(new FileInputStream(srcPath)));
                }
                data = brA.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }




        return data;
    }

    public static void gradeOutput(String[] result,int [] nums){//输出Grade文件

        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Grade.txt", false)));
            bw.write("");
            bw.flush();
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Grade.txt", true)));
            bw.append("Correct:"+nums[0]+"("+result[0]+")");//正确题号
            bw.newLine();
            bw.append("Wrong:"+nums[1]+"("+result[1]+")");//错误题号
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
