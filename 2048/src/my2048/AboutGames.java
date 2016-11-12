package my2048;

import java.io.File;
import java.util.Scanner;
/**
 * 游戏的相关操作
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
 *创造一个随机数，a与b记录了坐标，c体现了出现2或4的概率（2出现的概率为0.9）；
 *a1与b1同样记录了坐标，将在背景绘制时作为标记。
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
 * 创建一个新游戏，首先判断number数组中是否有非零值,有既为读取存档的情况，无既为新游戏。
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
 * 移动的过程。接受键盘的输入，根据输入进行相关操作。然后存档，判定游戏是否胜利或失败等。
 */
	public void move() throws Exception {

		in = new Scanner(System.in); // 输入一个操作
		String e = in.next();

		if (e.equals("q")) { // 如果选择退回
			Operation op = new Operation(number);
			op.keyPressed(e);
			number = op.getNumber();
			maxPoint = op.getMaxPoint();
			point = op.getPoint();
		} else { // 正常操作
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
 * 重新开始，当用户确认后，重置棋盘，重置当前得分，重新变为可移动。
 */
	public void restart() {
		System.out.println("你希望重新开始吗？\n如果是，请按下n键。");
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
 * 存档的方法，将会调用change（）方法，然后将内容存入txt文本。
 */
	private void save(String path) throws Exception {
		String string = change();
		fileName = new File(path);
		new TxtOperation().writeTxtFile(string, fileName);
	}
/*
 * 将当前棋盘，当前得分，最高分处理成字符串，以便存档。
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

	private void addPoint(int apoint) {         //计分
		point += apoint;
		if (point >= maxPoint) {
			maxPoint = point;
		}
	}
/*
 * 失败时打印，并delete存档，失败不可反悔！
 */
	private void lose() {
		System.out.println("很遗憾你输掉了这一局游戏，棋盘已经被填满了。");
		System.out.println("感谢试玩这个简陋的2048小游戏。");
		delete();
	}
/*
 * 检测棋盘，只要有2048就赢了。
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
 * 胜利时打印，并delete存档，胜利无需重来！
 */
	private void win() {
		System.out.println("恭喜您获得了胜利");
		System.out.println("您已经完成了2048的挑战");
		System.out.println("很遗憾，游戏到此结束");
		System.out.println("本版本我们不提供更高分的挑战");
		System.out.println("如果你希望继续游戏可以根据提示进行，谢谢。");
		delete();

	}
/*
 * 对是否可移动的判断。检查数组是否有零值，和是否有可合并值。
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
 * 删除文件的方法，存档和撤回的文件都会被删除。
 * 只会在win或lose后调用。
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
