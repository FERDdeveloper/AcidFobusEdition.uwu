/*    */ package me.earth.phobos.features.modules.player;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ 
/*    */ public class HasteSpoof
/*    */   extends Module {
/*    */   public HasteSpoof() {
/* 10 */     super("HasteSpoof", "Spoofs Haste", Module.Category.PLAYER, true, false, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 15 */     mc.field_71439_g.func_70690_d(new PotionEffect(MobEffects.field_76422_e));
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\HasteSpoof.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */