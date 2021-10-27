/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class Wireframe extends Module {
/*  9 */   private static Wireframe INSTANCE = new Wireframe();
/* 10 */   public Setting<Boolean> rainbow = register(new Setting("Rainbow", Boolean.TRUE));
/* 11 */   public Setting<Integer> rainbowHue = register(new Setting("RBrightness", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(600), v -> ((Boolean)this.rainbow.getValue()).booleanValue()));
/* 12 */   public Setting<Integer> red = register(new Setting("PRed", Integer.valueOf(168), Integer.valueOf(0), Integer.valueOf(255)));
/* 13 */   public Setting<Integer> green = register(new Setting("PGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
/* 14 */   public Setting<Integer> blue = register(new Setting("PBlue", Integer.valueOf(232), Integer.valueOf(0), Integer.valueOf(255)));
/* 15 */   public Setting<Integer> Cred = register(new Setting("CRed", Integer.valueOf(168), Integer.valueOf(0), Integer.valueOf(255)));
/* 16 */   public Setting<Integer> Cgreen = register(new Setting("CGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
/* 17 */   public Setting<Integer> Cblue = register(new Setting("CBlue", Integer.valueOf(232), Integer.valueOf(0), Integer.valueOf(255)));
/* 18 */   public final Setting<Float> alpha = register(new Setting("PAlpha", Float.valueOf(255.0F), Float.valueOf(0.1F), Float.valueOf(255.0F)));
/* 19 */   public final Setting<Float> cAlpha = register(new Setting("CAlpha", Float.valueOf(255.0F), Float.valueOf(0.1F), Float.valueOf(255.0F)));
/* 20 */   public final Setting<Float> lineWidth = register(new Setting("PLineWidth", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(3.0F)));
/* 21 */   public final Setting<Float> crystalLineWidth = register(new Setting("CLineWidth", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(3.0F)));
/* 22 */   public Setting<RenderMode> mode = register(new Setting("PMode", RenderMode.SOLID));
/* 23 */   public Setting<RenderMode> cMode = register(new Setting("CMode", RenderMode.SOLID));
/* 24 */   public Setting<Boolean> players = register(new Setting("Players", Boolean.FALSE));
/* 25 */   public Setting<Boolean> playerModel = register(new Setting("PlayerModel", Boolean.FALSE));
/* 26 */   public Setting<Boolean> crystals = register(new Setting("Crystals", Boolean.FALSE));
/* 27 */   public Setting<Boolean> crystalModel = register(new Setting("CrystalModel", Boolean.FALSE));
/*    */ 
/*    */   
/*    */   public Wireframe() {
/* 31 */     super("Wireframe", "Draws a wireframe esp around other players.", Module.Category.RENDER, false, false, false);
/* 32 */     setInstance();
/*    */   }
/*    */   
/*    */   public static Wireframe getInstance() {
/* 36 */     if (INSTANCE == null) {
/* 37 */       INSTANCE = new Wireframe();
/*    */     }
/* 39 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   private void setInstance() {
/* 43 */     INSTANCE = this;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderPlayerEvent(RenderPlayerEvent.Pre event) {
/* 48 */     (event.getEntityPlayer()).field_70737_aN = 0;
/*    */   }
/*    */   
/*    */   public enum RenderMode {
/* 52 */     SOLID,
/* 53 */     WIREFRAME;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\Wireframe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */