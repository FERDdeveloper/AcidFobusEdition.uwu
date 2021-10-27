/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*    */ import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ public class BoatPlace extends Module {
/*    */   public BoatPlace() {
/* 13 */     super("BoatPlace", "Tries to bypass Anti Boat placement", Module.Category.CUL, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 18 */     if (mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemBoat && Mouse.isButtonDown(1)) {
/* 19 */       mc.func_147114_u().func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
/* 20 */       mc.func_147114_u().func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(mc.field_71476_x.func_178782_a(), EnumFacing.SOUTH, EnumHand.MAIN_HAND, 1.0F, 1.0F, 1.0F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\BoatPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */