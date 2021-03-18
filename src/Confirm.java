import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		user.setSeibetuCustom(request.getParameter("custom")); //性別カスタム
		String proc = request.getParameter("proc");

		request.setAttribute("user", user);
		request.setAttribute("proc", proc);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/member.jsp");

		if("new".equals(proc)) {
			if(Insert(request, response, user)) {
				 dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/complete.jsp");
			}

		}else if("update".equals(proc)) {
			if(Update(request, response, user)) {
				 dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/complete.jsp");
			}

		}

		dispatcher.forward(request, response);

	}

	private boolean Insert(HttpServletRequest request, HttpServletResponse response, UserEntity user)
			throws SQLException {
		int executeCount = 0;
		List<String> paramList = new ArrayList<String>();
		paramList.add(user.getIdLoginUser());
		paramList.add(user.getPassword());
		paramList.add(user.getMeiUser());
		if (user.getSeibetu().equals("")) {
			paramList.add("NULL");
		} else {
			paramList.add(user.getSeibetu());
		}
		paramList.add(user.getAge());
		paramList.add(user.getSeibetuCustom());

		//クエリ生成
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO m_user (");
		sb.append("id_user");
		sb.append(", id_login_user");
		sb.append(", password");
		sb.append(", mei_user");
		sb.append(", seibetu");
		sb.append(", age");
		sb.append(", seibetu_custom");
		sb.append(") ");
		sb.append("VALUES (NULL, ?, ?, ?, ?, ?, ?)");
		String sql = sb.toString();

		executeCount = dba.update(sql, paramList);

		if (executeCount > 0) {
			request.setAttribute("result", "登録しました。");
			return true;
		}
		request.setAttribute("result", "登録に失敗しました。");
		return false;
	}

	private boolean Update(HttpServletRequest request, HttpServletResponse response, UserEntity user) throws SQLException{

		int executeCount = 0;
		List<String> paramList = new ArrayList<String>();
		paramList.add(user.getIdLoginUser());
		paramList.add(user.getPassword());
		paramList.add(user.getMeiUser());
		if (user.getSeibetu().equals("")) {
			paramList.add("NULL");
		} else {
			paramList.add(user.getSeibetu());
		}
		paramList.add(user.getAge());
		paramList.add(user.getSeibetuCustom());
		paramList.add(user.getIdUser());

		//クエリ生成
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE m_user ");
		sb.append("SET ");
		sb.append("id_login_user = ?");
		sb.append(", password = ?");
		sb.append(", mei_user = ?");
		sb.append(", seibetu = ?");
		sb.append(", age = ? ");
		sb.append(", seibetu_custom = ?");
		sb.append("WHERE ");
		sb.append("id_user = ?");
		String sql = sb.toString();

		executeCount = dba.update(sql, paramList);

		if (executeCount > 0) {
			request.setAttribute("result", "登録しました。");
			return true;
		}
		request.setAttribute("result", "登録に失敗しました。");
		return false;
	}


}
