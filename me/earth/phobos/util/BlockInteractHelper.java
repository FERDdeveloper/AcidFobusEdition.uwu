/*     */ package me.earth.phobos.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketEntityAction;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockInteractHelper
/*     */ {
/*  27 */   public static final List<Block> blackList = Arrays.asList(new Block[] { Blocks.field_150477_bB, (Block)Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150462_ai, Blocks.field_150467_bQ, Blocks.field_150382_bo, (Block)Blocks.field_150438_bZ, Blocks.field_150409_cd, Blocks.field_150367_z });
/*  28 */   public static final List<Block> shulkerList = Arrays.asList(new Block[] { Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA });
/*  29 */   private static final Minecraft mc = Minecraft.func_71410_x();
/*     */   
/*     */   public static PlaceResult place(BlockPos pos, float p_Distance, boolean p_Rotate, boolean p_UseSlabRule) {
/*  32 */     IBlockState l_State = mc.field_71441_e.func_180495_p(pos);
/*  33 */     boolean l_Replaceable = l_State.func_185904_a().func_76222_j();
/*  34 */     boolean l_IsSlabAtBlock = l_State.func_177230_c() instanceof net.minecraft.block.BlockSlab;
/*  35 */     if (!l_Replaceable && !l_IsSlabAtBlock) {
/*  36 */       return PlaceResult.NotReplaceable;
/*     */     }
/*  38 */     if (!checkForNeighbours(pos)) {
/*  39 */       return PlaceResult.Neighbors;
/*     */     }
/*  41 */     if (p_UseSlabRule && l_IsSlabAtBlock && !l_State.func_185917_h()) {
/*  42 */       return PlaceResult.CantPlace;
/*     */     }
/*  44 */     Vec3d eyesPos = new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v);
/*  45 */     for (EnumFacing side : EnumFacing.values()) {
/*     */ 
/*     */       
/*  48 */       BlockPos neighbor = pos.func_177972_a(side);
/*  49 */       EnumFacing side2 = side.func_176734_d(); Vec3d hitVec;
/*  50 */       if (mc.field_71441_e.func_180495_p(neighbor).func_177230_c().func_176209_a(mc.field_71441_e.func_180495_p(neighbor), false) && eyesPos.func_72438_d(hitVec = (new Vec3d((Vec3i)neighbor)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(side2.func_176730_m())).func_186678_a(0.5D))) <= p_Distance) {
/*  51 */         Block neighborPos = mc.field_71441_e.func_180495_p(neighbor).func_177230_c();
/*  52 */         boolean activated = neighborPos.func_180639_a((World)mc.field_71441_e, pos, mc.field_71441_e.func_180495_p(pos), (EntityPlayer)mc.field_71439_g, EnumHand.MAIN_HAND, side, 0.0F, 0.0F, 0.0F);
/*  53 */         if (blackList.contains(neighborPos) || shulkerList.contains(neighborPos) || activated) {
/*  54 */           mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
/*     */         }
/*  56 */         if (p_Rotate)
/*  57 */           faceVectorPacketInstant(hitVec); 
/*     */         EnumActionResult l_Result2;
/*  59 */         if ((l_Result2 = mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, neighbor, side2, hitVec, EnumHand.MAIN_HAND)) != EnumActionResult.FAIL)
/*  60 */         { mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
/*  61 */           if (activated) {
/*  62 */             mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
/*     */           }
/*  64 */           return PlaceResult.Placed; } 
/*     */       } 
/*  66 */     }  return PlaceResult.CantPlace;
/*     */   }
/*     */   
/*     */   public static ValidResult valid(BlockPos pos) {
/*  70 */     if (!mc.field_71441_e.func_72855_b(new AxisAlignedBB(pos))) {
/*  71 */       return ValidResult.NoEntityCollision;
/*     */     }
/*  73 */     if (!checkForNeighbours(pos)) {
/*  74 */       return ValidResult.NoNeighbors;
/*     */     }
/*  76 */     IBlockState l_State = mc.field_71441_e.func_180495_p(pos);
/*  77 */     if (l_State.func_177230_c() == Blocks.field_150350_a) {
/*     */       BlockPos[] l_Blocks;
/*  79 */       for (BlockPos l_Pos : l_Blocks = new BlockPos[] { pos.func_177978_c(), pos.func_177968_d(), pos.func_177974_f(), pos.func_177976_e(), pos.func_177984_a(), pos.func_177977_b() }) {
/*  80 */         IBlockState l_State2 = mc.field_71441_e.func_180495_p(l_Pos);
/*  81 */         if (l_State2.func_177230_c() != Blocks.field_150350_a) {
/*  82 */           EnumFacing[] arrayOfEnumFacing; int i; byte b; for (arrayOfEnumFacing = EnumFacing.values(), i = arrayOfEnumFacing.length, b = 0; b < i; ) { EnumFacing side = arrayOfEnumFacing[b];
/*  83 */             BlockPos neighbor = pos.func_177972_a(side);
/*  84 */             if (!mc.field_71441_e.func_180495_p(neighbor).func_177230_c().func_176209_a(mc.field_71441_e.func_180495_p(neighbor), false)) { b++; continue; }
/*  85 */              return ValidResult.Ok; }
/*     */         
/*     */         } 
/*  88 */       }  return ValidResult.NoNeighbors;
/*     */     } 
/*  90 */     return ValidResult.AlreadyBlockThere;
/*     */   }
/*     */   
/*     */   public static float[] getLegitRotations(Vec3d vec) {
/*  94 */     Vec3d eyesPos = getEyesPos();
/*  95 */     double diffX = vec.field_72450_a - eyesPos.field_72450_a;
/*  96 */     double diffY = vec.field_72448_b - eyesPos.field_72448_b;
/*  97 */     double diffZ = vec.field_72449_c - eyesPos.field_72449_c;
/*  98 */     double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
/*  99 */     float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
/* 100 */     float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));
/* 101 */     return new float[] { mc.field_71439_g.field_70177_z + MathHelper.func_76142_g(yaw - mc.field_71439_g.field_70177_z), mc.field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - mc.field_71439_g.field_70125_A) };
/*     */   }
/*     */   
/*     */   private static Vec3d getEyesPos() {
/* 105 */     return new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v);
/*     */   }
/*     */   
/*     */   public static void faceVectorPacketInstant(Vec3d vec) {
/* 109 */     float[] rotations = getLegitRotations(vec);
/* 110 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], mc.field_71439_g.field_70122_E));
/*     */   }
/*     */   
/*     */   public static boolean canBeClicked(BlockPos pos) {
/* 114 */     return getBlock(pos).func_176209_a(getState(pos), false);
/*     */   }
/*     */   
/*     */   private static Block getBlock(BlockPos pos) {
/* 118 */     return getState(pos).func_177230_c();
/*     */   }
/*     */   
/*     */   private static IBlockState getState(BlockPos pos) {
/* 122 */     return mc.field_71441_e.func_180495_p(pos);
/*     */   }
/*     */   
/*     */   public static boolean checkForNeighbours(BlockPos blockPos) {
/* 126 */     if (!hasNeighbour(blockPos)) {
/* 127 */       EnumFacing[] arrayOfEnumFacing; int i; byte b; for (arrayOfEnumFacing = EnumFacing.values(), i = arrayOfEnumFacing.length, b = 0; b < i; ) { EnumFacing side = arrayOfEnumFacing[b];
/* 128 */         BlockPos neighbour = blockPos.func_177972_a(side);
/* 129 */         if (!hasNeighbour(neighbour)) { b++; continue; }
/* 130 */          return true; }
/*     */       
/* 132 */       return false;
/*     */     } 
/* 134 */     return true; } private static boolean hasNeighbour(BlockPos blockPos) {
/*     */     EnumFacing[] arrayOfEnumFacing;
/*     */     int i;
/*     */     byte b;
/* 138 */     for (arrayOfEnumFacing = EnumFacing.values(), i = arrayOfEnumFacing.length, b = 0; b < i; ) { EnumFacing side = arrayOfEnumFacing[b];
/* 139 */       BlockPos neighbour = blockPos.func_177972_a(side);
/* 140 */       if (mc.field_71441_e.func_180495_p(neighbour).func_185904_a().func_76222_j()) { b++; continue; }
/* 141 */        return true; }
/*     */     
/* 143 */     return false;
/*     */   }
/*     */   
/*     */   public static List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
/* 147 */     ArrayList<BlockPos> circleblocks = new ArrayList<>();
/* 148 */     int cx = loc.func_177958_n();
/* 149 */     int cy = loc.func_177956_o();
/* 150 */     int cz = loc.func_177952_p();
/* 151 */     int x = cx - (int)r;
/* 152 */     while (x <= cx + r) {
/* 153 */       int z = cz - (int)r;
/* 154 */       while (z <= cz + r) {
/* 155 */         int y = sphere ? (cy - (int)r) : cy;
/*     */         
/*     */         while (true) {
/* 158 */           float f = sphere ? (cy + r) : (cy + h), f2 = f;
/* 159 */           if (y >= f)
/* 160 */             break;  double dist = ((cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0));
/* 161 */           if (dist < (r * r) && (!hollow || dist >= ((r - 1.0F) * (r - 1.0F)))) {
/* 162 */             BlockPos l = new BlockPos(x, y + plus_y, z);
/* 163 */             circleblocks.add(l);
/*     */           } 
/* 165 */           y++;
/*     */         } 
/* 167 */         z++;
/*     */       } 
/* 169 */       x++;
/*     */     } 
/* 171 */     return circleblocks;
/*     */   }
/*     */   
/*     */   public enum PlaceResult {
/* 175 */     NotReplaceable,
/* 176 */     Neighbors,
/* 177 */     CantPlace,
/* 178 */     Placed;
/*     */   }
/*     */   
/*     */   public enum ValidResult
/*     */   {
/* 183 */     NoEntityCollision,
/* 184 */     AlreadyBlockThere,
/* 185 */     NoNeighbors,
/* 186 */     Ok;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\BlockInteractHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */