package in.techpro424.itemblacklist.util;

import java.util.ArrayList;

public class FormatArrayList {
    public static String formatArrayList(ArrayList<String> arrayList) {
        String formattedString = "IDs in Blacklist:\n\n";
        for(String str : arrayList) {
            formattedString+="§b"+str+"\n";
        }
        return formattedString;
    }
}
