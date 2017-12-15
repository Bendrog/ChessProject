package hei.chessproject.commentaire.daos.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import hei.chessproject.commentaire.entities.Ouverture;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OuvertureDaoimpl  {

    protected DataSource getDatasource() {  //Connexion à la base de données
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("chessproject");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    public List<Ouverture> listOuvertures(){ // Méthode qui retourne la liste de toutes les ouvertures
        List<Ouverture> ouvertures = new ArrayList<>();
        try(Connection connection = getDatasource().getConnection();
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ouverture")){
            while (resultSet.next()) {
                ouvertures.add(new Ouverture(
                        resultSet.getInt("ouverture_id"),
                        resultSet.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  ouvertures;
    }   // Méthode qui retourne la liste de toutes les ouvertures

    public List<Ouverture> listOuverturesParAuteur(String AuteurMentionne){ //Méthode qui retourne les commentaires publiés par une même personne
        List<Ouverture> ouvertures = new ArrayList<>();
        try(Connection connection = getDatasource().getConnection();
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ouverture")){
            while (resultSet.next()) {
                ouvertures.add(new Ouverture(
                        resultSet.getInt("ouverture_id"),
                        resultSet.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  ouvertures;
    }

    public List<Ouverture> listOuvertureById(Integer id){
        List<Ouverture> ouvertures = new ArrayList<>();
        try(Connection connection = getDatasource().getConnection();
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ouverture WHERE ouverture_id like ?")){
            while (resultSet.next()) {
                ouvertures.add(new Ouverture(
                        resultSet.getInt("ouverture_id"),
                        resultSet.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  ouvertures;
    }

    }


