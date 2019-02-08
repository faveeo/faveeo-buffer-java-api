package com.faveeo.publishing.buffer.api.representations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BufferMediaItemRepresentation {

    public  String access_token;
    public  String link;
    public  String description;
    public  String title;
    public  String picture;
    public  String photo;
    public  String thumbnail;


    public Map<String, String> toMap() {
         Map<String, String> map = new LinkedHashMap<>(8);
        addingJsonKeyValue(map, "media[link]", link); //NON-NLS
        addingJsonKeyValue(map, "media[description]", description); //NON-NLS
        addingJsonKeyValue(map, "media[title]", title); //NON-NLS
        addingJsonKeyValue(map, "media[picture]", picture); //NON-NLS
        addingJsonKeyValue(map, "media[photo]", photo); //NON-NLS
        addingJsonKeyValue(map, "media[thumbnail]", thumbnail); //NON-NLS
        return map;
    }

    private static void addingJsonKeyValue( Map<? super String, ? super String> jsonKeyValue,  String key,
                                            String value) {
        if (value != null) {
            jsonKeyValue.put(key, value);
        }
    }
}
