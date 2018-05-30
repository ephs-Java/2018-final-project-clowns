package level;
 
import level.Point;
import engine.Engine;
import engine.InputHandler;
 
public class Camera {
 
    private int levelStage;
    private int cTicks = 0;
    private Engine game;
    Point camera1;
    Point objLoc;
    Point camera2;
    private Point camera3;
 
    public Camera(Engine g) {
 
        this.game = g;
    }
 
    public int getcTicks() {
        return cTicks;
    }
 
    public void setcTicks(int cTicks) {
        this.cTicks = cTicks;
    }
 
    public boolean isInRadius() {
        if (game.level.levelStage != 0 && game.level.levelStage != 5) {
            camera1 = new Point(50, 307);
            camera2 = new Point(319, 596);
            camera3 = new Point(656, 308);
            if ((camera1.getX() - game.player.x - 30) < 0 && (camera1.getX() - game.player.x) > -100
                    && (camera1.getY() - game.player.y - 70) < 0 && (camera1.getY() - game.player.y) > -100) {
                System.out.println("Danger");
                cTicks += 1;
                return true;
 
            }
            else if ((camera2.getX() - game.player.x) < 100 && (camera2.getX() - game.player.x) > -100
                    && (camera2.getY() - game.player.y - 70) < 100 && (camera2.getY() - game.player.y) > 0) {
                System.out.println("Danger");
                cTicks += 1;
                return true;
            }
            else if ((camera3.getX() - game.player.x) < 100 && (camera3.getX() - game.player.x) > -100
                    && (camera3.getY() - game.player.y - 70) > -100 && (camera3.getY() - game.player.y - 70) < 0) {
                System.out.println("Danger");
                cTicks += 1;
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean tenSeconds() {
        if (cTicks > 1000) {
            return true;
        }
        return false;
    }
    public boolean isAlarm() {
        //isInRadius();
        if (isInRadius() && cTicks >= (1000)) {
            System.out.println("Alarm");
            game.player.isRobbing = true;
            return true;
        }
        if (!isInRadius()) {
            cTicks = 0;
        }
        return false;
    }
 
    public void clickInteraction(int x, int y) {
        objLoc = new Point(x, y);
        if (Math.abs(objLoc.getX() - game.player.x) <= 100 && Math.abs(objLoc.getY() - game.player.y) <= 100) {
 
        }
    }
 
}