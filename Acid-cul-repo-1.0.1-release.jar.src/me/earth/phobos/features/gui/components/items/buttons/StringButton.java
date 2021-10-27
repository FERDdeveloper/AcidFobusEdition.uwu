/*     */ package me.earth.phobos.features.gui.components.items.buttons;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.gui.PhobosGui;
/*     */ import me.earth.phobos.features.modules.client.ClickGui;
/*     */ import me.earth.phobos.features.modules.client.HUD;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.ColorUtil;
/*     */ import me.earth.phobos.util.MathUtil;
/*     */ import me.earth.phobos.util.RenderUtil;
/*     */ import me.earth.phobos.util.Util;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class StringButton
/*     */   extends Button
/*     */ {
/*     */   private final Setting setting;
/*     */   public boolean isListening;
/*  25 */   private CurrentString currentString = new CurrentString("");
/*     */ 
/*     */   
/*     */   public StringButton(Setting setting) {
/*  29 */     super(setting.getName());
/*  30 */     this.setting = setting;
/*  31 */     this.width = 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String removeLastChar(String str) {
/*  36 */     String output = "";
/*  37 */     if (str != null && str.length() > 0) {
/*  38 */       output = str.substring(0, str.length() - 1);
/*     */     }
/*  40 */     return output;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  46 */     if (((Boolean)(ClickGui.getInstance()).rainbowRolling.getValue()).booleanValue()) {
/*  47 */       int color = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/*  48 */       int color1 = ColorUtil.changeAlpha(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue());
/*  49 */       RenderUtil.drawGradientRect(this.x, this.y, this.width + 7.4F, this.height - 0.5F, getState() ? (!isHovering(mouseX, mouseY) ? ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)))).intValue() : color) : (!isHovering(mouseX, mouseY) ? 290805077 : -2007673515), getState() ? (!isHovering(mouseX, mouseY) ? ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)))).intValue() : color1) : (!isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
/*     */     } else {
/*  51 */       RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4F, this.y + this.height - 0.5F, getState() ? (!isHovering(mouseX, mouseY) ? Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()).intValue()) : Phobos.colorManager.getColorWithAlpha(((Integer)((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).alpha.getValue()).intValue())) : (!isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
/*     */     } 
/*  53 */     if (this.isListening) {
/*  54 */       Phobos.textManager.drawStringWithShadow(this.currentString.getString() + Phobos.textManager.getIdleSign(), this.x + 2.3F, this.y - 1.7F - PhobosGui.getClickGui().getTextOffset(), getState() ? -1 : -5592406);
/*     */     } else {
/*  56 */       Phobos.textManager.drawStringWithShadow((this.setting.shouldRenderName() ? (this.setting.getName() + " §7") : "") + this.setting.getValue(), this.x + 2.3F, this.y - 1.7F - PhobosGui.getClickGui().getTextOffset(), getState() ? -1 : -5592406);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/*  63 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*  64 */     if (isHovering(mouseX, mouseY)) {
/*  65 */       Util.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a(SoundEvents.field_187682_dG, 1.0F));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onKeyTyped(char typedChar, int keyCode) {
/*  72 */     if (this.isListening) {
/*  73 */       if (keyCode == 1) {
/*     */         return;
/*     */       }
/*  76 */       if (keyCode == 28) {
/*  77 */         enterString();
/*  78 */       } else if (keyCode == 14) {
/*  79 */         setString(removeLastChar(this.currentString.getString()));
/*  80 */       } else if (keyCode == 47 && (Keyboard.isKeyDown(157) || Keyboard.isKeyDown(29))) {
/*     */         try {
/*  82 */           setString(this.currentString.getString() + Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
/*  83 */         } catch (Exception e) {
/*  84 */           e.printStackTrace();
/*     */         } 
/*  86 */       } else if (ChatAllowedCharacters.func_71566_a(typedChar)) {
/*  87 */         setString(this.currentString.getString() + typedChar);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  95 */     setHidden(!this.setting.isVisible());
/*     */   }
/*     */ 
/*     */   
/*     */   private void enterString() {
/* 100 */     if (this.currentString.getString().isEmpty()) {
/* 101 */       this.setting.setValue(this.setting.getDefaultValue());
/*     */     } else {
/* 103 */       this.setting.setValue(this.currentString.getString());
/*     */     } 
/* 105 */     setString("");
/* 106 */     onMouseClick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 112 */     return 14;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggle() {
/* 118 */     this.isListening = !this.isListening;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getState() {
/* 124 */     return !this.isListening;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setString(String newString) {
/* 129 */     this.currentString = new CurrentString(newString);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class CurrentString
/*     */   {
/*     */     private final String string;
/*     */     
/*     */     public CurrentString(String string) {
/* 138 */       this.string = string;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString() {
/* 143 */       return this.string;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\components\items\buttons\StringButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */