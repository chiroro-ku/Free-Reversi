package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ChangeGridStateLogic;
import model.Game;

/**
 * Servlet implementation class ChangeGridLogic
 */
@WebServlet("/NewGrid")
public class NewGrid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewGrid() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Game newGame = (Game) session.getAttribute("newGame");

		request.setCharacterEncoding("UTF-8");
		Integer index = Integer.valueOf(request.getParameter("index"));

		ChangeGridStateLogic changeGridStateLogic = new ChangeGridStateLogic();
		changeGridStateLogic.execute(newGame, index);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newGame.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
