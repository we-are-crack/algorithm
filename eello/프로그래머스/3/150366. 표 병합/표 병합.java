import java.util.*;

class Solution {
    
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        
        Table table = new Table();
        for (String command : commands) {
            String[] cmd = command.split(" ");
            if ("UPDATE".equals(cmd[0])) {
                if (cmd.length == 4) {
                    // UPDATE(r, c, value)
                    int r = Integer.parseInt(cmd[1]);
                    int c = Integer.parseInt(cmd[2]);
                    String value = cmd[3];

                    table.update(r, c, value);
                } else {
                    // UPDATE(value1, value2)
                    table.update(cmd[1], cmd[2]);
                }
            } else if ("MERGE".equals(cmd[0])) {
                int r1 = Integer.parseInt(cmd[1]);
                int c1 = Integer.parseInt(cmd[2]);
                int r2 = Integer.parseInt(cmd[3]);
                int c2 = Integer.parseInt(cmd[4]);

                table.merge(r1, c1, r2, c2);
            } else if ("UNMERGE".equals(cmd[0])) {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);

                table.unmerge(r, c);
            } else { // PRINT
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);

                answer.add(table.print(r, c));
            }
        }
        
        return answer.toArray(new String[0]);
    }
    
    private static class Table {
        
        private static final int SIZE = 50;
        
        private Cell[][] table;
        
        public Table() {
            table = new Cell[SIZE + 1][SIZE + 1];
            for (int r = 1; r <= SIZE; r++) {
                for (int c = 1; c <= SIZE; c++) {
                    table[r][c] = new Cell();
                }
            }
        }
        
        public void update(int r, int c, String value) {
            table[r][c].setValue(value);
        }
        
        public void update(String oldValue, String newValue) {
            for (int r = 1; r <= SIZE; r++) {
                for (int c = 1; c <= SIZE; c++) {
                    if (oldValue.equals(table[r][c].getValue())) {
                        update(r, c, newValue);
                    }
                }
            }
        }
        
        public void merge(int r1, int c1, int r2, int c2) {
            Cell cell1 = table[r1][c1].getParent();
            Cell cell2 = table[r2][c2].getParent();
            
            if (cell1 == cell2) {
                return;
            }
            
            if (cell1.getValue() != null) {
                // cell1만 값을 가지고 있거나 두 셀 모두 값을 가지고 있는 경우 cell2 -> cell1
                cell2.setValue(null);
                cell2.setParent(cell1);
                cell1.addChild(cell2);
            } else {
                // cell2만 값을 가지고 있거나 두 셀 모두 값을 가지고 있지 않은 경우 cell1 -> cell2
                cell1.setValue(null);
                cell1.setParent(cell2);
                cell2.addChild(cell1);
            }
        }
        
        public void unmerge(int r, int c) {
            Cell parent = table[r][c].getParent();
            String value = parent.getValue();
            parent.setValue(null);
            
            unmerge(parent);
            table[r][c].setValue(value);
        }
        
        private void unmerge(Cell cell) {
            cell.setParent(null);
            for (Cell child : cell.getChild()) {
                unmerge(child);
            }
            
            cell.getChild().clear();
        }
        
        public String print(int r, int c) {
            String value = table[r][c].getValue();
            return value == null ? "EMPTY" : value;
        }
    }
    
    private static class Cell {
        
        private String value;
        private Cell parent;
        private List<Cell> child = new ArrayList<>();
        
        public String getValue() {
            return getParent().value;
        }
        
        public void setValue(String value) {
            getParent().value = value;
        }
        
        public Cell getParent() {
            if (parent == null) {
                return this;
            }
            
            return parent.getParent();
        }
        
        public void setParent(Cell parent) {
            this.parent = parent;
        }
        
        public List<Cell> getChild() {
            return child;
        }
        
        public void addChild(Cell child) {
            this.child.add(child);
        }
    }
}