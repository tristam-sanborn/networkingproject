package tictactoe.packets;

public class CheckWinner {
private int[][] gameFields;
    
    public CheckWinner(int[][] gameFields){
        this.gameFields = gameFields;
    }
    
    public int checking(){
        for(int player = 1; player <= 2; player++) {

            if(checkHorizontal(player))
                return player;

            if(checkVertical(player))
                return player;

            if(checkDiagonal(player))
                return player;
            
        }
        return 0;
    }
    
    private boolean checkHorizontal(int player){
        for(int y = 0; y < 3; y++) {
            int playerCount = 0;
            for(int x = 0; x < 3; x++) {
                if(gameFields[x][y] == player) {
                    playerCount++;
                }
            }
            if(playerCount == 3)
                return true;
        }
        return false;
    }

    
    private boolean checkVertical(int player){
        for(int x = 0; x < 3; x++) {
            int playerCount = 0;
            for(int y = 0; y < 3; y++) {
                if(gameFields[x][y] == player) {
                    playerCount++;
                }
            }
            if(playerCount == 3)
                return true;
        }
        return false;
    }

    
    private boolean checkDiagonal(int player){
        int playerCount = 0;
        
        for(int coordinate = 0; coordinate < 3; coordinate++){
            if(gameFields[coordinate][coordinate] == player){
                playerCount++;
            }
        } 
            
        if(playerCount == 3)
            return true;
        
        playerCount = 0;
        
        for(int coordinate = 0; coordinate < 3; coordinate++) {
            if(gameFields[2-coordinate][coordinate] == player){
                playerCount++;
            }
        }

        if(playerCount == 3){
            return true;
        }
        
        return false;
    }
}
