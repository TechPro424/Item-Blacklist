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
        return switch (dimensionName) {
            case "minecraft:overworld" -> "overworld";
            case "minecraft:the_nether" -> "nether";
            case "minecraft:the_end" -> "end";
            default -> "global";
        };
    }
}
