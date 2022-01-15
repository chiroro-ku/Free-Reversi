package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Game;
import model.NewPlayerLogic;
import model.RemovePlayerLogic;

/**
 * Servlet implementation class NewPlayer
 */
@WebServlet("/NewPlayer")
public class NewPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewPlayer() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Game newGame = (Game) session.getAttribute("newGame");

		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("newPlayer") != null) {

			NewPlayerLogic newPlayerLogic = new NewPlayerLogic();
			newPlayerLogic.execute(newGame);

		} else if (request.getParameter("removePlayer") != null) {

			RemovePlayerLogic removePlayerLogic = new RemovePlayerLogic();
			removePlayerLogic.execute(newGame);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newGame.jsp");
		dispatcher.forward(request, response);
	}

}
