package crypt;

import org.apache.commons.codec.binary.Hex;

/* Las instancias de esta clase se utilizan para compartir los datos proporcionados por el usuario a traves
 * de la interfaz grafica*/
public class ProcessData {

    private final String originFilePath;
    private final String destinationPath;
    private final String password;
    private final String salt;
    private final Boolean randomIv;
    private final ActionMode mode;
    private final String outputFileName;

    public ProcessData(String pathOrigen, String destinationPath, String password, String salt, Boolean randomIv, ActionMode mode, String outputFileName) {
        this.originFilePath = pathOrigen;
        this.destinationPath = destinationPath;
        this.password = password;
        this.salt = salt;
        this.randomIv = randomIv;
        this.mode = mode;
        this.outputFileName = outputFileName;
    }

    public static ProcessData from(String pathOrigen, String destinationPath, String password, String salt, Boolean randomIv, ActionMode mode, String outputFileName) {
        assertPathNotEmpty(pathOrigen, "de archivo origen");
        assertPathNotEmpty(destinationPath, "destino de archivo salida");
        assertValidSalt(salt);
        assertOutputFileNameNotEmpty(outputFileName);
        return new ProcessData(pathOrigen, destinationPath, password, salt, randomIv, mode, outputFileName);
    }

    private static void assertOutputFileNameNotEmpty(String outputFileName) {
        if (outputFileName.isBlank()){
            throw new RuntimeException("El nombre del archivo salida no puede ser vacio");
        }
    }

    private static void assertValidSalt(String salt) {
        if (salt.isBlank() || salt.length() < 2 || salt.length() > 32){
            throw new RuntimeException("Longitud de salt invalida");
        }else{
            try {
                Hex.decodeHex(salt);
            }catch (Exception e){
                throw new RuntimeException("Salt con formato invalido, debe tratarse de una representacion en hexadecimal");
            }
        }

    }

    private static void assertPathNotEmpty(String pathOrigen, String errorComplemetaryDescription) {
        if (pathOrigen.isBlank()){
            throw new RuntimeException(String.format("Path %s vacio", errorComplemetaryDescription));
        }
    }

    public String getOriginFilePath() {
        return originFilePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }
    public String getPassword() {
        return password;
    }
    public String getSalt() { return salt;}
    public ActionMode getMode() { return mode;}
    public String getOutputFileName() {
        return outputFileName;
    }

    public Boolean getRandomIv() { return randomIv; }
}