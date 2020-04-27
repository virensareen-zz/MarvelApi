package models;

import lombok.NonNull;

public class Thumbnail {

    @NonNull
    private String path;

    @NonNull
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }



    public Thumbnail() {
    }
}
