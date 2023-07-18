package application;

public class Old_Quiz {
    private String name;
    private String description;
    private int time_limit;
    private String time_type;

    public Old_Quiz(String name, String description, int time_limit, String time_type) {
        this.name = name;
        this.description = description;
        this.time_limit = time_limit;
        this.time_type = time_type;
    }

    public Old_Quiz() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public String getTime_type() {
        return time_type;
    }

    public void setTime_type(String time_type) {
        this.time_type = time_type;
    }
}
