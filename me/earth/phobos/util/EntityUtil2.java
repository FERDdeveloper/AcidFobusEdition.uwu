/*     */ package me.earth.phobos.util;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntityIronGolem;
/*     */ import net.minecraft.entity.monster.EntityPigZombie;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ 
/*     */ public class EntityUtil2 {
/*     */   public static boolean isPassive(Entity e) {
/*  16 */     if (e instanceof EntityWolf && ((EntityWolf)e).func_70919_bu())
/*  17 */       return false; 
/*  18 */     if (e instanceof net.minecraft.entity.passive.EntityAnimal || e instanceof net.minecraft.entity.EntityAgeable || e instanceof net.minecraft.entity.passive.EntityTameable || e instanceof net.minecraft.entity.passive.EntityAmbientCreature || e instanceof net.minecraft.entity.passive.EntitySquid)
/*  19 */       return true; 
/*  20 */     if (e instanceof EntityIronGolem && ((EntityIronGolem)e).func_70643_av() == null)
/*  21 */       return true; 
/*  22 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isLiving(Entity e) {
/*  26 */     return e instanceof net.minecraft.entity.EntityLivingBase;
/*     */   }
/*     */   
/*     */   public static boolean isFakeLocalPlayer(Entity entity) {
/*  30 */     return (entity != null && entity.func_145782_y() == -100 && Wrapper.getPlayer() != entity);
/*     */   }
/*     */   
/*     */   public static Vec3d getInterpolatedAmount(Entity entity, double x, double y, double z) {
/*  34 */     return new Vec3d((entity.field_70165_t - entity.field_70142_S) * x, (entity.field_70163_u - entity.field_70137_T) * y, (entity.field_70161_v - entity.field_70136_U) * z);
/*     */   }
/*     */   
/*     */   public static Vec3d getInterpolatedAmount(Entity entity, Vec3d vec) {
/*  38 */     return getInterpolatedAmount(entity, vec.field_72450_a, vec.field_72448_b, vec.field_72449_c);
/*     */   }
/*     */   
/*     */   public static Vec3d getInterpolatedAmount(Entity entity, double ticks) {
/*  42 */     return getInterpolatedAmount(entity, ticks, ticks, ticks);
/*     */   }
/*     */   
/*     */   public static boolean isMobAggressive(Entity entity) {
/*  46 */     if (entity instanceof EntityPigZombie) {
/*  47 */       if (((EntityPigZombie)entity).func_184734_db() || ((EntityPigZombie)entity).func_175457_ck())
/*  48 */         return true; 
/*     */     } else {
/*  50 */       if (entity instanceof EntityWolf)
/*  51 */         return (((EntityWolf)entity).func_70919_bu() && 
/*  52 */           !Wrapper.getPlayer().equals(((EntityWolf)entity).func_70902_q())); 
/*  53 */       if (entity instanceof EntityEnderman)
/*  54 */         return ((EntityEnderman)entity).func_70823_r(); 
/*     */     } 
/*  56 */     return isHostileMob(entity);
/*     */   }
/*     */   
/*     */   public static boolean isNeutralMob(Entity entity) {
/*  60 */     return (entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman);
/*     */   }
/*     */   
/*     */   public static boolean isFriendlyMob(Entity entity) {
/*  64 */     return ((entity.isCreatureType(EnumCreatureType.CREATURE, false) && !EntityUtil.isNeutralMob(entity)) || entity
/*  65 */       .isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof net.minecraft.entity.passive.EntityVillager || entity instanceof EntityIronGolem || (
/*     */       
/*  67 */       isNeutralMob(entity) && !EntityUtil.isMobAggressive(entity)));
/*     */   }
/*     */   
/*     */   public static boolean isHostileMob(Entity entity) {
/*  71 */     return (entity.isCreatureType(EnumCreatureType.MONSTER, false) && !EntityUtil.isNeutralMob(entity));
/*     */   }
/*     */   
/*     */   public static Vec3d getInterpolatedPos(Entity entity, float ticks) {
/*  75 */     return (new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U)).func_178787_e(getInterpolatedAmount(entity, ticks));
/*     */   }
/*     */   
/*     */   public static Vec3d getInterpolatedRenderPos(Entity entity, float ticks) {
/*  79 */     return getInterpolatedPos(entity, ticks).func_178786_a((Wrapper.getMinecraft().func_175598_ae()).field_78725_b, (Wrapper.getMinecraft().func_175598_ae()).field_78726_c, (Wrapper.getMinecraft().func_175598_ae()).field_78723_d);
/*     */   }
/*     */   
/*     */   public static Vec3d getInterpolatedEyePos(Entity entity, float ticks) {
/*  83 */     return getInterpolatedPos(entity, ticks).func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/*     */   }
/*     */   
/*     */   public static boolean isInWater(Entity entity) {
/*  87 */     if (entity == null)
/*  88 */       return false; 
/*  89 */     double y = entity.field_70163_u + 0.01D;
/*  90 */     for (int x = MathHelper.func_76128_c(entity.field_70165_t); x < MathHelper.func_76143_f(entity.field_70165_t); x++) {
/*  91 */       for (int z = MathHelper.func_76128_c(entity.field_70161_v); z < MathHelper.func_76143_f(entity.field_70161_v); z++) {
/*  92 */         BlockPos pos = new BlockPos(x, (int)y, z);
/*  93 */         if (Wrapper.getWorld().func_180495_p(pos).func_177230_c() instanceof net.minecraft.block.BlockLiquid)
/*  94 */           return true; 
/*     */       } 
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isDrivenByPlayer(Entity entityIn) {
/* 101 */     return (Wrapper.getPlayer() != null && entityIn != null && entityIn.equals(Wrapper.getPlayer().func_184187_bx()));
/*     */   }
/*     */   
/*     */   public static boolean isAboveWater(Entity entity) {
/* 105 */     return isAboveWater(entity, false);
/*     */   }
/*     */   
/*     */   public static boolean isAboveWater(Entity entity, boolean packet) {
/* 109 */     if (entity == null)
/* 110 */       return false; 
/* 111 */     double y = entity.field_70163_u - (packet ? 0.03D : (EntityUtil.isPlayer(entity) ? 0.2D : 0.5D));
/* 112 */     for (int x = MathHelper.func_76128_c(entity.field_70165_t); x < MathHelper.func_76143_f(entity.field_70165_t); x++) {
/* 113 */       for (int z = MathHelper.func_76128_c(entity.field_70161_v); z < MathHelper.func_76143_f(entity.field_70161_v); z++) {
/* 114 */         BlockPos pos = new BlockPos(x, MathHelper.func_76128_c(y), z);
/* 115 */         if (Wrapper.getWorld().func_180495_p(pos).func_177230_c() instanceof net.minecraft.block.BlockLiquid)
/* 116 */           return true; 
/*     */       } 
/*     */     } 
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   public static double[] calculateLookAt(double px, double py, double pz, EntityPlayer me) {
/* 123 */     double dirx = me.field_70165_t - px;
/* 124 */     double diry = me.field_70163_u - py;
/* 125 */     double dirz = me.field_70161_v - pz;
/* 126 */     double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
/* 127 */     dirx /= len;
/* 128 */     diry /= len;
/* 129 */     dirz /= len;
/* 130 */     double pitch = Math.asin(diry);
/* 131 */     double yaw = Math.atan2(dirz, dirx);
/* 132 */     pitch = pitch * 180.0D / Math.PI;
/* 133 */     yaw = yaw * 180.0D / Math.PI;
/* 134 */     yaw += 90.0D;
/* 135 */     return new double[] { yaw, pitch };
/*     */   }
/*     */   
/*     */   public static boolean isPlayer(Entity entity) {
/* 139 */     return entity instanceof EntityPlayer;
/*     */   }
/*     */   
/*     */   public static double getRelativeX(float yaw) {
/* 143 */     return MathHelper.func_76126_a(-yaw * 0.017453292F);
/*     */   }
/*     */   
/*     */   public static double getRelativeZ(float yaw) {
/* 147 */     return MathHelper.func_76134_b(yaw * 0.017453292F);
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\EntityUtil2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */