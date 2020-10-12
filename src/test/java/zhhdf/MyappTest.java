package zhhdf;

import org.junit.Test;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MyappTest {


    @Test
    public void Test(){

//        System.out.println(**********);
        System.out.println("÷".equals(String.valueOf('\u00f7')));

    }

    @Test
    public void myappTest(){


        String[] args = {"-n","10000","-r","15"};
        Myapp.main(args);

    }

    @Test
    public void answerTest(){
        String expression = "( 4'8/2 + 3'9/2 ) × 9 - 8'11/2";
        Generate.generateAnswer(1,expression);

    }

    @Test
    public void calculationTest(){
        String expression = "15/12 ÷ ( 98/13 - 13/13 ) - 4'9/8";
        String expression1 = "6'7/13";
        String expression2 = "98/13 - 13/13";
        String expression3 = "15/12 ÷ ( 98/13 - 13/13 )";
        String expression4 = "( 98/13 - 13/13 ) - 4'9/8";
        Generate.generateAnswer(1,expression);

    }

    @Test
    public void isTrueFractionTest(){
        String str = "6/7";
        Check.isTrueFraction(str);

    }

    @Test
    public void spiltExpressionTest(){
        String expression = "12 + 6/7";
        Generate.spiltExpression(expression);

    }

    @Test
    public void spiltTest(){
        String expression = "6/7";
        calculation.split(expression);

    }

    @Test
    public void checkAnswerTest(){
        String exercisefile = "Exercises.txt";
        String answerfile = "Answers.txt";

        Check.checkAnswer(exercisefile,answerfile);
    }



























//    @Test
//    public static void inPut(String srcPath){
//         String ouptProblems = "Exercises.txt";
//         String ouptAnswers = "Answers.txt";
//         BufferedWriter bwP;
//         BufferedWriter bwA;
//         BufferedReader brP;
//         BufferedReader brA;
//        try {
//            File tempfile=new File(srcPath.trim());
//            String fileName=tempfile.getName();
//
//            if("exercisefile".equals(fileName)){
//                brP = new BufferedReader(new OutputStreamReader(new FileInputStream(fileName, false)));
//                String data;
//                while((data=brP.readLine())!=null){
//                    bwP = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouptAnswers, false)));
//                    bwP.write("");
//                    bwP.flush();
//                    bwP = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouptAnswers, true)));
//                  bwP.append(count + "." + generateAnswer(data));
//                  bwP.newLine();
//                  bwP.flush();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//        }
//
//    }

}
