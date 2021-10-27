/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ 
/*    */ public class PacketSwing
/*    */   extends Module
/*    */ {
/*    */   public PacketSwing() {
/*  9 */     super("PacketSwing", "Swings with packets :^]", Module.Category.CUL, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 14 */     if (mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword && mc.field_71460_t.field_78516_c.field_187470_g >= 0.9D) {
/* 15 */       mc.field_71460_t.field_78516_c.field_187469_f = 1.0F;
/* 16 */       mc.field_71460_t.field_78516_c.field_187467_d = mc.field_71439_g.func_184614_ca();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\PacketSwing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */