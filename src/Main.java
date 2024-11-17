import UI.UI;
import encriptado.Buffer;
import encriptado.DatosProceso;
import encriptado.Encrypter;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        //Se crea un buffer de tamaño 1 para sincronizar la comunicacion entre main y
        // la UI
        Buffer bufferUI = new Buffer(1);
        UI ui = new UI(bufferUI);

        //Se recuperan los datos ingresados por el usuario
        DatosProceso datosProceso = ui.getDatos();

        //Se lee archivo
        FileInputStream fileInput = new FileInputStream(datosProceso.getPathOrigen());

        //Se crea y escribe el archivo resultante
        FileOutputStream fileOutput = new FileOutputStream(datosProceso.getPathDirectorioDestino() + "/" + datosProceso.getNombreSalida());
        fileOutput.write(Encrypter.encrypt(datosProceso.getPassword(), fileInput.readAllBytes()));

        System.exit(0);
    }
}