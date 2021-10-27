/*     */ package me.earth.phobos.features.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.gui.components.Component;
/*     */ import me.earth.phobos.features.gui.components.items.Item;
/*     */ import me.earth.phobos.features.gui.components.items.buttons.Button;
/*     */ import me.earth.phobos.features.gui.components.items.buttons.ModuleButton;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PhobosGui
/*     */   extends GuiScreen
/*     */ {
/*     */   private static PhobosGui PhobosGui;
/*  20 */   private static PhobosGui INSTANCE = new PhobosGui();
/*     */ 
/*     */   
/*  23 */   private final ArrayList<Component> components = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public PhobosGui() {
/*  27 */     setInstance();
/*  28 */     load();
/*     */   }
/*     */   
/*     */   public static PhobosGui getInstance() {
/*  32 */     if (INSTANCE == null) {
/*  33 */       INSTANCE = new PhobosGui();
/*     */     }
/*  35 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public static PhobosGui getClickGui() {
/*  39 */     return getInstance();
/*     */   }
/*     */   
/*     */   private void setInstance() {
/*  43 */     INSTANCE = this;
/*     */   }
/*     */   
/*     */   private void load() {
/*  47 */     int x = -74;
/*  48 */     for (Module.Category category : Phobos.moduleManager.getCategories()) {
/*  49 */       x += 90; this.components.add(new Component(category.getName(), x, 4, true)
/*     */           {
/*     */             
/*     */             public void setupItems()
/*     */             {
/*  54 */               Phobos.moduleManager.getModulesByCategory(category).forEach(module -> {
/*     */                     if (!module.hidden) {
/*     */                       addButton((Button)new ModuleButton(module));
/*     */                     }
/*     */                   });
/*     */             }
/*     */           });
/*     */     } 
/*  62 */     this.components.forEach(components -> components.getItems().sort(()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateModule(Module module) {
/*  67 */     for (Component component : this.components) {
/*  68 */       for (Item item : component.getItems()) {
/*  69 */         if (!(item instanceof ModuleButton))
/*  70 */           continue;  ModuleButton button = (ModuleButton)item;
/*  71 */         Module mod = button.getModule();
/*  72 */         if (module == null || !module.equals(mod))
/*  73 */           continue;  button.initSettings();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*  80 */     checkMouseWheel();
/*  81 */     func_146276_q_();
/*  82 */     this.components.forEach(components -> components.drawScreen(mouseX, mouseY, partialTicks));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73864_a(int mouseX, int mouseY, int clickedButton) {
/*  87 */     this.components.forEach(components -> components.mouseClicked(mouseX, mouseY, clickedButton));
/*     */   }
/*     */   
/*     */   public void func_146286_b(int mouseX, int mouseY, int releaseButton) {
/*  91 */     this.components.forEach(components -> components.mouseReleased(mouseX, mouseY, releaseButton));
/*     */   }
/*     */   
/*     */   public boolean func_73868_f() {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   public final ArrayList<Component> getComponents() {
/*  99 */     return this.components;
/*     */   }
/*     */   
/*     */   public void checkMouseWheel() {
/* 103 */     int dWheel = Mouse.getDWheel();
/* 104 */     if (dWheel < 0) {
/* 105 */       this.components.forEach(component -> component.setY(component.getY() - 10));
/* 106 */     } else if (dWheel > 0) {
/* 107 */       this.components.forEach(component -> component.setY(component.getY() + 10));
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getTextOffset() {
/* 112 */     return -6;
/*     */   }
/*     */   
/*     */   public Component getComponentByName(String name) {
/* 116 */     for (Component component : this.components) {
/* 117 */       if (!component.getName().equalsIgnoreCase(name))
/* 118 */         continue;  return component;
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   public void func_73869_a(char typedChar, int keyCode) throws IOException {
/* 124 */     super.func_73869_a(typedChar, keyCode);
/* 125 */     this.components.forEach(component -> component.onKeyTyped(typedChar, keyCode));
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\PhobosGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */