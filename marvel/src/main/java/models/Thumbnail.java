package models;

import lombok.NonNull;

public class Thumbnail {

    @NonNull
    private String path;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @NonNull
    private String extension;

    public Thumbnail() {
    }
}
