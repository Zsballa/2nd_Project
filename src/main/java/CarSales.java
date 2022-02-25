public class CarSales {
    private int id;
    private int carId;
    private String dateOfSale;
    private int salesPersonId;
    private int salesprice;

    public CarSales(int id, int carId, String dateOfSale, int salesPersonId, int salesprice) {
        this.id = id;
        this.carId = carId;
        this.dateOfSale = dateOfSale;
        this.salesPersonId = salesPersonId;
        this.salesprice = salesprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public int getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(int salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public int getSalesprice() {
        return salesprice;
    }

    public void setSalesprice(int salesprice) {
        this.salesprice = salesprice;
    }
}
