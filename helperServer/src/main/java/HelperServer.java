import Demo.WorkerPrx;

public class HelperServer {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java -jar helperServer.jar <puerto> <nombre_worker>");
            return;
        }

        int port;
        String workerName;

        try {
            port = Integer.parseInt(args[0]);
            workerName = args[1]; // Obtener el nombre del worker desde los argumentos
        } catch (NumberFormatException e) {
            System.err.println("El puerto debe ser un número entero.");
            return;
        }

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            Demo.Worker worker = new WorkerI();
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("WorkerAdapter", "default -p " + port);
            com.zeroc.Ice.ObjectPrx object = adapter.add(worker, com.zeroc.Ice.Util.stringToIdentity(workerName)); 
            adapter.activate(); // Activar el adaptador

            WorkerPrx workerPrx = WorkerPrx.checkedCast(object);
            Demo.MasterPrx masterProxy = Demo.MasterPrx.checkedCast(communicator.stringToProxy("Master:default -p 10000"));

            if (masterProxy != null) {
                boolean added = masterProxy.addWorker(workerName, workerPrx);
                if (added) {
                    System.out.println("Worker registrado exitosamente en el Master.");
                } else {
                    System.out.println("No se pudo registrar el Worker en el Master. Ya existe un worker con ese nombre.");
                }
            } else {
                System.out.println("No se pudo conectar al Master.");
            }

            System.out.println("Worker listo y escuchando en el puerto " + port + "...");
            communicator.waitForShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

