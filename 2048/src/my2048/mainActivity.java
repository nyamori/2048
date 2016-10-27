package my2048;

import java.io.File;
import java.util.Scanner;

public class mainActivity {

	private int[][] number = new int[4][4];
	private boolean moveFalg = true;
	private int point = 0;// 计分用
	private int maxPoint = 0;// 最高分
	private File s2048 = null;
	private String path = "D:\\s2048.txt";
	private Scanner in;

	public mainActivity() throws Exception {
		s2048 = new File(path);
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				number[a][b] = 0;
			}
		}

		if (s2048.exists()) {
			String aString = new TxtOperation().readTxtFile(path);
			String[] a1 = aString.split(" ");
			maxPoint = Integer.parseInt(a1[17]);
			System.out.println("存在未完成的游戏，是否读取，如是，请输入y并回车(读取将损失上次游戏的一步)");
			in = new Scanner(System.in);
			String input = in.nextLine();
			if (input.equals("y")) {
				int j = 0, k = 0;
				for (int i = 0; i < 18; i++) {
					if (i < 16) {
						number[j][k] = Integer.parseInt(a1[i]);
						k++;
						if (k == 4) {
							k = 0;
							j++;
						}
					} else if (i == 18) {
						point = Integer.parseInt(a1[i]);
					}
				}
				game();
			}else game();
		} else
			game();
	}

	public void game() throws Exception {
		AboutGames abo = new AboutGames(number, moveFalg, point, maxPoint, path, s2048);
		while (true) {
			abo.newGame();
			do {
				abo.move();
				moveFalg = abo.getMoveFlag();
			} while (moveFalg == true);
			abo.restart();
			moveFalg = abo.getMoveFlag();
			if (moveFalg == false) {
				break;
			}
		}
	}

	public static void main(String[] arg) throws Exception {
		new mainActivity();
	}
}
