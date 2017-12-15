package hei.chessproject.commentaire.Services;

import hei.chessproject.commentaire.daos.impl.CommentaireDaoimpl;
import hei.chessproject.commentaire.daos.impl.OuvertureDaoimpl;
import hei.chessproject.commentaire.entities.Commentaire;
import hei.chessproject.commentaire.entities.Ouverture;

import java.util.List;

public class CommentaireService {

    private static class CommentaireServiceHolder{
        private static CommentaireService instance = new CommentaireService();
    }

    public static CommentaireService getInstance(){
        return CommentaireService.CommentaireServiceHolder.instance;
    }

    private CommentaireService() {
    }

    private CommentaireDaoimpl commentaireDaoimpl = new CommentaireDaoimpl();

    public List<Commentaire> listDeCommentaires(){
        return commentaireDaoimpl.listDeCommentaires();
    }
    public List<Commentaire> listDeCommentaireParAuteur(String Auteurmentionne){
        return commentaireDaoimpl.listDeCommentaireParAuteur(Auteurmentionne);
    }

    public List<Commentaire> listDeCommentaireParOuverture(String Ouverturementionne){
        return commentaireDaoimpl.listDeCommentaireParOuverture(Ouverturementionne);
    }

    public void addCommentaire (Commentaire commentaire){
        commentaireDaoimpl.addCommentaire(commentaire);
    }
    public void addCommentaire1 (Commentaire commentaires){
        commentaireDaoimpl.addCommentaire1(commentaires);
    }
    public List<Commentaire> listDeCommentaire1(){
        return commentaireDaoimpl.listDeCommentaires1();
    }

}
