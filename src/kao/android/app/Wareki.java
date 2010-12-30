package kao.android.app;

import java.util.Date;

public class Wareki {
	/* 明治 */
	private static final int I_MEIJI = 1868;
	private static final int I_MEIJI_M = 9;
	private static final int I_MEIJI_D = 8;
	/* 大正 */
	private static final int I_TAISHOU = 1912;
	private static final int I_TAISHOU_M = 7;
	private static final int I_TAISHOU_D = 30;
	/* 昭和 */
	private static final int I_SHOUWA = 1926;
	private static final int I_SHOUWA_M = 12;
	private static final int I_SHOUWA_D = 25;
	/* 平成 */
	private static final int I_HEISEI = 1989;
	private static final int I_HEISEI_M = 1;
	private static final int I_HEISEI_D = 8;

	private String strGengou = null;
	private Date date = null;

	/* コンストラクタ */
	/* int iY 西暦年 */
	/* int iM 月 */
	/* int iD 日 */
	public Wareki(int iY, int iM, int iD) {
		setSeireki(iY, iM, iD);
	}

	/* コンストラクタ */
	/* int strG 和暦元号 */
	/* int iY 和暦年 */
	/* int iM 月 */
	/* int iD 日 */
	public Wareki(String strG, int iY, int iM, int iD) {
		setWareki(strG, iY, iM, iD);
	}

	/* 西暦設定 */
	/* int iY 西暦年 */
	/* int iM 月 */
	/* int iD 日 */
	public void setSeireki(int iY, int iM, int iD) {
		strGengou = new String("明治以前");
		if ((iY > I_MEIJI) || (iY == I_MEIJI && iM > I_MEIJI_M)
				|| (iY == I_MEIJI && iM == I_MEIJI_M && iD >= I_MEIJI_D)) {
			strGengou = new String("明治");
		}
		if ((iY > I_TAISHOU) || (iY == I_TAISHOU && iM > I_TAISHOU_M)
				|| (iY == I_TAISHOU && iM == I_TAISHOU_M && iD >= I_TAISHOU_D)) {
			strGengou = new String("大正");
		}
		if ((iY > I_SHOUWA) || (iY == I_SHOUWA && iM > I_SHOUWA_M)
				|| (iY == I_SHOUWA && iM == I_SHOUWA_M && iD >= I_SHOUWA_D)) {
			strGengou = new String("昭和");
		}
		if ((iY > I_HEISEI) || (iY == I_HEISEI && iM > I_HEISEI_M)
				|| (iY == I_HEISEI && iM == I_HEISEI_M && iD >= I_HEISEI_D)) {
			strGengou = new String("平成");
		}
		this.date = new Date(iY - 1900, iM - 1, iD);
	}

	/* 和暦設定 */
	/* int strG 和暦元号 */
	/* int iY 和暦年 */
	/* int iM 月 */
	/* int iD 日 */
	public void setWareki(String strG, int iY, int iM, int iD) {
		if (strG.equals("明治")) {
			iY = iY + I_MEIJI - 1;
		} else if (strG.equals("大正")) {
			iY = iY + I_TAISHOU - 1;
		} else if (strG.equals("昭和")) {
			iY = iY + I_SHOUWA - 1;
		} else if (strG.equals("平成")) {
			iY = iY + I_HEISEI - 1;
		}
		strGengou = new String("明治以前");
		if ((iY > I_MEIJI) || (iY == I_MEIJI && iM > I_MEIJI_M)
				|| (iY == I_MEIJI && iM == I_MEIJI_M && iD >= I_MEIJI_D)) {
			strGengou = new String("明治");
		}
		if ((iY > I_TAISHOU) || (iY == I_TAISHOU && iM > I_TAISHOU_M)
				|| (iY == I_TAISHOU && iM == I_TAISHOU_M && iD >= I_TAISHOU_D)) {
			strGengou = new String("大正");
		}
		if ((iY > I_SHOUWA) || (iY == I_SHOUWA && iM > I_SHOUWA_M)
				|| (iY == I_SHOUWA && iM == I_SHOUWA_M && iD >= I_SHOUWA_D)) {
			strGengou = new String("昭和");
		}
		if ((iY > I_HEISEI) || (iY == I_HEISEI && iM > I_HEISEI_M)
				|| (iY == I_HEISEI && iM == I_HEISEI_M && iD >= I_HEISEI_D)) {
			strGengou = new String("平成");
		}
		this.date = new Date(iY - 1900, iM - 1, iD);
	}

	/* 和暦元号取得 */
	public String getStrGengou() {
		return strGengou;
	}

	/* 和暦年取得 */
	public int getIWarekiYear() {
		int iY = this.date.getYear() + 1900;
		if (getStrGengou().equals("明治")) {
			iY = iY - I_MEIJI + 1;
		} else if (getStrGengou().equals("大正")) {
			iY = iY - I_TAISHOU + 1;
		} else if (getStrGengou().equals("昭和")) {
			iY = iY - I_SHOUWA + 1;
		} else if (getStrGengou().equals("平成")) {
			iY = iY - I_HEISEI + 1;
		}
		return iY;
	}

	/* 西暦年取得 */
	public int getISeirekiYear() {
		return this.date.getYear() + 1900;
	}

	/* 月取得 */
	public int getIMonth() {
		return this.date.getMonth() + 1;
	}

	/* 日取得 */
	public int getIDate() {
		return this.date.getDate();
	}
}