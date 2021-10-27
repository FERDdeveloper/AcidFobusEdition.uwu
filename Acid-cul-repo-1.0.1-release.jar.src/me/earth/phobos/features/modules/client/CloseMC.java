/*    */ package me.earth.phobos.features.modules.client;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class CloseMC extends Module {
/*    */   public CloseMC() {
/*  8 */     super("MinecraftShutdown", "close block game", Module.Category.CLIENT, true, false, false);
/*    */ 
/*    */     
/* 11 */     this.mc = Minecraft.func_71410_x();
/*    */   } Minecraft mc;
/*    */   public void enable() {
/* 14 */     this.mc.func_71400_g();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\CloseMC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */