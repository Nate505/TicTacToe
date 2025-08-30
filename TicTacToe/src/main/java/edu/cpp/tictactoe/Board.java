package edu.cpp.tictactoe;

import java.util.Arrays;

public class Board {
    public void printBoard(String[] symbols){
        for(int row = 0; row < 3; row++){
            System.out.println("|---|---|---|");
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                System.out.print("| " + symbols[index] + " ");
            }
            System.out.println("|");
        }
        System.out.println("|---|---|---|");
    }

    public String winner(String[] symbols){
        for(int i = 0; i < 8; i++){
            String line = null;

            switch (i) {
                case 0:
                    line = symbols[0] + symbols[1] + symbols[2];
                    break;
                case 1:
                    line = symbols[3] + symbols[4] + symbols[5];
                    break;
                case 2:
                    line = symbols[6] + symbols[7] + symbols[8];
                    break;
                case 3:
                    line = symbols[0] + symbols[3] + symbols[6];
                    break;
                case 4:
                    line = symbols[1] + symbols[4] + symbols[7];
                    break;
                case 5:
                    line = symbols[2] + symbols[5] + symbols[8];
                    break;
                case 6:
                    line = symbols[0] + symbols[4] + symbols[8];
                    break;
                case 7:
                    line = symbols[2] + symbols[4] + symbols[6];
                    break;
            }

            if(line.equals("XXX")){
                return "X";
            } else if(line.equals("OOO")){
                return "O";
            }
        }

        for (int i = 0; i < 9; i++) {
            if (Arrays.asList(symbols).contains(String.valueOf(i + 1))) {
                return "No Winner Yet";
            }
        }

        return "Draw";
    }

    public boolean getCell(int place, String[] symbols){
       if((symbols[place - 1].equals("X")) || (symbols[place - 1].equals("O"))) return true;
       else return false;
    }

    public void place(int place, Mark mark, String[] symbols){
        symbols[place - 1] = mark.name();
    }

    public void reset(String[] symbols){
        for(int i = 0; i < symbols.length; i++){
            symbols[i] = String.valueOf(i + 1);
        }
    }
}

