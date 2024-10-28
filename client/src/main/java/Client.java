import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            Demo.MasterPrx masterProxy = Demo.MasterPrx.checkedCast(communicator.stringToProxy("Master:default -p 10000"));

            if (masterProxy == null) {
                System.out.println("No se pudo conectar al Master.");
                return;
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el número total de puntos para la estimación de π: ");
            int totalPoints = scanner.nextInt();

            // Iniciar el cronómetro
            long startTime = System.currentTimeMillis();

            double piEstimate = masterProxy.calculatePi(totalPoints);

            // Detener el cronómetro
            long endTime = System.currentTimeMillis();

            // Calcular el tiempo de espera
            long duration = endTime - startTime; // en milisegundos

            System.out.println("Estimación de π: " + piEstimate);
            System.out.println("Tiempo de espera: " + duration + " milisegundos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
