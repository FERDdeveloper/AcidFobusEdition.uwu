/*     */ package me.earth.phobos.features.gui.components.items.buttons;
/*     */ 
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.gui.PhobosGui;
/*     */ import me.earth.phobos.features.gui.components.Component;
/*     */ import me.earth.phobos.features.modules.client.ClickGui;
/*     */ import me.earth.phobos.features.modules.client.HUD;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.ColorUtil;
/*     */ import me.earth.phobos.util.MathUtil;
/*     */ import me.earth.phobos.util.RenderUtil;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Slider
/*     */   extends Button
/*     */ {
/*     */   private final Number min;
/*     */   private final Number max;
/*     */   private final int difference;
/*     */   public Setting setting;
/*     */   
/*     */   public Slider(Setting setting) {
/*  32 */     super(setting.getName());
/*  33 */     this.setting = setting;
/*  34 */     this.min = (Number)setting.getMin();
/*  35 */     this.max = (Number)setting.getMax();
/*  36 */     this.difference = this.max.intValue() - this.min.intValue();
/*  37 */     this.width = 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  43 */     dragSetting(mouseX, mouseY);
/*  44 */     RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4F, this.y + this.height - 0.5F, !isHovering(mouseX, mouseY) ? 290805077 : -2007673515);
/*  45 */     if (((Boolean)(ClickGui.getInstance()).rainbowRolling.getValue()).booleanValue()) {
/*  46 */       int color = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/*  47 */       int color1 = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/*  48 */       RenderUtil.drawGradientRect(this.x, this.y, (((Number)this.setting.getValue()).floatValue() <= this.min.floatValue()) ? 0.0F : ((this.width + 7.4F) * partialMultiplier()), this.height - 0.5F, !isHovering(mouseX, mouseY) ? ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue() : color, !isHovering(mouseX, mouseY) ? ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue() : color1);
/*     */     } else {
/*  50 */       RenderUtil.drawRect(this.x, this.y, (((Number)this.setting.getValue()).floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4F) * partialMultiplier()), this.y + this.height - 0.5F, !isHovering(mouseX, mouseY) ? Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue()) : Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).alpha.getValue()).intValue()));
/*     */     } 
/*  52 */     Phobos.textManager.drawStringWithShadow(getName() + " §7" + ((this.setting.getValue() instanceof Float) ? (String)this.setting.getValue() : (String)Double.valueOf(((Number)this.setting.getValue()).doubleValue())), this.x + 2.3F, this.y - 1.7F - PhobosGui.getClickGui().getTextOffset(), -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/*  58 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*  59 */     if (isHovering(mouseX, mouseY)) {
/*  60 */       setSettingFromX(mouseX);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHovering(int mouseX, int mouseY) {
/*  67 */     for (Component component : PhobosGui.getClickGui().getComponents()) {
/*  68 */       if (!component.drag)
/*  69 */         continue;  return false;
/*     */     } 
/*  71 */     return (mouseX >= getX() && mouseX <= getX() + getWidth() + 8.0F && mouseY >= getY() && mouseY <= getY() + this.height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  77 */     setHidden(!this.setting.isVisible());
/*     */   }
/*     */ 
/*     */   
/*     */   private void dragSetting(int mouseX, int mouseY) {
/*  82 */     if (isHovering(mouseX, mouseY) && Mouse.isButtonDown(0)) {
/*  83 */       setSettingFromX(mouseX);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/*  90 */     return 14;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSettingFromX(int mouseX) {
/*  95 */     float percent = (mouseX - this.x) / (this.width + 7.4F);
/*  96 */     if (this.setting.getValue() instanceof Double) {
/*  97 */       double result = ((Double)this.setting.getMin()).doubleValue() + (this.difference * percent);
/*  98 */       this.setting.setValue(Double.valueOf(Math.round(10.0D * result) / 10.0D));
/*  99 */     } else if (this.setting.getValue() instanceof Float) {
/* 100 */       float result = ((Float)this.setting.getMin()).floatValue() + this.difference * percent;
/* 101 */       this.setting.setValue(Float.valueOf(Math.round(10.0F * result) / 10.0F));
/* 102 */     } else if (this.setting.getValue() instanceof Integer) {
/* 103 */       this.setting.setValue(Integer.valueOf(((Integer)this.setting.getMin()).intValue() + (int)(this.difference * percent)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float middle() {
/* 109 */     return this.max.floatValue() - this.min.floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private float part() {
/* 114 */     return ((Number)this.setting.getValue()).floatValue() - this.min.floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private float partialMultiplier() {
/* 119 */     return part() / middle();
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\components\items\buttons\Slider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */