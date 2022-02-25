import java.util.Scanner;

public class UsedCars extends Cars {
    public UsedCars(int id, String vin, int milage, int listPrice, CarStatus carStatus) {
        super(id, vin, milage, listPrice, carStatus);
    }

    public UsedCars() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Futásteljesítmény?");
        int inputData = userInput.nextInt();
        setMilage(inputData);
    }

    @Override
    public String toString() {
        return super.toString()+" Used car";
    }
}
