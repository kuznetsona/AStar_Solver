import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class Test_Solver {

    public PlayingField OutputSolution(PlayingField playingField){
        if (playingField.isSolvable()) {
            System.out.println("Задача решается");
            AStarAlgorithm aStarSolver = new AStarAlgorithm(playingField);
            for (PlayingField field : aStarSolver.getSolution()) {
                System.out.println(field.getString());
            }
        } else System.out.println("Задача не может быть решена");
        return playingField;
    }

    @Test
    public void isSolverTest(){
        int[][] cells = new int[][]{{1, 2, 3, 0}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 4}};
        PlayingField playingField = new PlayingField(cells);
        assertTrue(OutputSolution(playingField).isSolvable());
    }

    @Test
    public void isSolverTest2(){
        int[][] cells = new int[][]{{15, 5, 12, 1}, {10, 7, 6, 11}, {14, 8, 3, 0}, {9, 13, 4, 2}};
        PlayingField playingField = new PlayingField(cells);
        assertFalse(OutputSolution(playingField).isSolvable());
    }

    @Test
    public void isSolverTest3(){
        int[][] cells = new int[][]{{0, 1, 2, 3}, {6, 7, 8, 4}, {5, 9, 10, 11}, {13, 14, 15, 12}};
        PlayingField playingField = new PlayingField(cells);
        assertTrue(OutputSolution(playingField).isSolvable());
    }

    @Test
    public void isSolverTest4(){
        int[][] cells = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        PlayingField playingField = new PlayingField(cells);
        assertTrue(OutputSolution(playingField).isSolvable());
    }



}

