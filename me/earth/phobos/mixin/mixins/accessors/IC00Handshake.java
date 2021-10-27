package me.earth.phobos.mixin.mixins.accessors;

import net.minecraft.network.handshake.client.C00Handshake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C00Handshake.class})
public interface IC00Handshake {
  @Accessor("ip")
  String getIp();
  
  @Accessor("ip")
  void setIp(String paramString);
}


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\accessors\IC00Handshake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */