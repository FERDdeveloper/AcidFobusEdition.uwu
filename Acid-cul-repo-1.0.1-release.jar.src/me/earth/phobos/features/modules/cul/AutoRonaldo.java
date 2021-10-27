/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ 
/*    */ public class AutoRonaldo
/*    */   extends Module {
/*    */   public AutoRonaldo() {
/*  9 */     super("AutoRonaldo", "siuuuuu", Module.Category.CUL, true, false, false);
/*    */   }
/*    */   
/*    */   public void enable() {
/* 13 */     super.enable();
/* 14 */     Command.sendMessage("SIUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
/* 15 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\AutoRonaldo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */