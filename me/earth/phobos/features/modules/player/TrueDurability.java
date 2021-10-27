/*    */ package me.earth.phobos.features.modules.player;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ 
/*    */ public class TrueDurability
/*    */   extends Module {
/*    */   private static TrueDurability instance;
/*    */   
/*    */   public TrueDurability() {
/* 10 */     super("TrueDurability", "Shows True Durability of items", Module.Category.PLAYER, false, false, false);
/* 11 */     instance = this;
/*    */   }
/*    */   
/*    */   public static TrueDurability getInstance() {
/* 15 */     if (instance == null) {
/* 16 */       instance = new TrueDurability();
/*    */     }
/* 18 */     return instance;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\TrueDurability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */