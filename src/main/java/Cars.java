import java.util.Scanner;

public class Cars {
  private int id;
  private String vin;
  private int milage;
  private int listPrice;
  private CarStatus carStatus;
  private String model;
  private String brand;
  private int productionYear;


    public Cars(int id, String vin, int milage, int listPrice, CarStatus carStatus) {
        this.id = id;
        this.vin = vin;
        this.milage = milage;
        this.listPrice = listPrice;
        this.carStatus = carStatus;
    }

    public Cars() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Milyen márkájú?");
        String inputString = userInput.nextLine();
        setBrand(inputString);
        System.out.println("Milyen tipusú?");
        inputString = userInput.nextLine();
        setModel(inputString);
        System.out.println("Mi az alvázszám?");
        inputString = userInput.nextLine();
        setVin(inputString);
        System.out.println("Gyártási év?");
        int inputData = userInput.nextInt();
        setProductionYear(inputData);
        System.out.println("Beszerzési ár?");
        inputData = userInput.nextInt();
        setListPrice(inputData);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", vin='" + vin + '\'' +
                ", milage=" + milage +
                ", listPrice=" + listPrice +
                ", carStatus=" + carStatus +
                ", productionYear=" + productionYear +
                '}';
    }
}
