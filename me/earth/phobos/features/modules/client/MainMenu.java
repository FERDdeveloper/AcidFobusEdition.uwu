/*    */ package me.earth.phobos.features.modules.client;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ 
/*    */ public class MainMenu extends Module {
/*    */   public static MainMenu INSTANCE;
/*  8 */   public Setting<Boolean> mainScreen = register(new Setting("MainScreen", Boolean.valueOf(false)));
/*    */   
/*    */   public MainMenu() {
/* 11 */     super("MainMenu", "Controls custom screens used by the client", Module.Category.CLIENT, true, false, false);
/* 12 */     INSTANCE = this;
/*    */   }
/*    */   
/*    */   public void onTick() {}
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\MainMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */