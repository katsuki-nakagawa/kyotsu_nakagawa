
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import constants.SystemConstants;
import entity.UserEntity;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends BaseServlet {
	/**
	 * メイン処理
	 */
	protected void executeLogic(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/jsp/member.jsp");

		//	遷移情報
		String proc = request.getParameter("proc");

		//	ログ
		System.out.println("遷移パラメーター" + proc);

		//update OR deleteの場合、user情報を取得しmember画面に表示する
		if(proc.equals("update_user") || proc.equals("delete_user")) {
			String id_user = request.getParameter("id_user");
			getUserData(request, response);

			if(proc.equals("update_user")) {
				request.setAttribute("proc", "update");
			} else if (proc.equals("delete_user")) {
				request.setAttribute("proc", "delete");
			}

			request.setAttribute("id_user", id_user);
			dispatch.forward(request, response);
			return;
		}

		//	入力情報を取得する
		UserEntity user = new UserEntity();
		user.setIdUser(request.getParameter("id_user")); //ID
		user.setIdLoginUser(request.getParameter("idLoginUser")); //ログインID
		user.setPassword(request.getParameter("password")); //パスワード
		user.setMeiUser(request.getParameter("meiUser")); //ユーザー名
		user.setAge(request.getParameter("age")); //年齢
		user.setSeibetu(request.getParameter("seibetsu")); //性別
		user.setSeibetuCustom(request.getParameter("custom")); //性別カスタム
		request.setAttribute("user", user);
		request.setAttribute("proc", proc);



		//	新規登録 OR 更新登録
		if (proc.contains("new") || proc.contains("update")) {

			//	バリデーションチェック
			boolean isMemberValidate = this.isMemberValidate(request, user);
			//	二重登録チェック
			boolean isDoubleCheck = this.isDoubleCheck(request, response, user, proc);

			if (isMemberValidate && isDoubleCheck) {
				//	確認画面へ遷移する
				dispatch = request.getRequestDispatcher("/WEB-INF/jsp/confirm.jsp");
			}

		} else if (proc.contains("delete")) {
			dispatch = request.getRequestDispatcher("/WEB-INF/jsp/confirm.jsp");
		}

		dispatch.forward(request, response);

	}

	//	重複チェック
	private boolean isDoubleCheck(HttpServletRequest request, HttpServletResponse response, UserEntity user,
			String proc)
			throws SQLException {

		List<String> paramList = new ArrayList<String>();
		paramList.add(user.getIdLoginUser());

		if (proc.contains("new")) {
			//	新規登録
			if (dba.selectOne("SELECT * FROM m_user WHERE id_login_user = ?", UserEntity.class, paramList) == null) {
				return true;
			}
		} else if (proc.contains("update")) {
			//	更新登録
			paramList.add(user.getIdUser());
			if (dba.selectOne("SELECT * FROM m_user WHERE id_login_user = ? AND id_user NOT IN(?)", UserEntity.class,
					paramList) == null) {
				return true;
			}

		}

		request.setAttribute("ERROR_MSG_ID", SystemConstants.Error_msgID);
		return false;

	}

	private void getUserData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id_user = request.getParameter("id_user");
		String proc = request.getParameter("proc");
		List<String> paramList = new ArrayList<String>();
		paramList.add(id_user);
		UserEntity user = null;
		user = dba.selectOne("SELECT * FROM m_user WHERE id_user = ?", UserEntity.class, paramList);

		if (user != null) {
			if ("update".equals(proc)) {
				request.setAttribute("proc", "update");
			} else if ("delete".equals(proc)) {
				request.setAttribute("proc", "delete");
			}
		}
		request.setAttribute("user", user);
	}

	/**
	 * 入力チェック
	 * @param request
	 * @param user
	 * @return
	 */
	private boolean isMemberValidate(HttpServletRequest request, UserEntity user) {
		boolean hasError = true;

		//ID入力チェック
		if (StringUtils.isBlank(user.getIdLoginUser())) { //空白チェック
			request.setAttribute("ERROR_MSG_ID", SystemConstants.Error_msgEMPTY);
			hasError = false;
		} else if (!user.getIdLoginUser().matches(SystemConstants.Regex001)) { //半角英数記号チェック
			request.setAttribute("ERROR_MSG_ID", SystemConstants.Error_msgHANKAKU_EISU);
			hasError = false;
		} else if (user.getIdLoginUser().getBytes().length > 20) { //文字数チェック
			request.setAttribute("ERROR_MSG_ID", SystemConstants.Error_msgMOJISU);
			hasError = false;
		}

		//PASSチェック
		if (StringUtils.isBlank(user.getPassword())) { //空白チェック
			request.setAttribute("ERROR_MSG_PASS", SystemConstants.Error_msgEMPTY);
			hasError = false;
		} else if (!user.getPassword().matches(SystemConstants.Regex001)) { //半角英数記号チェック
			request.setAttribute("ERROR_MSG_PASS", SystemConstants.Error_msgHANKAKU_EISU);
			hasError = false;
		} else if (user.getPassword().getBytes().length > 20) { //文字数チェック
			request.setAttribute("ERROR_MSG_PASS", SystemConstants.Error_msgMOJISU);
			hasError = false;
		}

		//氏名のチェック
		if (StringUtils.isBlank(user.getMeiUser())) {
			request.setAttribute("ERROR_MSG_NAME", SystemConstants.Error_msgEMPTY);
			hasError = false;
		} else if (!user.getMeiUser().matches(SystemConstants.Regex002)) {
			request.setAttribute("ERROR_MSG_NAME", SystemConstants.Error_msgZENKAKU);
			hasError = false;
		} else if (user.getMeiUser().getBytes().length > 20) {
			request.setAttribute("ERROR_MSG_NAME", SystemConstants.Error_msgMOJISU);
			hasError = false;
		}

		//年齢チェック
		Pattern p = Pattern.compile("^0+([0-9]+.*)");
		Matcher m = p.matcher(user.getAge());

		if (StringUtils.isBlank(user.getAge())) {
			request.setAttribute("ERROR_MSG_AGE", SystemConstants.Error_msgEMPTY);
			hasError = false;
		} else if (!user.getAge().matches(SystemConstants.Regex003)) {
			request.setAttribute("ERROR_MSG_AGE", SystemConstants.Error_msgSEISU);
			hasError = false;
		} else if (m.matches()) {
			request.setAttribute("ERROR_MSG_AGE", SystemConstants.Error_msgZERO_SUPP);
			hasError = false;
		} else if (user.getAge().getBytes().length > 3) {
			request.setAttribute("ERROR_MSG_AGE", SystemConstants.Error_msgMOJISU);
			hasError = false;
		}

		//性別（カスタム）チェック
		if (user.getSeibetu().equals("2")) {
			System.out.println("性別カスタム");
			if (StringUtils.isBlank(user.getSeibetuCustom())) {
				request.setAttribute("ERROR_MSG_CUSTOM", SystemConstants.Error_msgEMPTY);
				hasError = false;
			} else if (user.getSeibetuCustom().getBytes().length > 20) {
				request.setAttribute("ERROR_MSG_CUSTOM", SystemConstants.Error_msgMOJISU);
				hasError = false;
			}
		}
		return hasError;
	}

}
