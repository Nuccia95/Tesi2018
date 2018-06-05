package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Place;
import persistence.DaoFactory;

public class WriteFavorites extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String jsonFavorites = req.getParameter("favorites");
		Gson gson = new Gson();
		String [] favoriteslist = gson.fromJson(jsonFavorites, String[].class);
		ArrayList<Place> favorites  = new ArrayList<>();

		for (String i : favoriteslist) {
			if(i!=null && i!="") {
				long id = Long.parseLong(i);
				Place p = DaoFactory.getInstance().makePlaceDao().getByPrimaryKey(id);
				favorites.add(p);
			}
		}
		
		req.getSession().setAttribute("favorites", favorites);
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("writeonplan.jsp").forward(req,resp);			
	}
	
}
