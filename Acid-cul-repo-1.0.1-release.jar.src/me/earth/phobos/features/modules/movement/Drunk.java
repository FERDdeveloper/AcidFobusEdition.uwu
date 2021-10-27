/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ 
/*    */ public class Drunk
/*    */   extends Module
/*    */ {
/*    */   public Drunk() {
/*  9 */     super("Drunk", "just makes u drunk", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 15 */     if (!fullNullCheck()) { this; if (!mc.field_71439_g.func_70617_f_()) {
/*    */ 
/*    */         
/* 18 */         this; if (mc.field_71439_g.field_70122_E) {
/* 19 */           this; mc.field_71439_g.field_70159_w = 20.0D;
/* 20 */           this; mc.field_71439_g.field_70159_w = 10.0D;
/*    */         } 
/*    */         return;
/*    */       }  }
/*    */   
/*    */   }
/*    */   public void onDisable() {
/* 27 */     super.onDisable();
/* 28 */     mc.field_71439_g.field_70159_w = 0.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\Drunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */