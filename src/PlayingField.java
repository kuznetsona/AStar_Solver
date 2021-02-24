import java.util.ArrayList;

class PlayingField {
    private int[][] field;
    private int xo;
    private int yo;
    private int heuristicEvaluation;

    PlayingField(int[][] cells) {
        this.field = cells;
        heuristicEvaluation = 0;
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] != (i * getLength() + j + 1) && cells[i][j] != 0)
                    heuristicEvaluation += 1;
                if (cells[i][j] == 0) {
                    xo = i;
                    yo = j;
                }
            }
    }

    private PlayingField change2Cells(int[][] cells, int x1, int y1, int x2, int y2) {
        if ((x2 > -1) && (x2 < getLength()) && (y2 > -1) && (y2 < getLength())) {
            int var = cells[x2][y2];
            cells[x2][y2] = cells[x1][y1];
            cells[x1][y1] = var;
            return new PlayingField(cells);
        } else return null;
    }

    Iterable<PlayingField> swapAdjacentPositions() {
        ArrayList<PlayingField> fieldList = new ArrayList<>();
        fieldList.add(change2Cells(getNewField(), xo, yo, xo, yo + 1));
        fieldList.add(change2Cells(getNewField(), xo, yo, xo, yo - 1));
        fieldList.add(change2Cells(getNewField(), xo, yo, xo - 1, yo));
        fieldList.add(change2Cells(getNewField(), xo, yo, xo + 1, yo));
        return fieldList;
    }

    boolean isEquals(PlayingField field) {
        for (int i = 0; i < this.field.length; i++)
            for (int j = 0; j < this.field[i].length; j++)
                if (this.field[i][j] != field.field[i][j]) return false;
        return true;
    }

    boolean isGoal() {
        return heuristicEvaluation == 0;
    }

    private static int[][] getFieldCopy(int[][] field) {
        if (field == null)
            return null;
        else {
            int[][] result = new int[field.length][];
            for (int i = 0; i < field.length; i++) {
                result[i] = new int[field[i].length];
                System.arraycopy(field[i], 0, result[i], 0, field[i].length);
            }
            return result;
        }
    }

    boolean isSolvable() {
        int n = field.length;
        int m = field[0].length;
        int s = 0;
        int zero = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (field[i][j] != 0) {
                    for (int k = i; k < n; k++)
                        for (int l = j; l < m; l++)
                            if ((field[i][j] > field[k][l]) && (field[k][l] != 0))
                                s++;
                }
                else
                    zero = i + 1;
        s = s + zero;

        return (s % 2 == 0) || (s == zero);
    }

    int getHeuristicEvaluation() {
        return heuristicEvaluation;
    }

    private int getLength() {
        return field.length;
    }

    private int[][] getNewField() {
        return getFieldCopy(field);
    }

    String getString() {
        StringBuilder s = new StringBuilder();
        for (int[] cell : field) {
            for (int j = 0; j < field.length; j++) {
                if (cell[j] == 0)
                    s.append("|  ");
                else
                    s.append(String.format("|%2d", cell[j]));
            }
            s.append("|\n");
        }
        return s.toString();
    }
}