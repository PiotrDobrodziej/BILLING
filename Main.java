import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File phoneBill = new File("src\\log.txt");
        Scanner in = null;
        try {
            in = new Scanner(phoneBill, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Save all items from log file into String variable: allPhoneCalls
        String allPhoneCalls = "";
        while (in.hasNext()) {
            allPhoneCalls += in.nextLine() + "\n";
        }

        Solution sol = new Solution();
        System.out.println("-------------------------------------------------");
        System.out.println("Amount of money you have to pay is: " + sol.solution(allPhoneCalls) + " cents");
        System.out.println("-------------------------------------------------");
    }

}
