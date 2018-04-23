package test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class EMCUtil {


    public final static int compareVersion(String left, String right){
        if (left == null && right == null) {
            return 0;
        }

        if (left == null || right == null) {
            return left == null ? -1 : 1;
        }

        left = left.trim().split(" ")[0].replace('-', '.');
        right = right.trim().split(" ")[0].replace('-', '.');


        List<String> leftVersions = Arrays.asList(left.split("\\."));
        List<String> rightVersions = Arrays.asList(right.split("\\."));

        ListStrToInt converter = t -> t.stream()
                .mapToInt(s -> Integer.parseInt(removeSuffix(s)))
                .boxed()
                .collect(Collectors.toList());

        List<Integer> leftIntVersions = converter.arrStrToInt(leftVersions);
        List<Integer> rightIntVersions = converter.arrStrToInt(rightVersions);

        int min = Math.min(leftIntVersions.size(), rightIntVersions.size());

        for (int i = 0; i < min; ++i) {
            if (leftIntVersions.get(i) < rightIntVersions.get(i)) {
                return -1;
            }
            else if (leftIntVersions.get(i) > rightIntVersions.get(i)) {
                return 1;
            }
        }

        if(leftIntVersions.size() > min){
            for (int i = min; i<leftIntVersions.size();i++){
                if(leftIntVersions.get(i) > 0){
                    return 1;
                }
            }
        }

        if(rightIntVersions.size() > min){
            for (int i = min; i<rightIntVersions.size();i++){
                if(rightIntVersions.get(i) > 0){
                    return -1;
                }
            }
        }

        return 0;
    }

    @FunctionalInterface
    private interface ListStrToInt {
        public List<Integer> arrStrToInt(List<String> arr);
    }

    private static String removeSuffix(String s){
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c < '0' || c > '9' ){
                s = s.substring(0, i);
                break;
            }
        }
        return s;
    }

    public static void copyPropertiesExclude(Object from, Object to, String[] excludAttrs) throws Exception {
        List<String> excludesList = null;
        for (int i = 0; i < excludAttrs.length; i++) {
            excludAttrs[i] = excludAttrs[i].toLowerCase();
        }
        if (excludAttrs != null && excludAttrs.length > 0) {
            excludesList = Arrays.asList(excludAttrs);
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method toMethod = null;
        String fromMethodName = null, toMethodName = null;
        for (Method fromMethod : fromMethods) {
            fromMethodName = fromMethod.getName();
            if (!fromMethodName.contains("get")) {
                continue;
            }
            if (excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {
                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            for (Method m : toMethods) {
                if (m.getName().equals(toMethodName)) {
                    toMethod = m;
                    break;
                }
            }

            if (toMethod == null) {
                continue;
            }
            Object value = fromMethod.invoke(from, new Object[0]);
            if (value == null || value instanceof Collection || value.getClass().isArray()) {
                continue;
            }

            toMethod.invoke(to, new Object[] { value });
        }
    }

    public static String[] getSSHCommand(String host, String username, String password, String command) {
        String target = username + "@" + host;
        // In Bash, ' (single quote) doesn't accept any interpret of special characters;
        // The only thing cannot be put into a a pair of ' (s) is ' itself;
        // Thus all ' (s) must be wrapped with a pair of " (double quote);
        // Each section splitted by ' (s) must be wrapped with a pair of ' (single quote);
        // step 1: replace all ' (s) with '"'"' (sdsds)
        // step 2: quote the whole string with a pair of ' (s)
        String escapedTarget = target.replace("'", "'\"'\"'");
        String escapedPassword = password.replace("'", "'\"'\"'");
        String escapedCommand = command.replace("'", "'\"'\"'");

        String remoteCommand = "sshpass -p '" + escapedPassword
                + "' ssh -o StrictHostKeychecking=no -o UserKnownHostsFile=/dev/null '" + escapedTarget + "' '"
                + escapedCommand + "'";

        return new String[] { "bash", "-c", remoteCommand };
    }
}
