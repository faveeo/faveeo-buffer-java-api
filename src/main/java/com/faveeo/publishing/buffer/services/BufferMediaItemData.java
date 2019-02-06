package com.faveeo.publishing.buffer.services;

import com.faveeo.publishing.buffer.model.BufferMediaTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Collections;
import java.util.Set;

@Builder
@AllArgsConstructor
public class BufferMediaItemData {
    /**
     * URL to be provided as a media
     */
    public String url;
    /**
     * The title for the media
     */
    public String title;
    /**
     * The picture to be used a media
     */
    public String picture;
    /**
     * An optional media description
     */
    public String description;
    /**
     * The list of medias available to build this media item.
     */
    @Builder.Default
    public Set<BufferMediaTypes> medias = Collections.emptySet();
}
