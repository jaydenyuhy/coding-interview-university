package dynamicprogramming;

public class ABCTest {

    public static void main(String[] args) {

        char[][] boards = new char[][]{new char[]{'a', 'b', 'c', 'e'},
                new char[]{'s', 'f', 'c', 's'},
                new char[]{'a', 'd', 'e', 'e'}};
        String word = "abcesb";

        System.out.println("exist : " + new ABCTest().exist(boards, word));
    }

    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(recur(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean recur(char[][] board, String word, int i, int j, int offset) {
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
            return false;
        }
        if(board[i][j] == '0' || board[i][j] != word.charAt(offset)){
            return false;
        }
        if(offset == word.length() - 1){
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '0';
        boolean result = recur(board, word, i - 1, j, offset + 1) ||
                recur(board, word, i + 1, j, offset + 1) ||
                recur(board, word, i, j - 1, offset + 1) ||
                recur(board, word, i, j + 1, offset + 1);

        board[i][j] = temp;
        return result;
    }
}
