/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ 
/*    */ public class AutoSuicide
/*    */   extends Module {
/*    */   public AutoSuicide() {
/*  8 */     super("AutoSuicide", "kill urself", Module.Category.CUL, true, false, false);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 12 */     mc.field_71439_g.func_71165_d("/kill");
/* 13 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\AutoSuicide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */