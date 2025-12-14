package in.techpro424.itemblacklist.util;

import java.util.ArrayList;

public class Formatting {
    public static String formatArrayList(ArrayList<String> arrayList) {
        StringBuilder formattedString = new StringBuilder("Items in the blacklist:\n\n");
        for(String str : arrayList) {
            formattedString.append("§b").append(str).append("\n");
        }
        return formattedString.toString();
    }
}
