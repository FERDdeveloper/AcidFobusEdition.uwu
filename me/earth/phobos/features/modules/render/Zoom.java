/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ 
/*    */ public class Zoom
/*    */   extends Module {
/*    */   float cachedFov;
/*    */   
/*    */   public Zoom() {
/* 10 */     super("Zoom", "zoomss", Module.Category.RENDER, true, false, false);
/* 11 */     this; this.cachedFov = mc.field_71474_y.field_74334_X;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 16 */     this; if (mc.field_71439_g != null) {
/* 17 */       this; mc.field_71474_y.field_74334_X = 15.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onDissable() {
/* 22 */     this; if (mc.field_71439_g != null) {
/* 23 */       this; mc.field_71474_y.field_74334_X = this.cachedFov;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\Zoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */