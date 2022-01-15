package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import model.Player;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
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
		// ゲームリストをアプリケーションから取得
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		List<Game> gameList = (List<Game>) application.getAttribute("gameList");

		// 取得できなかった時は、ゲームリストを新規作成して、アプリケーションスコープに保存
		if (gameList == null) {
			gameList = new ArrayList<Game>();
			application.setAttribute("gameList", gameList);
		}

		// ログインしているか確認するため、セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		Player loginPlayer = (Player) session.getAttribute("loginPlayer");

		if (loginPlayer == null) {
			response.sendRedirect("/Free-Reversi/");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
