package my2048;

public class Background {
	// ���ڱ�����ʾ�ͳ�ʼ������
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
		System.out.println("��ǰ�÷�Ϊ��" + point + "                                            ��ǰ��߷�Ϊ��" + maxPoint);
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
		System.out.println("�� �� Ϸ �� w a s d  �� �ƣ��� �� �� �� �� �� �� �� �� �� �� һ �� �� Ч �� ����");
		System.out.println("�� �� �� �� q �� �� ������ �� ͬ ʱ �� �� �� �� �� ��,�� �� �� �� �� �� ���� �� ˼��");
		System.out.println("�� �� �� �� **   ** �� �� �� �� �� ʱ �� �� �� �壬�� �� �� Ϊ �� �� �� �� �� �� ����");
	}
}
