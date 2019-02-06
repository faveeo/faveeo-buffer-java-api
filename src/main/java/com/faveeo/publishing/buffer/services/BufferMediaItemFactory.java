package com.faveeo.publishing.buffer.services;

import com.faveeo.publishing.buffer.api.representations.BufferMediaItemRepresentation;
import com.faveeo.publishing.buffer.model.BufferMediaTypes;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * This factory can be used to build the Buffer media item to associate a media to a buffer update.
 */
@Slf4j
public final class BufferMediaItemFactory {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("api");

    private BufferMediaItemFactory() {
        super();
    }

    /**
     * Creates the media for the buffer post.
     *
     * @param bufferMediaItemData the data required to build a buffer media item.
     * @return the buffer media representation.
     */
    @SuppressWarnings("FeatureEnvy")
    public static Optional<BufferMediaItemRepresentation> createMedia(final BufferMediaItemData bufferMediaItemData) {
        String link = null;
        String description = null;
        String title = null;
        String picture = null;

        final Set<BufferMediaTypes> medias = bufferMediaItemData.medias;
        if (medias == null || medias.isEmpty()) {
            log.warn(resourceBundle.getString("no.media.provided.for.the.buffer.update.creation"));
            return Optional.empty();
        }
        for (final BufferMediaTypes media : medias) {
            switch (media) {
                case link:
                    // we use the update link
                    link = bufferMediaItemData.url;
                    break;
                case title:
                    title = bufferMediaItemData.title;
                    break;
                case picture:
                    picture = bufferMediaItemData.picture;
                    break;
                case description:
                    description = bufferMediaItemData.description;
                    break;
            }
        }
        // Buffer API require the thumbnail to be set if picture is set. So we copy picture to thumbnail
        final BufferMediaItemRepresentation.BufferMediaItemRepresentationBuilder builder =
                BufferMediaItemRepresentation.builder();
        builder.link(link);
        builder.description(description);
        builder.title(title);
        builder.picture(picture);
        builder.photo(picture);
        builder.thumbnail(picture);
        final BufferMediaItemRepresentation bufferMediaItemRepresentation = builder.build();
        return Optional.of(bufferMediaItemRepresentation);

    }

}
