import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("Master:default -p 10000");
            Demo.MasterPrx master = Demo.MasterPrx.checkedCast(base);

            if (master == null) {
                throw new Error("Invalid proxy");
            }

            // Solicitar al usuario la cantidad de puntos
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el número total de puntos para la estimación de π: ");
            int totalPoints = scanner.nextInt();

            // Llamar al método calculatePi en el servidor
            double piEstimate = master.calculatePi(totalPoints);
            System.out.println("Estimación de π: " + piEstimate);
        }
    }
}