import java.util.Scanner;

public class NewCar extends Cars{

    public NewCar() {
    }

    public NewCar(int id, String vin, int milage, int listPrice, CarStatus carStatus) {
        super(id, vin, milage, listPrice, carStatus);
    }

    @Override
    public String toString() {
        return super.toString()+" new car";
    }
}
