package model;

public class Input {
    private final String directoryName;
    private final String searchText;

    // constructeur et getters
    public Input(String directoryName, String searchText){
        this.directoryName = directoryName;
        this.searchText = searchText;
    }

    public String getDirectoryName(){return directoryName;}
    public String getSearchText(){return searchText;}
}
