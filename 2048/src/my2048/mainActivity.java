package my2048;

import java.io.File;
import java.util.Scanner;

public class mainActivity {

	private int[][] number = new int[4][4];
	private boolean moveFalg = true;
	private int point = 0;// �Ʒ���
	private int maxPoint = 0;// ��߷�
	private File s2048 = null;
	private Scanner in;

	public mainActivity() throws Exception {
		
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				number[a][b] = 0;
			}
		}
		
		s2048 = new File("D:\\2048\\s2048-1.txt");
		if (s2048.exists()) {                          //����ļ����ڣ���ȡ�ļ��� 
			String aString = new TxtOperation().readTxtFile("D:\\2048\\s2048-1.txt");
			String[] a1 = aString.split(" ");
			maxPoint = Integer.parseInt(a1[17]);
			System.out.println("����δ��ɵ���Ϸ���Ƿ��ȡ�����ǣ�������y���س�(�������������ַ�Ϊ����ȡ)");
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

	public void game() throws Exception {    //��ʽ��Ϸ����
		AboutGames abo = new AboutGames(number, moveFalg, point, maxPoint, s2048);
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
