/*    */ package me.earth.phobos.util;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.ClickType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemUtil {
/* 10 */   private static final Minecraft mc = Minecraft.func_71410_x();
/*    */   
/*    */   public static int getSlotFromInventory(Item item) {
/* 13 */     if (item == null) {
/* 14 */       return -1;
/*    */     }
/*    */     
/* 17 */     int slot = -1;
/*    */     
/* 19 */     for (int i = 44; i >= 0; i--) {
/* 20 */       if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == item) {
/* 21 */         if (i < 9) {
/* 22 */           i += 36;
/*    */         }
/* 24 */         slot = i;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 29 */     return slot;
/*    */   }
/*    */   
/*    */   public static int getItemFromHotbar(Item item) {
/* 33 */     int slot = -1;
/*    */     
/* 35 */     for (int i = 8; i >= 0; i--) {
/* 36 */       if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == item) {
/* 37 */         slot = i;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 42 */     return slot;
/*    */   }
/*    */   
/*    */   public static int getBlockFromHotbar(Block block) {
/* 46 */     int slot = -1;
/*    */     
/* 48 */     for (int i = 8; i >= 0; i--) {
/* 49 */       if (Block.func_149634_a(mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b()) == block) {
/* 50 */         slot = i;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 55 */     return slot;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getItemCount(Item item) {
/* 60 */     int count = 0;
/*    */     
/* 62 */     for (int i = 0; i < 45; i++) {
/* 63 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 64 */       if (stack.func_77973_b() == item) {
/* 65 */         count += stack.func_190916_E();
/*    */       }
/*    */     } 
/*    */     
/* 69 */     return count;
/*    */   }
/*    */   
/*    */   public static void replaceOffhand(int slot) {
/* 73 */     mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
/* 74 */     mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
/* 75 */     mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
/*    */   }
/*    */   
/*    */   public static int getItemDamage(ItemStack stack) {
/* 79 */     return stack.func_77958_k() - stack.func_77952_i();
/*    */   }
/*    */   
/*    */   public static float getDamageInPercent(ItemStack stack) {
/* 83 */     return getItemDamage(stack) / stack.func_77958_k() * 100.0F;
/*    */   }
/*    */   
/*    */   public static int getRoundedDamage(ItemStack stack) {
/* 87 */     return (int)getDamageInPercent(stack);
/*    */   }
/*    */   
/*    */   public static boolean hasDurability(ItemStack stack) {
/* 91 */     Item item = stack.func_77973_b();
/* 92 */     return (item instanceof net.minecraft.item.ItemArmor || item instanceof net.minecraft.item.ItemSword || item instanceof net.minecraft.item.ItemTool || item instanceof net.minecraft.item.ItemShield);
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\ItemUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */