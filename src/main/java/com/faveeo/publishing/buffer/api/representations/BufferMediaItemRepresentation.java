package com.faveeo.publishing.buffer.api.representations;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BufferMediaItemRepresentation {

    public final String link;
    public final String description;
    public final String title;
    public final String picture;
    public final String photo;
    public final String thumbnail;

    public BufferMediaItemRepresentation(String link, String description, String title, String picture, String photo, String thumbnail) {
        this.link = link;
        this.description = description;
        this.title = title;
        this.picture = picture;
        this.photo = photo;
        this.thumbnail = thumbnail;
    }

    // for FaveeoObjectMapper deserialization
    public BufferMediaItemRepresentation() {
        this.link = null;
        this.description = null;
        this.title = null;
        this.picture = null;
        this.photo = null;
        this.thumbnail = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BufferMediaItemRepresentation that = (BufferMediaItemRepresentation) o;
        return Objects.equals(link, that.link) &&
                Objects.equals(description, that.description) &&
                Objects.equals(title, that.title) &&
                Objects.equals(picture, that.picture) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(thumbnail, that.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, description, title, picture, photo, thumbnail);
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        put(map,"media[link]", link);
        put(map,"media[description]", description);
        put(map,"media[title]", title);
        put(map,"media[picture]", picture);
        put(map,"media[photo]", photo);
        put(map,"media[thumbnail]", thumbnail);
        return map;
    }

    private void put(Map<String, String> map, String key, String value) {
        if(value != null) {
            map.put(key, value);
        }
    }
}
