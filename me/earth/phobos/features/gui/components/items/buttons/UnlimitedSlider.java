/*    */ package me.earth.phobos.features.gui.components.items.buttons;
/*    */ 
/*    */ import me.earth.phobos.Phobos;
/*    */ import me.earth.phobos.features.gui.PhobosGui;
/*    */ import me.earth.phobos.features.modules.client.ClickGui;
/*    */ import me.earth.phobos.features.modules.client.HUD;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.ColorUtil;
/*    */ import me.earth.phobos.util.MathUtil;
/*    */ import me.earth.phobos.util.RenderUtil;
/*    */ import me.earth.phobos.util.Util;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.client.audio.PositionedSoundRecord;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ 
/*    */ public class UnlimitedSlider
/*    */   extends Button
/*    */ {
/*    */   public Setting setting;
/*    */   
/*    */   public UnlimitedSlider(Setting setting) {
/* 22 */     super(setting.getName());
/* 23 */     this.setting = setting;
/* 24 */     this.width = 15;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 30 */     if (((Boolean)(ClickGui.getInstance()).rainbowRolling.getValue()).booleanValue()) {
/* 31 */       int color = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/* 32 */       int color1 = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/* 33 */       RenderUtil.drawGradientRect((int)this.x, (int)this.y, this.width + 7.4F, this.height, color, color1);
/*    */     } else {
/* 35 */       RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4F, this.y + this.height - 0.5F, !isHovering(mouseX, mouseY) ? Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue()) : Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).alpha.getValue()).intValue()));
/*    */     } 
/* 37 */     Phobos.textManager.drawStringWithShadow(" - " + this.setting.getName() + " §7" + this.setting.getValue() + "§r +", this.x + 2.3F, this.y - 1.7F - PhobosGui.getClickGui().getTextOffset(), getState() ? -1 : -5592406);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 43 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 44 */     if (isHovering(mouseX, mouseY)) {
/* 45 */       Util.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a(SoundEvents.field_187682_dG, 1.0F));
/* 46 */       if (isRight(mouseX)) {
/* 47 */         if (this.setting.getValue() instanceof Double) {
/* 48 */           this.setting.setValue(Double.valueOf(((Double)this.setting.getValue()).doubleValue() + 1.0D));
/* 49 */         } else if (this.setting.getValue() instanceof Float) {
/* 50 */           this.setting.setValue(Float.valueOf(((Float)this.setting.getValue()).floatValue() + 1.0F));
/* 51 */         } else if (this.setting.getValue() instanceof Integer) {
/* 52 */           this.setting.setValue(Integer.valueOf(((Integer)this.setting.getValue()).intValue() + 1));
/*    */         } 
/* 54 */       } else if (this.setting.getValue() instanceof Double) {
/* 55 */         this.setting.setValue(Double.valueOf(((Double)this.setting.getValue()).doubleValue() - 1.0D));
/* 56 */       } else if (this.setting.getValue() instanceof Float) {
/* 57 */         this.setting.setValue(Float.valueOf(((Float)this.setting.getValue()).floatValue() - 1.0F));
/* 58 */       } else if (this.setting.getValue() instanceof Integer) {
/* 59 */         this.setting.setValue(Integer.valueOf(((Integer)this.setting.getValue()).intValue() - 1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 67 */     setHidden(!this.setting.isVisible());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 73 */     return 14;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void toggle() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getState() {
/* 84 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRight(int x) {
/* 89 */     return (x > this.x + (this.width + 7.4F) / 2.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\components\items\buttons\UnlimitedSlider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */