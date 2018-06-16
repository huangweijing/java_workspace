import java.util.Scanner;

public class BotSavesPrincess {

static void displayPathtoPrincess(int n, String [] grid){
    int botX = -1;
    int botY = -1;
    int pX = -1;
    int pY = -1;
    
    for(int i=0; i<grid.length; i++) {
    	for(int j=0; j<grid[i].length(); j++) {
    		if(grid[i].charAt(j) == 'm') {
    			botX = j;
    			botY = i;
    		} else if(grid[i].charAt(j) == 'p') {
    			pX = j;
    			pY = i;
    		}
    	}
    }
    
    //System.out.println(String.format("%s,%s,%s,%s", botX, botY, pX, pY));
    
    if(botY < pY) {
        for(int i=botY; i<pY; i++) {
            System.out.println("DOWN");
        }
    } else if(botY > pY){
        for(int i=botY; i>pY; i--) {
            System.out.println("UP");
        }
    }
    if(botX < pX) {
        for(int i=botX; i<pX; i++) {
            System.out.println("RIGHT");
        }
    } else if(botX > pX) {
        for(int i=botX; i>pX; i--) {
            System.out.println("LEFT");
        }
    }
}

public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m;
        m = in.nextInt();
        String grid[] = new String[m];
        for(int i = 0; i < m; i++) {
            grid[i] = in.next();
        }

    displayPathtoPrincess(m,grid);
    }
}