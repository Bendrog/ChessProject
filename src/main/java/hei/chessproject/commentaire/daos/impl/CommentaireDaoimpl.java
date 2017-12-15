
/*              /\ CE PROJET N'A PAS PU ETRE TERMINEEE /\
*
* OBJECTIFS INITIAL: -Pouvoir commenter chacune des ouvertures (ICI DEFENCE=OUVERTURE),
 *
 *                    pour que les connaisseurs puissent partager leurs expériences aux novices.
 *
 *
 * #Que fera-t-il dans le futur? -> Commenter les ouvertures et consulterles commentaires émis par les autres vistiteurs
 *                               -> Liker les commentaires les plus pertinants et ses ouvertures favorites
 *                               -> Contacter les administrateurs via le formulaire de contact
 *-------------------------------------------------------------------------------------------------------------------------------
 * EXPLICATION DES DIFFERENTS addCommentaires:  2options
 *
 * 1) Faire une fonction add pour chacune des ouvertures, où "ouverture_id" est écrit en dur
 *          Problème -> long et répétitif
 *
 * 2) Faire une seule fonction addCommentaire et remplir l'id avec un input caché de l'utilisateur
 *      Comme ci-après <input type="hidden"  th:value="${ouverture.id}">
 *----------------------------------------------------------------------------------------------------------------------------
 *       */



package hei.chessproject.commentaire.daos.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import hei.chessproject.commentaire.entities.Commentaire;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireDaoimpl {
    protected DataSource getDatasource() {  //Connexion à la base de données, Adaptez-le à votre SGBD ;)
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("chessproject");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        return dataSource;
    }       //Connexion à la base de données, Adaptez-le à votre SGBD ;)

    public List<Commentaire> listDeCommentaires() { //Renvoye la liste de tous les commentaires
        List<Commentaire> commentaires = new ArrayList<>();
        try (Connection connection = getDatasource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM commentaire")) {
            while (resultSet.next()) {
                commentaires.add(new Commentaire(
                        resultSet.getInt("commentaire_id"),
                        resultSet.getString("auteur"),
                        resultSet.getString("texte"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("ouverture_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }//Renvoye la liste de tous les commentaires

    public List<Commentaire> listDeCommentaireParAuteur(String Auteurmentionne){  // Retourne les commentaires de l'auteur renseigné
        List<Commentaire> commentaires = new ArrayList<>();
        try(Connection connection = getDatasource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM commentaire WHERE auteur LIKE ?")){

            statement.setString(1, "%"+Auteurmentionne+"%");
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    commentaires.add(new Commentaire(
                            resultSet.getInt("commentaire_id"),
                            resultSet.getString("auteur"),
                            resultSet.getString("texte"),
                            resultSet.getInt("likes"),
                            resultSet.getInt("ouverture_id")));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }   // Retourne les commentaires de l'auteur renseigné


    public List<Commentaire> listDeCommentaireParOuverture(String Ouverturementionne){   //Méthode qui retourne une list des comments de l'ouverture saisie
        List<Commentaire> commentaires = new ArrayList<>();
        try(Connection connection = getDatasource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM commentaire LEFT JOIN ouverture ON commentaire.ouverture_id = ouverture.ouverture_id WHERE commentaire.nom LIKE ?")){

            statement.setString(1, "%"+Ouverturementionne+"%");
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    commentaires.add(new Commentaire(
                            resultSet.getInt("commentaire_id"),
                            resultSet.getString("auteur"),
                            resultSet.getString("texte"),
                            resultSet.getInt("likes"),
                            resultSet.getInt("ouverture_id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }

    public void addCommentaire(Commentaire commentaire){
        String query = "INSERT INTO commentaire (texte, auteur, likes, ouverture_id) VALUES(?,?,?,?)";
        try(Connection connection = getDatasource().getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, commentaire.getTexte());
            statement.setString(2, commentaire.getAuteur());
            statement.setInt(3, commentaire.getLikes());
            statement.executeUpdate();
            } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void addCommentaire1(Commentaire commentaire){//Ajouter un commentaire
        String query = "INSERT INTO commentaire (texte, auteur, likes, ouverture_id) VALUES(?,?,?, 1)";
        try(Connection connection = getDatasource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, commentaire.getTexte());
            statement.setString(2, commentaire.getAuteur());
            statement.setInt(3, commentaire.getLikes());
            statement.executeUpdate();
            } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Commentaire> listDeCommentaires1() { //Retourne les comments de l'ouverture 1
        List<Commentaire> commentaires = new ArrayList<>();
        try (Connection connection = getDatasource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM commentaire WHERE ouverture_id LIKE 1")) {
            while (resultSet.next()) {
                commentaires.add(new Commentaire(
                        resultSet.getInt("commentaire_id"),
                        resultSet.getString("auteur"),
                        resultSet.getString("texte"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("ouverture_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }

    public void addCommentaire2(Commentaire commentaire){//Ajouter un commentaire
        String query = "INSERT INTO commentaire (texte, auteur, likes, ouverture_id) VALUES(?,?,?, 2)";
        try(Connection connection = getDatasource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, commentaire.getTexte());
            statement.setString(2, commentaire.getAuteur());
            statement.setInt(3, commentaire.getLikes());
            statement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Commentaire> listDeCommentaires2() { //Retourne les comments de l'ouverture 1
        List<Commentaire> commentaires = new ArrayList<>();
        try (Connection connection = getDatasource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM commentaire WHERE ouverture_id LIKE 2")) {
            while (resultSet.next()) {
                commentaires.add(new Commentaire(
                        resultSet.getInt("commentaire_id"),
                        resultSet.getString("auteur"),
                        resultSet.getString("texte"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("ouverture_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }
}