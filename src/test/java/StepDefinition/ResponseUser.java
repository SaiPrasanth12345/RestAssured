package StepDefinition;

public class ResponseUser {

    private data data;
    private support support;

    public data getData() {
        return data;
    }

    public void setData(data data) {
        this.data = data;
    }

    public support getSupport() {
        return support;
    }

    public void setSupport(support support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
}
