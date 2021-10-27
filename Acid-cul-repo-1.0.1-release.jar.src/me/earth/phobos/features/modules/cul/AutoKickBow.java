/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketHeldItemChange;
/*    */ import net.minecraft.network.play.client.CPacketPlayerDigging;
/*    */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class AutoKickBow
/*    */   extends Module {
/*    */   public AutoKickBow() {
/* 15 */     super("AutoKickBow", "oh", Module.Category.CUL, true, false, false);
/*    */   }
/*    */   
/*    */   static {
/*    */   
/*    */   }
/*    */   
/*    */   public int findShulker() {
/* 23 */     byte b = -1;
/* 24 */     for (byte b1 = 0; b1 < 9; b1 = (byte)(b1 + 1)) {
/* 25 */       ItemStack itemStack = (ItemStack)mc.field_71439_g.field_71071_by.field_70462_a.get(b1);
/* 26 */       if (itemStack.func_77973_b() instanceof net.minecraft.item.ItemShulkerBox)
/* 27 */         b = b1; 
/*    */     } 
/* 29 */     return b;
/*    */   }
/*    */   
/*    */   public void changeItem(int paramInt) {
/* 33 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(paramInt));
/* 34 */     mc.field_71439_g.field_71071_by.field_70461_c = paramInt;
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 38 */     if (mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() instanceof net.minecraft.item.ItemBow && mc.field_71439_g.func_184587_cr() && mc.field_71439_g.func_184612_cw() >= 25) {
/* 39 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, mc.field_71439_g.func_174811_aO()));
/* 40 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(mc.field_71439_g.func_184600_cs()));
/* 41 */       mc.field_71439_g.func_184597_cx();
/* 42 */       if (findShulker() != -1) {
/* 43 */         changeItem(findShulker());
/*    */       } else {
/* 45 */         Command.sendMessage("§dNo shulker found in hotbar, not switching...");
/* 46 */         disable();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\AutoKickBow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */