package my2048;

import java.io.File;
import java.util.Scanner;

public class AboutGames {
	private boolean moveFalg;
	private Scanner in;
	private Scanner in2;
	private int[][] number;
	private int point;
	private int maxPoint;
	private boolean winJudge;
	private int b1;
	private int a1;
	private String path;
	private File fileName;

	public AboutGames(int[][] n, boolean mov, int p, int mp, String p1, File f) {
		moveFalg = mov;
		number = n;
		point = p;
		maxPoint = mp;
		fileName = f;
		path = p1;
		winJudge = false;
	}

	private void creatRandomNum() {
		int a = (int) (Math.random() * 4);
		int b = (int) (Math.random() * 4);
		int c = (int) (Math.random() * 10);
		if (number[a][b] > 0 && moveFalg == true) {
			creatRandomNum();
		} else {
			if (c == 0) {
				number[a][b] = 4;
			} else {
				number[a][b] = 2;
			}
			a1 = a;
			b1 = b;
		}
	}

	public void newGame() throws Exception {
		int flag=0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (number[a][b] != 0) {
					flag = 1;
				}
			}
		}
		if (flag == 0) {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					number[a][b] = 0;
				}
			}
			creatRandomNum();
			creatRandomNum();
		}
		save();
		new Background(number, point, maxPoint).background(5, 5);
	}

	public void move() throws Exception {

		in = new Scanner(System.in); // ����һ������
		String e = in.next();

		if (e.equals("q")) { // ���ѡ���˻�
			Operation op = new Operation(number);
			op.keyPressed(e);
			number = op.getNumber();
			maxPoint = op.getMaxPoint();
			point = op.getPoint();
		} else { // ��������
			save();
			Operation op = new Operation(number);
			op.keyPressed(e);
			number = op.getNumber();
			boolean isMove = op.getisMove();
			if (isMove == true) {
				int apoint = op.getPoint();
				addPoint(apoint);
				creatRandomNum();
				judgeMove(number);

			}
		}
		winJudge();
		if (moveFalg == false) {
			lose();
		}
		if (winJudge == true) {
			win();
			moveFalg = false;
		}
		if (moveFalg == true) {
			new Background(number, point, maxPoint).background(a1, b1);
		}
	}

	public void restart() {
		System.out.println("��ϣ�����¿�ʼ��\n����ǣ��밴��n����");
		in2 = new Scanner(System.in);
		String e = in2.next();
		if (e.equals("n")) {
			point = 0;
			moveFalg = true;
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					number[a][b] = 0;
				}
			}
		}
	}

	public boolean getMoveFlag() {
		return moveFalg;
	}

	private void save() throws Exception {
		String string = change();
		fileName = new File(path);
		new TxtOperation().writeTxtFile(string, fileName);
	}

	private String change() {
		String content = "";
		for (int a = 0; a <= 3; a++) {
			for (int b = 0; b <= 3; b++) {
				content = content + String.format("%d", number[a][b]);
				content = content + " \n";
			}
		}
		content = content + String.format("%d", maxPoint) + " \n" + String.format("%d", point) + "\n";
		return content;
	}

	private void addPoint(int apoint) {
		point += apoint;
		if (point >= maxPoint) {
			maxPoint = point;
		}
	}

	private void lose() {
		System.out.println("���ź����������һ����Ϸ�������Ѿ��������ˡ�");
		System.out.println("��л���������ª��2048С��Ϸ��");
		if (fileName.exists()) {
			fileName.delete();
		}
	}

	private void winJudge() {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (number[a][b] == 2048)
					winJudge = true;
			}
		}
	}

	private void win() {
		System.out.println("��ϲ�������ʤ��");
		System.out.println("���Ѿ������2048����ս");
		System.out.println("���ź�����Ϸ���˽���");
		System.out.println("���汾���ǲ��ṩ���߷ֵ���ս");
		System.out.println("�����ϣ��������Ϸ���Ը�����ʾ���У�лл��");
		if (fileName.exists()) {
			fileName.delete();
		}

	}

	private void judgeMove(int[][] number) {
		moveFalg = false;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (number[a][b] == 0) {
					moveFalg = true;
				}
			}
		}
		if (moveFalg == false) {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					if (b < 3) {
						if (number[a][b] == number[a][b + 1]) {
							moveFalg = true;
						}
					}
					if (a < 3) {
						if (number[a][b] == number[a + 1][b]) {
							moveFalg = true;
						}
					}
				}
			}

		}
	}

}
