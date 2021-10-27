/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class PackReload
/*    */   extends Module
/*    */ {
/*    */   public PackReload() {
/* 11 */     super("PackReload", "efficiency", Module.Category.CUL, false, false, false);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick() {
/* 16 */     mc.func_110436_a();
/* 17 */     disable();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\PackReload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */