public class Final {
    public String name;
    public String day;
    public String date;
    public String location;
    public String description;

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Final(String day,String name,String location ,String date, String description) {
        this.name = name;
        this.day = day;
        this.date = date;
        this.location = location;
        this.description = description;
    }
}
