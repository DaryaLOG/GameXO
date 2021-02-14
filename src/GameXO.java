import java.util.Random;
import java.util.Scanner;

public class GameXO {
    public static char [][] gameMap = new char[3][3];
    public static char gamerSimbol = 'X';
    public static char aiSimbol = 'O';
    public static char emptySimbol= '_';
    public static int mapSize = 3;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static int cellCounter=0;


    public static void createMap(){
        for (int x = 0; x < mapSize; x++){
            for (int y = 0; y < mapSize; y++){
                gameMap[x][y] = emptySimbol;
            }
        }
    }
    public static void showMap(){
        for (int x = 0; x < mapSize; x++){
            for (int y = 0; y < mapSize; y++){
                System.out.print(gameMap[x][y]+ "|");
            }
            System.out.println();
        }
        System.out.println("_______________________");
    }
    public static void gamerTurn(){
        int x;
        int y;
        while (true){
            System.out.println("Введите координаты:");
            x = scanner.nextInt()-1;
            y = scanner.nextInt()-1;

            if (pageCheck(x,y)==true && checkEmpty(x,y)==true)
                break;
        }

        gameMap[x][y]=gamerSimbol;
        cellCounter++;

    }
    public static boolean pageCheck(int x, int y){
        if (x >=0 && x <= mapSize && y >=0 && y <= mapSize) {
            return true;
        }
        return false;
    }
    public static boolean checkEmpty(int x,int y) {
        if (gameMap[x][y]==emptySimbol) {
            return true;
        }
        return false;
    }
    public static void aiTurn () {

        for (int x = 0; x < mapSize; x++){
            int yCounter = 0;
            int yEmpty = -1;
            for (int y = 0; y < mapSize; y++){
               if (gameMap[x][y] ==gamerSimbol) {
                   yCounter++;
               }
               else if(gameMap[x][y] == emptySimbol)
                   yEmpty = y;
            }
            if (yCounter == 2 && yEmpty !=-1) {
                gameMap[x][yEmpty] = aiSimbol;
                cellCounter++;
                return;
            }
        }

        for (int y = 0; y < mapSize; y++){
            int xCounter = 0;
            int xEmpty = -1;
            for (int x = 0; x < mapSize; x++){
                if (gameMap[x][y] ==gamerSimbol) {
                    xCounter++;
                }
                else if(gameMap[x][y] == emptySimbol)
                    xEmpty = x;
            }
            if (xCounter == 2 && xEmpty !=-1) {
                gameMap[xEmpty][y] = aiSimbol;
                cellCounter++;
                return;
            }
        }

        int xCounter = 0;
        int xEmpty = -1;
        int xCounter2 = 0;
        int xEmpty2 = -1;
        for (int x = 0; x < mapSize; x++){
            if (gameMap[x][x] ==gamerSimbol) {
                xCounter++;
            }
            else if(gameMap[x][x] == emptySimbol) {
                xEmpty = x;
            }
            if (gameMap[x][mapSize - x - 1] ==gamerSimbol) {
                xCounter2++;
            }
            else if(gameMap[x][mapSize - x - 1] == emptySimbol)
                xEmpty2 = x;
        }
        if (xCounter == 2 && xEmpty !=-1) {
            gameMap[xEmpty][xEmpty] = aiSimbol;
            cellCounter++;
            return;
        }
        if (xCounter2 == 2 && xEmpty2 !=-1) {
            gameMap[xEmpty2][mapSize - xEmpty2 -1] = aiSimbol;
            cellCounter++;
            return;
        }

        while (true){
            int x = random.nextInt(mapSize);
            int y = random.nextInt(mapSize);
            if (checkEmpty(x,y) == true){
                gameMap[x][y] = aiSimbol;
                break;
            }
        }
        cellCounter++;
    }

    public static void main(String[] args){
        createMap();
        showMap();

        do {
            gamerTurn();
            showMap();
            if (conditionWin()){
                break;
            }
            if (cellCounter == 9){
                System.out.println("Никто не выиграл");
                break;
            }
            aiTurn();
            showMap();
            if (conditionWin()){
                break;
            }
            if (cellCounter == 9){
                System.out.println("Никто не выиграл");
                break;
            }
        }while (true);

    }
    public static boolean conditionWin() {
        for (int x = 0; x < mapSize; x++) {
            int gamerCounter =0;
            int aiCounter =0;
            for (int y = 0; y < mapSize; y++) {
                if (gameMap[x][y]==gamerSimbol){
                    gamerCounter++;
                }
                else if (gameMap[x][y]==gamerSimbol){
                    aiCounter++;
                }
            }
            if (gamerCounter==3){
                System.out.println("Игрок выиграл");
                return true;
            }
            if (aiCounter==3){
                System.out.println("Компьютер выиграл");
                return true;
            }
        }
        for (int y = 0; y < mapSize; y++) {
            int gamerCounter =0;
            int aiCounter =0;
            for (int x = 0; x < mapSize; x++) {
                if (gameMap[x][y]==gamerSimbol){
                    gamerCounter++;
                }
                else if (gameMap[x][y]==gamerSimbol){
                    aiCounter++;
                }
            }
            if (gamerCounter==3){
                System.out.println("Игрок выиграл");
                return true;
            }
            if (aiCounter==3){
                System.out.println("Компьютер выиграл");
                return true;
            }
        }

        int y=0;
        int y2=2;
        int gamerCounter = 0;
        int aiCounter = 0;
        int gamerCounter2 = 0;
        int aiCounter2 = 0;
        for (int x = 0; x < mapSize; x++, y++, y2--) {
            if (gameMap[x][y]==gamerSimbol){
                gamerCounter++;
            }
            else if (gameMap[x][y]==aiSimbol) {
                aiCounter++;
            }
            if (gameMap[x][y2]==gamerSimbol){
                gamerCounter2++;
            }
            else if (gameMap[x][y2]==aiSimbol) {
                aiCounter2++;
            }
            if (gamerCounter==3 || gamerCounter2==3){
                System.out.println("Игрок выиграл");
                return true;
            }
            if (aiCounter==3 || aiCounter2==3){
                System.out.println("Компьютер выиграл");
                return true;
            }
        }

        return false;
    }
}
