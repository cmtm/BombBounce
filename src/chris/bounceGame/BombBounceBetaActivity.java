package chris.bounceGame;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class BombBounceBetaActivity extends AndroidGame
{
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}
