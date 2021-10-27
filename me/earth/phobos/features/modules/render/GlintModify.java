/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ 
/*    */ public class GlintModify
/*    */   extends Module
/*    */ {
/* 10 */   public Setting<Integer> red = register(new Setting("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 11 */   public Setting<Integer> green = register(new Setting("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 12 */   public Setting<Integer> blue = register(new Setting("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 13 */   public Setting<Boolean> rainbow = register(new Setting("Rainbow", Boolean.valueOf(false)));
/*    */ 
/*    */   
/*    */   public GlintModify() {
/* 17 */     super("GlintModify", "Changes the enchant glint color", Module.Category.RENDER, true, false, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Color getColor(long offset, float fade) {
/* 22 */     float hue = (float)(System.nanoTime() + offset) / 1.0E10F % 1.0F;
/* 23 */     long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0F, 1.0F)), 16);
/* 24 */     Color c = new Color((int)color);
/* 25 */     return new Color(c.getRed() / 255.0F * fade, c.getGreen() / 255.0F * fade, c.getBlue() / 255.0F * fade, c.getAlpha() / 255.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 31 */     if (((Boolean)this.rainbow.getValue()).booleanValue()) {
/* 32 */       cycleRainbow();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void cycleRainbow() {
/* 39 */     float[] tick_color = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
/*    */     
/* 41 */     int color_rgb_o = Color.HSBtoRGB(tick_color[0], 0.8F, 0.8F);
/*    */     
/* 43 */     this.red.setValue(Integer.valueOf(color_rgb_o >> 16 & 0xFF));
/* 44 */     this.green.setValue(Integer.valueOf(color_rgb_o >> 8 & 0xFF));
/* 45 */     this.blue.setValue(Integer.valueOf(color_rgb_o & 0xFF));
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\GlintModify.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */