/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class Parkour
/*    */   extends Module {
/*    */   public Parkour() {
/*  9 */     super("Parkour", "ebic parkour", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 13 */     if (nullCheck()) {
/*    */       return;
/*    */     }
/* 16 */     if (mc.field_71439_g.field_70122_E && !mc.field_71439_g.func_70093_af() && !mc.field_71474_y.field_74311_E.func_151468_f() && !mc.field_71474_y.field_74314_A.func_151468_f() && mc.field_71441_e.func_184144_a((Entity)mc.field_71439_g, mc.field_71439_g.func_174813_aQ().func_72317_d(0.0D, -0.5D, 0.0D).func_72321_a(-0.001D, 0.0D, -0.001D)).isEmpty())
/* 17 */       mc.field_71439_g.func_70664_aZ(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\Parkour.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */