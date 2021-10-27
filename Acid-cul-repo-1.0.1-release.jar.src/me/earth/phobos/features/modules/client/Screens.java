/*    */ package me.earth.phobos.features.modules.client;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ 
/*    */ public class Screens
/*    */   extends Module {
/*    */   public static Screens INSTANCE;
/*  9 */   public Setting<Boolean> mainScreen = register(new Setting("MainScreen", Boolean.valueOf(true)));
/*    */   
/*    */   public Screens() {
/* 12 */     super("Screens", "Controls custom screens used by the client", Module.Category.CLIENT, true, false, false);
/* 13 */     INSTANCE = this;
/*    */   }
/*    */   
/*    */   public void onTick() {}
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\Screens.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */