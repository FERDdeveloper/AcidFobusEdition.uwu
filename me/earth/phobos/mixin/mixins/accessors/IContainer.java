package me.earth.phobos.mixin.mixins.accessors;

import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Container.class})
public interface IContainer {
  @Accessor("transactionID")
  void setTransactionID(short paramShort);
}


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\accessors\IContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */