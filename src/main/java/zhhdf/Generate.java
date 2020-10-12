package zhhdf;

import java.util.Random;

public class Generate {

    private static final String[] symbols = {"+","-","×","÷"};


    public static void generateProblems(int problemCount,int numericalRange){       //生成问题
        //problemCount问题数量，numericalRange数值范围
        Random random = new Random();
        int e;
        String num;
        int count = 1,symbol = 0;
        int Parentheses;//总括号数
        int openParentheses,closeParentheses;   //左括号，右括号个数

        for(int i = 1;i<=problemCount;i++){
            boolean invalid = false;
            String expression = "";
            do{
                e = random.nextInt(4);      //随机生成运算符的个数，不超过3个。
            }while(e==0);
            Parentheses = openParentheses = random.nextInt(e);
            closeParentheses = 0;
            int[] ParenthesesRepeat = new int[Parentheses];
            int parindex = 0;
//            System.out.println("Parentheses = "+Parentheses);
//            System.out.println("e = "+e);
            for(int j = 0;j<=e;j++){
                boolean probability;
                if(j==e){
                    if(symbol==3){  //若有÷号
                        do{
                            num = generateNum(numericalRange);
                        }while("0".equals(num));
                    }
                    else num = generateNum(numericalRange);
                    expression=expression+" "+num+" ";
                    if(closeParentheses!=0){
                        do{
                            expression+=") ";
                            closeParentheses--;
                        }while(closeParentheses!=0);
                    }
                    if(Check.mainCheck(expression,true)){//剔除不符合要求的问题；题目数-1；
                        i--;
                        break;
                    }
//                    System.out.println("已完成问题生成："+count+"."+expression);
                    if("ERROR".equals(generateAnswer(count,expression))){//检查答案是否为负数，若是则剔除这道题；
                        i--;
                        break;
                    }
                    IOModule.outPut(count,expression,true);
                    count++;
                    break;
                }
                if(symbol==3){
                    do{
                        num = generateNum(numericalRange);
                    }while("0".equals(num));
                }
                else num = generateNum(numericalRange);
                symbol = random.nextInt(4);
                if(openParentheses!=0){
                    String exp = "";
                    do{
                        if(parindex!=0 && ParenthesesRepeat[parindex-1]==(e-j))
                            break;
                        if(invalid && j==e-1)
                            break;
                        probability = (random.nextInt(e)>=Parentheses);
                        if(probability){
                            if(!invalid)
                                invalid = true;
                            exp=exp+" (";
                            openParentheses--;  closeParentheses++; ParenthesesRepeat[parindex]++;
                        }
                    }while(probability&&openParentheses!=0);
                    exp=exp+" "+num+" "+symbols[symbol];
//                    System.out.println("左：");
//                    System.out.println(expression);
                    if(ParenthesesRepeat[parindex]!=0){
                        parindex++;
                        expression+=exp;
                        continue;
                    }
                }
                if(closeParentheses!=0){
//                    String exp = "";
                    expression=expression+" "+num+" ";
                    do{
                        probability = (random.nextInt(e)>=Parentheses);
//                        System.out.println("j= "+j+"   e= "+e);
//                        System.out.println("ParenthesesRepeat[parindex-1]= "+ParenthesesRepeat[parindex-1]);
                        if(ParenthesesRepeat[parindex-1]>=(e-j))
                            probability = true;
                        if(probability){
                            expression+=") ";
                            closeParentheses--;
                            ParenthesesRepeat[parindex-1]--;
                        }
                        if(ParenthesesRepeat[parindex-1]==0){
                            parindex--;
                        }else
                            break;
                    }while(probability&&closeParentheses!=0);
                    expression=expression+symbols[symbol];
//                    System.out.println("右：");
//                    System.out.println(expression);
                    continue;
                }
                expression=expression+" "+num+" "+symbols[symbol];
            }
        }
    }

    public static String generateAnswer(int count,String expression){     //生成答案
        String[] exp = spiltExpression(expression);
        String result = calculation(exp);
//        System.out.println("已生成答案： "+result);
        if("ERROR".equals(result)||Check.mainCheck(result,false))
            return "ERROR";
        IOModule.outPut(count, String.valueOf(result),false);
        return result;
    }

