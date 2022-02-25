public class Salary {
    private String salesPerson;
    private int baseSalary;
    private double comission;
    private double teamComission;
    private String month;
    private int newCar;

    public int getNewCar() {
        return newCar;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salesPerson='" + salesPerson + '\'' +
                ", baseSalary (Ft)=" + baseSalary +
                ", comission (Ft)=" + comission +
                ", teamComission%=" + teamComission +
                ", month='" + month + '\'' +
                ", newCar=" + newCar +
                '}'+"\n";
    }

    public void setNewCar(int newCar) {
        this.newCar = newCar;
    }

    public Salary() {
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getComission() {
        return comission;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    public double getTeamComission() {
        return teamComission;
    }

    public void setTeamComission(double teamComission) {
        this.teamComission = teamComission;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

