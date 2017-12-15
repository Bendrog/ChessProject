package hei.chessproject.commentaire.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Commentaire {

    private Integer id;
    private String auteur;
    private String texte;
    private Integer likes;
    private Integer ID;
    
    public Commentaire(Integer id, String auteur, String texte, Integer likes, Integer ID) {
        this.id = id;
        this.auteur = auteur;
        this.texte = texte;
        this.likes = likes;
        this.ID = ID;
    }

    public Commentaire(Integer ID) {
        this.ID = ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
