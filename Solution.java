import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    private String freeNumber;
    private int total;
    private String time, number;
    private int timeInSec, timeInMin;
    private int hours, minutes, secunds;
    private int topay;
    HashMap<String, Integer> callNumber = new HashMap<>();

    public int solution(String s) {
        freeNumber = findFreeNumber(s);         // find the lowest phone number with maximum total calling time

        String parts[] = s.split("\n");
        for (String part : parts) {
            String[] item = part.split(",");
            time = item[0];
            number = item[1];
            if (!(number.equals(freeNumber))) {
                String[] numbers = time.split(":");
                hours = Integer.parseInt(numbers[0]);       // hours
                minutes = Integer.parseInt(numbers[1]);     // minutes
                secunds = Integer.parseInt(numbers[2]);     // secunds
                timeInSec = hours * 3600 + minutes * 60 + secunds;
                timeInMin = hours * 60 + minutes;
                if (minutes < 5) {                           // 3 cents for every startes second
                    topay = timeInSec * 3;
                } else {                                    // 150 cents for every started minute
                    topay = timeInMin * 150;
                    if (secunds > 0) {                      // start new minute
                        topay = topay + 150;
                    }
                }
                total = total + topay;
            }
        }
        return total;
    }


    String findFreeNumber(String s) {                   // find number with longest total duration
        TreeMap<String, Integer> tempCallNumber = new TreeMap<>();
        String parts[] = s.split("\n");
        for (String part : parts) {
            String[] item = part.split(",");
            String numer = item[1];
            Integer czas = timeS(item[0]);
            if (tempCallNumber.containsKey(numer)) {      // if exist update call time in secunds
                Integer oldtime = tempCallNumber.get(numer);
                tempCallNumber.replace(numer, oldtime + czas);
            } else {                                     // in not exist write number and call time in secunds
                tempCallNumber.put(numer, czas);
            }
        }

//        System.out.println(tempCallNumber.entrySet());
        Integer maxValueInMap = 0;
        for (Map.Entry<String, Integer> entry : tempCallNumber.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() > maxValueInMap) {
                maxValueInMap = entry.getValue();
                freeNumber = entry.getKey();            //  the key with max value
            }
        }
        System.out.println("Free phone number is: " + freeNumber);
        return freeNumber;
    }

    Integer timeS(String czas) {
        String[] numbers = czas.split(":");
        secunds = Integer.parseInt(numbers[2]);      // secunds
        timeInSec = timeM(czas) * 60 + secunds;
        return timeInSec;
    }

    Integer timeM(String czas) {
        String[] numbers = czas.split(":");
        hours = Integer.parseInt(numbers[0]);        // hours
        minutes = Integer.parseInt(numbers[1]);      // minutes
        timeInMin = hours * 60 + minutes;
        return timeInMin;
    }

}




