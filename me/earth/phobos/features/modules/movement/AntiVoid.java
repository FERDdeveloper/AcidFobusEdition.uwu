/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ 
/*    */ public class AntiVoid
/*    */   extends Module {
/* 10 */   public Setting<Double> yLevel = register(new Setting("YLevel", Double.valueOf(1.0D), Double.valueOf(0.1D), Double.valueOf(5.0D)));
/* 11 */   public Setting<Double> yForce = register(new Setting("YMotion", Double.valueOf(0.1D), Double.valueOf(0.0D), Double.valueOf(1.0D)));
/*    */ 
/*    */ 
/*    */   
/*    */   public AntiVoid() {
/* 16 */     super("AntiVoid", "Glitches you up from void.", Module.Category.MOVEMENT, false, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 21 */     if (fullNullCheck()) {
/*    */       return;
/*    */     }
/* 24 */     if (!mc.field_71439_g.field_70145_X && mc.field_71439_g.field_70163_u <= ((Double)this.yLevel.getValue()).doubleValue()) {
/* 25 */       RayTraceResult trace = mc.field_71441_e.func_147447_a(mc.field_71439_g.func_174791_d(), new Vec3d(mc.field_71439_g.field_70165_t, 0.0D, mc.field_71439_g.field_70161_v), false, false, false);
/* 26 */       if (trace != null && trace.field_72313_a == RayTraceResult.Type.BLOCK) {
/*    */         return;
/*    */       }
/* 29 */       mc.field_71439_g.field_70181_x = ((Double)this.yForce.getValue()).doubleValue();
/* 30 */       if (mc.field_71439_g.func_184187_bx() != null)
/* 31 */         (mc.field_71439_g.func_184187_bx()).field_70181_x = ((Double)this.yForce.getValue()).doubleValue(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\AntiVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */