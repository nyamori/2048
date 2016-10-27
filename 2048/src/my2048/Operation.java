package my2048;

public class Operation {
	// 玩家的操作相关问题
	private int[][] aNumber;
	private boolean isMove;
	private int point;
	private int maxPoint;

	public Operation(int[][] num) {
		aNumber = num;
		isMove = false;
		point = 0;
	}

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

	private void back() throws Exception {
		int j = 0, k = 0;
		String a = new TxtOperation().readTxtFile("D:\\s2048.txt");
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
