

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends BaseServlet {
	/**
	 * メイン処理
	 */
	protected void executeLogic(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String proc = request.getParameter("proc");
		System.out.println("遷移パラメーター"+proc);
		System.out.println("新規登録画面に遷移");

		if(proc.contains("new")) {

			//	バリデーション処理

			//	確認画面に進む


		}

	}


}
