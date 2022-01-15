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
import model.NewTableLogic;

/**
 * Servlet implementation class NewTable
 */
@WebServlet("/NewTable")
public class NewTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewTable() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Integer maxColumn = Integer.valueOf(request.getParameter("maxColumn"));
		Integer maxRow = Integer.valueOf(request.getParameter("maxRow"));

		HttpSession session = request.getSession();
		Game newGame = (Game) session.getAttribute("newGame");
		NewTableLogic newTableLogic = new NewTableLogic();
		newTableLogic.execute(newGame, maxColumn, maxRow);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newGame.jsp");
		dispatcher.forward(request, response);
	}

}