    public static String calculation(String[] expression){       //计算答案
        int count = 0;
        boolean haveBrackets = false;
        String[] exp = new String[expression.length];
        String[] e = new String[expression.length];
        for(int i = 0,j = 0,k = 0;i<expression.length;i++){      //将括号分割成式子
            if(expression[i]==null)
                break;
            if(("(").equals(expression[i])){
                count++;
                if(!haveBrackets){
                    haveBrackets = true;
                    continue;
                }
            }
            if((")").equals(expression[i])){
                count--;
                if(count==0){
                    haveBrackets = false;
                    String temp = calculation(e);
                    if("ERROR".equals(temp))
                        return "ERROR";
                    exp[k++] = temp;
                    continue;
                }
            }
            if(!haveBrackets)
                exp[k++] = expression[i];
            else e[j++] = expression[i];        //要递归的式子
        }

//                    System.out.println("exp=     ");
//                    for(String s : exp){
//                        System.out.print(s);
//                    }
//                    System.out.println("");

        //处理乘除加减法
        String result = "0";
        int lastSymbol = 1;
        String[] exp2 = new String[exp.length];
        String lastNum = null;
        for(int i = 0,k = 0;i<exp.length;i++){
            if(("×").equals(exp[i])||("÷").equals(exp[i])){     //一级处理，先计算乘除算术表达式
                String num = "1";
                if(lastNum!=null)
                    num = lastNum;
                int firstSymbol = 0;
                for(;!(("+").equals(exp[i])||("-").equals(exp[i]));i++){
                    if(firstSymbol==1){     //乘法
                        num = calculation.times(num,exp[i]);
                        firstSymbol=0;
                    }else if(firstSymbol==2){   //除法
                        num = calculation.divides(num,exp[i]);
                        if("ERROR".equals(num))
                            return "ERROR";
                        firstSymbol=0;
                    }else if(("×").equals(exp[i])){
                        firstSymbol=1;
                    }else if(("÷").equals(exp[i])){
                        firstSymbol=2;
                    }
                    if(i==exp.length-1)
                        break;
                }
                exp2[k-1] = String.valueOf(num);
                if(i==exp.length-1)
                    break;
            }
            lastNum = exp[i];
            exp2[k++] = lastNum;
        }
//        System.out.println("完成一级处理");

        for(String str : exp2){          //二级处理，计算加减算术表达式
            if(str==null)
                continue;
            switch (str){
                case "+":
                    lastSymbol = 1;
                    break;
                case "-":
                    lastSymbol = 2;
                    break;
                default:
//                    String num = str;
                    switch (lastSymbol){
                        case 1:
                            result = calculation.plus(result,str);
                            break;
                        case 2:
                            result = calculation.minus(result,str);
                            break;
                    }
            }
        }
//        System.out.println(result);
//        System.out.println("完成二级处理");

        return result;
    }



    public static String[] spiltExpression(String expression){      //分割运算符和数字
        String[] exp = new String[15];
        String[] e = expression.split("");
        int i = 0;
        for(String s : e){
            if(" ".equals(s)){
                continue;
            }
            if(("+").equals(s)||("-").equals(s)||("×").equals(s)||("÷").equals(s)||("(").equals(s)||(")").equals(s)){
                if(exp[i]!=null)
                    exp[++i] = s;
                else exp[i] = s;
                i++;
                continue;
            }
            if(exp[i]==null)
                exp[i]=s;
            else
                exp[i]+=s;
        }

//        System.out.println("已完成分割：");
//
//        for(String s : exp){
//            System.out.print(s);
//        }
//        System.out.println("");

        return exp;
    }

    public static String generateNum(int numericalRange){//生成数字
        Random random = new Random();
        String num = "";
        if(random.nextInt(10)<2){
            int[] n = {0,1,1};
            int i = 0;
            do{
                if(i==1){
                    n[i] = random.nextInt(numericalRange-1);
                }else
                    n[i] = random.nextInt(numericalRange);
                if(n[i]==0)
                    continue;
                if(n[2]<n[1])
                    continue;;
                if(i==2 && n[i]==1)
                    continue;
                i++;
            }while(i<3);
            if(n[0]==0){
                num = n[1]+"/"+n[2];
            }else{
                num = n[0]+"'"+n[1]+"/"+n[2];
            }
        }else
            num+=random.nextInt(numericalRange);
        return num;
    }


}
