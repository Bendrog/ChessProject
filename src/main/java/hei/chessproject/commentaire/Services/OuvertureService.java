package hei.chessproject.commentaire.Services;

import com.sun.org.apache.xpath.internal.operations.String;
import hei.chessproject.commentaire.daos.impl.OuvertureDaoimpl;
import hei.chessproject.commentaire.entities.Ouverture;

import javax.print.DocFlavor;
import java.util.List;

public class OuvertureService {

    private static class OuvertureServiceHolder{
        private static OuvertureService instance = new OuvertureService();
    }

    public static OuvertureService getInstance(){
        return OuvertureServiceHolder.instance;
    }

    private OuvertureService() {
    }

    private OuvertureDaoimpl ouvertureDaoimpl = new OuvertureDaoimpl();

    public List<Ouverture> listOuvertures(){
        return ouvertureDaoimpl.listOuvertures();
    }


}
