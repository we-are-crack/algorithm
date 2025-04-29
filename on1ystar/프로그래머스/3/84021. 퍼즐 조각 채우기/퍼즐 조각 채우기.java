import java.util.*;

class Solution {

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        List<List<Piece>> emptyPieces = find(game_board, 0);
        List<List<Piece>> puzzlePieces = find(table, 1);

        for (int i = 1; i < 7; i++) {
            if (puzzlePieces.get(i).isEmpty()) {
                continue;
            }

            for (Piece puzzlePiece : puzzlePieces.get(i)) {
                for (int j = 0; j < 4; j++) {
                    for (Piece emptyPiece : emptyPieces.get(i)) {
                        if (!emptyPiece.matched && emptyPiece.match(puzzlePiece)) {
                            emptyPiece.matched = true;
                            puzzlePiece.matched = true;
                            answer += i;
                            break;
                        }
                    }

                    if (puzzlePiece.matched) {
                        break;
                    }

                    puzzlePiece.rotate();
                }
            }
        }

        return answer;
    }

    /**
     * @param board       = game_board || table
     * @param targetState = = 0(empty piece) || 1(puzzle piece)
     */
    public List<List<Piece>> find(int[][] board, int targetState) {
        List<List<Piece>> pieces = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pieces.add(new ArrayList<>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == targetState) {
                    board[i][j] += 1;
                    Piece piece = findPiece(board, new Block(i, j), targetState);
                    pieces.get(piece.size()).add(piece);
                }
            }
        }

        return pieces;
    }

    public Piece findPiece(int[][] board, Block start, int targetState) {
        Piece piece = new Piece();
        Deque<Block> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            Block block = q.removeFirst();
            piece.add(block);

            for (int i = 0; i < 4; i++) {
                int nextR = block.r + dr[i];
                int nextC = block.c + dc[i];
                if (valid(nextR, nextC, board.length) && board[nextR][nextC] == targetState) {
                    board[nextR][nextC] += 1;
                    q.addLast(new Block(nextR, nextC));
                }
            }
        }

        return piece;
    }

    public boolean valid(int r, int c, int n) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    static class Piece {

        List<Block> blocks = new ArrayList<>();
        Block firstBlock;
        boolean matched = false;

        public void add(Block block) {
            if (firstBlock == null) {
                firstBlock = block;
            }

            blocks.add(block);
        }

        public int size() {
            return blocks.size();
        }

        public boolean match(Piece target) {
            int diffR = this.firstBlock.r - target.firstBlock.r;
            int diffC = this.firstBlock.c - target.firstBlock.c;

            for (Block targetBlock : target.blocks) {
                boolean fit = false;

                for (Block block : this.blocks) {
                    if (block.fit(targetBlock.r + diffR, targetBlock.c + diffC)) {
                        fit = true;
                        break;
                    }
                }

                if (!fit) {
                    return false;
                }
            }

            return true;
        }

        public void rotate() {
            firstBlock = null;

            for (Block block : blocks) {
                block.rotate();

                if (firstBlock == null) {
                    firstBlock = block;
                    continue;
                }

                if (block.r < firstBlock.r || (block.r == firstBlock.r && block.c < firstBlock.c)) {
                    firstBlock = block;
                }
            }
        }
    }

    static class Block {

        int r;
        int c;

        Block(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void rotate() {
            int temp = r;
            r = c;
            c = -temp;
        }

        public boolean fit(int r, int c) {
            return this.r == r && this.c == c;
        }
    }
}