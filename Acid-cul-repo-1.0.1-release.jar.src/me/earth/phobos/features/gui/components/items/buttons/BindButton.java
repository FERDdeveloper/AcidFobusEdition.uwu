/*    */ package me.earth.phobos.features.gui.components.items.buttons;
/*    */ 
/*    */ import me.earth.phobos.Phobos;
/*    */ import me.earth.phobos.features.gui.PhobosGui;
/*    */ import me.earth.phobos.features.modules.client.ClickGui;
/*    */ import me.earth.phobos.features.modules.client.HUD;
/*    */ import me.earth.phobos.features.setting.Bind;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.ColorUtil;
/*    */ import me.earth.phobos.util.MathUtil;
/*    */ import me.earth.phobos.util.RenderUtil;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.client.audio.PositionedSoundRecord;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ 
/*    */ public class BindButton
/*    */   extends Button
/*    */ {
/*    */   private final Setting setting;
/*    */   public boolean isListening;
/*    */   
/*    */   public BindButton(Setting setting) {
/* 23 */     super(setting.getName());
/* 24 */     this.setting = setting;
/* 25 */     this.width = 15;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 31 */     if (((Boolean)(ClickGui.getInstance()).rainbowRolling.getValue()).booleanValue()) {
/* 32 */       int color = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/* 33 */       int color1 = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/* 34 */       RenderUtil.drawGradientRect(this.x, this.y, this.width + 7.4F, this.height - 0.5F, getState() ? (!isHovering(mouseX, mouseY) ? ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue() : color) : (!isHovering(mouseX, mouseY) ? 290805077 : -2007673515), getState() ? (!isHovering(mouseX, mouseY) ? ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)))).intValue() : color1) : (!isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
/*    */     } else {
/* 36 */       RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4F, this.y + this.height - 0.5F, getState() ? (!isHovering(mouseX, mouseY) ? Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByName("ClickGui")).hoverAlpha.getValue()).intValue()) : Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByName("ClickGui")).alpha.getValue()).intValue())) : (!isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
/*    */     } 
/* 38 */     if (this.isListening) {
/* 39 */       Phobos.textManager.drawStringWithShadow("Listening...", this.x + 2.3F, this.y - 1.7F - PhobosGui.getClickGui().getTextOffset(), getState() ? -1 : -5592406);
/*    */     } else {
/* 41 */       Phobos.textManager.drawStringWithShadow(this.setting.getName() + " §7" + this.setting.getValue().toString(), this.x + 2.3F, this.y - 1.7F - PhobosGui.getClickGui().getTextOffset(), getState() ? -1 : -5592406);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 48 */     setHidden(!this.setting.isVisible());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 54 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 55 */     if (isHovering(mouseX, mouseY)) {
/* 56 */       mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a(SoundEvents.field_187682_dG, 1.0F));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onKeyTyped(char typedChar, int keyCode) {
/* 63 */     if (this.isListening) {
/* 64 */       Bind bind = new Bind(keyCode);
/* 65 */       if (bind.toString().equalsIgnoreCase("Escape")) {
/*    */         return;
/*    */       }
/* 68 */       if (bind.toString().equalsIgnoreCase("Delete")) {
/* 69 */         bind = new Bind(-1);
/*    */       }
/* 71 */       this.setting.setValue(bind);
/* 72 */       onMouseClick();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 79 */     return 14;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void toggle() {
/* 85 */     this.isListening = !this.isListening;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getState() {
/* 91 */     return !this.isListening;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\components\items\buttons\BindButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */