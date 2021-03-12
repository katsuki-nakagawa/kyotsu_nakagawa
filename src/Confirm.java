

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.UserEntity;

/**
 * Servlet implementation class Confirm
 */
@WebServlet("/Confirm")
public class Confirm extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * メイン処理
	 */
	protected void executeLogic(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserEntity user = new UserEntity();
		user.setIdUser(request.getParameter("idUser")); //ID
		user.setIdLoginUser(request.getParameter("idLoginUser")); //ログインID
		user.setPassword(request.getParameter("password")); //パスワード
		user.setMeiUser(request.getParameter("meiUser")); //ユーザー名
		user.setAge(request.getParameter("age")); //年齢
		user.setSeibetu(request.getParameter("seibetsu")); //性別
		user.setCustom(request.getParameter("custom")); //性別カスタム
		String proc = request.getParameter("proc");

		request.setAttribute("user", user);
		request.setAttribute("proc", proc);

		RequestDispatcher dispatcher = request.getRequestDispatcher("./member.jsp");

		dispatcher.forward(request, response);

	}


}
