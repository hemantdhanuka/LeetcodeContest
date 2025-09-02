package PracticeQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q0093 {
    static List<String> ansList = new ArrayList<String>();

    public static void main(String[] args) {
        Q0093 obj = new Q0093();
        System.out.println(obj.restoreIpAddresses("101023"));
    }

    public List<String> restoreIpAddresses(String s) {
        ansList = new ArrayList<String>();
        if (s.length() > 12 || s.length() < 4) {
            return ansList;
        }

        breakIp(s, new LinkedList<>());

        return ansList;

    }

    private void breakIp(String remainingString, LinkedList<String> breakUpList) {
        if (breakUpList.size() == 3) {
            if (validateIpPart(remainingString)) {
                breakUpList.addLast(remainingString);
                ansList.add(breakUpList.get(0) + "." + breakUpList.get(1) + "." + breakUpList.get(2) + "." + breakUpList.get(3));
                breakUpList.removeLast();
            }
            return;
        }

        for (int positionToInsertDot = 1; positionToInsertDot <= 3 && positionToInsertDot < remainingString.length(); positionToInsertDot++) {
            String ip = remainingString.substring(0, positionToInsertDot);
            boolean isValid = validateIpPart(ip);

            if (isValid) {
                breakUpList.addLast(ip);
                breakIp(remainingString.substring(positionToInsertDot), breakUpList);
                breakUpList.removeLast();
            }
        }
    }

    private boolean validateIpPart(String ip) {
        if (ip.length() > 3) {
            return false;
        }
        if (ip.charAt(0) == '0' && ip.length() > 1) {
            return false;
        }

        int ipValue = Integer.parseInt(ip);
        if (ipValue > 255) {
            return false;
        }

        return true;
    }

}
