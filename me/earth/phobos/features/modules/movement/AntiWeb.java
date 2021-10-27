/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.EntityUtil;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class AntiWeb extends Module {
/*    */   private Setting<Boolean> HoleOnly;
/* 10 */   public Setting<Float> timerSpeed = register(new Setting("Speed", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(50.0F)));
/*    */   
/* 12 */   public float speed = 1.0F;
/*    */ 
/*    */   
/*    */   public AntiWeb() {
/* 16 */     super("AntiWeb", "Turns on timer when in a web", Module.Category.MOVEMENT, true, false, false);
/* 17 */     this.HoleOnly = register(new Setting("HoleOnly", Boolean.valueOf(true)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 22 */     this.speed = ((Float)this.timerSpeed.getValue()).floatValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 29 */     if (((Boolean)this.HoleOnly.getValue()).booleanValue()) {
/* 30 */       if (mc.field_71439_g.field_70134_J && EntityUtil.isInHole((Entity)mc.field_71439_g)) {
/* 31 */         mc.field_71428_T.field_194149_e = 50.0F / ((((Float)this.timerSpeed.getValue()).floatValue() == 0.0F) ? 0.1F : ((Float)this.timerSpeed.getValue()).floatValue());
/*    */       } else {
/* 33 */         mc.field_71428_T.field_194149_e = 50.0F;
/*    */       } 
/* 35 */       if (mc.field_71439_g.field_70122_E && EntityUtil.isInHole((Entity)mc.field_71439_g)) {
/* 36 */         mc.field_71428_T.field_194149_e = 50.0F;
/*    */       }
/*    */     } 
/* 39 */     if (!((Boolean)this.HoleOnly.getValue()).booleanValue()) {
/* 40 */       if (mc.field_71439_g.field_70134_J) {
/* 41 */         mc.field_71428_T.field_194149_e = 50.0F / ((((Float)this.timerSpeed.getValue()).floatValue() == 0.0F) ? 0.1F : ((Float)this.timerSpeed.getValue()).floatValue());
/*    */       } else {
/*    */         
/* 44 */         mc.field_71428_T.field_194149_e = 50.0F;
/*    */       } 
/*    */       
/* 47 */       if (mc.field_71439_g.field_70122_E)
/* 48 */         mc.field_71428_T.field_194149_e = 50.0F; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\AntiWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */