package pundix.merchant.com.filerecvandroiddemo.datamodel;

public class ServiceModle {
    private String path = "";
    private String baseStore = "";
    private String term = "";
    private String Expires = "";
    private String distance = "";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBaseStore() {
        return baseStore;
    }

    public void setBaseStore(String baseStore) {
        this.baseStore = baseStore;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getExpires() {
        return Expires;
    }

    public void setExpires(String expires) {
        Expires = expires;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
