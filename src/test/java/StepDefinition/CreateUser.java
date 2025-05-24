package StepDefinition;

public class CreateUser {
    private String name;
    private String job;


    public CreateUser(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

}
