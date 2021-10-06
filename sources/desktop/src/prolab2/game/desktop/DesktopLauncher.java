package prolab2.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import prolab2.game.TestSinifi;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.resizable=true;
                config.height=1080;
                config.width=1920;
                config.vSyncEnabled=true;
                config.fullscreen=true;
               
                config.title="Prolab Game";
                
                
		new LwjglApplication(new TestSinifi(), config);
                
	}
}
