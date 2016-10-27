package my2048;

public class Background {
	// 关于背景显示和初始化数组
	private int[][] aNum;
	private int point, maxPoint;

	public Background(int[][] n, int p, int mp) {
		aNum = n;
		point = p;
		maxPoint = mp;
	}

	public void background(int a1, int b1) {
		int k, l, a = 0, b = 0;
		for(int i=0;i<=10;i++){
			System.out.println();
			}
		System.out.println("当前得分为：" + point + "                                            当前最高分为：" + maxPoint);
		System.out.println("---------------------------------------------------------------------");
		for (int i = 0; i < 4; i++) {
			l = 0;
			for (k = 0; k < 5; k++) {
				for (int j = 0; j < 5; j++) {
					System.out.print("|");
					if (l == 2) {
						if (j < 4) {
							if (aNum[a][b] != 0) {
								String num = String.format("%04d", aNum[a][b]);
								if (b == b1 && a == a1) {
									System.out.print("    **" + num + "**    ");
								} else {
									System.out.print("      " + num + "      ");
								}
							} else
								System.out.print("                ");
							b++;
						}
					} else
						System.out.print("                ");

				}
				System.out.println("");
				l++;
			}
			System.out.println("---------------------------------------------------------------------");
			a++;
			b = 0;
		}
		System.out.println(" ");
		System.out.println("本 游 戏 由 w a s d  控 制，输 入 方 向 后 回 车 才 可 完 成 一 次 有 效 操 作。");
		System.out.println("悔 棋 请 按 q 并 回 车，悔 棋 同 时 最 高 分 会 返 回,不 能 悔 棋 两 步 ，请 三 思。");
		System.out.println("棋 盘 上 有 **   ** 标 记 的 数 字 时 可 以 悔 棋，该 数 字 为 新 生 成 的 随 机 数。");
	}
}
