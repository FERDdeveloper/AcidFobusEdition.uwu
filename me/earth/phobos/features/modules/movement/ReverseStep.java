/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ 
/*    */ public class ReverseStep
/*    */   extends Module
/*    */ {
/*  9 */   private final Setting<Integer> speed = register(new Setting("Speed", Integer.valueOf(10), Integer.valueOf(1), Integer.valueOf(20)));
/*    */ 
/*    */   
/*    */   public ReverseStep() {
/* 13 */     super("ReverseStep", "whatuknowboutrollingdowninthedeep", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 19 */     if (!fullNullCheck()) { this; if (!mc.field_71439_g.func_70090_H()) { this; if (!mc.field_71439_g.func_180799_ab()) { this; if (!mc.field_71439_g.func_70617_f_()) {
/*    */ 
/*    */             
/* 22 */             this; if (mc.field_71439_g.field_70122_E) {
/* 23 */               this; mc.field_71439_g.field_70181_x -= (((Integer)this.speed.getValue()).intValue() / 10.0F);
/*    */             } 
/*    */             return;
/*    */           }  }
/*    */          }
/*    */        }
/*    */      } public void onDisable() {
/* 30 */     super.onDisable();
/* 31 */     mc.field_71439_g.field_70181_x = 0.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\ReverseStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */