package models;

import java.util.List;

public class Data {
    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<Character> results;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Character> getCharacterList() {
        return results;
    }

    public void setCharacterList(List<Character> characterList) {
        this.results = characterList;
    }
}
