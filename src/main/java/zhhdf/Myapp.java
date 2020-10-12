package zhhdf;

public class Myapp {
    private static int parameterType;
    private static int problemCount;
    private static int numericalRange;
    private static String exercisefile = null;
    private static String answerfile = null;

    public static void main(String[] args) {

        for(String str : args){
            if(("-n").equals(str)){
                parameterType = 1;
                continue;
            }
            if(("-r").equals(str)){
                parameterType = 2;
                continue;
            }
            if(("-e").equals(str)){
                parameterType = 3;
                continue;
            }
            if(("-a").equals(str)){
                parameterType = 4;
                continue;
            }
            if(parameterType==1){
                problemCount = Integer.parseInt(str);
            }
            if(parameterType==2){
                numericalRange = Integer.parseInt(str);
            }
            if(parameterType==3){
                exercisefile = str;
            }
            if(parameterType==4){
                answerfile = str;
            }
            parameterType = 0;
        }

        if(exercisefile!=null)
            Check.checkAnswer(exercisefile,answerfile);
//        System.out.println(problemCount+"       "+numericalRange);


        Generate.generateProblems(problemCount,numericalRange);




    }

}
