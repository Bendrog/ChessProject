
/* ICI ON TEST Les méthodes de la DAO Commentaire
*
* On peut se connecter à la base de données puisque l'on est dans le même package que des class contenant la méthode getDatasource() */





package hei.chessproject.commentaire.daos.impl;

import hei.chessproject.commentaire.entities.Commentaire;
import hei.chessproject.commentaire.daos.impl.CommentaireDaoimpl;

import org.junit.Test;
import org.junit.Before;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentaireDaoTestCase {

    private CommentaireDaoimpl commentaireDao = new CommentaireDaoimpl();
    private OuvertureDaoimpl ouvertureDaoimpl = new OuvertureDaoimpl();

    @Before
    public void initDatabase() throws SQLException {
        try (Connection connection = commentaireDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM ouverture");
            stmt.executeUpdate("DELETE FROM commentaire");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (1,'Française')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (2,'Scandinave')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (3,'Sicilienne')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (4,'Alekhine')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (5,'Caro-kann')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (6,'Pirc')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (7,'Hollandaise')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (8,'Bogo-Indienne')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (9,'Catalane')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (10,'Ecossaise')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (11,'Espagnole')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (12,'Italienne')");
        }
    }

    @Test
    public void shouldAddCommentaire() throws SQLException {
        //GIVEN
        Commentaire commentaire = new Commentaire(0, "new auteur", "new texte", 32, 1);
        //WHEN
        commentaireDao.addCommentaire1(commentaire);
        //THEN
        try (Connection connection = commentaireDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM commentaire WHERE auteur = 'new auteur'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("commentaire_id")).isGreaterThan(0);
                assertThat(rs.getString("auteur")).isEqualTo("new auteur");
                assertThat(rs.getString("texte")).isEqualTo("new texte");
                assertThat(rs.getInt("likes")).isEqualTo(32);
                assertThat(rs.next()).isFalse();
            }
        }

    }
}