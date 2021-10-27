/*     */ package me.earth.phobos.features.gui.components.items.buttons;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.gui.PhobosGui;
/*     */ import me.earth.phobos.features.gui.components.items.Item;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.modules.client.ClickGui;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.Util;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class ModuleButton
/*     */   extends Button
/*     */ {
/*     */   private final Module module;
/*  23 */   private final ResourceLocation logo = new ResourceLocation("textures/gear.png");
/*  24 */   private List<Item> items = new ArrayList<>();
/*     */   
/*     */   private boolean subOpen;
/*     */   
/*     */   public ModuleButton(Module module) {
/*  29 */     super(module.getName());
/*  30 */     this.module = module;
/*  31 */     initSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawCompleteImage(float posX, float posY, int width, int height) {
/*  36 */     GL11.glPushMatrix();
/*  37 */     GL11.glTranslatef(posX, posY, 0.0F);
/*  38 */     GL11.glBegin(7);
/*  39 */     GL11.glTexCoord2f(0.0F, 0.0F);
/*  40 */     GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/*  41 */     GL11.glTexCoord2f(0.0F, 1.0F);
/*  42 */     GL11.glVertex3f(0.0F, height, 0.0F);
/*  43 */     GL11.glTexCoord2f(1.0F, 1.0F);
/*  44 */     GL11.glVertex3f(width, height, 0.0F);
/*  45 */     GL11.glTexCoord2f(1.0F, 0.0F);
/*  46 */     GL11.glVertex3f(width, 0.0F, 0.0F);
/*  47 */     GL11.glEnd();
/*  48 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initSettings() {
/*  53 */     ArrayList<Item> newItems = new ArrayList<>();
/*  54 */     if (!this.module.getSettings().isEmpty()) {
/*  55 */       for (Setting setting : this.module.getSettings()) {
/*  56 */         if (setting.getValue() instanceof Boolean && !setting.getName().equals("Enabled")) {
/*  57 */           newItems.add(new BooleanButton(setting));
/*     */         }
/*  59 */         if (setting.getValue() instanceof me.earth.phobos.features.setting.Bind && !this.module.getName().equalsIgnoreCase("Hud")) {
/*  60 */           newItems.add(new BindButton(setting));
/*     */         }
/*  62 */         if (setting.getValue() instanceof String || setting.getValue() instanceof Character) {
/*  63 */           newItems.add(new StringButton(setting));
/*     */         }
/*  65 */         if (setting.isNumberSetting()) {
/*  66 */           if (setting.hasRestriction()) {
/*  67 */             newItems.add(new Slider(setting));
/*     */             continue;
/*     */           } 
/*  70 */           newItems.add(new UnlimitedSlider(setting));
/*     */         } 
/*  72 */         if (!setting.isEnumSetting())
/*  73 */           continue;  newItems.add(new EnumButton(setting));
/*     */       } 
/*     */     }
/*  76 */     this.items = newItems;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  82 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*  83 */     if (!this.items.isEmpty()) {
/*  84 */       ClickGui gui = (ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class);
/*  85 */       Phobos.textManager.drawStringWithShadow(((Boolean)gui.openCloseChange.getValue()).booleanValue() ? (this.subOpen ? (String)gui.close.getValue() : (String)gui.open.getValue()) : (String)gui.moduleButton.getValue(), this.x - 1.5F + this.width - 7.4F, this.y - 2.0F - PhobosGui.getClickGui().getTextOffset(), -1);
/*     */     } 
/*  87 */     if (((Boolean)(ClickGui.getInstance()).gear.getValue()).booleanValue()) {
/*  88 */       mc.func_110434_K().func_110577_a(this.logo);
/*  89 */       drawCompleteImage(this.x - 1.5F + this.width - 7.4F, this.y - 2.2F - PhobosGui.getClickGui().getTextOffset(), 9, 9);
/*     */     } 
/*  91 */     if (this.subOpen) {
/*  92 */       float height = 1.0F;
/*  93 */       for (Item item : this.items) {
/*  94 */         if (!item.isHidden()) {
/*  95 */           item.setLocation(this.x + 1.0F, this.y + (height += 15.0F));
/*  96 */           item.setHeight(15);
/*  97 */           item.setWidth(this.width - 9);
/*  98 */           item.drawScreen(mouseX, mouseY, partialTicks);
/*     */         } 
/* 100 */         item.update();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 108 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 109 */     if (!this.items.isEmpty()) {
/* 110 */       if (mouseButton == 1 && isHovering(mouseX, mouseY)) {
/* 111 */         this.subOpen = !this.subOpen;
/* 112 */         Util.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a(SoundEvents.field_187682_dG, 1.0F));
/*     */       } 
/* 114 */       if (this.subOpen) {
/* 115 */         for (Item item : this.items) {
/* 116 */           if (item.isHidden())
/* 117 */             continue;  item.mouseClicked(mouseX, mouseY, mouseButton);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onKeyTyped(char typedChar, int keyCode) {
/* 126 */     super.onKeyTyped(typedChar, keyCode);
/* 127 */     if (!this.items.isEmpty() && this.subOpen) {
/* 128 */       for (Item item : this.items) {
/* 129 */         if (item.isHidden())
/* 130 */           continue;  item.onKeyTyped(typedChar, keyCode);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 138 */     if (this.subOpen) {
/* 139 */       int height = 14;
/* 140 */       for (Item item : this.items) {
/* 141 */         if (item.isHidden())
/* 142 */           continue;  height += item.getHeight() + 1;
/*     */       } 
/* 144 */       return height + 2;
/*     */     } 
/* 146 */     return 14;
/*     */   }
/*     */ 
/*     */   
/*     */   public Module getModule() {
/* 151 */     return this.module;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggle() {
/* 157 */     this.module.toggle();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getState() {
/* 163 */     return this.module.isEnabled();
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\components\items\buttons\ModuleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */