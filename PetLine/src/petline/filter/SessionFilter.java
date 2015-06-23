package petline.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.axis.utils.StringUtils;

public class SessionFilter implements Filter {
	private FilterConfig config;
	private String urlLogin;
	private String appName;
	private Collection<String> exceptions = new ArrayList<String>();

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.urlLogin = config.getInitParameter("URLINICIO");

		if (urlLogin == null || urlLogin.trim().length() == 0) { // Error al cargar la url de login
			throw new ServletException("No se ha configurado URL de login");
		}

		this.appName = config.getInitParameter("APPNAME");
		if (appName == null || appName.trim().length() == 0) { // Error al cargar la url de login
			throw new ServletException("No se ha configurado el nombre de la aplicación.");
		}
		this.appName = "/" + this.appName + "/";

		String exc = config.getInitParameter("EXCEPTIONS");
		if (!StringUtils.isEmpty(exc)) {
			String[] excps = exc.split(",");
			for (int i = 0; i < excps.length; i++) {
				String exception = excps[i];
				exceptions.add(appName + exception.trim());
			}
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getRequestURI();
		HttpSession session = ((HttpServletRequest) request).getSession(false);// don't create if it doesn't exist

		if ((session != null && !session.isNew() && session.getAttribute("SESSION_USER") != null) || appName.equalsIgnoreCase(path) || exceptions.contains(path) || path.indexOf("/services/") != -1) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/" + urlLogin + "?message=Timeout.");
			dispatcher.forward(request, response);
		}
	}
	
	public void destroy() {
		config = null;
	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}
}
