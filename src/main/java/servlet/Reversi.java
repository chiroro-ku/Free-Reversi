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
import model.PlacePieceLogic;
import model.Player;
import model.PlayerEntryGameLogic;

/**
 * Servlet implementation class Reversi
 */
@WebServlet("/Reversi")
public class Reversi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reversi() {
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
		Game game = (Game) session.getAttribute("game");

		request.setCharacterEncoding("UTF-8");
		Integer index = Integer.valueOf(request.getParameter("index"));

		PlacePieceLogic placePieceLogic = new PlacePieceLogic();
		placePieceLogic.execute(game, index);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reversi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ゲームリストをアプリケーションから取得
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		List<Game> gameList = (List<Game>) application.getAttribute("gameList");

		for (Game aGame : gameList) {
			if (request.getParameter(String.valueOf(aGame.getId())) != null) {

				HttpSession session = request.getSession();
				Player player = (Player) session.getAttribute("loginPlayer");
				PlayerEntryGameLogic playerEntryGameLogic = new PlayerEntryGameLogic();
				playerEntryGameLogic.execute(aGame, player);

				session.setAttribute("game", aGame);

				break;
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reversi.jsp");
		dispatcher.forward(request, response);
	}

}
