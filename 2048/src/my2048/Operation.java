package my2048;
/**
 * 玩家的操作
 */
public class Operation {
	
	private int[][] aNumber;
	private boolean isMove;
	private int point;
	private int maxPoint;

	public Operation(int[][] num) {
		aNumber = num;
		isMove = false;
		point = 0;
	}
/**
 * 各方向的移动逻辑是相同的。
 * 以向右为例。
 * 首先取右方方块向左判定，当可合并时，目标方块乘2，左方方块归零，左方方块变为目标方块； 
 * 不可合并但可移动，则目标方块获得左方方块的值，左方方块归零，再从目标方块判定；
 * 不可合并不可移动，左方方块成为目标方块。
 */
	private void right(int[][] number) {

		for (int a = 0; a < 4; a++) {
			for (int b = 3; b > -1; b--) {
				for (int b1 = b - 1; b1 > -1; b1--) {
					if (number[a][b1] > 0) {
						if (number[a][b] <= 0) {
							number[a][b] = number[a][b1];
							number[a][b1] = 0;
							b++;
							isMove = true;
						} else if (number[a][b1] == number[a][b]) {
							number[a][b] = number[a][b] * 2;
							number[a][b1] = 0;
							isMove = true;
							point = number[a][b];
						}
						break;
					}
				}
			}
		}

	}

	private void left(int[][] number) {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				for (int b1 = b + 1; b1 < 4; b1++) {
					if (number[a][b1] > 0) {
						if (number[a][b] <= 0) {
							number[a][b] = number[a][b1];
							number[a][b1] = 0;
							b--;
							isMove = true;
						} else if (number[a][b1] == number[a][b]) {
							number[a][b] = number[a][b] * 2;
							number[a][b1] = 0;
							isMove = true;
							point = number[a][b];
						}
						break;
					}
				}
			}
		}
	}

	private void down(int[][] number) {
		for (int b = 0; b < 4; b++) {
			for (int a = 3; a > -1; a--) {
				for (int a1 = a - 1; a1 > -1; a1--) {
					if (number[a1][b] > 0) {
						if (number[a][b] <= 0) {
							number[a][b] = number[a1][b];
							number[a1][b] = 0;
							a++;
							isMove = true;
						} else if (number[a][b] == number[a1][b]) {
							number[a][b] = number[a][b] * 2;
							number[a1][b] = 0;
							isMove = true;
							point = number[a][b];
						}
						break;
					}
				}
			}
		}
	}

	private void up(int[][] number) {
		for (int b = 0; b < 4; b++) {
			for (int a = 0; a < 4; a++) {
				for (int a1 = a + 1; a1 < 4; a1++) {
					if (number[a1][b] > 0) {
						if (number[a][b] <= 0) {
							number[a][b] = number[a1][b];
							number[a1][b] = 0;
							a--;
							isMove = true;
						} else if (number[a][b] == number[a1][b]) {
							number[a][b] = number[a][b] * 2;
							number[a1][b] = 0;
							isMove = true;
							point = number[a][b];
						}
						break;
					}
				}
			}
		}
	}

	public void keyPressed(String e) throws Exception {
		switch (e) {
		case "a":
			left(aNumber);
			break;
		case "w":
			up(aNumber);
			break;
		case "s":
			down(aNumber);
			break;
		case "d":
			right(aNumber);
			break;
		case "q":
			back();
		}

	}
/*
 * 获取txt文本中的棋盘数据，根据存储方式，将字符串还原到相应数据中去。
 */
	private void back() throws Exception {
		int j = 0, k = 0;
		String a = new TxtOperation().readTxtFile("D:\\2048\\s2048.txt");
		String[] a1 = a.split(" ");
		for (int i = 0; i < 18; i++) {
			if (i < 16) {
				aNumber[j][k] = Integer.parseInt(a1[i]);
				k++;
				if (k == 4) {
					k = 0;
					j++;
				}
			} else if (i == 17) {
				maxPoint = Integer.parseInt(a1[i]);
			} else {
				point = Integer.parseInt(a1[i]);
			}
		}

	}

	public boolean getisMove() {
		return isMove;
	}

	public int[][] getNumber() {
		return aNumber;
	}

	public int getPoint() {
		return point;
	}

	public int getMaxPoint() {
		return maxPoint;
	}

}
