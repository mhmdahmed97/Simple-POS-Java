public class Employes {
    public String Address;
    public String Area;
    public String Purpose;
    public int Price;

    public Employes(String FirstName, String SurName, String role, int workinghours) {
        this.Address = FirstName;
        this.Area = SurName;
        this.Purpose = role;
        this.Price = workinghours;
    }

    public String getAddress() {
        return Address;
    }

    public String getArea() {
        return Area;
    }

    public String getPurpose() {
        return Purpose;
    }

    public int getPrice() {
        return Price;
    }
    
}