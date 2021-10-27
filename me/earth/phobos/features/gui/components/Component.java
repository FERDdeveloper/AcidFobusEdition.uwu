/*     */ package me.earth.phobos.features.gui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.Feature;
/*     */ import me.earth.phobos.features.gui.PhobosGui;
/*     */ import me.earth.phobos.features.gui.components.items.Item;
/*     */ import me.earth.phobos.features.gui.components.items.buttons.Button;
/*     */ import me.earth.phobos.features.modules.client.ClickGui;
/*     */ import me.earth.phobos.features.modules.client.Colors;
/*     */ import me.earth.phobos.features.modules.client.HUD;
/*     */ import me.earth.phobos.util.ColorUtil;
/*     */ import me.earth.phobos.util.MathUtil;
/*     */ import me.earth.phobos.util.RenderUtil;
/*     */ import me.earth.phobos.util.Util;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Component
/*     */   extends Feature
/*     */ {
/*  26 */   private final ArrayList<Item> items = new ArrayList<>();
/*     */   
/*     */   public boolean drag;
/*     */   private int x;
/*     */   private int y;
/*     */   private int x2;
/*     */   private int y2;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean open;
/*     */   private boolean hidden = false;
/*     */   
/*     */   public Component(String name, int x, int y, boolean open) {
/*  39 */     super(name);
/*  40 */     this.x = x;
/*  41 */     this.y = y;
/*  42 */     this.width = 88;
/*  43 */     this.height = 18;
/*  44 */     this.open = open;
/*  45 */     setupItems();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupItems() {}
/*     */ 
/*     */   
/*     */   private void drag(int mouseX, int mouseY) {
/*  54 */     if (!this.drag) {
/*     */       return;
/*     */     }
/*  57 */     this.x = this.x2 + mouseX;
/*  58 */     this.y = this.y2 + mouseY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  63 */     drag(mouseX, mouseY);
/*  64 */     float totalItemHeight = this.open ? (getTotalItemHeight() - 2.0F) : 0.0F;
/*  65 */     int color = -7829368;
/*  66 */     if (((Boolean)(ClickGui.getInstance()).devSettings.getValue()).booleanValue()) {
/*  67 */       int i = color = ((Boolean)(ClickGui.getInstance()).colorSync.getValue()).booleanValue() ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toARGB(((Integer)(ClickGui.getInstance()).topRed.getValue()).intValue(), ((Integer)(ClickGui.getInstance()).topGreen.getValue()).intValue(), ((Integer)(ClickGui.getInstance()).topBlue.getValue()).intValue(), ((Integer)(ClickGui.getInstance()).topAlpha.getValue()).intValue());
/*     */     }
/*  69 */     if (((Boolean)(ClickGui.getInstance()).rainbowRolling.getValue()).booleanValue() && ((Boolean)(ClickGui.getInstance()).colorSync.getValue()).booleanValue() && ((Boolean)Colors.INSTANCE.rainbow.getValue()).booleanValue()) {
/*  70 */       RenderUtil.drawGradientRect(this.x, this.y - 1.5F, this.width, (this.height - 4), ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)))).intValue(), ((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp(this.y + this.height - 4, 0, this.renderer.scaledHeight)))).intValue());
/*     */     } else {
/*  72 */       RenderUtil.drawRect(this.x, this.y - 1.5F, (this.x + this.width), (this.y + this.height - 6), color);
/*     */     } 
/*  74 */     if (this.open) {
/*  75 */       RenderUtil.drawRect(this.x, this.y + 12.5F, (this.x + this.width), (this.y + this.height) + totalItemHeight, 1996488704);
/*  76 */       if (((Boolean)(ClickGui.getInstance()).outline.getValue()).booleanValue()) {
/*  77 */         if (((Boolean)(ClickGui.getInstance()).rainbowRolling.getValue()).booleanValue()) {
/*  78 */           GlStateManager.func_179090_x();
/*  79 */           GlStateManager.func_179147_l();
/*  80 */           GlStateManager.func_179118_c();
/*  81 */           GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*  82 */           GlStateManager.func_179103_j(7425);
/*  83 */           GL11.glBegin(1);
/*  84 */           Color currentColor = new Color(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)))).intValue());
/*  85 */           GL11.glColor4f(currentColor.getRed() / 255.0F, currentColor.getGreen() / 255.0F, currentColor.getBlue() / 255.0F, currentColor.getAlpha() / 255.0F);
/*  86 */           GL11.glVertex3f((this.x + this.width), this.y - 1.5F, 0.0F);
/*  87 */           GL11.glVertex3f(this.x, this.y - 1.5F, 0.0F);
/*  88 */           GL11.glVertex3f(this.x, this.y - 1.5F, 0.0F);
/*  89 */           float currentHeight = getHeight() - 1.5F;
/*  90 */           for (Item item : getItems()) {
/*  91 */             currentColor = new Color(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)(this.y + (currentHeight += item.getHeight() + 1.5F)), 0, this.renderer.scaledHeight)))).intValue());
/*  92 */             GL11.glColor4f(currentColor.getRed() / 255.0F, currentColor.getGreen() / 255.0F, currentColor.getBlue() / 255.0F, currentColor.getAlpha() / 255.0F);
/*  93 */             GL11.glVertex3f(this.x, this.y + currentHeight, 0.0F);
/*  94 */             GL11.glVertex3f(this.x, this.y + currentHeight, 0.0F);
/*     */           } 
/*  96 */           currentColor = new Color(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)((this.y + this.height) + totalItemHeight), 0, this.renderer.scaledHeight)))).intValue());
/*  97 */           GL11.glColor4f(currentColor.getRed() / 255.0F, currentColor.getGreen() / 255.0F, currentColor.getBlue() / 255.0F, currentColor.getAlpha() / 255.0F);
/*  98 */           GL11.glVertex3f((this.x + this.width), (this.y + this.height) + totalItemHeight, 0.0F);
/*  99 */           GL11.glVertex3f((this.x + this.width), (this.y + this.height) + totalItemHeight, 0.0F);
/* 100 */           for (Item item : getItems()) {
/* 101 */             currentColor = new Color(((Integer)(HUD.getInstance()).colorMap.get(Integer.valueOf(MathUtil.clamp((int)(this.y + (currentHeight -= item.getHeight() + 1.5F)), 0, this.renderer.scaledHeight)))).intValue());
/* 102 */             GL11.glColor4f(currentColor.getRed() / 255.0F, currentColor.getGreen() / 255.0F, currentColor.getBlue() / 255.0F, currentColor.getAlpha() / 255.0F);
/* 103 */             GL11.glVertex3f((this.x + this.width), this.y + currentHeight, 0.0F);
/* 104 */             GL11.glVertex3f((this.x + this.width), this.y + currentHeight, 0.0F);
/*     */           } 
/* 106 */           GL11.glVertex3f((this.x + this.width), this.y, 0.0F);
/* 107 */           GL11.glEnd();
/* 108 */           GlStateManager.func_179103_j(7424);
/* 109 */           GlStateManager.func_179084_k();
/* 110 */           GlStateManager.func_179141_d();
/* 111 */           GlStateManager.func_179098_w();
/*     */         } else {
/* 113 */           GlStateManager.func_179090_x();
/* 114 */           GlStateManager.func_179147_l();
/* 115 */           GlStateManager.func_179118_c();
/* 116 */           GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 117 */           GlStateManager.func_179103_j(7425);
/* 118 */           GL11.glBegin(2);
/* 119 */           Color outlineColor = ((Boolean)(ClickGui.getInstance()).colorSync.getValue()).booleanValue() ? new Color(Colors.INSTANCE.getCurrentColorHex()) : new Color(Phobos.colorManager.getColorAsIntFullAlpha());
/* 120 */           GL11.glColor4f(outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue(), outlineColor.getAlpha());
/* 121 */           GL11.glVertex3f(this.x, this.y - 1.5F, 0.0F);
/* 122 */           GL11.glVertex3f((this.x + this.width), this.y - 1.5F, 0.0F);
/* 123 */           GL11.glVertex3f((this.x + this.width), (this.y + this.height) + totalItemHeight, 0.0F);
/* 124 */           GL11.glVertex3f(this.x, (this.y + this.height) + totalItemHeight, 0.0F);
/* 125 */           GL11.glEnd();
/* 126 */           GlStateManager.func_179103_j(7424);
/* 127 */           GlStateManager.func_179084_k();
/* 128 */           GlStateManager.func_179141_d();
/* 129 */           GlStateManager.func_179098_w();
/*     */         } 
/*     */       }
/*     */     } 
/* 133 */     Phobos.textManager.drawStringWithShadow(getName(), this.x + 3.0F, this.y - 4.0F - PhobosGui.getClickGui().getTextOffset(), -1);
/* 134 */     if (this.open) {
/* 135 */       float y = (getY() + getHeight()) - 3.0F;
/* 136 */       for (Item item : getItems()) {
/* 137 */         if (item.isHidden())
/* 138 */           continue;  item.setLocation(this.x + 2.0F, y);
/* 139 */         item.setWidth(getWidth() - 4);
/* 140 */         item.drawScreen(mouseX, mouseY, partialTicks);
/* 141 */         y += item.getHeight() + 1.5F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 148 */     if (mouseButton == 0 && isHovering(mouseX, mouseY)) {
/* 149 */       this.x2 = this.x - mouseX;
/* 150 */       this.y2 = this.y - mouseY;
/* 151 */       PhobosGui.getClickGui().getComponents().forEach(component -> {
/*     */             if (component.drag) {
/*     */               component.drag = false;
/*     */             }
/*     */           });
/* 156 */       this.drag = true;
/*     */       return;
/*     */     } 
/* 159 */     if (mouseButton == 1 && isHovering(mouseX, mouseY)) {
/* 160 */       this.open = !this.open;
/* 161 */       Util.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a(SoundEvents.field_187682_dG, 1.0F));
/*     */       return;
/*     */     } 
/* 164 */     if (!this.open) {
/*     */       return;
/*     */     }
/* 167 */     getItems().forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
/* 172 */     if (releaseButton == 0) {
/* 173 */       this.drag = false;
/*     */     }
/* 175 */     if (!this.open) {
/*     */       return;
/*     */     }
/* 178 */     getItems().forEach(item -> item.mouseReleased(mouseX, mouseY, releaseButton));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onKeyTyped(char typedChar, int keyCode) {
/* 183 */     if (!this.open) {
/*     */       return;
/*     */     }
/* 186 */     getItems().forEach(item -> item.onKeyTyped(typedChar, keyCode));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addButton(Button button) {
/* 191 */     this.items.add(button);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 196 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setX(int x) {
/* 201 */     this.x = x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 206 */     return this.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setY(int y) {
/* 211 */     this.y = y;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 216 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWidth(int width) {
/* 221 */     this.width = width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 226 */     return this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeight(int height) {
/* 231 */     this.height = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHidden() {
/* 236 */     return this.hidden;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHidden(boolean hidden) {
/* 241 */     this.hidden = hidden;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 246 */     return this.open;
/*     */   }
/*     */ 
/*     */   
/*     */   public final ArrayList<Item> getItems() {
/* 251 */     return this.items;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isHovering(int mouseX, int mouseY) {
/* 256 */     return (mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight() - (this.open ? 2 : 0));
/*     */   }
/*     */ 
/*     */   
/*     */   private float getTotalItemHeight() {
/* 261 */     float height = 0.0F;
/* 262 */     for (Item item : getItems()) {
/* 263 */       height += item.getHeight() + 1.5F;
/*     */     }
/* 265 */     return height;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\components\Component.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */