/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ 
/*    */ public class EntityControl
/*    */   extends Module {
/*    */   public static EntityControl INSTANCE;
/*    */   
/*    */   public EntityControl() {
/* 10 */     super("EntityControl", "Control entities with the force or some shit", Module.Category.MOVEMENT, false, false, false);
/* 11 */     INSTANCE = this;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\EntityControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */