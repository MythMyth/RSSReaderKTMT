package ktmt.rssreader;

import com.google.gson.Gson;

public enum AppAction {
    REFRESH("refresh");

    public String value;

    AppAction(String value) {
        this.value = value;
    }

    private String extraData;

    public AppAction setData(Object extraData) {
        this.extraData = new Gson().toJson(extraData);
        return this;
    }

    public <T> T getData(Class<T> target) {
        return new Gson().fromJson(extraData, target);
    }

    @Override
    public String toString() {
        return value;
    }
}
