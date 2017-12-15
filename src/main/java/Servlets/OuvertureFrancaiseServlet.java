package Servlets;

import hei.chessproject.commentaire.Services.CommentaireService;
import hei.chessproject.commentaire.entities.Commentaire;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/OuvertureFrancaise")

public class OuvertureFrancaiseServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Commentaire> commentaires = CommentaireService.getInstance().listDeCommentaire1();
        String texte = req.getParameter("commentaire");
        String auteur = req.getParameter("auteur");
        Commentaire commentaire = new Commentaire(null, texte, auteur, 1, 1 );

        context.setVariable("commentaires", commentaires);


        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.process("OuvertureFrancaise", context, resp.getWriter());
    }
}
