package model;

public class Input {
    private String nomRepertoire;
    private String texteRecherche;

    public Input(String nomRepertoire, String texteRecherche) {
        this.nomRepertoire = nomRepertoire;
        this.texteRecherche = texteRecherche;
    }

    public String getNomRepertoire() {
        return nomRepertoire;
    }

    public String getTexteRecherche() {
        return texteRecherche;
    }
}
