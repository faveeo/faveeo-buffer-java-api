package com.faveeo.publishing.buffer.api.representations;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@ToString
@Builder
@EqualsAndHashCode
public class BufferMediaItemRepresentation {

    public final String link;
    public final String description;
    public final String title;
    public final String picture;
    public final String photo;
    public final String thumbnail;

    public BufferMediaItemRepresentation(final String link, final String description, final String title, final String picture, final String photo, final String thumbnail) {
        this.link = link;
        this.description = description;
        this.title = title;
        this.picture = picture;
        this.photo = photo;
        this.thumbnail = thumbnail;
    }

    // for FaveeoObjectMapper deserialization
    public BufferMediaItemRepresentation() {
        link = null;
        description = null;
        title = null;
        picture = null;
        photo = null;
        thumbnail = null;
    }

    public Map<String, String> toMap() {
        final Map<String, String> map = new LinkedHashMap<>();
        put(map, "media[link]", link);
        put(map, "media[description]", description);
        put(map, "media[title]", title);
        put(map, "media[picture]", picture);
        put(map, "media[photo]", photo);
        put(map, "media[thumbnail]", thumbnail);
        return map;
    }

    private void put(final Map<String, String> map, final String key, final String value) {
        if (value != null) {
            map.put(key, value);
        }
    }
}
