/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ 
/*    */ public class ItemPhysics
/*    */   extends Module {
/*  8 */   public static ItemPhysics INSTANCE = new ItemPhysics();
/*  9 */   public final Setting<Float> Scaling = register(new Setting("Scaling", Float.valueOf(0.5F), Float.valueOf(0.0F), Float.valueOf(10.0F)));
/*    */   
/*    */   public ItemPhysics() {
/* 12 */     super("ItemPhysics", "Apply physics to items.", Module.Category.RENDER, false, false, false);
/* 13 */     setInstance();
/*    */   }
/*    */   
/*    */   public static ItemPhysics getInstance() {
/* 17 */     if (INSTANCE == null) {
/* 18 */       INSTANCE = new ItemPhysics();
/*    */     }
/* 20 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   private void setInstance() {
/* 24 */     INSTANCE = this;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\ItemPhysics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */