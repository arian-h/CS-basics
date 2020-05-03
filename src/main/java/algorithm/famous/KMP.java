package algorithm.famous;

public class KMP {
    public static int find(String text, String p) {
        int[] states = createStateMachine(p);
        int nextIndexToMatch = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == p.charAt(nextIndexToMatch)) {
                nextIndexToMatch++;
            } else {
                while (nextIndexToMatch > 0 && c != p.charAt(nextIndexToMatch)) {
                    nextIndexToMatch = states[nextIndexToMatch - 1];
                }
            }
            if (nextIndexToMatch == p.length()) {
                return i - p.length() + 1;
            }
        }
        return -1;
    }

//    public static int[] createStateMachine(String p) {
//        int[] states = new int[p.length()];
//        for (int i = 0; i < p.length(); i++) {
//            for (int l = 1; l <= i; l++) {
//                if (p.substring(0, i + 1).substring(l, i + 1).equals(p.substring(0, i - l + 1))) {
//                    states[i] = i - l + 1;
//                    break;
//                }
//            }
//        }
//        return states;
//    }

    public static int[] createStateMachine(String p) {
        int[] states = new int[p.length()];
        for (int i = 1; i < p.length(); i++) {
            int nextIndexToMatch = i;
            while (nextIndexToMatch > 0 && p.charAt(states[nextIndexToMatch - 1]) != p.charAt(i)) {
                nextIndexToMatch = states[nextIndexToMatch - 1];
            }
            if (nextIndexToMatch > 0) {
                states[i] = states[nextIndexToMatch - 1] + 1;
            }
        }
        return states;
    }
}
