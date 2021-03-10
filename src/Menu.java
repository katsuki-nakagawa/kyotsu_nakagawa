
import java.util.Objects;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends BaseServlet {
	/**
	 * メイン処理
	 */
	protected void executeLogic(HttpServletRequest request, HttpServletResponse response) throws Exception {

		switch (Objects.toString(request.getParameter("proc"), "")) {
		case "new":
			request.setAttribute("proc", "new");
			request.getRequestDispatcher("/WEB-INF/jsp/member.jsp").forward(request, response);
		case "memberList":
			break;
		default:
			request.getRequestDispatcher("./Login").forward(request, response);
		}

	}
}
