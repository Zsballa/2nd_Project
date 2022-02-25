import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public void menuPrint () {
        System.out.println("------------------------------");
        System.out.println("| 1. Készlet lista           |");
        System.out.println("| 2. Rendelési lista         |");
        System.out.println("| 3. Értékesített autók      |");
        System.out.println("| 4. Új gépjármű felvitele   |");
        System.out.println("| 5. Rendelés feladás        |");
        System.out.println("| 6. Értékesítés             |");
        System.out.println("| 7. Fizetés kalkulátor      |");
        System.out.println("| 8. Kilépés                 |");
        System.out.println("------------------------------");
    }
    public void menuSelect(){
        Scanner userInput = new Scanner(System.in);
        int selectedMenu = userInput.nextInt();
        DealershipRepository repository = new DealershipRepository();
        switch (selectedMenu) {
            case 1:
                repository.stockList();
                repository.close();
                break;
            case 2:
                repository.orderList();
                repository.close();
                break;
            case 3:
                repository.soldCars();
                repository.close();
                break;
            case 4:
                repository.createNewStock();
                repository.close();
                break;
            case 5:
                repository.orderNewCar();
                repository.close();
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                List<Salary> salaryList = new ArrayList<>();
                salaryList=repository.salaryCalculator();
                double team_comission =0;
                for (Salary salary:salaryList) {
                    System.out.print("Hónap: "+salary.getMonth()+", ");
                    System.out.println("Név: "+salary.getSalesPerson());
                    System.out.println("Alapfizetés: "+ salary.getBaseSalary()+"Ft");
                    System.out.println("Értékesítési jutalék: "+salary.getComission()+"Ft");
                    team_comission= repository.getTeamComission(salary.getNewCar()==1,salary.getMonth());
                    System.out.println("Csopot jutalék: "+(team_comission*salary.getTeamComission())+"Ft");
                    System.out.println("Teljes havi fizetés: "+(salary.getBaseSalary()+ salary.getComission()+team_comission*salary.getTeamComission())+"Ft");
                    System.out.println("-----------------------------------------------");
                }
                System.out.println();
                repository.close();
                break;
            case 8:
                System.exit(0);
                break;
        }
        System.out.println("nem a menüből választott");
    }
}
