/*    */ package me.earth.phobos.features.modules.misc;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ 
/*    */ public class FriendSettings
/*    */   extends Module {
/*    */   private static FriendSettings INSTANCE;
/*  9 */   public Setting<Boolean> notify = register(new Setting("Notify", Boolean.valueOf(false)));
/*    */   
/*    */   public FriendSettings() {
/* 12 */     super("FriendSettings", "Change aspects of friends", Module.Category.MISC, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public static FriendSettings getInstance() {
/* 17 */     if (INSTANCE == null) {
/* 18 */       INSTANCE = new FriendSettings();
/*    */     }
/* 20 */     return INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\FriendSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */