package in.techpro424.itemblacklist.util;

import java.util.ArrayList;

public class Formatting {
    public static String formatArrayList(ArrayList<String> arrayList) {
        String formattedString = "IDs in Blacklist:\n\n";
        for(String str : arrayList) {
            formattedString+="§b"+str+"\n";
        }
        return formattedString;
    }

    public static String formatDimension(String dimensionName) {
        String[] array = dimensionName.split("minecraft:");
        String dimensionName2 = array[1];
        if(!dimensionName2.contains("the_")) return dimensionName2;
        String[] array2 = dimensionName2.split("the_");
        return array2[1];
    }
}
