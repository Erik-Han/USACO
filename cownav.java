import java.io.*;
import java.util.*;

public class cownav {
	private static final String DIRECTORY = "C:\\Users\\Erik Han\\Google Drive\\eclipse\\Contests\\src\\",
			TASK = "cownav";
	private static BufferedReader reader;
	private static PrintWriter out;
	private static int N;
	private static int[][] grid;
	private static int[][][][][][] min;
	private static String[][][][][][] minS;
	private static LinkedList<int[]> queue = new LinkedList<int[]>();
	private static LinkedList<String> q2 = new LinkedList<String>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			reader = new BufferedReader(new FileReader(// DIRECTORY +
					TASK + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(// DIRECTORY
																	// +
					TASK + ".out")));
		} catch (IOException e) {
			reader = new BufferedReader(new FileReader(DIRECTORY + TASK + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTORY + TASK + ".out")));
		}

		// System.out.println(ct.length);
		N = Integer.parseInt(reader.readLine());
		min = new int[N][N][4][N][N][4];
		minS = new String[N][N][4][N][N][4];
		for (int a = 0; a < N; a++) {
			for (int b = 0; b < N; b++) {
				for (int c = 0; c < N; c++) {
					for (int d = 0; d < N; d++) {
						for (int x = 0; x < 4; x++) {
							for (int y = 0; y < 4; y++) {
								min[a][b][x][c][d][y] = Integer.MAX_VALUE;
							}
						}
					}
				}
			}
		}
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			String row = reader.readLine();
			for (int a = 0; a < row.length(); a++) {
				switch (row.charAt(a)) {
				case ('E'):
					grid[N - i - 1][a] = 0;
					break;
				case ('H'):
					grid[N - i - 1][a] = 1;
					break;
				}
			}
		}
		queue.add(new int[] { 0, 0, 0, 0, 0, 1, 0 });
		q2.add("");
		while (!queue.isEmpty()) {
			int[] curr = queue.remove();
			// System.out.println(curr[0] + " " + curr[1]);
			if (curr[0] == N - 1 && curr[1] == N - 1) {
				// System.out.println("yep");
			}
			doSqu(curr[0], curr[1], curr[2], curr[3], curr[4], curr[5], curr[6], q2.pop());
		}
		int mn = Integer.MAX_VALUE;
		String s="oops";
		for (int i = 0; i < 4; i++) {
			for (int i2 = 0; i2 < 4; i2++) {
				if (mn > min[N - 1][N - 1][i][N - 1][N - 1][i2]) {
					mn = min[N - 1][N - 1][i][N - 1][N - 1][i2];
					s = minS[N - 1][N - 1][i][N - 1][N - 1][i2];
				}
			}
		}
		//System.out.println(mn);
		//System.out.println(s);
		out.println(mn);
		out.close();
	}

	public static void doSqu(int r, int c, int dir, int r2, int c2, int dir2, int ct, String s) {
		if (ct < min[r][c][dir][r2][c2][dir2]) {
			/*
			 * if (r==N-1&&c==N-1&&r2==N-1&&c2==N-1){ System.out.println("yay");
			 * }
			 */
			min[r][c][dir][r2][c2][dir2] = ct;
			minS[r][c][dir][r2][c2][dir2] = s;
			if (r == N - 1 && c == N - 1 && r2 == N - 1 && c2 == N - 1) {
				return;
			}
			int[] next1 = squDir(r, c, dir);
			int[] next2 = squDir(r2, c2, dir2);
			if (!(next1 == null && next2 == null)) {
				if (next1 == null||(r==N-1&&c==N-1)) {
					next1 = new int[] { r, c };
				}
				if (next2 == null||(r2==N-1&&c2==N-1)) {
					next2 = new int[] { r2, c2 };
				}
				queue.add(new int[] { next1[0], next1[1], dir, next2[0], next2[1], dir2, ct + 1 });
				q2.add(s + "F");
			}
			queue.add(new int[] { r, c, (dir + 1) % 4, r2, c2, (dir2 + 1) % 4, ct + 1 });
			q2.add(s + "R");
			queue.add(new int[] { r, c, (dir + 3) % 4, r2, c2, (dir2 + 3) % 4, ct + 1 });
			q2.add(s + "L");
		}
	}

	public static int[] squDir(int r, int c, int dir) {
		switch (dir) {
		case 0:
			r++;
			break;
		case 1:
			c++;
			break;
		case 2:
			r--;
			break;
		case 3:
			c--;
			break;
		}
		if (r >= 0 && r < N && c >= 0 && c < N && grid[r][c] == 0) {
			return new int[] { r, c };
		}
		return null;
	}
}