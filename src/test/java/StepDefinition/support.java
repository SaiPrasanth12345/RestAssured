package StepDefinition;

public class support {

    private String url;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "support{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
