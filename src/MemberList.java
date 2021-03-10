

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.UserEntity;

/**
 * Servlet implementation class MemberList
 */
@WebServlet("/MemberList")
public class MemberList extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メイン処理
	 */
	protected void executeLogic(HttpServletRequest request, HttpServletResponse response) throws Exception {

		getUserList(request, response);
		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/jsp/memberList.jsp");
		dispatch.forward(request, response);
	}

	private void getUserList(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		List<UserEntity> userList = new ArrayList<UserEntity>();
		userList = dba.selectList("SELECT * FROM m_user", UserEntity.class);
		request.setAttribute("userList", userList);
	}
}
