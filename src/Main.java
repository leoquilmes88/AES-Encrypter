import UI.UI;
import crypt.Buffer;
import crypt.ProcessData;
import crypt.AESCipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        //Se crea un buffer de tamaño 1 para sincronizar la comunicacion entre main y
        // la UI
        Buffer bufferUI = new Buffer(1);
        UI ui = new UI(bufferUI);

        //Se recuperan los datos ingresados por el usuario
        ProcessData processData = ui.getData();

        //Se lee archivo
        FileInputStream fileInput = new FileInputStream(processData.getOriginFilePath());

        //Se crea y escribe el archivo resultante
        FileOutputStream fileOutput = new FileOutputStream(processData.getDestinationPath() + "/" +
                processData.getOutputFileName());
        fileOutput.write(AESCipher.doAction(processData.getPassword(), processData.getSalt(), processData.getRandomIv(), processData.getMode(),
                fileInput.readAllBytes()));

        ui.notification("Proceso realizado con exito");
        System.exit(0);
    }
}