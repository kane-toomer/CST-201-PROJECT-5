import java.io.*;
import java.util.*;

public class SlopChecker {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int N = Integer.parseInt(br.readLine());
            System.out.println("SLOPS OUTPUT");

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                boolean isSlop = isSlop(str);
                System.out.println(isSlop ? "YES" : "NO");
            }

            System.out.println("END OF OUTPUT");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if a string is a Slop
    public static boolean isSlop(String str) {
        if (str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                int indexOfSlip = findSlipIndex(str.substring(i));
                if (indexOfSlip != -1) {
                    String subSlap = str.substring(i, i + indexOfSlip + 1);
                    String subRest = str.substring(i + indexOfSlip + 1);
                    if (isSlap(subSlap) && isSlip(subRest)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Find the index of the end of a Slip within a string
    private static int findSlipIndex(String str) {
        int balance = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                balance++;
            } else if (str.charAt(i) == 'H') {
                balance--;
                if (balance == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Check if a string is a Slap
    public static boolean isSlap(String str) {
        if (str.equals("AH")) {
            return true;
        }
        if (str.startsWith("A") && str.endsWith("H")) {
            int indexOfC = str.indexOf('C');
            if (indexOfC > 1) {
                String sub1 = str.substring(2, indexOfC);
                String sub2 = str.substring(indexOfC + 1);
                return isSlap(sub1) && (isSlap(sub2) || isSlip(sub2));
            }
        }
        return false;
    }

    // Check if a string is a Slip
    public static boolean isSlip(String str) {
        if (str.isEmpty()) {
            return false;
        }
        if ((str.charAt(0) == 'D' || str.charAt(0) == 'E') && str.length() > 1) {
            int i = 1;
            while (i < str.length() && str.charAt(i) == 'F') {
                i++;
            }
            if (i < str.length() && (isSlip(str.substring(i)) || str.charAt(i) == 'G')) {
                return true;
            }
        }
        return false;
    }
}
