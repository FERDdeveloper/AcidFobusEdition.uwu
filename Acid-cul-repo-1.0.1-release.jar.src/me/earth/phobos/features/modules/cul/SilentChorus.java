/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.util.InventoryUtil;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketHeldItemChange;
/*    */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*    */ import net.minecraft.util.EnumHand;
/*    */ 
/*    */ public class SilentChorus
/*    */   extends Module {
/*    */   int oldSlot;
/*    */   
/*    */   public SilentChorus() {
/* 16 */     super("SilentChorus", "yes", Module.Category.CUL, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 21 */     this.oldSlot = mc.field_71439_g.field_71071_by.field_70461_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 26 */     int slot = InventoryUtil.findItemInHotbar(Items.field_185161_cS);
/* 27 */     if (InventoryUtil.findItemInHotbar(Items.field_185161_cS) != -1) {
/* 28 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(slot));
/* 29 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
/* 30 */       toggle();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\SilentChorus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */