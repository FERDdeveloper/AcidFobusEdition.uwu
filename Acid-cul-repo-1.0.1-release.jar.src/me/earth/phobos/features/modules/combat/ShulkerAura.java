/*     */ package me.earth.phobos.features.modules.combat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ 
/*     */ public class ShulkerAura extends Module {
/*     */   public final List<Block> blackList;
/*     */   public final List<Block> shulkerList;
/*     */   private int oldSlot;
/*     */   private int shulkerSlot;
/*     */   private int crystalSlot;
/*     */   private int waitTicks;
/*     */   private boolean doShulker;
/*     */   private boolean doCrystal;
/*     */   private boolean openShulker;
/*     */   private boolean detonate;
/*     */   
/*     */   public ShulkerAura() {
/*  33 */     super("ShulkerAura", "Uses shulkers to push crystals into enemies", Module.Category.COMBAT, true, false, false);
/*     */ 
/*     */     
/*  36 */     this.blackList = Arrays.asList(new Block[] { Blocks.field_150477_bB, (Block)Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150462_ai, Blocks.field_150467_bQ, Blocks.field_150382_bo, (Block)Blocks.field_150438_bZ, Blocks.field_150409_cd, Blocks.field_150367_z, Blocks.field_150415_aT, Blocks.field_150381_bn });
/*  37 */     this.shulkerList = Arrays.asList(new Block[] { Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     this.debug = register(new Setting("Debug", Boolean.valueOf(false)));
/*  56 */     this.rotate = register(new Setting("Rotation Lock", Boolean.valueOf(false)));
/*  57 */     this.antiWeakness = register(new Setting("Anti Weakness", Boolean.valueOf(false)));
/*  58 */     this.detonateDelay = register(new Setting("Detonate Delay", Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(10)));
/*  59 */     this.endDelay = register(new Setting("Await Delay", Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(10)));
/*  60 */     this.restartDelay = register(new Setting("Attempt Delay", Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(10)));
/*     */   } private boolean finishedDetonate; private BlockPos shulkerSpot; private BlockPos crystalSpot; private direction spoofDirection; private EntityPlayer target; private Setting<Boolean> debug; private Setting<Boolean> rotate; private Setting<Boolean> antiWeakness; private Setting<Integer> detonateDelay; private Setting<Integer> endDelay; private Setting<Integer> restartDelay; private enum direction {
/*     */     NORTH, SOUTH, EAST, WEST; }
/*     */   public void onEnable() {
/*  64 */     this.spoofDirection = direction.NORTH;
/*  65 */     this.target = null;
/*  66 */     this.oldSlot = -1;
/*  67 */     this.doShulker = false;
/*  68 */     this.doCrystal = false;
/*  69 */     this.openShulker = false;
/*  70 */     this.detonate = false;
/*  71 */     this.finishedDetonate = false;
/*  72 */     this.crystalSlot = -1;
/*  73 */     this.shulkerSlot = -1;
/*  74 */     this.waitTicks = 0;
/*     */   }
/*     */   
/*     */   private EntityPlayer getTarget() {
/*  78 */     EntityPlayer temp = null;
/*     */     
/*  80 */     for (EntityPlayer player : mc.field_71441_e.field_73010_i) {
/*  81 */       if (player != null && player != mc.field_71439_g && player.func_110143_aJ() > 0.0F && mc.field_71439_g.func_70032_d((Entity)player) < 5.0F && 
/*  82 */         !Phobos.friendManager.isFriend(player.func_70005_c_())) {
/*  83 */         temp = player;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  88 */     if (temp != null && (
/*  89 */       (Boolean)this.debug.getValue()).booleanValue()) Command.sendMessage("Target Set: " + temp.func_70005_c_());
/*     */     
/*  91 */     return temp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  97 */     if (this.doShulker) {
/*  98 */       if (mc.field_71439_g.field_71071_by.field_70461_c != this.shulkerSlot) {
/*  99 */         if (((Boolean)this.debug.getValue()).booleanValue())
/* 100 */           Command.sendMessage("Swapping to slot " + this.shulkerSlot); 
/* 101 */         mc.field_71439_g.field_71071_by.field_70461_c = this.shulkerSlot;
/*     */         
/*     */         return;
/*     */       } 
/* 105 */       placeBlock(this.shulkerSpot);
/*     */       
/* 107 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.oldSlot));
/*     */       
/* 109 */       this.doShulker = false;
/*     */       
/*     */       return;
/*     */     } 
/* 113 */     if (this.doCrystal) {
/* 114 */       if (mc.field_71439_g.field_71071_by.field_70461_c != this.crystalSlot) {
/* 115 */         if (((Boolean)this.debug.getValue()).booleanValue())
/* 116 */           Command.sendMessage("Swapping to slot " + this.crystalSlot); 
/* 117 */         mc.field_71439_g.field_71071_by.field_70461_c = this.crystalSlot;
/*     */         
/*     */         return;
/*     */       } 
/* 121 */       placeCrystal(this.crystalSpot);
/*     */       
/* 123 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.oldSlot));
/*     */       
/* 125 */       this.doCrystal = false;
/* 126 */       this.openShulker = true;
/*     */       
/*     */       return;
/*     */     } 
/* 130 */     if (this.openShulker) {
/* 131 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(this.shulkerSpot, EnumFacing.UP, EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
/* 132 */       if (mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiShulkerBox) {
/* 133 */         if (((Boolean)this.debug.getValue()).booleanValue()) Command.sendMessage("Closing Shulker"); 
/* 134 */         mc.field_71439_g.func_175159_q();
/* 135 */         this.openShulker = false;
/* 136 */         this.detonate = true;
/* 137 */         this.waitTicks = 0;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 142 */     if (this.detonate) {
/*     */       
/* 144 */       if (this.waitTicks++ > ((Integer)this.detonateDelay.getValue()).intValue()) {
/*     */         
/* 146 */         if (this.waitTicks - ((Integer)this.detonateDelay.getValue()).intValue() > ((Integer)this.restartDelay.getValue()).intValue()) {
/* 147 */           if (((Boolean)this.debug.getValue()).booleanValue()) Command.sendMessage("Re-Attempting"); 
/* 148 */           this.detonate = false;
/* 149 */           this.doCrystal = true;
/*     */         } 
/*     */         
/* 152 */         for (Entity e : mc.field_71441_e.field_72996_f) {
/* 153 */           if (e instanceof net.minecraft.entity.item.EntityEnderCrystal && 
/* 154 */             e != null && 
/* 155 */             !e.field_70128_L && 
/* 156 */             mc.field_71439_g.func_70032_d(e) < 5.0F) {
/* 157 */             mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(e));
/* 158 */             mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
/* 159 */             this.detonate = false;
/* 160 */             this.finishedDetonate = true;
/* 161 */             this.waitTicks = 0;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 172 */     if (this.finishedDetonate) {
/* 173 */       if (this.waitTicks++ > ((Integer)this.endDelay.getValue()).intValue()) {
/* 174 */         this.finishedDetonate = false;
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 180 */     this.target = getTarget();
/*     */     
/* 182 */     this.oldSlot = mc.field_71439_g.field_71071_by.field_70461_c;
/*     */     
/* 184 */     if (this.target != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 196 */       Vec3d offset1 = this.target.func_174791_d().func_72441_c(1.0D, 0.0D, 0.0D);
/* 197 */       Vec3d offset2 = this.target.func_174791_d().func_72441_c(2.0D, 0.0D, 0.0D);
/* 198 */       Vec3d offset3 = this.target.func_174791_d().func_72441_c(3.0D, 0.0D, 0.0D);
/* 199 */       Vec3d offset4 = this.target.func_174791_d().func_72441_c(1.0D, 1.0D, 0.0D);
/* 200 */       Vec3d offset5 = this.target.func_174791_d().func_72441_c(2.0D, 1.0D, 0.0D);
/* 201 */       Vec3d offset6 = this.target.func_174791_d().func_72441_c(3.0D, 1.0D, 0.0D);
/*     */       
/* 203 */       if (mc.field_71441_e.func_180495_p(new BlockPos(offset1)).func_177230_c() != Blocks.field_150350_a && 
/* 204 */         mc.field_71441_e.func_180495_p(new BlockPos(offset2)).func_177230_c() != Blocks.field_150350_a && 
/* 205 */         mc.field_71441_e.func_180495_p(new BlockPos(offset3)).func_177230_c() != Blocks.field_150350_a && 
/* 206 */         mc.field_71441_e.func_180495_p(new BlockPos(offset4)).func_177230_c() == Blocks.field_150350_a && (
/* 207 */         mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c() == Blocks.field_150350_a || this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) && 
/* 208 */         mc.field_71441_e.func_180495_p(new BlockPos(offset6)).func_177230_c() != Blocks.field_150350_a) {
/* 209 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 210 */           Command.sendMessage("Target is vulnerable!");
/*     */         }
/* 212 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 213 */           Command.sendMessage("Method 1");
/*     */         }
/* 215 */         this.spoofDirection = direction.EAST;
/*     */         
/* 217 */         this.shulkerSlot = -1;
/* 218 */         this.crystalSlot = -1;
/*     */         
/* 220 */         if (this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) {
/* 221 */           if (((Boolean)this.debug.getValue()).booleanValue())
/* 222 */             Command.sendMessage("Shulker already in place."); 
/* 223 */           this.shulkerSlot = 1337;
/*     */         } else {
/* 225 */           for (Block b : this.shulkerList) {
/* 226 */             if (findHotbarBlock(b) != -1) {
/* 227 */               this.shulkerSlot = findHotbarBlock(b);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 233 */         this.crystalSlot = findHotbarItem(Items.field_185158_cP);
/*     */         
/* 235 */         if (this.shulkerSlot != -1 && 
/* 236 */           this.crystalSlot != -1) {
/* 237 */           this.shulkerSpot = new BlockPos(offset5);
/* 238 */           this.crystalSpot = new BlockPos(offset1);
/* 239 */           if (!this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c()))
/* 240 */             this.doShulker = true; 
/* 241 */           this.doCrystal = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 262 */       offset1 = this.target.func_174791_d().func_72441_c(-1.0D, 0.0D, 0.0D);
/* 263 */       offset2 = this.target.func_174791_d().func_72441_c(-2.0D, 0.0D, 0.0D);
/* 264 */       offset3 = this.target.func_174791_d().func_72441_c(-3.0D, 0.0D, 0.0D);
/* 265 */       offset4 = this.target.func_174791_d().func_72441_c(-1.0D, 1.0D, 0.0D);
/* 266 */       offset5 = this.target.func_174791_d().func_72441_c(-2.0D, 1.0D, 0.0D);
/* 267 */       offset6 = this.target.func_174791_d().func_72441_c(-3.0D, 1.0D, 0.0D);
/*     */       
/* 269 */       if (mc.field_71441_e.func_180495_p(new BlockPos(offset1)).func_177230_c() != Blocks.field_150350_a && 
/* 270 */         mc.field_71441_e.func_180495_p(new BlockPos(offset2)).func_177230_c() != Blocks.field_150350_a && 
/* 271 */         mc.field_71441_e.func_180495_p(new BlockPos(offset3)).func_177230_c() != Blocks.field_150350_a && 
/* 272 */         mc.field_71441_e.func_180495_p(new BlockPos(offset4)).func_177230_c() == Blocks.field_150350_a && (
/* 273 */         mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c() == Blocks.field_150350_a || this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) && 
/* 274 */         mc.field_71441_e.func_180495_p(new BlockPos(offset6)).func_177230_c() != Blocks.field_150350_a) {
/* 275 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 276 */           Command.sendMessage("Target is vulnerable!");
/*     */         }
/* 278 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 279 */           Command.sendMessage("Method 2");
/*     */         }
/* 281 */         this.spoofDirection = direction.WEST;
/*     */         
/* 283 */         this.shulkerSlot = -1;
/* 284 */         this.crystalSlot = -1;
/*     */         
/* 286 */         if (this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) {
/* 287 */           if (((Boolean)this.debug.getValue()).booleanValue())
/* 288 */             Command.sendMessage("Shulker already in place."); 
/* 289 */           this.shulkerSlot = 1337;
/*     */         } else {
/* 291 */           for (Block b : this.shulkerList) {
/* 292 */             if (findHotbarBlock(b) != -1) {
/* 293 */               this.shulkerSlot = findHotbarBlock(b);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 299 */         this.crystalSlot = findHotbarItem(Items.field_185158_cP);
/*     */         
/* 301 */         if (this.shulkerSlot != -1 && 
/* 302 */           this.crystalSlot != -1) {
/* 303 */           this.shulkerSpot = new BlockPos(offset5);
/* 304 */           this.crystalSpot = new BlockPos(offset1);
/* 305 */           if (!this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c()))
/* 306 */             this.doShulker = true; 
/* 307 */           this.doCrystal = true;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 319 */       offset1 = this.target.func_174791_d().func_72441_c(0.0D, 0.0D, 1.0D);
/* 320 */       offset2 = this.target.func_174791_d().func_72441_c(0.0D, 0.0D, 2.0D);
/* 321 */       offset3 = this.target.func_174791_d().func_72441_c(0.0D, 0.0D, 3.0D);
/* 322 */       offset4 = this.target.func_174791_d().func_72441_c(0.0D, 1.0D, 1.0D);
/* 323 */       offset5 = this.target.func_174791_d().func_72441_c(0.0D, 1.0D, 2.0D);
/* 324 */       offset6 = this.target.func_174791_d().func_72441_c(0.0D, 1.0D, 3.0D);
/*     */       
/* 326 */       if (mc.field_71441_e.func_180495_p(new BlockPos(offset1)).func_177230_c() != Blocks.field_150350_a && 
/* 327 */         mc.field_71441_e.func_180495_p(new BlockPos(offset2)).func_177230_c() != Blocks.field_150350_a && 
/* 328 */         mc.field_71441_e.func_180495_p(new BlockPos(offset3)).func_177230_c() != Blocks.field_150350_a && 
/* 329 */         mc.field_71441_e.func_180495_p(new BlockPos(offset4)).func_177230_c() == Blocks.field_150350_a && (
/* 330 */         mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c() == Blocks.field_150350_a || this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) && 
/* 331 */         mc.field_71441_e.func_180495_p(new BlockPos(offset6)).func_177230_c() != Blocks.field_150350_a) {
/* 332 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 333 */           Command.sendMessage("Target is vulnerable!");
/*     */         }
/* 335 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 336 */           Command.sendMessage("Method 3");
/*     */         }
/* 338 */         this.spoofDirection = direction.SOUTH;
/*     */         
/* 340 */         this.shulkerSlot = -1;
/* 341 */         this.crystalSlot = -1;
/*     */         
/* 343 */         if (this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) {
/* 344 */           if (((Boolean)this.debug.getValue()).booleanValue())
/* 345 */             Command.sendMessage("Shulker already in place."); 
/* 346 */           this.shulkerSlot = 1337;
/*     */         } else {
/* 348 */           for (Block b : this.shulkerList) {
/* 349 */             if (findHotbarBlock(b) != -1) {
/* 350 */               this.shulkerSlot = findHotbarBlock(b);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 356 */         this.crystalSlot = findHotbarItem(Items.field_185158_cP);
/*     */         
/* 358 */         if (this.shulkerSlot != -1 && 
/* 359 */           this.crystalSlot != -1) {
/* 360 */           this.shulkerSpot = new BlockPos(offset5);
/* 361 */           this.crystalSpot = new BlockPos(offset1);
/* 362 */           if (!this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c()))
/* 363 */             this.doShulker = true; 
/* 364 */           this.doCrystal = true;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 376 */       offset1 = this.target.func_174791_d().func_72441_c(0.0D, 0.0D, -1.0D);
/* 377 */       offset2 = this.target.func_174791_d().func_72441_c(0.0D, 0.0D, -2.0D);
/* 378 */       offset3 = this.target.func_174791_d().func_72441_c(0.0D, 0.0D, -3.0D);
/* 379 */       offset4 = this.target.func_174791_d().func_72441_c(0.0D, 1.0D, -1.0D);
/* 380 */       offset5 = this.target.func_174791_d().func_72441_c(0.0D, 1.0D, -2.0D);
/* 381 */       offset6 = this.target.func_174791_d().func_72441_c(0.0D, 1.0D, -3.0D);
/*     */       
/* 383 */       if (mc.field_71441_e.func_180495_p(new BlockPos(offset1)).func_177230_c() != Blocks.field_150350_a && 
/* 384 */         mc.field_71441_e.func_180495_p(new BlockPos(offset2)).func_177230_c() != Blocks.field_150350_a && 
/* 385 */         mc.field_71441_e.func_180495_p(new BlockPos(offset3)).func_177230_c() != Blocks.field_150350_a && 
/* 386 */         mc.field_71441_e.func_180495_p(new BlockPos(offset4)).func_177230_c() == Blocks.field_150350_a && (
/* 387 */         mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c() == Blocks.field_150350_a || this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) && 
/* 388 */         mc.field_71441_e.func_180495_p(new BlockPos(offset6)).func_177230_c() != Blocks.field_150350_a) {
/* 389 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 390 */           Command.sendMessage("Target is vulnerable!");
/*     */         }
/* 392 */         if (((Boolean)this.debug.getValue()).booleanValue()) {
/* 393 */           Command.sendMessage("Method 4");
/*     */         }
/* 395 */         this.spoofDirection = direction.NORTH;
/*     */         
/* 397 */         this.shulkerSlot = -1;
/* 398 */         this.crystalSlot = -1;
/*     */         
/* 400 */         if (this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c())) {
/* 401 */           if (((Boolean)this.debug.getValue()).booleanValue())
/* 402 */             Command.sendMessage("Shulker already in place."); 
/* 403 */           this.shulkerSlot = 1337;
/*     */         } else {
/* 405 */           for (Block b : this.shulkerList) {
/* 406 */             if (findHotbarBlock(b) != -1) {
/* 407 */               this.shulkerSlot = findHotbarBlock(b);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 413 */         this.crystalSlot = findHotbarItem(Items.field_185158_cP);
/*     */         
/* 415 */         if (this.shulkerSlot != -1 && 
/* 416 */           this.crystalSlot != -1) {
/* 417 */           this.shulkerSpot = new BlockPos(offset5);
/* 418 */           this.crystalSpot = new BlockPos(offset1);
/* 419 */           if (!this.shulkerList.contains(mc.field_71441_e.func_180495_p(new BlockPos(offset5)).func_177230_c()))
/* 420 */             this.doShulker = true; 
/* 421 */           this.doCrystal = true;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int findHotbarItem(Item itemIn) {
/* 437 */     for (int i = 0; i < 9; ) {
/*     */       
/* 439 */       ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i); Item item;
/* 440 */       if (stack == ItemStack.field_190927_a || !(stack.func_77973_b() instanceof Item) || (item = stack.func_77973_b()) != itemIn) {
/*     */         i++; continue;
/* 442 */       }  return i;
/*     */     } 
/* 444 */     return -1;
/*     */   }
/*     */   
/*     */   public int findHotbarBlock(Block blockIn) {
/* 448 */     for (int i = 0; i < 9; ) {
/*     */       
/* 450 */       ItemStack stack = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i); Block block;
/* 451 */       if (stack == ItemStack.field_190927_a || !(stack.func_77973_b() instanceof ItemBlock) || (block = ((ItemBlock)stack.func_77973_b()).func_179223_d()) != blockIn) {
/*     */         i++; continue;
/* 453 */       }  return i;
/*     */     } 
/* 455 */     return -1;
/*     */   }
/*     */   
/*     */   private void placeCrystal(BlockPos pos) {
/* 459 */     if (((Boolean)this.debug.getValue()).booleanValue())
/* 460 */       Command.sendMessage("Debug " + pos); 
/* 461 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos, EnumFacing.UP, EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
/*     */   }
/*     */   
/*     */   private void placeBlock(BlockPos pos) {
/* 465 */     if (this.spoofDirection == direction.NORTH) {
/* 466 */       if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 467 */         mc.field_71439_g.field_70177_z = 180.0F;
/*     */       }
/* 469 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(180.0F, 0.0F, mc.field_71439_g.field_70122_E));
/* 470 */     } else if (this.spoofDirection == direction.SOUTH) {
/* 471 */       if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 472 */         mc.field_71439_g.field_70177_z = 0.0F;
/*     */       }
/* 474 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(0.0F, 0.0F, mc.field_71439_g.field_70122_E));
/* 475 */     } else if (this.spoofDirection == direction.WEST) {
/* 476 */       if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 477 */         mc.field_71439_g.field_70177_z = 90.0F;
/*     */       }
/* 479 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(90.0F, 0.0F, mc.field_71439_g.field_70122_E));
/* 480 */     } else if (this.spoofDirection == direction.EAST) {
/* 481 */       if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 482 */         mc.field_71439_g.field_70177_z = -90.0F;
/*     */       }
/* 484 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(-90.0F, 0.0F, mc.field_71439_g.field_70122_E));
/*     */     } 
/*     */     
/* 487 */     boolean isSneaking = placeBlock(pos, EnumHand.MAIN_HAND, false, true, mc.field_71439_g.func_70093_af());
/*     */     
/* 489 */     if (isSneaking) {
/* 490 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean placeBlock(BlockPos pos, EnumHand hand, boolean rotate, boolean packet, boolean isSneaking) {
/* 496 */     boolean sneaking = false;
/* 497 */     EnumFacing side = getFirstFacing(pos);
/* 498 */     if (side == null) {
/* 499 */       return isSneaking;
/*     */     }
/* 501 */     BlockPos neighbour = pos.func_177972_a(side);
/* 502 */     EnumFacing opposite = side.func_176734_d();
/* 503 */     Vec3d hitVec = (new Vec3d((Vec3i)neighbour)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(opposite.func_176730_m())).func_186678_a(0.5D));
/* 504 */     Block neighbourBlock = mc.field_71441_e.func_180495_p(neighbour).func_177230_c();
/* 505 */     if (!mc.field_71439_g.func_70093_af() && (this.blackList.contains(neighbourBlock) || this.shulkerList.contains(neighbourBlock))) {
/* 506 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
/* 507 */       mc.field_71439_g.func_70095_a(true);
/* 508 */       sneaking = true;
/*     */     } 
/* 510 */     if (rotate) {
/* 511 */       RotationUtil.faceVector(hitVec, true);
/*     */     }
/*     */     
/* 514 */     rightClickBlock(neighbour, hitVec, hand, opposite, packet);
/*     */     
/* 516 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
/* 517 */     mc.field_71467_ac = 4;
/* 518 */     return (sneaking || isSneaking);
/*     */   }
/*     */   
/*     */   public EnumFacing getFirstFacing(BlockPos pos) {
/* 522 */     Iterator<EnumFacing> iterator = getPossibleSides(pos).iterator();
/* 523 */     if (iterator.hasNext()) {
/* 524 */       EnumFacing facing = iterator.next();
/* 525 */       return facing;
/*     */     } 
/* 527 */     return null;
/*     */   }
/*     */   
/*     */   public List<EnumFacing> getPossibleSides(BlockPos pos) {
/* 531 */     ArrayList<EnumFacing> facings = new ArrayList<>();
/*     */     
/* 533 */     List<EnumFacing> directions = new ArrayList<>();
/* 534 */     directions.add(EnumFacing.NORTH);
/* 535 */     directions.add(EnumFacing.SOUTH);
/* 536 */     directions.add(EnumFacing.EAST);
/* 537 */     directions.add(EnumFacing.WEST);
/*     */     
/* 539 */     for (EnumFacing side : directions) {
/*     */       
/* 541 */       BlockPos neighbour = pos.func_177972_a(side); IBlockState blockState;
/* 542 */       if (!mc.field_71441_e.func_180495_p(neighbour).func_177230_c().func_176209_a(mc.field_71441_e.func_180495_p(neighbour), false) || (blockState = mc.field_71441_e.func_180495_p(neighbour)).func_185904_a().func_76222_j())
/*     */         continue; 
/* 544 */       facings.add(side);
/*     */     } 
/* 546 */     return facings;
/*     */   }
/*     */   
/*     */   public void rightClickBlock(BlockPos pos, Vec3d vec, EnumHand hand, EnumFacing enumFacing, boolean packet) {
/* 550 */     if (packet) {
/* 551 */       float f = (float)(vec.field_72450_a - pos.func_177958_n());
/* 552 */       float f1 = (float)(vec.field_72448_b - pos.func_177956_o());
/* 553 */       float f2 = (float)(vec.field_72449_c - pos.func_177952_p());
/* 554 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos, enumFacing, hand, f, f1, f2));
/*     */     } else {
/* 556 */       mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, pos, enumFacing, vec, hand);
/*     */     } 
/* 558 */     mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
/* 559 */     mc.field_71467_ac = 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\combat\ShulkerAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */