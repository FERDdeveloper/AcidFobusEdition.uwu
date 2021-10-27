/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.ClickType;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ChestStealer
/*    */   extends Module {
/*    */   public ChestStealer() {
/* 12 */     super("ChestStealer", "steal monkes in chests", Module.Category.CUL, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 17 */     if (mc.field_71439_g.field_71070_bA instanceof ContainerChest) {
/* 18 */       ContainerChest chest = (ContainerChest)mc.field_71439_g.field_71070_bA;
/* 19 */       for (int items = 0; items < chest.func_85151_d().func_70302_i_(); items++) {
/* 20 */         ItemStack stack = chest.func_85151_d().func_70301_a(items);
/* 21 */         mc.field_71442_b.func_187098_a(chest.field_75152_c, items, 0, ClickType.QUICK_MOVE, (EntityPlayer)mc.field_71439_g);
/* 22 */         if (isChestEmpty(chest))
/* 23 */           mc.field_71439_g.func_71053_j(); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean isChestEmpty(ContainerChest chest) {
/* 29 */     int items = 0;
/* 30 */     if (items < chest.func_85151_d().func_70302_i_()) {
/* 31 */       ItemStack slot = chest.func_85151_d().func_70301_a(items);
/* 32 */       return false;
/*    */     } 
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\ChestStealer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */