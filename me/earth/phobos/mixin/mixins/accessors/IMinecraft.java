package me.earth.phobos.mixin.mixins.accessors;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;

public interface IMinecraft {
  Timer getTimer();
  
  Session getSession();
  
  void setSession(Session paramSession);
  
  void setRightClickDelayTimer(int paramInt);
  
  void clickMouse();
  
  ServerData getCurrentServerData();
}


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\accessors\IMinecraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */