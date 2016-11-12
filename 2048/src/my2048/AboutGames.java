package my2048;

import java.io.File;
import java.util.Scanner;
/**
 * ��Ϸ����ز���
 */
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
    private File fileName;

	public AboutGames(int[][] n, boolean mov, int p, int mp, File f) {
		moveFalg = mov;
		number = n;
		point = p;
		maxPoint = mp;
		fileName = f;
		winJudge = false;
	}
/*
 *����һ���������a��b��¼�����꣬c�����˳���2��4�ĸ��ʣ�2���ֵĸ���Ϊ0.9����
 *a1��b1ͬ����¼�����꣬���ڱ�������ʱ��Ϊ��ǡ�
 */
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
/*
 * ����һ������Ϸ�������ж�number�������Ƿ��з���ֵ,�м�Ϊ��ȡ�浵��������޼�Ϊ����Ϸ��
 */
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
			creatRandomNum();
			creatRandomNum();
		}
		save("D:\\2048\\s2048.txt");
		new Background(number, point, maxPoint).background(5, 5);
	}
/*
 * �ƶ��Ĺ��̡����ܼ��̵����룬�������������ز�����Ȼ��浵���ж���Ϸ�Ƿ�ʤ����ʧ�ܵȡ�
 */
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
			save("D:\\2048\\s2048.txt");
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
			save("D:\\2048\\s2048-1.txt");
			new Background(number, point, maxPoint).background(a1, b1);
		}
	}
/*
 * ���¿�ʼ�����û�ȷ�Ϻ��������̣����õ�ǰ�÷֣����±�Ϊ���ƶ���
 */
	public void restart() {
		System.out.println("��ϣ�����¿�ʼ��\n����ǣ��밴��n����");
		in2 = new Scanner(System.in);
		String e = in2.next();
		if (e.equals("n")) {
			delete();
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
/*
 * �浵�ķ������������change����������Ȼ�����ݴ���txt�ı���
 */
	private void save(String path) throws Exception {
		String string = change();
		fileName = new File(path);
		new TxtOperation().writeTxtFile(string, fileName);
	}
/*
 * ����ǰ���̣���ǰ�÷֣���߷ִ�����ַ������Ա�浵��
 */
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

	private void addPoint(int apoint) {         //�Ʒ�
		point += apoint;
		if (point >= maxPoint) {
			maxPoint = point;
		}
	}
/*
 * ʧ��ʱ��ӡ����delete�浵��ʧ�ܲ��ɷ��ڣ�
 */
	private void lose() {
		System.out.println("���ź����������һ����Ϸ�������Ѿ��������ˡ�");
		System.out.println("��л���������ª��2048С��Ϸ��");
		delete();
	}
/*
 * ������̣�ֻҪ��2048��Ӯ�ˡ�
 */
	private void winJudge() {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (number[a][b] == 2048)
					winJudge = true;
			}
		}
	}
/*
 * ʤ��ʱ��ӡ����delete�浵��ʤ������������
 */
	private void win() {
		System.out.println("��ϲ�������ʤ��");
		System.out.println("���Ѿ������2048����ս");
		System.out.println("���ź�����Ϸ���˽���");
		System.out.println("���汾���ǲ��ṩ���߷ֵ���ս");
		System.out.println("�����ϣ��������Ϸ���Ը�����ʾ���У�лл��");
		delete();

	}
/*
 * ���Ƿ���ƶ����жϡ���������Ƿ�����ֵ�����Ƿ��пɺϲ�ֵ��
 */
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
/*
 * ɾ���ļ��ķ������浵�ͳ��ص��ļ����ᱻɾ����
 * ֻ����win��lose����á�
 */
	private void delete(){
		fileName=new File("D:\\2048\\s2048.txt");
		if (fileName.exists()) {
			fileName.delete();
		}
		fileName=new File("D:\\2048\\s2048-1.txt");
		if (fileName.exists()) {
			fileName.delete();
		}
	}
}
