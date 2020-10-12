package zhhdf;

public class calculation {
    private static int[] numT;
    private static int[] eleT;
    private static String result = null;
    private static int numerator;//分子
    private static int denominator;//分母


        public static String plus(String num,String element){  //加法
            if(Check.isTrueFraction(num)||Check.isTrueFraction(element)) {
                numT = split(num);
                eleT = split(element);
                numerator = numT[0] * eleT[1] + eleT[0] * numT[1];//相加后的分子
                denominator = numT[1] * eleT[1];//分母
                if (numerator == 0 || denominator == 0) {//分子或分母为0的情况
                    if ("0".equals(num)) return element;
                    if ("0".equals(element)) return num;
                }
                result = reductionFraction(numerator, denominator);
            }else
            result = String.valueOf(Integer.parseInt(num)+Integer.parseInt(element));
        return result;
    }

    public static String minus(String num,String element){//减法
        if(Check.isTrueFraction(num)||Check.isTrueFraction(element)){
            numT = split(num);
            eleT = split(element);
            numerator = numT[0]*eleT[1]-eleT[0]*numT[1];
            denominator = numT[1]*eleT[1];
            if(numerator==0 || denominator==0){
                if("0".equals(num)) return "-"+element;
                if("0".equals(element)) return num;
            }
            result = reductionFraction(numerator,denominator);
        }else
            result = String.valueOf(Integer.parseInt(num)-Integer.parseInt(element));
        return result;
    }

    public static String times(String num,String element){//乘法
        if(Check.isTrueFraction(num)||Check.isTrueFraction(element)){
            numT = split(num);
            eleT = split(element);
            numerator = numT[0]*eleT[0];
            denominator = numT[1]*eleT[1];
            if(denominator==0 || numerator==0)
                return "0";
            result = reductionFraction(numerator,denominator);
        }else
            result = String.valueOf(Integer.parseInt(num)*Integer.parseInt(element));
        return result;
    }

    public static String divides(String num,String element){//除法
//        if(Check.isTrueFraction(num)||Check.isTrueFraction(element)){
        if("0".equals(element))
            return "ERROR";
        if("0".equals(num))
            return "0";
            numT = split(num);
            eleT = split(element);
            numerator = numT[0]*eleT[1];
            denominator = numT[1]*eleT[0];
            result = reductionFraction(numerator,denominator);
//        }else{
//            try{
//                result = String.valueOf(Integer.parseInt(num)/Integer.parseInt(element));
//            }catch (ArithmeticException e){
//                return "ERROR";
//            }
//        }
        return result;
    }

    public static int[] split(String element){//将数转换成数组，例如3/4转换成{3，4}；
//        System.out.println(element);
        int[] result = {0,1};
        String[] strs = element.split("");
        int times = 0;
        String temp = "0";
        String num = "";
        int trueFraction = 0;
        for(String str : strs){
            if("'".equals(str)){
                times = Integer.parseInt(num);
                num = "";
                continue;
            }
            if("/".equals(str)){
                temp = num;             //保存分子
                num = "";
                trueFraction = 1;
                continue;
            }
            num+=str;
        }
        if(trueFraction==0){
            result[0] = Integer.parseInt(num);
        }else{
            if(!"".equals(num))
                result[1] = Integer.parseInt(num);//分母
            result[0] = times*result[1]+Integer.parseInt(temp);//分子
        }
//        System.out.println(result[0]+"        "+result[1]);
        return result;
    }

//传入分子分母，化简分数
    public static String reductionFraction(int numerator,int denominator){
//        System.out.println("分子："+numerator+"   分母："+denominator);
        String result;
        if(numerator<denominator){              //分子小于分母
            for(int i = 2;i<=numerator;i++){    //约至最简
                if(numerator%i==0 && denominator%i==0){
                    numerator/=i;
                    denominator/=i;
                    i = 1;
                }
            }
            result = numerator+"/"+denominator;
        }else{                                    //分子大于分母
            for(int i = 2;i<=denominator;i++){    //约至最简
                if(numerator%i==0 && denominator%i==0){
                    numerator/=i;
                    denominator/=i;
                    i = 1;
                }
            }
//            System.out.println( numerator+"        "+denominator);
            if(numerator%denominator==0)
                result = String.valueOf(numerator/denominator);
            else
                result = numerator/denominator+"'"+numerator%denominator+"/"+denominator;
        }
//        System.out.println(result);
        return result;
    }

}
