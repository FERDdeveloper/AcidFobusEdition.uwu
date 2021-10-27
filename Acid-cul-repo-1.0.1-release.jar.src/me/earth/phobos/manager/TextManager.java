/*     */ package me.earth.phobos.manager;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.Feature;
/*     */ import me.earth.phobos.features.gui.font.CustomFont;
/*     */ import me.earth.phobos.features.modules.client.FontMod;
/*     */ import me.earth.phobos.util.MathUtil;
/*     */ import me.earth.phobos.util.Timer;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ public class TextManager
/*     */   extends Feature
/*     */ {
/*  16 */   private final Timer idleTimer = new Timer();
/*  17 */   private final CustomFont headerFont = new CustomFont(new Font("Tahoma", 1, 40), true, false);
/*  18 */   private final CustomFont smallString = new CustomFont(new Font("tahoma", 1, 10), true, false);
/*     */   public int scaledWidth;
/*     */   public int scaledHeight;
/*     */   public int scaleFactor;
/*  22 */   private CustomFont customFont = new CustomFont(new Font("Verdana", 0, 17), true, false);
/*     */   
/*     */   private boolean idling;
/*     */   
/*     */   public TextManager() {
/*  27 */     updateResolution();
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(boolean startup) {
/*  32 */     FontMod cFont = Phobos.moduleManager.<FontMod>getModuleByClass(FontMod.class);
/*     */     try {
/*  34 */       setFontRenderer(new Font((String)cFont.fontName.getValue(), ((Integer)cFont.fontStyle.getValue()).intValue(), ((Integer)cFont.fontSize.getValue()).intValue()), ((Boolean)cFont.antiAlias.getValue()).booleanValue(), ((Boolean)cFont.fractionalMetrics.getValue()).booleanValue());
/*  35 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawStringWithShadow(String text, float x, float y, int color) {
/*  42 */     drawString(text, x, y, color, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public float drawString(String text, float x, float y, int color, boolean shadow) {
/*  47 */     if (Phobos.moduleManager.isModuleEnabled(FontMod.class)) {
/*  48 */       if (shadow) {
/*  49 */         return this.customFont.drawStringWithShadow(text, x, y, color);
/*     */       }
/*  51 */       return this.customFont.drawString(text, x, y, color);
/*     */     } 
/*  53 */     return mc.field_71466_p.func_175065_a(text, x, y, color, shadow);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawRainbowString(String text, float x, float y, int startColor, float factor, boolean shadow) {
/*  58 */     Color currentColor = new Color(startColor);
/*  59 */     float hueIncrement = 1.0F / factor;
/*  60 */     String[] rainbowStrings = text.split("§.");
/*  61 */     float currentHue = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[0];
/*  62 */     float saturation = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[1];
/*  63 */     float brightness = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[2];
/*  64 */     int currentWidth = 0;
/*  65 */     boolean shouldRainbow = true;
/*  66 */     boolean shouldContinue = false;
/*  67 */     for (int i = 0; i < text.length(); i++) {
/*  68 */       char currentChar = text.charAt(i);
/*  69 */       char nextChar = text.charAt(MathUtil.clamp(i + 1, 0, text.length() - 1));
/*  70 */       if ((String.valueOf(currentChar) + nextChar).equals("§r")) {
/*  71 */         shouldRainbow = false;
/*  72 */       } else if ((String.valueOf(currentChar) + nextChar).equals("§+")) {
/*  73 */         shouldRainbow = true;
/*     */       } 
/*  75 */       if (shouldContinue) {
/*  76 */         shouldContinue = false;
/*     */       } else {
/*     */         
/*  79 */         if ((String.valueOf(currentChar) + nextChar).equals("§r")) {
/*  80 */           String escapeString = text.substring(i);
/*  81 */           drawString(escapeString, x + currentWidth, y, Color.WHITE.getRGB(), shadow);
/*     */           break;
/*     */         } 
/*  84 */         drawString(String.valueOf(currentChar).equals("§") ? "" : String.valueOf(currentChar), x + currentWidth, y, shouldRainbow ? currentColor.getRGB() : Color.WHITE.getRGB(), shadow);
/*  85 */         if (String.valueOf(currentChar).equals("§")) {
/*  86 */           shouldContinue = true;
/*     */         }
/*  88 */         currentWidth += getStringWidth(String.valueOf(currentChar));
/*  89 */         if (!String.valueOf(currentChar).equals(" ")) {
/*  90 */           currentColor = new Color(Color.HSBtoRGB(currentHue, saturation, brightness));
/*  91 */           currentHue += hueIncrement;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public int getStringWidth(String text) {
/*  97 */     if (Phobos.moduleManager.isModuleEnabled(FontMod.class)) {
/*  98 */       return this.customFont.getStringWidth(text);
/*     */     }
/* 100 */     return mc.field_71466_p.func_78256_a(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFontHeight() {
/* 105 */     if (Phobos.moduleManager.isModuleEnabled(FontMod.class)) {
/* 106 */       String text = "A";
/* 107 */       return this.customFont.getStringHeight(text);
/*     */     } 
/* 109 */     return mc.field_71466_p.field_78288_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFontRenderer(Font font, boolean antiAlias, boolean fractionalMetrics) {
/* 114 */     this.customFont = new CustomFont(font, antiAlias, fractionalMetrics);
/*     */   }
/*     */ 
/*     */   
/*     */   public Font getCurrentFont() {
/* 119 */     return this.customFont.getFont();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateResolution() {
/* 124 */     this.scaledWidth = mc.field_71443_c;
/* 125 */     this.scaledHeight = mc.field_71440_d;
/* 126 */     this.scaleFactor = 1;
/* 127 */     boolean flag = mc.func_152349_b();
/* 128 */     int i = mc.field_71474_y.field_74335_Z;
/* 129 */     if (i == 0) {
/* 130 */       i = 1000;
/*     */     }
/* 132 */     while (this.scaleFactor < i && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
/* 133 */       this.scaleFactor++;
/*     */     }
/* 135 */     if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
/* 136 */       this.scaleFactor--;
/*     */     }
/* 138 */     double scaledWidthD = this.scaledWidth / this.scaleFactor;
/* 139 */     double scaledHeightD = this.scaledHeight / this.scaleFactor;
/* 140 */     this.scaledWidth = MathHelper.func_76143_f(scaledWidthD);
/* 141 */     this.scaledHeight = MathHelper.func_76143_f(scaledHeightD);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIdleSign() {
/* 146 */     if (this.idleTimer.passedMs(500L)) {
/* 147 */       this.idling = !this.idling;
/* 148 */       this.idleTimer.reset();
/*     */     } 
/* 150 */     if (this.idling) {
/* 151 */       return "_";
/*     */     }
/* 153 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public float drawStringBig(String string, float x, float y, int colour, boolean shadow) {
/* 158 */     if (shadow) {
/* 159 */       return this.headerFont.drawStringWithShadow(string, x, y, colour);
/*     */     }
/* 161 */     return this.headerFont.drawString(string, x, y, colour);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float drawStringSmall(String string, float x, float y, int colour, boolean shadow) {
/* 167 */     if (shadow) {
/* 168 */       return this.smallString.drawStringWithShadow(string, x, y, colour);
/*     */     }
/* 170 */     return this.smallString.drawString(string, x, y, colour);
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\manager\TextManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */