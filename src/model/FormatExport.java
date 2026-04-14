package model;
import annotation.Output;

@Output
public class FormatExport {
    private String fileName;
    private String positionInFile;

    public FormatExport(String result){
        // split la chaîne avec .split("§")
        String[] parts = result.split("§");
        if(parts.length >= 2){
            this.fileName = parts[0];
            this.positionInFile = parts[1];
        }
    }

    // getters
    public String getFileName(){
        return fileName;
    }
    public String getPositionInFile(){
        return positionInFile;
    }
}
