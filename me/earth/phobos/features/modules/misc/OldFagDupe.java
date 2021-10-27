/*    */ package me.earth.phobos.features.modules.misc;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.passive.AbstractChestHorse;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumHand;
/*    */ 
/*    */ public class OldFagDupe
/*    */   extends Module
/*    */ {
/*    */   Entity donkey;
/*    */   
/*    */   public OldFagDupe() {
/* 21 */     super("OldFagDupe", "Donkey Dupe", Module.Category.MISC, true, false, false);
/*    */   }
/*    */   
/*    */   public boolean setup() {
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 30 */     if (findAirInHotbar() == -1) {
/* 31 */       disable();
/*    */       return;
/*    */     } 
/* 34 */     if (findChestInHotbar() == -1) {
/* 35 */       disable();
/*    */       return;
/*    */     } 
/* 38 */     this.donkey = mc.field_71441_e.field_72996_f.stream().filter(this::isValidEntity).min(Comparator.comparing(p_Entity -> Float.valueOf(mc.field_71439_g.func_70032_d(p_Entity)))).orElse(null);
/* 39 */     if (this.donkey == null) {
/* 40 */       disable();
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 47 */     if (findAirInHotbar() == -1) {
/* 48 */       disable();
/*    */       return;
/*    */     } 
/* 51 */     if (findChestInHotbar() == -1) {
/* 52 */       disable();
/*    */       return;
/*    */     } 
/* 55 */     this.donkey = mc.field_71441_e.field_72996_f.stream().filter(this::isValidEntity).min(Comparator.comparing(p_Entity -> Float.valueOf(mc.field_71439_g.func_70032_d(p_Entity)))).orElse(null);
/* 56 */     if (this.donkey == null) {
/* 57 */       disable();
/*    */       return;
/*    */     } 
/* 60 */     putChestOn();
/* 61 */     Command.sendMessage("put chest on the donkey");
/* 62 */     toggle();
/*    */   }
/*    */   
/*    */   public void putChestOn() {
/* 66 */     mc.field_71439_g.field_71071_by.field_70461_c = findAirInHotbar();
/* 67 */     mc.field_71439_g.field_71071_by.field_70461_c = findChestInHotbar();
/* 68 */     mc.field_71442_b.func_187097_a((EntityPlayer)mc.field_71439_g, this.donkey, EnumHand.MAIN_HAND);
/*    */   }
/*    */   
/*    */   private int findChestInHotbar() {
/* 72 */     int slot = -1;
/* 73 */     for (int i = 0; i < 9; ) {
/*    */       
/* 75 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i); Block block;
/* 76 */       if (stack == ItemStack.field_190927_a || !(stack.func_77973_b() instanceof ItemBlock) || !(block = ((ItemBlock)stack.func_77973_b()).func_179223_d() instanceof net.minecraft.block.BlockChest)) { i++; continue; }
/* 77 */        slot = i;
/*    */     } 
/*    */     
/* 80 */     return slot;
/*    */   }
/*    */   
/*    */   private int findAirInHotbar() {
/* 84 */     int slot = -1;
/* 85 */     for (int i = 0; i < 9; i++) {
/* 86 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 87 */       if (stack.func_77973_b() == Items.field_190931_a)
/* 88 */         slot = i; 
/*    */     } 
/* 90 */     return slot;
/*    */   }
/*    */   
/*    */   private boolean isValidEntity(Entity entity) {
/* 94 */     if (entity instanceof AbstractChestHorse) {
/* 95 */       AbstractChestHorse donkey = (AbstractChestHorse)entity;
/* 96 */       return (!donkey.func_70631_g_() && donkey.func_110248_bS());
/*    */     } 
/* 98 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\OldFagDupe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */