package crypt;

/* Las instancias de esta clase se utilizan para compartir los datos proporcionados por el usuario a traves
 * de la interfaz grafica*/
public class ProcessData {

    private final String originFilePath;
    private final String destinationPath;
    private final String password;
    private final String salt;
    private final int mode;
    private final String outputFileName;

    public ProcessData(String pathOrigen, String destinationPath, String password, String salt, int mode, String outputFileName) {
        this.originFilePath = pathOrigen;
        this.destinationPath = destinationPath;
        this.password = password;
        this.salt = salt;
        this.mode = mode;
        this.outputFileName = outputFileName;
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
    public int getMode() { return mode;}
    public String getOutputFileName() {
        return outputFileName;
    }

}