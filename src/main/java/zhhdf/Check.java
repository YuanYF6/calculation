package zhhdf;

public class Check {

    public static boolean isTrueFraction(String element){ //检查是否是真分数
        Boolean result = false;
        String[] strs = element.split("");//拆分运算式子

        for(String s : strs){
            if("/".equals(s) || "'".equals(s)){
                result = true;
                break;
            }
        }
        return result;
    }

    public static void checkAnswer(String exercisefile,String answerfile){//检查答案

        String[] result = new String[2];
        int[] nums = new int[2];
        String exp = null;
        for(;;){

            String str = "";
            int index = 0;          //存储的序号

            if((exp = IOModule.inPut(exercisefile,true)) == null)        //读问题
                break;
            String[] temp = exp.split("");
            int i = 0;
            String t = "";
            for(String s : temp){               //剔除运算式前的序号
                if(" ".equals(s))
                    continue;
                if(i==1){
                    str+=s;
                    continue;
                }
                if(".".equals(s)){
                    i = 1;
                    continue;
                }
                t+=s;
            }
            index = Integer.parseInt(t);        //存序号
            String ans = Generate.calculation(Generate.spiltExpression(str));   //计算问题
            exp = IOModule.inPut(answerfile,false);//读给定的答案文件
            temp = exp.split("");//分割答案
            str = "";
            i = 0;
            for(String s : temp){               //剔除序号
                if(i==1)
                    str+=s;
                if(".".equals(s))
                    i = 1;
            }

//            System.out.println(ans+"            "+str);

            if(ans.equals(str)) {               //读答案并比较
                if(result[0]==null)
                    result[0] = String.valueOf(index);
                else result[0]+=","+index;      //正确答案
                nums[0]++;
            }else{
                if(result[1]==null)
                    result[1] = String.valueOf(index);
                else result[1]+=","+index;      //错误答案
                nums[1]++;
            }
        }
        IOModule.gradeOutput(result,nums);
    }

    public static Boolean mainCheck(String exp,Boolean isExpression){//检查生成的问题是否符合要求
        Boolean result = false;
        if(!isExpression){
            String[] temp = exp.split("");
            for(String s : temp){
                if("-".equals(s)){
                    result = true;
                    break;
                }
            }
        }else{


        }


        return result;
    }




}
