/*    */ package me.earth.phobos.util;
/*    */ 
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ 
/*    */ public class StrafeUtil {
/*    */   public static double[] transformStrafeMovement(EntityPlayerSP entity) {
/*  7 */     double entity_rotation_yaw = entity.field_70177_z;
/*  8 */     double entity_rotation_pitch = entity.field_70125_A;
/*  9 */     double entity_movement_forward = entity.field_71158_b.field_192832_b;
/* 10 */     double entity_movement_strafe = entity.field_71158_b.field_78902_a;
/* 11 */     if (entity_movement_forward != 0.0D && entity_movement_strafe != 0.0D && entity_movement_forward != 0.0D) {
/* 12 */       if (entity_movement_strafe > 0.0D) {
/* 13 */         entity_rotation_yaw += ((entity_movement_forward > 0.0D) ? -45 : 45);
/* 14 */       } else if (entity_movement_strafe < 0.0D) {
/* 15 */         entity_rotation_yaw += ((entity_movement_forward > 0.0D) ? 45 : -45);
/*    */       } 
/* 17 */       entity_movement_strafe = 0.0D;
/* 18 */       if (entity_movement_forward > 0.0D) {
/* 19 */         entity_movement_forward = 1.0D;
/* 20 */       } else if (entity_movement_forward < 0.0D) {
/* 21 */         entity_movement_forward = -1.0D;
/*    */       } 
/*    */     } 
/* 24 */     return new double[] { entity_rotation_yaw, entity_rotation_pitch, entity_movement_forward, entity_movement_strafe };
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\StrafeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */