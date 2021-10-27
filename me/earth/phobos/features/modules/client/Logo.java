/*    */ package me.earth.phobos.features.modules.client;
/*    */ 
/*    */ import me.earth.phobos.event.events.Render2DEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.ColorUtil;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class Logo extends Module {
/* 12 */   public static final ResourceLocation mark = new ResourceLocation("textures/Supernova.png");
/*    */   public Setting<Integer> imageX;
/*    */   public Setting<Integer> imageY;
/*    */   public Setting<Integer> imageWidth;
/*    */   public Setting<Integer> imageHeight;
/*    */   private int color;
/*    */   
/*    */   public Logo() {
/* 20 */     super("Logo", "Puts a logo there (there)", Module.Category.CLIENT, false, false, false);
/* 21 */     this.imageX = register(new Setting("WatermarkX", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(300)));
/* 22 */     this.imageY = register(new Setting("WatermarkY", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(300)));
/* 23 */     this.imageWidth = register(new Setting("WatermarkWidth", Integer.valueOf(97), Integer.valueOf(0), Integer.valueOf(1000)));
/* 24 */     this.imageHeight = register(new Setting("WatermarkHeight", Integer.valueOf(97), Integer.valueOf(0), Integer.valueOf(1000)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderLogo() {
/* 29 */     int width = ((Integer)this.imageWidth.getValue()).intValue();
/* 30 */     int height = ((Integer)this.imageHeight.getValue()).intValue();
/* 31 */     int x = ((Integer)this.imageX.getValue()).intValue();
/* 32 */     int y = ((Integer)this.imageY.getValue()).intValue();
/* 33 */     this; mc.field_71446_o.func_110577_a(mark);
/* 34 */     GlStateManager.func_179124_c(255.0F, 255.0F, 255.0F);
/* 35 */     Gui.func_152125_a(x - 2, y - 36, 7.0F, 7.0F, width - 7, height - 7, width, height, width, height);
/*    */   }
/*    */   
/*    */   public void onRender2D(Render2DEvent event) {
/* 39 */     if (!fullNullCheck()) {
/* 40 */       int width = this.renderer.scaledWidth;
/* 41 */       int height = this.renderer.scaledHeight;
/* 42 */       this.color = ColorUtil.toRGBA(((Integer)(ClickGui.getInstance()).red.getValue()).intValue(), ((Integer)(ClickGui.getInstance()).green.getValue()).intValue(), ((Integer)(ClickGui.getInstance()).blue.getValue()).intValue());
/* 43 */       if (((Boolean)this.enabled.getValue()).booleanValue())
/* 44 */         renderLogo(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\Logo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */