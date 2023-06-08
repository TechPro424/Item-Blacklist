package in.techpro424.itemblacklist.altconfig;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ConfigTypeAdapter extends TypeAdapter<Config> {

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
