package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Game;

/**
 * Servlet implementation class NewGame
 */
@WebServlet("/NewGame")
public class NewGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewGame() {
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
		Game newGame = new Game();
		HttpSession session = request.getSession();
		session.setAttribute("newGame", newGame);

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

		if (request.getParameter("back") != null) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);

		}

		// ゲームリストをアプリケーションから取得
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		List<Game> gameList = (List<Game>) application.getAttribute("gameList");

		HttpSession session = request.getSession();
		Game newGame = (Game) session.getAttribute("newGame");

		newGame.setId(gameList.size());
		gameList.add(newGame);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
