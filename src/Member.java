
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
		System.out.println("新規登録画面に遷移");

		if (proc.contains("new")) {

			//	入力情報を取得する
			UserEntity user = new UserEntity();
			user.setIdUser(request.getParameter("idUser")); //ID
			user.setIdLoginUser(request.getParameter("idLoginUser")); //ログインID
			user.setPassword(request.getParameter("password")); //パスワード
			user.setMeiUser(request.getParameter("meiUser")); //ユーザー名
			user.setAge(request.getParameter("age")); //年齢
			user.setSeibetu(request.getParameter("seibetsu")); //性別
			user.setCustom(request.getParameter("custom")); //性別カスタム
			request.setAttribute("user", user);
			request.setAttribute("proc", proc);

			System.out.println(user);

			//	バリデーションチェック
			boolean isMemberValidate = isMemberValidate(request, user);

			if (isMemberValidate) {
				dispatch = request.getRequestDispatcher("/WEB-INF/jsp/confirm.jsp");
			}

			//	確認画面に進む

		} else if (proc.contains("update")) {

		}

		dispatch.forward(request, response);

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
			if (StringUtils.isBlank(user.getCustom())) {
				request.setAttribute("ERROR_MSG_CUSTOM", SystemConstants.Error_msgEMPTY);
				hasError = false;
			} else if (user.getCustom().getBytes().length > 20) {
				request.setAttribute("ERROR_MSG_CUSTOM", SystemConstants.Error_msgMOJISU);
				hasError = false;
			}
		}
		return hasError;
	}

}
