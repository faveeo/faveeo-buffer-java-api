package com.faveeo.publishing.buffer.api.representations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
public class BufferMediaItemRepresentation {

    public final String access_token;
    public final String link;
    public final String description;
    public final String title;
    public final String picture;
    public final String photo;
    public final String thumbnail;


    public Map<String, String> toMap() {
        final Map<String, String> map = new LinkedHashMap<>(8);
        addingJsonKeyValue(map, "media[link]", link); //NON-NLS
        addingJsonKeyValue(map, "media[description]", description); //NON-NLS
        addingJsonKeyValue(map, "media[title]", title); //NON-NLS
        addingJsonKeyValue(map, "media[picture]", picture); //NON-NLS
        addingJsonKeyValue(map, "media[photo]", photo); //NON-NLS
        addingJsonKeyValue(map, "media[thumbnail]", thumbnail); //NON-NLS
        return map;
    }

    private static void addingJsonKeyValue(final Map<? super String, ? super String> jsonKeyValue, final String key,
                                           final String value) {
        if (value != null) {
            jsonKeyValue.put(key, value);
        }
    }
}
