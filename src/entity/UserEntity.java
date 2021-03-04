package entity;

public class UserEntity {
	/**
	 * ユーザーID
	 */
	private String idUser;

	/**
	 * ログインユーザーID
	 */
	private String idLoginUser;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * ユーザー名
	 */
	private String meiUser;

	/**
	 * 性別（0：男／1：女）
	 */
	private String seibetu;

	/**
	 * 年齢
	 */
	private String age;

	/**
	 * @return idUser
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser セットする idUser
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return idLoginUser
	 */
	public String getIdLoginUser() {
		return idLoginUser;
	}

	/**
	 * @param idLoginUser セットする idLoginUser
	 */
	public void setIdLoginUser(String idLoginUser) {
		this.idLoginUser = idLoginUser;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return meiUser
	 */
	public String getMeiUser() {
		return meiUser;
	}

	/**
	 * @param meiUser セットする meiUser
	 */
	public void setMeiUser(String meiUser) {
		this.meiUser = meiUser;
	}

	/**
	 * @return seibetu
	 */
	public String getSeibetu() {
		return seibetu;
	}

	/**
	 * @param seibetu セットする seibetu
	 */
	public void setSeibetu(String seibetu) {
		this.seibetu = seibetu;
	}

	/**
	 * @return age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age セットする age
	 */
	public void setAge(String age) {
		this.age = age;
	}
}
