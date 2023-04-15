import java.time.LocalTime;
 
public class testingpurposes {
 
    public static void main(String[] args) {
 
        // 1. string
        String strTime = "06:28";
 
 
        // 2. parse time in String format using above dateTimeFormatter
        LocalTime localDate = LocalTime.parse(strTime);
 
 
        // 3. print to console
        System.out.println("Original String :- \n" + strTime);
        System.out.print("\nString in default (HH:mm) format to LocalTime :- \n"
                + localDate);
    }
}