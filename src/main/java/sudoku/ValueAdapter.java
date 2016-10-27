package sudoku;


public class ValueAdapter {

    public static Value getValue(char c){
        switch (c){
            case '1':return Value.ONE;
            case '2':return Value.TWO;
            case '3':return Value.THREE;
            case '4':return Value.FOUR;
            case '5':return Value.FIVE;
            case '6':return Value.SIX;
            case '7':return Value.SEVEN;
            case '8':return Value.EIGHT;
            case '9':return Value.NINE;
            default:return null;

        }

    }
    public static String getValue(Value v){
        if(v==null)return "#";
        switch (v){
            case ONE :return "1";
            case TWO:return "2";
            case THREE:return "3";
            case FOUR:return "4";
            case FIVE :return "5";
            case SIX:return"6" ;
            case SEVEN:return "7";
            case EIGHT:return"8" ;
            case NINE:return "9";
            default:return "#";

        }

    }
}
