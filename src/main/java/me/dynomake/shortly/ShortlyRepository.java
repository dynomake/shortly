package me.dynomake.shortly;

import com.google.gson.Gson;
import lombok.NonNull;
import me.dynomake.generator.RandomStringsPattern;
import me.dynomake.shortly.config.ShortlyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(value = "singleton")
public class ShortlyRepository {

    @Autowired
    private ShortlyConfiguration configuration;

    private Map<String, String> codeLinkMap = new HashMap<>();
    private Gson gson = new Gson();
    private RandomStringsPattern codePattern = RandomStringsPattern.create()
            .letters()
            .numbers();

    private static final String DATA_FILE_PATH = "data.json";

    public ShortlyRepository() {
        loadFromJson();
    }

    public String getLink(@NonNull String code) {
        return codeLinkMap.getOrDefault(code.toLowerCase(), null);
    }

    public String createLink(@NonNull String link) {
        if (configuration.isFixDuplicate() && codeLinkMap.containsValue(link))
            return getKeyByValue(link);

        String code = codePattern.next(configuration.getCharactersInCode());

        if (codeLinkMap.containsKey(code))
            return createLink(link);

        codeLinkMap.put(code.toLowerCase(), link);
        saveToJson();

        return configuration.getDomain() + "/" + code;
    }

    private void saveToJson() {
        try (FileWriter writer = new FileWriter(DATA_FILE_PATH)) {
            gson.toJson(codeLinkMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromJson() {
        if (!new File(DATA_FILE_PATH).exists())
            return;

        try (FileReader reader = new FileReader(DATA_FILE_PATH))
        {
            Map<String, String> data = gson.fromJson(reader, Map.class);

            if (data != null)
                codeLinkMap.putAll(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKeyByValue(String value) {
        for (Map.Entry<String, String> entry : codeLinkMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
