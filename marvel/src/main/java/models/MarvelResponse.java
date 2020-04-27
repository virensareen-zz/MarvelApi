package models;

import org.springframework.http.HttpStatus;

public class MarvelResponse {

    public int statusCode;
    public Data data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
