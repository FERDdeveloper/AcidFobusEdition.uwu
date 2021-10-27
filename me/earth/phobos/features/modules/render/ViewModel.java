/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.event.events.RenderItemEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class ViewModel
/*    */   extends Module {
/* 10 */   private static ViewModel INSTANCE = new ViewModel();
/* 11 */   public Setting<Settings> settings = register(new Setting("Settings", Settings.TRANSLATE));
/* 12 */   public Setting<Boolean> noEatAnimation = register(new Setting("NoEatAnimation", Boolean.valueOf(false), v -> (this.settings.getValue() == Settings.TWEAKS)));
/* 13 */   public Setting<Double> eatX = register(new Setting("EatX", Double.valueOf(1.0D), Double.valueOf(-2.0D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.TWEAKS && !((Boolean)this.noEatAnimation.getValue()).booleanValue())));
/* 14 */   public Setting<Double> eatY = register(new Setting("EatY", Double.valueOf(1.0D), Double.valueOf(-2.0D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.TWEAKS && !((Boolean)this.noEatAnimation.getValue()).booleanValue())));
/* 15 */   public Setting<Boolean> doBob = register(new Setting("ItemBob", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.TWEAKS)));
/* 16 */   public Setting<Double> mainX = register(new Setting("MainX", Double.valueOf(1.2D), Double.valueOf(-2.0D), Double.valueOf(4.0D), v -> (this.settings.getValue() == Settings.TRANSLATE)));
/* 17 */   public Setting<Double> mainY = register(new Setting("MainY", Double.valueOf(-0.95D), Double.valueOf(-3.0D), Double.valueOf(3.0D), v -> (this.settings.getValue() == Settings.TRANSLATE)));
/* 18 */   public Setting<Double> mainZ = register(new Setting("MainZ", Double.valueOf(-1.45D), Double.valueOf(-5.0D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.TRANSLATE)));
/* 19 */   public Setting<Double> offX = register(new Setting("OffX", Double.valueOf(1.2D), Double.valueOf(-2.0D), Double.valueOf(4.0D), v -> (this.settings.getValue() == Settings.TRANSLATE)));
/* 20 */   public Setting<Double> offY = register(new Setting("OffY", Double.valueOf(-0.95D), Double.valueOf(-3.0D), Double.valueOf(3.0D), v -> (this.settings.getValue() == Settings.TRANSLATE)));
/* 21 */   public Setting<Double> offZ = register(new Setting("OffZ", Double.valueOf(-1.45D), Double.valueOf(-5.0D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.TRANSLATE)));
/* 22 */   public Setting<Integer> mainRotX = register(new Setting("MainRotationX", Integer.valueOf(0), Integer.valueOf(-36), Integer.valueOf(36), v -> (this.settings.getValue() == Settings.ROTATE)));
/* 23 */   public Setting<Integer> mainRotY = register(new Setting("MainRotationY", Integer.valueOf(0), Integer.valueOf(-36), Integer.valueOf(36), v -> (this.settings.getValue() == Settings.ROTATE)));
/* 24 */   public Setting<Integer> mainRotZ = register(new Setting("MainRotationZ", Integer.valueOf(0), Integer.valueOf(-36), Integer.valueOf(36), v -> (this.settings.getValue() == Settings.ROTATE)));
/* 25 */   public Setting<Integer> offRotX = register(new Setting("OffRotationX", Integer.valueOf(0), Integer.valueOf(-36), Integer.valueOf(36), v -> (this.settings.getValue() == Settings.ROTATE)));
/* 26 */   public Setting<Integer> offRotY = register(new Setting("OffRotationY", Integer.valueOf(0), Integer.valueOf(-36), Integer.valueOf(36), v -> (this.settings.getValue() == Settings.ROTATE)));
/* 27 */   public Setting<Integer> offRotZ = register(new Setting("OffRotationZ", Integer.valueOf(0), Integer.valueOf(-36), Integer.valueOf(36), v -> (this.settings.getValue() == Settings.ROTATE)));
/* 28 */   public Setting<Double> mainScaleX = register(new Setting("MainScaleX", Double.valueOf(1.0D), Double.valueOf(0.1D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.SCALE)));
/* 29 */   public Setting<Double> mainScaleY = register(new Setting("MainScaleY", Double.valueOf(1.0D), Double.valueOf(0.1D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.SCALE)));
/* 30 */   public Setting<Double> mainScaleZ = register(new Setting("MainScaleZ", Double.valueOf(1.0D), Double.valueOf(0.1D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.SCALE)));
/*    */   
/* 32 */   public Setting<Double> offScaleX = register(new Setting("OffScaleX", Double.valueOf(1.0D), Double.valueOf(0.1D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.SCALE)));
/* 33 */   public Setting<Double> offScaleY = register(new Setting("OffScaleY", Double.valueOf(1.0D), Double.valueOf(0.1D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.SCALE)));
/* 34 */   public Setting<Double> offScaleZ = register(new Setting("OffScaleZ", Double.valueOf(1.0D), Double.valueOf(0.1D), Double.valueOf(5.0D), v -> (this.settings.getValue() == Settings.SCALE)));
/*    */ 
/*    */ 
/*    */   
/*    */   public ViewModel() {
/* 39 */     super("ViewModel", "Cool", Module.Category.RENDER, true, false, false);
/* 40 */     setInstance();
/*    */   }
/*    */ 
/*    */   
/*    */   public static ViewModel getInstance() {
/* 45 */     if (INSTANCE == null) {
/* 46 */       INSTANCE = new ViewModel();
/*    */     }
/* 48 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   private void setInstance() {
/* 53 */     INSTANCE = this;
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onItemRender(RenderItemEvent event) {
/* 59 */     event.setMainX(((Double)this.mainX.getValue()).doubleValue());
/* 60 */     event.setMainY(((Double)this.mainY.getValue()).doubleValue());
/* 61 */     event.setMainZ(((Double)this.mainZ.getValue()).doubleValue());
/*    */     
/* 63 */     event.setOffX(-((Double)this.offX.getValue()).doubleValue());
/* 64 */     event.setOffY(((Double)this.offY.getValue()).doubleValue());
/* 65 */     event.setOffZ(((Double)this.offZ.getValue()).doubleValue());
/*    */     
/* 67 */     event.setMainRotX((((Integer)this.mainRotX.getValue()).intValue() * 5));
/* 68 */     event.setMainRotY((((Integer)this.mainRotY.getValue()).intValue() * 5));
/* 69 */     event.setMainRotZ((((Integer)this.mainRotZ.getValue()).intValue() * 5));
/*    */     
/* 71 */     event.setOffRotX((((Integer)this.offRotX.getValue()).intValue() * 5));
/* 72 */     event.setOffRotY((((Integer)this.offRotY.getValue()).intValue() * 5));
/* 73 */     event.setOffRotZ((((Integer)this.offRotZ.getValue()).intValue() * 5));
/*    */     
/* 75 */     event.setOffHandScaleX(((Double)this.offScaleX.getValue()).doubleValue());
/* 76 */     event.setOffHandScaleY(((Double)this.offScaleY.getValue()).doubleValue());
/* 77 */     event.setOffHandScaleZ(((Double)this.offScaleZ.getValue()).doubleValue());
/*    */     
/* 79 */     event.setMainHandScaleX(((Double)this.mainScaleX.getValue()).doubleValue());
/* 80 */     event.setMainHandScaleY(((Double)this.mainScaleY.getValue()).doubleValue());
/* 81 */     event.setMainHandScaleZ(((Double)this.mainScaleZ.getValue()).doubleValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private enum Settings
/*    */   {
/* 89 */     TRANSLATE,
/* 90 */     ROTATE,
/* 91 */     SCALE,
/* 92 */     TWEAKS;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\ViewModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */