package hei.chessproject.commentaire.daos.impl;

import hei.chessproject.commentaire.entities.Ouverture;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

public class OuvertureDaoTestCase {

    private OuvertureDaoimpl ouvertureDaoimpl = new OuvertureDaoimpl();

    @Before
    public void initDatabase () throws SQLException{
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM ouverture");
            stmt.executeUpdate("DELETE FROM commentaire");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (1,'Française')");
            stmt.executeUpdate("INSERT INTO `ouverture`(`ouverture_id`,`nom`) VALUES (2,'Catalane')");
        }
    }

    @Test
    public void shouldListOuvertures(){
        //WHEN
        List<Ouverture> ouvertures =ouvertureDaoimpl.listOuvertures();
        //THEN
        assertThat(ouvertures).hasSize(2);
        assertThat(ouvertures).extracting("id", "nom").containsOnly(
                tuple(1, "Française"),
                tuple(2, "Catalane")
        );
    }
}
