package market;

import java.math.BigDecimal;
import java.util.HashMap;

import static java.math.BigDecimal.ROUND_CEILING;

public class testDouble {

    public static void main(String[] args) {
//        String s1="-1.0";
//        BigDecimal num1=new BigDecimal(s1);
//        BigDecimal num2=new BigDecimal("2");
//        HashMap<String,String> hashMap=new HashMap<>();
//        BigDecimal result1 = num1.divide(num2,3,ROUND_CEILING);
//        hashMap.put("de",result1+"");
//        System.out.println(hashMap.get("de"));
//        if(num1.compareTo(num2)<1){
//            System.out.println("1<2");
//        }
        BigDecimal num1=new BigDecimal("1");
        BigDecimal num2=new BigDecimal("0.001");
        num1=num1.divide(new BigDecimal("100.0"),8,BigDecimal.ROUND_CEILING);
        if(num1.compareTo(num2)<1){
            System.out.println("1<2");
        }else{
            System.out.println("false");
        }
        System.out.println(num1);
    }
}
