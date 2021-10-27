/*    */ package me.earth.phobos.features.modules.misc;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ 
/*    */ public class Display
/*    */   extends Module {
/*  8 */   private static Display INSTANCE = new Display();
/*  9 */   public Setting<String> gang = register(new Setting("Title", "Acid"));
/* 10 */   public Setting<Boolean> version = register(new Setting("Version", Boolean.valueOf(true)));
/*    */   
/*    */   public Display() {
/* 13 */     super("Display", "Sets the title of your game", Module.Category.MISC, true, false, false);
/* 14 */     setInstance();
/*    */   }
/*    */   
/*    */   public static Display getInstance() {
/* 18 */     if (INSTANCE == null) {
/* 19 */       INSTANCE = new Display();
/*    */     }
/* 21 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   private void setInstance() {
/* 25 */     INSTANCE = this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 30 */     org.lwjgl.opengl.Display.setTitle((String)this.gang.getValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\Display.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */