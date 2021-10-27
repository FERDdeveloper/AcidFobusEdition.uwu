/*    */ package me.earth.phobos.features.modules.misc;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.util.EnumHand;
/*    */ 
/*    */ public class OffhandSwing
/*    */   extends Module {
/*    */   public OffhandSwing() {
/*  9 */     super("OffhandSwing", "sexc", Module.Category.MISC, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 14 */     mc.field_71439_g.field_184622_au = EnumHand.OFF_HAND;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\OffhandSwing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */