/*     */ package me.earth.phobos.features.modules.player;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.SystemTray;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.TrayIcon;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityNotifier
/*     */   extends Module
/*     */ {
/*  20 */   private final Set<Entity> ghasts = new HashSet<>();
/*  21 */   private final Set<Entity> donkeys = new HashSet<>();
/*  22 */   private final Set<Entity> mules = new HashSet<>();
/*  23 */   private final Set<Entity> llamas = new HashSet<>();
/*  24 */   private final Image image = Toolkit.getDefaultToolkit().getImage("resources/background.png");
/*  25 */   private final TrayIcon icon = new TrayIcon(this.image, "Acid");
/*  26 */   public Setting<Boolean> Chat = register(new Setting("Chat", Boolean.valueOf(true)));
/*  27 */   public Setting<Boolean> Sound = register(new Setting("Sound", Boolean.valueOf(true)));
/*  28 */   public Setting<Boolean> Desktop = register(new Setting("DesktopNotifs", Boolean.valueOf(true)));
/*  29 */   public Setting<Boolean> Ghasts = register(new Setting("Ghasts", Boolean.valueOf(true)));
/*  30 */   public Setting<Boolean> Donkeys = register(new Setting("Donkeys", Boolean.valueOf(true)));
/*  31 */   public Setting<Boolean> Mules = register(new Setting("Mules", Boolean.valueOf(true)));
/*  32 */   public Setting<Boolean> Llamas = register(new Setting("Llamas", Boolean.valueOf(true)));
/*     */ 
/*     */   
/*     */   public EntityNotifier() {
/*  36 */     super("EntityNotifier", "Helps you find certain things", Module.Category.PLAYER, true, false, false);
/*  37 */     this.icon.setImageAutoSize(true);
/*     */     try {
/*  39 */       SystemTray tray = SystemTray.getSystemTray();
/*  40 */       tray.add(this.icon);
/*  41 */     } catch (Exception exception) {
/*  42 */       exception.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  49 */     this.ghasts.clear();
/*  50 */     this.donkeys.clear();
/*  51 */     this.mules.clear();
/*  52 */     this.llamas.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  58 */     if (((Boolean)this.Ghasts.getValue()).booleanValue()) {
/*  59 */       for (Entity entity : mc.field_71441_e.func_72910_y()) {
/*  60 */         if (!(entity instanceof net.minecraft.entity.monster.EntityGhast) || this.ghasts.contains(entity))
/*  61 */           continue;  if (((Boolean)this.Chat.getValue()).booleanValue()) {
/*  62 */           Command.sendMessage("Ghast Detected at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.");
/*     */         }
/*  64 */         this.ghasts.add(entity);
/*  65 */         if (!((Boolean)this.Desktop.getValue()).booleanValue())
/*  66 */           continue;  this.icon.displayMessage("Acid", "I found a ghast at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.", TrayIcon.MessageType.INFO);
/*  67 */         if (!((Boolean)this.Sound.getValue()).booleanValue())
/*  68 */           continue;  mc.field_71439_g.func_184185_a(SoundEvents.field_187680_c, 1.0F, 1.0F);
/*     */       } 
/*     */     }
/*  71 */     if (((Boolean)this.Donkeys.getValue()).booleanValue()) {
/*  72 */       for (Entity entity : mc.field_71441_e.func_72910_y()) {
/*  73 */         if (!(entity instanceof net.minecraft.entity.passive.EntityDonkey) || this.donkeys.contains(entity))
/*  74 */           continue;  if (((Boolean)this.Chat.getValue()).booleanValue()) {
/*  75 */           Command.sendMessage("Donkey Detected at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.");
/*     */         }
/*  77 */         this.donkeys.add(entity);
/*  78 */         if (!((Boolean)this.Desktop.getValue()).booleanValue())
/*  79 */           continue;  this.icon.displayMessage("Acid", "I found a donkey at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.", TrayIcon.MessageType.INFO);
/*  80 */         if (!((Boolean)this.Sound.getValue()).booleanValue())
/*  81 */           continue;  mc.field_71439_g.func_184185_a(SoundEvents.field_187680_c, 1.0F, 1.0F);
/*     */       } 
/*     */     }
/*  84 */     if (((Boolean)this.Mules.getValue()).booleanValue()) {
/*  85 */       for (Entity entity : mc.field_71441_e.func_72910_y()) {
/*  86 */         if (!(entity instanceof net.minecraft.entity.passive.EntityMule) || this.mules.contains(entity))
/*  87 */           continue;  if (((Boolean)this.Chat.getValue()).booleanValue()) {
/*  88 */           Command.sendMessage("Mule Detected at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.");
/*     */         }
/*  90 */         this.mules.add(entity);
/*  91 */         if (!((Boolean)this.Desktop.getValue()).booleanValue())
/*  92 */           continue;  this.icon.displayMessage("Acid", "I found a mule at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.", TrayIcon.MessageType.INFO);
/*  93 */         if (!((Boolean)this.Sound.getValue()).booleanValue())
/*  94 */           continue;  mc.field_71439_g.func_184185_a(SoundEvents.field_187680_c, 1.0F, 1.0F);
/*     */       } 
/*     */     }
/*  97 */     if (((Boolean)this.Llamas.getValue()).booleanValue())
/*  98 */       for (Entity entity : mc.field_71441_e.func_72910_y()) {
/*  99 */         if (!(entity instanceof net.minecraft.entity.passive.EntityLlama) || this.llamas.contains(entity))
/* 100 */           continue;  if (((Boolean)this.Chat.getValue()).booleanValue()) {
/* 101 */           Command.sendMessage("Llama Detected at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.");
/*     */         }
/* 103 */         this.llamas.add(entity);
/* 104 */         if (!((Boolean)this.Desktop.getValue()).booleanValue())
/* 105 */           continue;  this.icon.displayMessage("Acid", "I found a llama at: " + Math.round(entity.func_180425_c().func_177958_n()) + "x, " + Math.round(entity.func_180425_c().func_177956_o()) + "y, " + Math.round(entity.func_180425_c().func_177952_p()) + "z.", TrayIcon.MessageType.INFO);
/* 106 */         if (!((Boolean)this.Sound.getValue()).booleanValue())
/* 107 */           continue;  mc.field_71439_g.func_184185_a(SoundEvents.field_187680_c, 1.0F, 1.0F);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\EntityNotifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */