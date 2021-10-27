/*     */ package me.earth.phobos.features.modules.client;
/*     */ 
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.event.events.ClientEvent;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.gui.PhobosGui;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.Util;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class ClickGui
/*     */   extends Module {
/*  17 */   private static ClickGui INSTANCE = new ClickGui();
/*  18 */   public Setting<Boolean> colorSync = register(new Setting("Speed", "Sync", 0.0D, 0.0D, Boolean.valueOf(false), 0));
/*  19 */   public Setting<Boolean> outline = register(new Setting("Speed", "Outline", 0.0D, 0.0D, Boolean.valueOf(false), 0));
/*  20 */   public Setting<Boolean> gear = register(new Setting("gear", Boolean.valueOf(true), "draws gear :^]"));
/*  21 */   public Setting<Boolean> blurEffect = register(new Setting("Speed", "Blur", 0.0D, 0.0D, Boolean.valueOf(true), 0));
/*  22 */   public Setting<Boolean> rainbowRolling = register(new Setting("RollingRainbow", Boolean.valueOf(false), v -> (((Boolean)this.colorSync.getValue()).booleanValue() && ((Boolean)Colors.INSTANCE.rainbow.getValue()).booleanValue())));
/*  23 */   public Setting<String> prefix = register((new Setting("Speed", "Prefix", 0.0D, 0.0D, ".", 0)).setRenderName(true));
/*  24 */   public Setting<Integer> red = register(new Setting("Red", Integer.valueOf(170), Integer.valueOf(0), Integer.valueOf(255)));
/*  25 */   public Setting<Integer> green = register(new Setting("Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
/*  26 */   public Setting<Integer> blue = register(new Setting("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/*  27 */   public Setting<Integer> hoverAlpha = register(new Setting("Alpha", Integer.valueOf(180), Integer.valueOf(0), Integer.valueOf(255)));
/*  28 */   public Setting<Integer> alpha = register(new Setting("HoverAlpha", Integer.valueOf(240), Integer.valueOf(0), Integer.valueOf(255)));
/*  29 */   public Setting<Boolean> customFov = register(new Setting("Speed", "CustomFov", 0.0D, 0.0D, Boolean.valueOf(false), 0));
/*  30 */   public Setting<Float> fov = register(new Setting("Fov", Float.valueOf(150.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F), v -> ((Boolean)this.customFov.getValue()).booleanValue()));
/*  31 */   public Setting<Boolean> openCloseChange = register(new Setting("Speed", "Open/Close", 0.0D, 0.0D, Boolean.valueOf(false), 0));
/*  32 */   public Setting<String> open = register((new Setting("Open:", "", v -> ((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));
/*  33 */   public Setting<String> close = register((new Setting("Close:", "", v -> ((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));
/*  34 */   public Setting<String> moduleButton = register((new Setting("Buttons:", "", v -> !((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));
/*  35 */   public Setting<Boolean> devSettings = register(new Setting("Speed", "TopSetting", 0.0D, 0.0D, Boolean.valueOf(true), 0));
/*  36 */   public Setting<Integer> topRed = register(new Setting("TopRed", Integer.valueOf(80), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
/*  37 */   public Setting<Integer> topGreen = register(new Setting("TopGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
/*  38 */   public Setting<Integer> topBlue = register(new Setting("TopBlue", Integer.valueOf(185), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
/*  39 */   public Setting<Integer> topAlpha = register(new Setting("TopAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
/*     */ 
/*     */   
/*     */   public ClickGui() {
/*  43 */     super("ClickGui", "Opens the ClickGui", Module.Category.CLIENT, true, false, false);
/*  44 */     setInstance();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ClickGui getInstance() {
/*  49 */     if (INSTANCE == null) {
/*  50 */       INSTANCE = new ClickGui();
/*     */     }
/*  52 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInstance() {
/*  57 */     INSTANCE = this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  63 */     if (((Boolean)this.customFov.getValue()).booleanValue()) {
/*  64 */       mc.field_71474_y.func_74304_a(GameSettings.Options.FOV, ((Float)this.fov.getValue()).floatValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onSettingChange(ClientEvent event) {
/*  71 */     if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
/*  72 */       if (event.getSetting().equals(this.prefix)) {
/*  73 */         Phobos.commandManager.setPrefix((String)this.prefix.getPlannedValue());
/*  74 */         Command.sendMessage("Prefix set to §a" + Phobos.commandManager.getPrefix());
/*     */       } 
/*  76 */       Phobos.colorManager.setColor(((Integer)this.red.getPlannedValue()).intValue(), ((Integer)this.green.getPlannedValue()).intValue(), ((Integer)this.blue.getPlannedValue()).intValue(), ((Integer)this.hoverAlpha.getPlannedValue()).intValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  83 */     Util.mc.func_147108_a((GuiScreen)new PhobosGui());
/*  84 */     if (((Boolean)this.blurEffect.getValue()).booleanValue()) {
/*  85 */       mc.field_71460_t.func_175069_a(new ResourceLocation("-"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLoad() {
/*  92 */     if (((Boolean)this.colorSync.getValue()).booleanValue()) {
/*  93 */       Phobos.colorManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), ((Integer)this.hoverAlpha.getValue()).intValue());
/*     */     } else {
/*  95 */       Phobos.colorManager.setColor(((Integer)this.red.getValue()).intValue(), ((Integer)this.green.getValue()).intValue(), ((Integer)this.blue.getValue()).intValue(), ((Integer)this.hoverAlpha.getValue()).intValue());
/*     */     } 
/*  97 */     Phobos.commandManager.setPrefix((String)this.prefix.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTick() {
/* 103 */     if (!(mc.field_71462_r instanceof PhobosGui)) {
/* 104 */       disable();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 111 */     if (mc.field_71462_r instanceof PhobosGui)
/* 112 */       Util.mc.func_147108_a(null); 
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\ClickGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */