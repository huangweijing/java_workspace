/**
 *      A sample code for the first IBM China Research Lab Programming Contest
 *
 *  This code segment implement the interface of  com.ibm.crl.contest.AbstractPlayer.
 *  It gives the most simple action in the Othello game, which:
 *  1.  Print basic informations at the beginning of the game.
 *  2.  For each turn, it will print the current chessboard, pause for 1 seconds, and select a
 *  random position.
 */

package mycode;

import java.awt.Point;
import java.util.Hashtable;
import java.util.Random;

import com.ibm.crl.contest.AbstractPlayer;
import com.ibm.crl.contest.ChessContext;
import java.util.Stack;

public class AdvanceAI extends AbstractPlayer {
    java.util.Random random = new Random();
    
    ChessContext chessContext;
    int chessWidth;
    int chessHeight;
    byte myColor;
    Stack<Point>unvailableStack;
    
    @Override public int init(ChessContext chessContext, byte[][] chessboard,
            int timeLimit, int memoryLimit, boolean color) {
        this.chessContext = chessContext;
        chessWidth=chessContext.getChessboardWidth();
        chessHeight=chessContext.getChessboardHeight();
        myColor=chessContext.getMyColor()?BLACK:WHITE;
        this.getUnvailableStack(chessboard);
        
//        byte[][] test=new byte[8][8];
//        for(int i=0;i<8;i++)
//            for(int j=0;j<8;j++)
//                test[i][j]=0;
//        test[0][0]=WHITE;
//        test[0][5]=NOT_AVAILABLE;
//        test[1][5]=NOT_AVAILABLE;
//        test[0][4]=WHITE;
//        test[0][3]=WHITE;
//        test[1][4]=WHITE;
////        test[0][7]=WHITE;
//
//        this.getUnvailableStack(test);
//
//        this.printChessboard(test,null);
//        System.out.println(this.calculateSteadyCount(WHITE,test));
        
        return 0;
    }
    
    /**
     * Print chessboard
     *
     * @param cb
     * @param availables
     */
    private void printChessboard(byte[][] cb, Point[] availables) {
        java.util.Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        if (availables != null) {
            for (int i = 0; i < availables.length; i++) {
                Point point = availables[i];
                hashtable.put(point.x + "_" + point.y, i);
            }
        }
        for (int y = 0; y < chessContext.getChessboardHeight(); y++) {
            for (int x = 0; x < chessContext.getChessboardWidth(); x++) {
                byte b = cb[x][y];
                String s;
                if (hashtable.containsKey(x + "_" + y)) {
                    s = String.format("%-2d", hashtable.get(x + "_" + y));
                } else {
                    switch (b) {
                        case AVAILABLE:
                            s = ". ";
                            break;
                        case BLACK:
                            s = "b ";
                            break;
                        case WHITE:
                            s = "w ";
                            break;
                        default:
                            s = "X ";
                    }
                }
                System.out.print(s);
            }
            System.out.println();
        }
    }
    
    @Override public int myTurn(byte[][] chessboard, Point[] availables, int curStep,
            Point lastMove) {
        this.printChessboard(chessboard,null);
        System.out.println("I ve got "+this.calculateSteadyCount(myColor,chessboard)+
                " Steady stone!");
        int n=0;
        int maxValue=Integer.MIN_VALUE;
        for(int i=0;i<availables.length;i++) {
            int value=0;
            
            byte[][] tempboard=this.copyArr(chessboard);
            this.eatInBoard(availables[i],myColor,tempboard);
            if(chessWidth*chessHeight-curStep>=15){
                if(this.posInDanger(availables[i])){
                    //System.out.println(availables[i].x+","+availables[i].y+" is in danger!");
                    continue;
                }
                value=-this.predict(tempboard,(byte)(3-myColor),Integer.MIN_VALUE,Integer.MAX_VALUE,3);

            } else
                value=-this.predictByCount(tempboard,(byte)(3-myColor),Integer.MIN_VALUE,Integer.MAX_VALUE,15);
            //System.out.println(availables[i].x+","+availables[i].y+":"+value);
            if(value>maxValue){
                maxValue=value;
                n=i;
            }
        }
        try{
            Thread.sleep(0);
        }catch(Exception e){
            
        }
        return n;
    }
    
    private boolean posIsInBoard(Point pos){
        return (pos.x<this.chessWidth && pos.x>=0 &&
                pos.y<this.chessHeight && pos.y>=0);
    }
    
    private int calculateCount(byte player,byte[][] chessBoard){
        int count=0;
        for(int x=0;x<this.chessWidth;x++)
            for(int y=0;y<this.chessHeight;y++) {
            if(chessBoard[x][y]==player)
                count++;
            }
        return count;
    }
    
    private int predictByCount(byte[][] chessboard, byte player, int alpha, int beta, int depth){
        if(depth<=0)
            return this.evaluateBoardByCount(chessboard,player);
        Stack<Point> availablePos=this.getAvailablePosition(chessboard,player);
//        System.out.println("Im thinking..."+depth);
//        this.printChessboard(chessboard,null);
        if(availablePos.size()==0)
            return -predict(chessboard,player==WHITE?BLACK:WHITE,-beta,-alpha,depth-1);
        Point position=availablePos.peek();
        while(!availablePos.isEmpty()){
            int value=0;
            Point tryToEat=availablePos.pop();
            byte[][] tempboard=copyArr(chessboard);
            this.eatInBoard(tryToEat,player,tempboard);
            value=-predict(tempboard,player==WHITE?BLACK:WHITE,-beta,-alpha,depth-1);
            if(value>=alpha){
                position=tryToEat;
                alpha=value;
            }
            if(alpha>=beta)
                break;
        }
        //System.out.println("Im thinking..."+depth);
        //this.printChessboard(chessboard,null);
        //System.out.println("alpha:"+alpha);
        return alpha;
    }
    
    
    private boolean posIsAvailable(byte[][] chessboard, Point pos, byte player){
        if(chessboard[pos.x][pos.y]!=AVAILABLE)
            return false;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++) {
            if(i==0 && j==0)
                continue;
            Point searchPos=(Point)pos.clone();
            boolean first=true;
            boolean currentDirectionCanEat=false;
            searchPos.x+=i;
            searchPos.y+=j;
            while(posIsInBoard(searchPos) &&
                    chessboard[searchPos.x][searchPos.y]!=AVAILABLE &&
                    chessboard[searchPos.x][searchPos.y]!=NOT_AVAILABLE) {
                if(first) {
                    if(chessboard[searchPos.x][searchPos.y]==player)
                        break;
                    // if searchPos is the neighbour of pos
                    // and has the same color then cannot
                    // eat in this direction.
                    first=false;
                } else{
                    if(chessboard[searchPos.x][searchPos.y]==player) {
                        currentDirectionCanEat=true;
                        break;
                    }
                }
                searchPos.x+=i;
                searchPos.y+=j;
            }
            if(currentDirectionCanEat)
                return true;
            }
        
        return false;
    }
    
    private Stack<Point> getAvailablePosition(byte[][] chessboard, byte player){
        Stack<Point>posStack=new Stack<Point>();
        for(int i=0;i<chessWidth;i++)
            for(int j=0;j<chessHeight;j++)
                if(posIsAvailable(chessboard,new Point(i,j),player))
                    posStack.push(new Point(i,j));
        return posStack;
        
    }
    
    private void eatInBoard(Point pos, byte player, byte[][]chessboard){
        chessboard[pos.x][pos.y]=player;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++) {
            if(i==0 && j==0)
                continue;
            Point searchPos=(Point) pos.clone();
            searchPos.x+=i;
            searchPos.y+=j;
            Stack<Point>changeColor=new Stack<Point>();
            while(posIsInBoard(searchPos) &&
                    chessboard[searchPos.x][searchPos.y]!=AVAILABLE &&
                    chessboard[searchPos.x][searchPos.y]!=NOT_AVAILABLE) {
                if(chessboard[searchPos.x][searchPos.y]==player) {
                    while(!changeColor.isEmpty()) {
                        Point toChange=changeColor.pop();
                        chessboard[toChange.x][toChange.y]=player;
                    }
                    break;
                }
                changeColor.push((Point)searchPos.clone());
                searchPos.x+=i;
                searchPos.y+=j;
            }
            }
    }
    
    private int predict(byte[][] chessboard, byte player, int alpha, int beta, int depth){
        if(depth<=0)
            return this.evaluateBoardBySteadyCount(chessboard,player);
        Stack<Point> availablePos=this.getAvailablePosition(chessboard,player);
//        System.out.println("Im thinking..."+depth);
//        this.printChessboard(chessboard,null);
        if(availablePos.size()==0)
            return -predict(chessboard,player==WHITE?BLACK:WHITE,-beta,-alpha,depth-1);
        Point position=availablePos.peek();
        while(!availablePos.isEmpty()){
            int value=0;
            Point tryToEat=availablePos.pop();
            if(this.isGreatPoint(tryToEat)){
                //System.out.println("Great!");
                value+=100;
            }
            byte[][] tempboard=copyArr(chessboard);
            this.eatInBoard(tryToEat,player,tempboard);
            value=-predict(tempboard,player==WHITE?BLACK:WHITE,-beta,-alpha,depth-1);
            if(value>=alpha){
                position=tryToEat;
                alpha=value;
            }
            if(alpha>=beta)
                break;
        }
        //System.out.println("Im thinking..."+depth);
        //this.printChessboard(chessboard,null);
        //System.out.println("alpha:"+alpha);
        return alpha;
    }
    
    private int evaluateBoardByCount(byte[][] chessboard,byte player){
        return (this.calculateCount(player,chessboard)-
                this.calculateCount(player==BLACK?WHITE:BLACK,chessboard));
    }
    
    private int evaluateBoardBySteadyCount(byte[][] chessboard,byte player){
        return (this.calculateSteadyCount(player,chessboard)-
                this.calculateSteadyCount((byte)(3-player),chessboard));
    }
    
    private byte[][] copyArr(byte[][] arr) {
        if(arr==null || arr[0]==null)
            return null;
        byte[][] toCopy=new byte[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++)
            toCopy[i]=(byte[])arr[i].clone();
        return toCopy;
    }
    
    private int calculateSteadyCount(byte player,byte[][]chessboard){
//        if(
//                chessboard[0][0]!=player    &&
//                chessboard[0][chessHeight-1]!=player  &&
//                chessboard[chessWidth-1][0]!=player   &&
//                chessboard[chessWidth-1][chessHeight-1]!=player
//                )
//            return 0;
        int boardWidth=chessboard.length;
        int boardHeight=chessboard[0].length;
        int[][][]weight=new int[boardWidth][boardHeight][8];
        int totalValue=0;
        int totalCount=0;
        //Search from left to right
        for (int y = 0; y < boardHeight; y++)
            for (int add = 0; add < boardWidth; add++)
                if (chessboard[add][y] == player &&
                (!posIsInBoard(new Point(add-1, y)) || weight[add-1][y][0] == 1)) {
            weight[add][y][0] = 1;
            totalValue++;
                } else
                    break;
        //Search from lefttop to rightbuttom
        for(int x=0;x<boardWidth;x++)
            for(int add=0;add<boardHeight && add+x<boardWidth;add++)
                if(chessboard[x+add][add]==player &&
                (!posIsInBoard(new Point(x+add-1,add-1))|| weight[x+add-1][add-1][1]==1)){
            weight[x+add][add][1]=1;
            totalValue++;
                }
        for(int y=1;y<boardHeight;y++)
            for(int add=0;add<boardWidth && add+y<boardHeight;add++)
                if(chessboard[add][add+y]==player &&
                (!posIsInBoard(new Point(add-1,add+y-1))||weight[add-1][add+y-1][1]==1)){
            weight[add][add+y][1]=1;
            //System.out.println(add+","+(add+y));
            totalValue++;
                }
        //Search from top to down
        for(int x=0;x<boardWidth;x++)
            for(int add=0;add<boardHeight;add++)
                if(chessboard[x][add]==player &&
                (!posIsInBoard(new Point(x,add-1))||weight[x][add-1][2]==1)){
            weight[x][add][2]=1;
            totalValue++;
                }
        //Search from righttop to leftbuttom
        for(int x=boardWidth-1;x>=0;x--)
            for(int add=0;add<boardHeight && x-add>=0; add++)
                if(chessboard[x-add][add]==player &&
                (!posIsInBoard(new Point(x-add+1,add-1))||weight[x-add+1][add-1][3]==1)){
            weight[x-add][add][3]=1;
            totalValue++;
                }
        for(int y=1;y<boardHeight;y++)
            for(int add=0;add+y<boardHeight && add<boardWidth;add++)
                if(chessboard[boardWidth-1-add][y+add]==player &&
                (!posIsInBoard(new Point(boardWidth-add/*width-1-add+1*/,y+add-1)) ||
                weight[boardWidth-add][y+add-1][3]==1)){
            weight[boardWidth-add-1][y+add][3]=1;
            totalValue++;
                }
        //Search from right to left
        for(int y=0;y<boardHeight;y++)
            for(int add=0;add<boardWidth;add++)
                if(chessboard[boardWidth-1-add][y]==player &&
                (!posIsInBoard(new Point(boardWidth-add/*boardWidth-1-add+1*/,y)) ||
                weight[boardWidth-add][y][4]==1)){
            weight[boardWidth-1-add][y][4]=1;
            totalValue++;
                }
        //Search from rightdown to lefttop
        for(int y=boardHeight-1;y>=0;y--)
            for(int add=0;y-add>=0 && add<boardWidth;add++)
                if(chessboard[boardWidth-1-add][y-add]==player &&
                (!posIsInBoard(new Point(boardWidth-add,y-add+1))|| weight[boardWidth-add][y-add+1][5]==1)){
            weight[boardWidth-1-add][y-add][5]=1;
            totalValue++;
                }
        for(int x=boardWidth-2;x>=0;x--)
            for(int add=0;x-add>=0 && add<boardHeight;add++)
                if(chessboard[x-add][boardHeight-1-add]==player &&
                (!posIsInBoard(new Point(x-add+1,boardHeight-add))||weight[x-add+1][boardHeight-add][5]==1)){
            weight[x-add][boardHeight-1-add][5]=1;
            totalValue++;
                }
        //Search from down to top
        for(int x=0;x<boardWidth;x++)
            for(int add=0;add<boardHeight;add++)
                if(chessboard[x][boardHeight-1-add]==player &&
                (!posIsInBoard(new Point(x,boardHeight-add)) || weight[x][boardHeight-add][6]==1)){
            weight[x][boardHeight-1-add][6]=1;
            totalValue++;
                }
        //Search from leftdown to righttop
        for(int x=0;x<boardWidth;x++)
            for(int add=0;x+add<boardWidth && add<boardHeight;add++)
                if(chessboard[x+add][boardHeight-1-add]==player &&
                (!posIsInBoard(new Point(x+add-1,boardHeight-add))||weight[x+add-1][boardHeight-add][7]==1)){
            weight[x+add][boardHeight-1-add][7]=1;
            totalValue++;
                }
        for(int y=boardHeight-2;y>=0;y--)
            for(int add=0;y-add>=0 && add<boardWidth;add++)
                if(chessboard[add][y-add]==player &&
                (!posIsInBoard(new Point(add-1,y-add+1))||weight[add-1][y-add+1][7]==1)){
            weight[add][y-add][7]=1;
            totalValue++;
                }
        
        for(int i=0;i<this.unvailableStack.size();i++) {
            Point unvalPoint=this.unvailableStack.get(i);
            //System.out.println(unvalPoint.x+","+unvalPoint.y);
            for(int dx=-1;dx<=1;dx++)
                for(int dy=-1;dy<=1;dy++) {
                if(dx==0 && dy==0)
                    continue;
                Point searchPoint=new Point(unvalPoint);
                searchPoint.x+=dx;
                searchPoint.y+=dy;
                while(this.posIsInBoard(searchPoint) &&
                        chessboard[searchPoint.x][searchPoint.y]==player) {
                    //totalValue++;
                    if(dx==1 && dy==0)
                        weight[searchPoint.x][searchPoint.y][0]=1;
                    else if(dx==1 && dy==1)
                        weight[searchPoint.x][searchPoint.y][1]=1;
                    else if(dx==0 && dy==1)
                        weight[searchPoint.x][searchPoint.y][2]=1;
                    else if(dx==-1 && dy==1)
                        weight[searchPoint.x][searchPoint.y][3]=1;
                    else if(dx==-1 && dy==0)
                        weight[searchPoint.x][searchPoint.y][4]=1;
                    else if(dx==-1 && dy==-1)
                        weight[searchPoint.x][searchPoint.y][5]=1;
                    else if(dx==0 && dy==-1)
                        weight[searchPoint.x][searchPoint.y][6]=1;
                    else if(dx==1 && dy==-1)
                        weight[searchPoint.x][searchPoint.y][7]=1;
                    searchPoint.x+=dx;
                    searchPoint.y+=dy;
                }
                }
        }
        
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                int count = 0;
                int last = 0;
                for (int k = 0; k <= 11; k++) {
                    int t = k <= 7 ? k : k - 8;
                    if (count == 3) {
                        totalCount++;
                        break;
                    }
                    if (last == 1 && weight[i][j][t] == 1)
                        count++;
                    else
                        count = 0;
                    last = weight[i][j][t];
                }
            }
        }
        return totalCount;
    }
    
    private boolean isGreatPoint(Point pos){
        int x=pos.x;
        int y=pos.y;
        if(
                (x==0&&y==0)    ||
                (x==0&&y==chessHeight-1)    ||
                (x==chessWidth-1&&y==chessHeight-1)  ||
                (x==chessWidth-1&&y==0)
                )
            return true;
        return false;
    }
    
    private boolean posInDanger(Point pos) {
        int x=pos.x;
        int y=pos.y;
        if(
                (x==1&&y==1)    ||
                (x==1&&y==chessHeight-2)    ||
                (x==chessWidth-2&&y==chessHeight-2)  ||
                (x==chessWidth-2&&y==1)
                )
            return true;
        return false;
    }
    
    private int countGreatPoint(byte player,byte[][] chessboard) {
        return(
                chessboard[0][0]==player?1:0    +
                chessboard[0][chessHeight-1]==player?1:0  +
                chessboard[chessWidth-1][0]==player?1:0   +
                chessboard[chessWidth-1][chessHeight-1]==player?1:0
                );
    }
    
    private void getUnvailableStack(byte[][] chessboard) {
        unvailableStack=new Stack<Point>();
        for(int i=0;i<chessWidth;i++)
            for(int j=0;j<chessHeight;j++)
                if(chessboard[i][j]==NOT_AVAILABLE)
                    unvailableStack.push(new Point(i,j));
    }
}
