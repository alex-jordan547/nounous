package nounous.jsf.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import nounous.jsf.util.CompteActif;
import nounous.jsf.util.UtilJsf;

@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/pages/user/*" })
public class FilterUser implements Filter {

	
	@Inject
	private CompteActif		compteActif;
	

	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if ( compteActif.isUtilisateur() ) {
	        // si OK, on traite l'URL normalement
	        chain.doFilter(request, response);
	    } else {
	        // sinon on affiche la page d'erreur avec un message
//		    request.setAttribute( "javax.servlet.error.message", "Vous n'êtes pas autorisé à effectuer l'action demandée." );
//	        request.getRequestDispatcher( "/erreur.xhtml" ).forward(request, response);
			request.setAttribute( UtilJsf.MSG_ERROR, "Autorisation insuffisante."  );
			request.getRequestDispatcher( "/pages/info.xhtml" ).forward(request, response);
	    }
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
