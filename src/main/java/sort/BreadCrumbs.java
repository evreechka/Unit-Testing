package sort;

public class BreadCrumbs {

    private StringBuilder path = new StringBuilder("/");

    public StringBuilder getPath() {
        return path;
    }

    public void addPathValue(Integer value) {
        path.append(value).append("_");
    }
    public void endSubPath() {
        path.replace(path.length() - 1, path.length(), "/");
    }
}
