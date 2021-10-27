/*    */ package me.earth.phobos.util;
/*    */ 
/*    */ import me.earth.phobos.MinecraftInstance;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import net.minecraft.client.gui.GuiMainMenu;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketEntityAction;
/*    */ import net.minecraft.network.play.client.CPacketPlayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.math.Vec3i;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldUtil
/*    */   implements MinecraftInstance
/*    */ {
/*    */   public static void placeBlock(BlockPos pos) {
/* 25 */     for (EnumFacing enumFacing : EnumFacing.values()) {
/* 26 */       if (!mc.field_71441_e.func_180495_p(pos.func_177972_a(enumFacing)).func_177230_c().equals(Blocks.field_150350_a) && !isIntercepted(pos)) {
/* 27 */         Vec3d vec = new Vec3d(pos.func_177958_n() + 0.5D + enumFacing.func_82601_c() * 0.5D, pos.func_177956_o() + 0.5D + enumFacing.func_96559_d() * 0.5D, pos.func_177952_p() + 0.5D + enumFacing.func_82599_e() * 0.5D);
/* 28 */         float[] old = { mc.field_71439_g.field_70177_z, mc.field_71439_g.field_70125_A };
/* 29 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(vec.field_72449_c - mc.field_71439_g.field_70161_v, vec.field_72450_a - mc.field_71439_g.field_70165_t)) - 90.0F, (float)-Math.toDegrees(Math.atan2(vec.field_72448_b - mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), Math.sqrt((vec.field_72450_a - mc.field_71439_g.field_70165_t) * (vec.field_72450_a - mc.field_71439_g.field_70165_t) + (vec.field_72449_c - mc.field_71439_g.field_70161_v) * (vec.field_72449_c - mc.field_71439_g.field_70161_v)))), mc.field_71439_g.field_70122_E));
/* 30 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
/* 31 */         mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, pos.func_177972_a(enumFacing), enumFacing.func_176734_d(), new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
/* 32 */         mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
/* 33 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
/* 34 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(old[0], old[1], mc.field_71439_g.field_70122_E));
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void placeBlock(BlockPos pos, int slot) {
/* 41 */     if (slot == -1) {
/*    */       return;
/*    */     }
/* 44 */     int prev = mc.field_71439_g.field_71071_by.field_70461_c;
/* 45 */     mc.field_71439_g.field_71071_by.field_70461_c = slot;
/* 46 */     placeBlock(pos);
/* 47 */     mc.field_71439_g.field_71071_by.field_70461_c = prev;
/*    */   }
/*    */   
/*    */   public static boolean isIntercepted(BlockPos pos) {
/* 51 */     for (Entity entity : mc.field_71441_e.field_72996_f) {
/* 52 */       if ((new AxisAlignedBB(pos)).func_72326_a(entity.func_174813_aQ())) {
/* 53 */         return true;
/*    */       }
/*    */     } 
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public static BlockPos GetLocalPlayerPosFloored() {
/* 60 */     return new BlockPos(Math.floor(mc.field_71439_g.field_70165_t), Math.floor(mc.field_71439_g.field_70163_u), Math.floor(mc.field_71439_g.field_70161_v));
/*    */   }
/*    */   
/*    */   public static boolean canBreak(BlockPos pos) {
/* 64 */     return (mc.field_71441_e.func_180495_p(pos).func_177230_c().func_176195_g(mc.field_71441_e.func_180495_p(pos), (World)mc.field_71441_e, pos) != -1.0F);
/*    */   }
/*    */   
/*    */   public static void disconnectFromWorld(Command command) {
/* 68 */     mc.field_71441_e.func_72882_A();
/* 69 */     mc.func_71403_a((WorldClient)null);
/* 70 */     mc.func_147108_a((GuiScreen)new GuiMainMenu());
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\WorldUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */