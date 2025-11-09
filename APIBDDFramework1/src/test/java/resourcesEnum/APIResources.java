package resourcesEnum;

public enum APIResources {
    AddPlace("/maps/api/place/add/json"),
    GetPlace("/maps/api/place/get/json"),
    DeletePlace("/maps/api/place/delete/json");

    private final String path;
    APIResources(String path)
    {
        this.path = path;

    }
    public String path()
    {
        return path;
    }
}
