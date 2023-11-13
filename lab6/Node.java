package lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		 generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
	    Random random = new Random();
	    state = new Queen[N]; // Thêm dòng này để khởi tạo state
	    for (int i = 0; i < N; i++) {
	        state[i] = new Queen(random.nextInt(N), i);
	    }
	}


	public int getH() {
	    int heuristic = 0;
	    for (int i = 0; i < state.length; i++) {
	        if (state[i] != null) { // Kiểm tra không rỗng trước khi sử dụng
	            for (int j = i + 1; j < state.length; j++) {
	                if (state[j] != null && state[i].isConflict(state[j])) {
	                    heuristic++;
	                }
	            }
	        }
	    }
	    return heuristic;
	}



	public List<Node> generateAllCandidates() {
	    List<Node> result = new ArrayList<Node>();
	    if (state != null) { // Kiểm tra không rỗng trước khi sử dụng
	        for (int currentColumn = 0; currentColumn < state.length; currentColumn++) {
	            for (int newRow = 0; newRow < state.length; newRow++) {
	                Node candidates = new Node();
	                if (candidates.state[currentColumn] != null) {
	                    candidates.state[currentColumn].move();
	                }
	                result.add(candidates);
	            }
	        }
	    }
	    return result;
	}

	// lay node ngau nhien
	public Node selectNextRandomCandidate(List<Node> candidates) {
	    if (candidates.isEmpty()) {
	        return null; 
	    }
	    Random random = new Random();
	    int randomIndex = random.nextInt(candidates.size());
	    return candidates.get(randomIndex);
	}
	
	public void displayBoard() {
		int[][] board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
	
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}
}
