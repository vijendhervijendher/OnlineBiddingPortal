package com.sourcecode.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] c = request.getCookies();
		if(c == null){
			HttpSession session = request.getSession();
			Cookie cookie = new Cookie("id", session.getId());
			cookie.setSecure(true);
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><title>Datta</title><body><p>Login here!<p><form method ='POST' action='loginservlet'>User name: <input name='user' type='text'></input><br/>Password : <input name='pass' type='password'></input><br/><input type='submit'></form></body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] c = request.getCookies();
		Cookie cookie;
		HttpSession session;
		if((String)request.getParameter("user") != null){
			request.getSession().invalidate();
			session = request.getSession();
			session.setAttribute("username", request.getParameter("user"));
			c = request.getCookies();
			if(c == null){
				cookie = new Cookie("id", session.getId());
				cookie.setSecure(true);
				cookie.setMaxAge(60*60);
			}
			else{
				cookie = c[0];
				cookie.setValue(session.getId());
			}
			response.addCookie(cookie);
			response.sendRedirect("mainservlet");
		}else{
			if(c == null){
				session = request.getSession();
				cookie = new Cookie("id", session.getId());
				cookie.setSecure(true);
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
			}
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><title>aw,a</title><body><br/>login again!</body></html>");
		}
	}
}
