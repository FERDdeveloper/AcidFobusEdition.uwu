/*    */ package me.earth.phobos.mixin.mixins.accessors;
/*    */ 
/*    */ import net.minecraft.client.multiplayer.ServerAddress;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.gen.Invoker;
/*    */ 
/*    */ @Mixin({ServerAddress.class})
/*    */ public interface IServerAddress {
/*    */   @Invoker("getServerAddress")
/*    */   static String[] getServerAddress(String string) {
/* 11 */     throw new IllegalStateException("Mixin didnt transform this");
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\accessors\IServerAddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */