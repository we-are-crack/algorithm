import java.util.*;

class Solution {
    
    private Row[] rows;
    private Stack<Row> revert;
    
    public String solution(int n, int k, String[] cmd) {
        revert = new Stack<>();
        
        rows = new Row[n];
        rows[0] = new Row();
        for (int i = 1; i < n; i++) {
            rows[i] = new Row();
            rows[i - 1].setBack(rows[i]);
            rows[i].setFront(rows[i - 1]);
        }
        
        Row cursor = rows[k];
        for (String command : cmd) {
            String[] split = command.split(" ");
            
            switch(split[0]) {
                case "U":
                    cursor = executeU(cursor, Integer.parseInt(split[1]));
                    break;
                case "D":
                    cursor = executeD(cursor, Integer.parseInt(split[1]));
                    break;
                case "C":
                    cursor = executeC(cursor);
                    break;
                case "Z":
                    executeZ();
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (Row row : rows) {
            sb.append(row.isDeleted ? "X" : "O");
        }
        return sb.toString();
    }
    
    private Row executeU(Row cursor, int count) {
        return cursor.getFront(count);
    }
    
    private Row executeD(Row cursor, int count) {
        return cursor.getBack(count);
    }
    
    private Row executeC(Row cursor) {
        revert.push(cursor);
        cursor.delete();
        
        Row back = cursor.getBack();
        return back != null ? back : cursor.getFront();
    }
    
    private void executeZ() {
        revert.pop().recovery();
    }
    
    private static class Row {
        private boolean isDeleted;
        private Row front;
        private Row back;
        
        public void delete() {
            isDeleted = true;
            if (front != null) front.setBack(back);
            if (back != null) back.setFront(front);
        }
        
        public void recovery() {
            isDeleted = false;
            
            setFront(getFront());
            if (front != null) front.setBack(this);
            
            setBack(getBack());
            if (back != null) back.setFront(this);
        }
        
        public void setFront(Row front) {
            this.front = front;
        }
        
        public void setBack(Row back) {
            this.back = back;
        }
        
        // 앞에 있는 행 중 삭제되지 않은 행 리턴
        public Row getFront() {
            if (front == null) {
                return null;
            }
            
            if (front.isDeleted) {
                return front.getFront();
            } else return front;
        }
        
        // count만큼 앞에 있는 행 리턴
        public Row getFront(int count) {
            return count == 0 ? this : front.getFront(count - 1);
        }
        
        // 뒤에 있는 행 중 삭제되지 않은 행 리턴
        public Row getBack() {
            if (back == null) {
                return null;
            }
            
            if (back.isDeleted) {
                return back.getBack();
            } else return back;
        }
        
        // count만큼 뒤에 있는 행 리턴
        public Row getBack(int count) {
            return count == 0 ? this : back.getBack(count - 1);
        }
    }
}