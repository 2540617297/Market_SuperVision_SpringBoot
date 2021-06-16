package market;

import java.math.BigDecimal;
import java.util.HashMap;

import static java.math.BigDecimal.ROUND_CEILING;

public class testDouble {

    public static void main(String[] args) {

        String base63img="<img contenteditable=\"false\"/>";
        int indexstart=base63img.indexOf("<img");
        int indexend=base63img.indexOf("contenteditable=\"false\"/>");
        System.out.println(base63img.substring(indexstart,indexend+25));
    }
}
