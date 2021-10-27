/*     */ package me.earth.phobos.features.modules.player;
/*     */ 
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.BurrowUtil;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockEnderChest;
/*     */ import net.minecraft.block.BlockObsidian;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketEntityAction;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Burrow
/*     */   extends Module
/*     */ {
/*     */   private final Setting<Integer> offset;
/*     */   private final Setting<Boolean> rotate;
/*     */   private final Setting<Mode> mode;
/*     */   private BlockPos originalPos;
/*     */   private int oldSlot;
/*     */   Block returnBlock;
/*     */   
/*     */   public Burrow() {
/*  32 */     super("SelfPlace", "TPs you into a block", Module.Category.PLAYER, true, false, false);
/*  33 */     this.offset = register(new Setting("Offset", Integer.valueOf(3), Integer.valueOf(-5), Integer.valueOf(5)));
/*  34 */     this.rotate = register(new Setting("Rotate", Boolean.valueOf(false)));
/*  35 */     this.mode = register(new Setting("Mode", Mode.OBBY));
/*  36 */     this.oldSlot = -1;
/*  37 */     this.returnBlock = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  42 */     super.onEnable();
/*  43 */     this.originalPos = new BlockPos(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v);
/*  44 */     switch ((Mode)this.mode.getValue()) {
/*     */       case OBBY:
/*  46 */         this.returnBlock = Blocks.field_150343_Z;
/*     */         break;
/*     */       
/*     */       case ECHEST:
/*  50 */         this.returnBlock = Blocks.field_150477_bB;
/*     */         break;
/*     */     } 
/*     */     
/*  54 */     if (mc.field_71441_e.func_180495_p(new BlockPos(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v)).func_177230_c().equals(this.returnBlock) || intersectsWithEntity(this.originalPos)) {
/*  55 */       toggle();
/*     */       return;
/*     */     } 
/*  58 */     this.oldSlot = mc.field_71439_g.field_71071_by.field_70461_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  63 */     switch ((Mode)this.mode.getValue()) {
/*     */       case OBBY:
/*  65 */         if (BurrowUtil.findHotbarBlock(BlockObsidian.class) == -1) {
/*  66 */           Command.sendMessage("Can't find obby in hotbar!");
/*  67 */           toggle();
/*     */           return;
/*     */         } 
/*     */         break;
/*     */       
/*     */       case ECHEST:
/*  73 */         if (BurrowUtil.findHotbarBlock(BlockEnderChest.class) == -1) {
/*  74 */           Command.sendMessage("Can't find echest in hotbar!");
/*  75 */           toggle();
/*     */           return;
/*     */         } 
/*     */         break;
/*     */     } 
/*     */     
/*  81 */     switch ((Mode)this.mode.getValue()) {
/*     */       case OBBY:
/*  83 */         BurrowUtil.switchToSlot(BurrowUtil.findHotbarBlock(BlockObsidian.class));
/*     */         break;
/*     */       
/*     */       case ECHEST:
/*  87 */         BurrowUtil.switchToSlot(BurrowUtil.findHotbarBlock(BlockEnderChest.class));
/*     */         break;
/*     */     } 
/*     */     
/*  91 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.41999998688698D, mc.field_71439_g.field_70161_v, true));
/*  92 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.7531999805211997D, mc.field_71439_g.field_70161_v, true));
/*  93 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.00133597911214D, mc.field_71439_g.field_70161_v, true));
/*  94 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.16610926093821D, mc.field_71439_g.field_70161_v, true));
/*  95 */     BurrowUtil.placeBlock(this.originalPos, EnumHand.MAIN_HAND, ((Boolean)this.rotate.getValue()).booleanValue(), true, false);
/*  96 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + ((Integer)this.offset.getValue()).intValue(), mc.field_71439_g.field_70161_v, false));
/*  97 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
/*  98 */     mc.field_71439_g.func_70095_a(false);
/*  99 */     BurrowUtil.switchToSlot(this.oldSlot);
/* 100 */     toggle();
/*     */   }
/*     */   
/*     */   private boolean intersectsWithEntity(BlockPos pos) {
/* 104 */     for (Entity entity : mc.field_71441_e.field_72996_f) {
/* 105 */       if (entity.equals(mc.field_71439_g)) {
/*     */         continue;
/*     */       }
/* 108 */       if (entity instanceof net.minecraft.entity.item.EntityItem) {
/*     */         continue;
/*     */       }
/* 111 */       if ((new AxisAlignedBB(pos)).func_72326_a(entity.func_174813_aQ())) {
/* 112 */         return true;
/*     */       }
/*     */     } 
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 120 */     OBBY,
/* 121 */     ECHEST;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\Burrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */