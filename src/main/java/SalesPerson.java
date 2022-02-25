public class SalesPerson {
    private int id;
    private String name;
    private boolean newCarSales;
    private boolean manager;

    public SalesPerson(int id, String name, boolean newCarSales, boolean manager) {
        this.id = id;
        this.name = name;
        this.newCarSales = newCarSales;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNewCarSales() {
        return newCarSales;
    }

    public void setNewCarSales(boolean newCarSales) {
        this.newCarSales = newCarSales;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
