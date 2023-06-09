package in.techpro424.itemblacklist.config;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ConfigTypeAdapter extends TypeAdapter<Config> {

    /* TODO find a way to add these comments to the config
     * # The list of items to block.
     * # Each item must be separated by a comma, and must be in the following format.
     * # "mod_id:item_id"
     * # For example, warped planks would be"minecraft:warped_planks"
     */

    @Override
    public Config read(JsonReader in) throws IOException {
        Config config = new Config();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
    
            case "item-blacklist":
                in.beginArray();
                while (in.hasNext()) {
                    config.itemBlacklist.add(in.nextString());
                }
                in.endArray();
              break;
            }

          }
          in.endObject();
          return config;
    }

    @Override
    public void write(final JsonWriter out, Config config) throws IOException {
        out.beginObject();
        out.name("item-blacklist").beginArray();

        for (String logName : config.itemBlacklist) {
            out.value(logName);
        }
        out.endArray();
        out.endObject();
    }
    
}
