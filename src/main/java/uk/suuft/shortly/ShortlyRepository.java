package uk.suuft.shortly;

import lombok.NonNull;
import uk.suuft.shortly.util.PasswordGenerator;

import java.util.HashMap;
import java.util.Map;

public class ShortlyRepository {
    private PasswordGenerator generator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .build();
    private Map<String, String> codeLinkMap = new HashMap<>();

    public String getLink(@NonNull String code) {
        return codeLinkMap.get(code.toLowerCase());
    }

    public String createLink(@NonNull String link) {
        String code = generator.generate(7);
        codeLinkMap.put(code.toLowerCase(), link);
        return "https://shrly.xyz/" + code;
    }
}
