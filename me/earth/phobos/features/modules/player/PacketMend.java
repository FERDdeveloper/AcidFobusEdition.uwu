/*     */ package me.earth.phobos.features.modules.player;
/*     */ 
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketHeldItemChange;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ 
/*     */ public class PacketMend
/*     */   extends Module {
/*  19 */   public Setting<Boolean> sneakOnly = register(new Setting("SneakOnly", Boolean.valueOf(false)));
/*  20 */   public Setting<Boolean> noEntityCollision = register(new Setting("No Collision", Boolean.valueOf(true)));
/*  21 */   public Setting<Boolean> silentSwitch = register(new Setting("Silent Switch", Boolean.valueOf(true)));
/*  22 */   public Setting<Integer> minDamage = register(new Setting("Min Damage", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(100)));
/*  23 */   public Setting<Integer> maxHeal = register(new Setting("Repair To", Integer.valueOf(90), Integer.valueOf(1), Integer.valueOf(100)));
/*  24 */   public Setting<Boolean> predict = register(new Setting("Predict", Boolean.valueOf(false)));
/*  25 */   public Setting<Boolean> DisableWhenDone = register(new Setting("AutoDisable", Boolean.valueOf(true)));
/*  26 */   public Setting<Boolean> rotate = register(new Setting("Rotate", Boolean.valueOf(true)));
/*     */   
/*     */   char toMend;
/*     */   
/*     */   public PacketMend() {
/*  31 */     super("PacketMend", "Automatically mends cool.", Module.Category.PLAYER, true, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  36 */     if (mc.field_71439_g == null || mc.field_71441_e == null) {
/*     */       return;
/*     */     }
/*     */     
/*  40 */     int sumOfDamage = 0;
/*     */     
/*  42 */     NonNullList<ItemStack> nonNullList = mc.field_71439_g.field_71071_by.field_70460_b;
/*  43 */     for (int i = 0; i < nonNullList.size(); i++) {
/*  44 */       ItemStack itemStack = nonNullList.get(i);
/*  45 */       if (!itemStack.field_190928_g) {
/*     */ 
/*     */ 
/*     */         
/*  49 */         float damageOnArmor = (itemStack.func_77958_k() - itemStack.func_77952_i());
/*  50 */         float damagePercent = 100.0F - 100.0F * (1.0F - damageOnArmor / itemStack.func_77958_k());
/*     */         
/*  52 */         if (damagePercent < ((Integer)this.maxHeal.getValue()).intValue() && ((Boolean)this.DisableWhenDone.getValue()).booleanValue()) {
/*  53 */           toggle();
/*     */         }
/*     */         
/*  56 */         if (damagePercent <= ((Integer)this.maxHeal.getValue()).intValue()) {
/*  57 */           if (damagePercent <= ((Integer)this.minDamage.getValue()).intValue()) {
/*  58 */             this.toMend = (char)(this.toMend | 1 << i);
/*     */           }
/*     */ 
/*     */           
/*  62 */           if (((Boolean)this.predict.getValue()).booleanValue()) {
/*  63 */             sumOfDamage = (int)(sumOfDamage + (itemStack.func_77958_k() * ((Integer)this.maxHeal.getValue()).intValue()) / 100.0F - (itemStack.func_77958_k() - itemStack.func_77952_i()));
/*     */           }
/*     */         } else {
/*  66 */           this.toMend = (char)(this.toMend & (1 << i ^ 0xFFFFFFFF));
/*     */         } 
/*     */       } 
/*     */     } 
/*  70 */     if (this.toMend > '\000') {
/*  71 */       if (((Boolean)this.predict.getValue()).booleanValue()) {
/*     */ 
/*     */ 
/*     */         
/*  75 */         int totalXp = mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityXPOrb).filter(entity -> (entity.func_70068_e((Entity)mc.field_71439_g) <= 1.0D)).mapToInt(entity -> ((EntityXPOrb)entity).field_70530_e).sum();
/*     */         
/*  77 */         if (totalXp * 2 < sumOfDamage) {
/*  78 */           mendArmor(mc.field_71439_g.field_71071_by.field_70461_c);
/*     */         }
/*     */       } else {
/*  81 */         mendArmor(mc.field_71439_g.field_71071_by.field_70461_c);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void mendArmor(int oldSlot) {
/*  88 */     if (((Boolean)this.noEntityCollision.getValue()).booleanValue()) {
/*  89 */       for (EntityPlayer entityPlayer : mc.field_71441_e.field_73010_i) {
/*  90 */         if (entityPlayer.func_70032_d((Entity)mc.field_71439_g) < 1.0F && entityPlayer != mc.field_71439_g) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  96 */     if (((Boolean)this.sneakOnly.getValue()).booleanValue() && !mc.field_71439_g.func_70093_af()) {
/*     */       return;
/*     */     }
/*     */     
/* 100 */     int newSlot = findXPSlot();
/*     */     
/* 102 */     if (newSlot == -1) {
/*     */       return;
/*     */     }
/*     */     
/* 106 */     if (oldSlot != newSlot) {
/* 107 */       if (((Boolean)this.silentSwitch.getValue()).booleanValue()) {
/* 108 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(newSlot));
/*     */       } else {
/* 110 */         mc.field_71439_g.field_71071_by.field_70461_c = newSlot;
/*     */       } 
/* 112 */       mc.field_71442_b.func_78750_j();
/*     */     } 
/*     */     
/* 115 */     if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 116 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(0.0F, 90.0F, true));
/*     */     }
/* 118 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
/* 119 */     if (((Boolean)this.silentSwitch.getValue()).booleanValue()) {
/* 120 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(oldSlot));
/*     */     } else {
/* 122 */       mc.field_71439_g.field_71071_by.field_70461_c = oldSlot;
/*     */     } 
/* 124 */     mc.field_71442_b.func_78750_j();
/*     */   }
/*     */ 
/*     */   
/*     */   private int findXPSlot() {
/* 129 */     int slot = -1;
/*     */     
/* 131 */     for (int i = 0; i < 9; i++) {
/* 132 */       if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_151062_by) {
/* 133 */         slot = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 138 */     return slot;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\PacketMend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */