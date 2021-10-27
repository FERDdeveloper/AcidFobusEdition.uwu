/*     */ package me.earth.phobos.features.modules.cul;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.BlockInteractHelper;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketEntityAction;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortalBuilder
/*     */   extends Module
/*     */ {
/*  26 */   public Setting<Boolean> rotate = register(new Setting("Rotate", Boolean.valueOf(true)));
/*  27 */   private final Setting<Integer> tick_for_place = register(new Setting("BPT", Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(8)));
/*  28 */   Vec3d[] targets = new Vec3d[] { new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 2.0D), new Vec3d(1.0D, 1.0D, 3.0D), new Vec3d(1.0D, 2.0D, 0.0D), new Vec3d(1.0D, 3.0D, 0.0D), new Vec3d(1.0D, 4.0D, 0.0D), new Vec3d(1.0D, 5.0D, 0.0D), new Vec3d(1.0D, 5.0D, 1.0D), new Vec3d(1.0D, 5.0D, 2.0D), new Vec3d(1.0D, 5.0D, 3.0D), new Vec3d(1.0D, 4.0D, 3.0D), new Vec3d(1.0D, 3.0D, 3.0D), new Vec3d(1.0D, 2.0D, 3.0D) };
/*  29 */   int new_slot = 0;
/*  30 */   int old_slot = 0;
/*  31 */   int y_level = 0;
/*  32 */   int tick_runs = 0;
/*  33 */   int blocks_placed = 0;
/*  34 */   int offset_step = 0;
/*     */   boolean sneak = false;
/*     */   
/*     */   public PortalBuilder() {
/*  38 */     super("PortalBuilder", "Auto nether portal.", Module.Category.CUL, true, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  43 */     if (mc.field_71439_g != null) {
/*  44 */       this.old_slot = mc.field_71439_g.field_71071_by.field_70461_c;
/*  45 */       this.new_slot = find_in_hotbar();
/*  46 */       if (this.new_slot == -1) {
/*  47 */         Command.sendMessage(ChatFormatting.RED + "Cannot find obi in hotbar!");
/*  48 */         toggle();
/*     */       } 
/*  50 */       this.y_level = (int)Math.round(mc.field_71439_g.field_70163_u);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  56 */     if (mc.field_71439_g != null) {
/*  57 */       if (this.new_slot != this.old_slot && this.old_slot != -1) {
/*  58 */         mc.field_71439_g.field_71071_by.field_70461_c = this.old_slot;
/*     */       }
/*  60 */       if (this.sneak) {
/*  61 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
/*  62 */         this.sneak = false;
/*     */       } 
/*  64 */       this.old_slot = -1;
/*  65 */       this.new_slot = -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  71 */     if (mc.field_71439_g != null) {
/*  72 */       this.blocks_placed = 0;
/*  73 */       while (this.blocks_placed < ((Integer)this.tick_for_place.getValue()).intValue()) {
/*  74 */         if (this.offset_step >= this.targets.length) {
/*  75 */           this.offset_step = 0;
/*     */           break;
/*     */         } 
/*  78 */         BlockPos offsetPos = new BlockPos(this.targets[this.offset_step]);
/*  79 */         BlockPos targetPos = (new BlockPos(mc.field_71439_g.func_174791_d())).func_177982_a(offsetPos.func_177958_n(), offsetPos.func_177956_o(), offsetPos.func_177952_p()).func_177977_b();
/*  80 */         boolean try_to_place = true;
/*  81 */         if (!mc.field_71441_e.func_180495_p(targetPos).func_185904_a().func_76222_j()) {
/*  82 */           try_to_place = false;
/*     */         }
/*  84 */         for (Entity entity : mc.field_71441_e.func_72839_b(null, new AxisAlignedBB(targetPos))) {
/*  85 */           if (entity instanceof net.minecraft.entity.item.EntityItem || entity instanceof net.minecraft.entity.item.EntityXPOrb)
/*  86 */             continue;  try_to_place = false;
/*     */         } 
/*     */         
/*  89 */         if (try_to_place && place_blocks(targetPos)) {
/*  90 */           this.blocks_placed++;
/*     */         }
/*  92 */         this.offset_step++;
/*     */       } 
/*  94 */       if (this.blocks_placed > 0 && this.new_slot != this.old_slot) {
/*  95 */         mc.field_71439_g.field_71071_by.field_70461_c = this.old_slot;
/*     */       }
/*  97 */       this.tick_runs++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean place_blocks(BlockPos pos) {
/* 102 */     if (!mc.field_71441_e.func_180495_p(pos).func_185904_a().func_76222_j()) {
/* 103 */       return false;
/*     */     }
/* 105 */     if (!BlockInteractHelper.checkForNeighbours(pos))
/* 106 */       return false;  EnumFacing[] arrayOfEnumFacing; int i;
/*     */     byte b;
/* 108 */     for (arrayOfEnumFacing = EnumFacing.values(), i = arrayOfEnumFacing.length, b = 0; b < i; ) { EnumFacing side = arrayOfEnumFacing[b];
/* 109 */       BlockPos neighbor = pos.func_177972_a(side);
/* 110 */       EnumFacing side2 = side.func_176734_d();
/* 111 */       if (!BlockInteractHelper.canBeClicked(neighbor)) { b++; continue; }
/* 112 */        mc.field_71439_g.field_71071_by.field_70461_c = this.new_slot;
/* 113 */       Block neighborPos = mc.field_71441_e.func_180495_p(neighbor).func_177230_c();
/* 114 */       if (BlockInteractHelper.blackList.contains(neighborPos)) {
/* 115 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
/* 116 */         this.sneak = true;
/*     */       } 
/* 118 */       Vec3d hitVec = (new Vec3d((Vec3i)neighbor)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(side2.func_176730_m())).func_186678_a(0.5D));
/* 119 */       if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 120 */         BlockInteractHelper.faceVectorPacketInstant(hitVec);
/*     */       }
/* 122 */       mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
/* 123 */       mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
/* 124 */       return true; }
/*     */     
/* 126 */     return false;
/*     */   }
/*     */   
/*     */   private int find_in_hotbar() {
/* 130 */     for (int i = 0; i < 9; ) {
/*     */       
/* 132 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i); Block block;
/* 133 */       if (stack == ItemStack.field_190927_a || !(stack.func_77973_b() instanceof ItemBlock) || !(block = ((ItemBlock)stack.func_77973_b()).func_179223_d() instanceof net.minecraft.block.BlockObsidian)) { i++; continue; }
/* 134 */        return i;
/*     */     } 
/* 136 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\PortalBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */