/*     */ package me.earth.phobos.features.modules.combat;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.EntityUtil;
/*     */ import me.earth.phobos.util.MathUtil;
/*     */ import me.earth.phobos.util.Timer;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.item.EntityEnderCrystal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
/*     */ import net.minecraft.network.play.client.CPacketUseEntity;
/*     */ import net.minecraft.network.play.server.SPacketSpawnObject;
/*     */ import net.minecraft.util.CombatRules;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class AutoCrystalCustom extends Module {
/*  46 */   private final Timer placeTimer = new Timer();
/*     */   
/*  48 */   private final Timer breakTimer = new Timer();
/*     */   
/*  50 */   private final Timer preditTimer = new Timer();
/*     */   
/*  52 */   private final Timer manualTimer = new Timer();
/*     */   
/*  54 */   private final Setting<Integer> attackFactor = register(new Setting("PredictDelay", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(200)));
/*     */   
/*  56 */   private final Setting<Integer> red = register(new Setting("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
/*     */   
/*  58 */   private final Setting<Integer> green = register(new Setting("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/*     */   
/*  60 */   private final Setting<Integer> blue = register(new Setting("Blue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
/*     */   
/*  62 */   private final Setting<Integer> alpha = register(new Setting("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/*     */   
/*  64 */   private final Setting<Integer> boxAlpha = register(new Setting("BoxAlpha", Integer.valueOf(125), Integer.valueOf(0), Integer.valueOf(255)));
/*     */   
/*  66 */   private final Setting<Float> lineWidth = register(new Setting("LineWidth", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(5.0F)));
/*     */   
/*  68 */   public Setting<Boolean> place = register(new Setting("Place", Boolean.valueOf(true)));
/*     */   
/*  70 */   public Setting<Float> placeDelay = register(new Setting("PlaceDelay", Float.valueOf(4.0F), Float.valueOf(0.0F), Float.valueOf(300.0F)));
/*     */   
/*  72 */   public Setting<Float> placeRange = register(new Setting("PlaceRange", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(7.0F)));
/*     */   
/*  74 */   public Setting<Boolean> explode = register(new Setting("Break", Boolean.valueOf(true)));
/*     */   
/*  76 */   public Setting<Boolean> packetBreak = register(new Setting("PacketBreak", Boolean.valueOf(true)));
/*     */   
/*  78 */   public Setting<Boolean> predicts = register(new Setting("Predict", Boolean.valueOf(true)));
/*     */   
/*  80 */   public Setting<Boolean> rotate = register(new Setting("Rotate", Boolean.valueOf(true)));
/*     */   
/*  82 */   public Setting<Float> breakDelay = register(new Setting("BreakDelay", Float.valueOf(4.0F), Float.valueOf(0.0F), Float.valueOf(300.0F)));
/*     */   
/*  84 */   public Setting<Float> breakRange = register(new Setting("BreakRange", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(7.0F)));
/*     */   
/*  86 */   public Setting<Float> breakWallRange = register(new Setting("BreakWallRange", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(7.0F)));
/*     */   
/*  88 */   public Setting<Boolean> opPlace = register(new Setting("1.13 Place", Boolean.valueOf(true)));
/*     */   
/*  90 */   public Setting<Boolean> suicide = register(new Setting("AntiSuicide", Boolean.valueOf(true)));
/*     */   
/*  92 */   public Setting<Boolean> autoswitch = register(new Setting("AutoSwitch", Boolean.valueOf(true)));
/*     */   
/*  94 */   public Setting<Boolean> ignoreUseAmount = register(new Setting("IgnoreUseAmount", Boolean.valueOf(true)));
/*     */   
/*  96 */   public Setting<Integer> wasteAmount = register(new Setting("UseAmount", Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(5)));
/*     */   
/*  98 */   public Setting<Boolean> facePlaceSword = register(new Setting("FacePlaceSword", Boolean.valueOf(true)));
/*     */   
/* 100 */   public Setting<Float> targetRange = register(new Setting("TargetRange", Float.valueOf(4.0F), Float.valueOf(1.0F), Float.valueOf(12.0F)));
/*     */   
/* 102 */   public Setting<Float> minDamage = register(new Setting("MinDamage", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(20.0F)));
/*     */   
/* 104 */   public Setting<Float> facePlace = register(new Setting("FacePlaceHP", Float.valueOf(4.0F), Float.valueOf(0.0F), Float.valueOf(36.0F)));
/*     */   
/* 106 */   public Setting<Float> breakMaxSelfDamage = register(new Setting("BreakMaxSelf", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(12.0F)));
/*     */   
/* 108 */   public Setting<Float> breakMinDmg = register(new Setting("BreakMinDmg", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(7.0F)));
/*     */   
/* 110 */   public Setting<Float> minArmor = register(new Setting("MinArmor", Float.valueOf(4.0F), Float.valueOf(0.1F), Float.valueOf(80.0F)));
/*     */   
/* 112 */   public Setting<SwingMode> swingMode = register(new Setting("Swing", SwingMode.MainHand));
/*     */   
/* 114 */   public Setting<Boolean> render = register(new Setting("Render", Boolean.valueOf(true)));
/*     */   
/* 116 */   public Setting<Boolean> renderDmg = register(new Setting("RenderDmg", Boolean.valueOf(true)));
/*     */   
/* 118 */   public Setting<Boolean> box = register(new Setting("Box", Boolean.valueOf(true)));
/*     */   
/* 120 */   public Setting<Boolean> outline = register(new Setting("Outline", Boolean.valueOf(true)));
/*     */   
/* 122 */   private final Setting<Integer> cRed = register(new Setting("OL-Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*     */   
/* 124 */   private final Setting<Integer> cGreen = register(new Setting("OL-Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*     */   
/* 126 */   private final Setting<Integer> cBlue = register(new Setting("OL-Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*     */   
/* 128 */   private final Setting<Integer> cAlpha = register(new Setting("OL-Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*     */   
/*     */   EntityEnderCrystal crystal;
/*     */   
/*     */   private EntityLivingBase target;
/*     */   
/*     */   private BlockPos pos;
/*     */   
/*     */   private int hotBarSlot;
/*     */   
/*     */   private boolean armor;
/*     */   
/*     */   private boolean armorTarget;
/*     */   
/*     */   private int crystalCount;
/*     */   
/*     */   private int predictWait;
/*     */   
/*     */   private int predictPackets;
/*     */   
/*     */   private boolean packetCalc;
/*     */   
/* 150 */   private float yaw = 0.0F;
/*     */   
/*     */   private EntityLivingBase realTarget;
/*     */   
/*     */   private int predict;
/*     */   
/* 156 */   private float pitch = 0.0F;
/*     */   
/*     */   private boolean rotating = false;
/*     */   
/*     */   public AutoCrystalCustom() {
/* 161 */     super("AutoCrystal+", "Automatically places crystals (custom version)", Module.Category.COMBAT, true, false, false);
/*     */   }
/*     */   
/*     */   public static List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
/* 165 */     ArrayList<BlockPos> circleblocks = new ArrayList<>();
/* 166 */     int cx = loc.func_177958_n();
/* 167 */     int cy = loc.func_177956_o();
/* 168 */     int cz = loc.func_177952_p();
/* 169 */     int x = cx - (int)r;
/* 170 */     while (x <= cx + r) {
/* 171 */       int z = cz - (int)r;
/* 172 */       while (z <= cz + r) {
/* 173 */         int y = sphere ? (cy - (int)r) : cy;
/*     */         while (true) {
/* 175 */           float f = sphere ? (cy + r) : (cy + h), f2 = f;
/* 176 */           if (y >= f)
/*     */             break; 
/* 178 */           double dist = ((cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0));
/* 179 */           if (dist < (r * r) && (!hollow || dist >= ((r - 1.0F) * (r - 1.0F)))) {
/* 180 */             BlockPos l = new BlockPos(x, y + plus_y, z);
/* 181 */             circleblocks.add(l);
/*     */           } 
/* 183 */           y++;
/*     */         } 
/* 185 */         z++;
/*     */       } 
/* 187 */       x++;
/*     */     } 
/* 189 */     return circleblocks;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketSend(PacketEvent.Send event) {
/* 194 */     if (event.getStage() == 0 && ((Boolean)this.rotate.getValue()).booleanValue() && this.rotating && event.getPacket() instanceof CPacketPlayer) {
/* 195 */       CPacketPlayer packet = (CPacketPlayer)event.getPacket();
/* 196 */       packet.field_149476_e = this.yaw;
/* 197 */       packet.field_149473_f = this.pitch;
/* 198 */       this.rotating = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void rotateTo(Entity entity) {
/* 203 */     if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 204 */       float[] angle = MathUtil.calcAngle(AutoCrystal.mc.field_71439_g.func_174824_e(mc.func_184121_ak()), entity.func_174791_d());
/* 205 */       this.yaw = angle[0];
/* 206 */       this.pitch = angle[1];
/* 207 */       this.rotating = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void rotateToPos(BlockPos pos) {
/* 212 */     if (((Boolean)this.rotate.getValue()).booleanValue()) {
/* 213 */       float[] angle = MathUtil.calcAngle(AutoCrystal.mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((pos.func_177958_n() + 0.5F), (pos.func_177956_o() - 0.5F), (pos.func_177952_p() + 0.5F)));
/* 214 */       this.yaw = angle[0];
/* 215 */       this.pitch = angle[1];
/* 216 */       this.rotating = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onEnable() {
/* 221 */     this.placeTimer.reset();
/* 222 */     this.breakTimer.reset();
/* 223 */     this.predictWait = 0;
/* 224 */     this.hotBarSlot = -1;
/* 225 */     this.pos = null;
/* 226 */     this.crystal = null;
/* 227 */     this.predict = 0;
/* 228 */     this.predictPackets = 1;
/* 229 */     this.target = null;
/* 230 */     this.packetCalc = false;
/* 231 */     this.realTarget = null;
/* 232 */     this.armor = false;
/* 233 */     this.armorTarget = false;
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 237 */     this.rotating = false;
/*     */   }
/*     */   
/*     */   public void onTick() {
/* 241 */     onCrystal();
/*     */   }
/*     */   
/*     */   public String getDisplayInfo() {
/* 245 */     if (this.realTarget != null)
/* 246 */       return this.realTarget.func_70005_c_(); 
/* 247 */     return null;
/*     */   }
/*     */   
/*     */   public void onCrystal() {
/* 251 */     if (AutoCrystal.mc.field_71441_e == null || AutoCrystal.mc.field_71439_g == null)
/*     */       return; 
/* 253 */     this.realTarget = null;
/* 254 */     manualBreaker();
/* 255 */     this.crystalCount = 0;
/* 256 */     if (!((Boolean)this.ignoreUseAmount.getValue()).booleanValue())
/* 257 */       for (Entity crystal : AutoCrystal.mc.field_71441_e.field_72996_f) {
/* 258 */         if (!(crystal instanceof EntityEnderCrystal) || !IsValidCrystal(crystal))
/*     */           continue; 
/* 260 */         boolean count = false;
/* 261 */         double damage = calculateDamage(this.target.func_180425_c().func_177958_n() + 0.5D, this.target.func_180425_c().func_177956_o() + 1.0D, this.target.func_180425_c().func_177952_p() + 0.5D, (Entity)this.target);
/* 262 */         if (damage >= ((Float)this.minDamage.getValue()).floatValue())
/* 263 */           count = true; 
/* 264 */         if (!count)
/*     */           continue; 
/* 266 */         this.crystalCount++;
/*     */       }  
/* 268 */     this.hotBarSlot = -1;
/* 269 */     if (AutoCrystal.mc.field_71439_g.func_184592_cb().func_77973_b() != Items.field_185158_cP) {
/* 270 */       int crystalSlot = (AutoCrystal.mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP) ? AutoCrystal.mc.field_71439_g.field_71071_by.field_70461_c : -1, n = crystalSlot;
/* 271 */       if (crystalSlot == -1)
/* 272 */         for (int l = 0; l < 9; ) {
/* 273 */           if (AutoCrystal.mc.field_71439_g.field_71071_by.func_70301_a(l).func_77973_b() != Items.field_185158_cP) {
/* 274 */             l++;
/*     */             continue;
/*     */           } 
/* 277 */           crystalSlot = l;
/* 278 */           this.hotBarSlot = l;
/*     */         }  
/* 280 */       if (crystalSlot == -1) {
/* 281 */         this.pos = null;
/* 282 */         this.target = null;
/*     */         return;
/*     */       } 
/*     */     } 
/* 286 */     if (AutoCrystal.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151153_ao && AutoCrystal.mc.field_71439_g.func_184614_ca().func_77973_b() != Items.field_185158_cP) {
/* 287 */       this.pos = null;
/* 288 */       this.target = null;
/*     */       return;
/*     */     } 
/* 291 */     if (this.target == null)
/* 292 */       this.target = (EntityLivingBase)getTarget(); 
/* 293 */     if (this.target == null) {
/* 294 */       this.crystal = null;
/*     */       return;
/*     */     } 
/* 297 */     if (this.target.func_70032_d((Entity)AutoCrystal.mc.field_71439_g) > 12.0F) {
/* 298 */       this.crystal = null;
/* 299 */       this.target = null;
/*     */     } 
/* 301 */     this.crystal = AutoCrystal.mc.field_71441_e.field_72996_f.stream().filter(this::IsValidCrystal).map(p_Entity -> (EntityEnderCrystal)p_Entity).min(Comparator.comparing(p_Entity -> Float.valueOf(this.target.func_70032_d((Entity)p_Entity)))).orElse(null);
/* 302 */     if (this.crystal != null && ((Boolean)this.explode.getValue()).booleanValue() && this.breakTimer.passedMs(((Float)this.breakDelay.getValue()).longValue())) {
/* 303 */       this.breakTimer.reset();
/* 304 */       if (((Boolean)this.packetBreak.getValue()).booleanValue()) {
/* 305 */         rotateTo((Entity)this.crystal);
/* 306 */         AutoCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity((Entity)this.crystal));
/*     */       } else {
/* 308 */         rotateTo((Entity)this.crystal);
/* 309 */         AutoCrystal.mc.field_71442_b.func_78764_a((EntityPlayer)AutoCrystal.mc.field_71439_g, (Entity)this.crystal);
/*     */       } 
/* 311 */       if (this.swingMode.getValue() == SwingMode.MainHand) {
/* 312 */         AutoCrystal.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
/* 313 */       } else if (this.swingMode.getValue() == SwingMode.OffHand) {
/* 314 */         AutoCrystal.mc.field_71439_g.func_184609_a(EnumHand.OFF_HAND);
/*     */       } 
/*     */     } 
/* 317 */     if (this.placeTimer.passedMs(((Float)this.placeDelay.getValue()).longValue()) && ((Boolean)this.place.getValue()).booleanValue()) {
/* 318 */       this.placeTimer.reset();
/* 319 */       double damage = 0.5D;
/* 320 */       for (BlockPos blockPos : placePostions(((Float)this.placeRange.getValue()).floatValue())) {
/*     */         double targetRange;
/* 322 */         if (blockPos == null || this.target == null || !AutoCrystal.mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(blockPos)).isEmpty() || (targetRange = this.target.func_70011_f(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p())) > ((Float)this.targetRange.getValue()).floatValue() || this.target.field_70128_L || this.target.func_110143_aJ() + this.target.func_110139_bj() <= 0.0F)
/*     */           continue; 
/* 324 */         double targetDmg = calculateDamage(blockPos.func_177958_n() + 0.5D, blockPos.func_177956_o() + 1.0D, blockPos.func_177952_p() + 0.5D, (Entity)this.target);
/* 325 */         this.armor = false;
/* 326 */         for (ItemStack is : this.target.func_184193_aE()) {
/* 327 */           float green = ((is.func_77958_k() - is.func_77952_i()) / is.func_77958_k());
/* 328 */           float red = 1.0F - green;
/* 329 */           int dmg = 100 - (int)(red * 100.0F);
/* 330 */           if (dmg > ((Float)this.minArmor.getValue()).floatValue())
/*     */             continue; 
/* 332 */           this.armor = true;
/*     */         } 
/* 334 */         if (targetDmg < ((Float)this.minDamage.getValue()).floatValue() && (((Boolean)this.facePlaceSword.getValue()).booleanValue() ? (this.target.func_110139_bj() + this.target.func_110143_aJ() > ((Float)this.facePlace.getValue()).floatValue()) : (AutoCrystal.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword || this.target.func_110139_bj() + this.target.func_110143_aJ() > ((Float)this.facePlace.getValue()).floatValue())) && (((Boolean)this.facePlaceSword.getValue()).booleanValue() ? !this.armor : (AutoCrystal.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword || !this.armor)))
/*     */           continue; 
/*     */         double selfDmg;
/* 337 */         if (((selfDmg = calculateDamage(blockPos.func_177958_n() + 0.5D, blockPos.func_177956_o() + 1.0D, blockPos.func_177952_p() + 0.5D, (Entity)AutoCrystal.mc.field_71439_g)) + (((Boolean)this.suicide.getValue()).booleanValue() ? 2.0D : 0.5D) >= (AutoCrystal.mc.field_71439_g.func_110143_aJ() + AutoCrystal.mc.field_71439_g.func_110139_bj()) && selfDmg >= targetDmg && targetDmg < (this.target.func_110143_aJ() + this.target.func_110139_bj())) || damage >= targetDmg)
/*     */           continue; 
/* 339 */         this.pos = blockPos;
/* 340 */         damage = targetDmg;
/*     */       } 
/* 342 */       if (damage == 0.5D) {
/* 343 */         this.pos = null;
/* 344 */         this.target = null;
/* 345 */         this.realTarget = null;
/*     */         return;
/*     */       } 
/* 348 */       if (this.hotBarSlot != -1 && ((Boolean)this.autoswitch.getValue()).booleanValue() && !AutoCrystal.mc.field_71439_g.func_70644_a(MobEffects.field_76437_t))
/* 349 */         AutoCrystal.mc.field_71439_g.field_71071_by.field_70461_c = this.hotBarSlot; 
/* 350 */       if (!((Boolean)this.ignoreUseAmount.getValue()).booleanValue()) {
/* 351 */         int crystalLimit = ((Integer)this.wasteAmount.getValue()).intValue();
/* 352 */         if (this.crystalCount >= crystalLimit)
/*     */           return; 
/* 354 */         if (damage < ((Float)this.minDamage.getValue()).floatValue())
/* 355 */           crystalLimit = 1; 
/* 356 */         if (this.crystalCount < crystalLimit && this.pos != null) {
/* 357 */           rotateToPos(this.pos);
/* 358 */           AutoCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(this.pos, EnumFacing.UP, (AutoCrystal.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
/*     */         } 
/* 360 */       } else if (this.pos != null) {
/* 361 */         rotateToPos(this.pos);
/* 362 */         AutoCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(this.pos, EnumFacing.UP, (AutoCrystal.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/*     */     SPacketSpawnObject packet;
/* 370 */     if (event.getPacket() instanceof SPacketSpawnObject && (packet = (SPacketSpawnObject)event.getPacket()).func_148993_l() == 51 && ((Boolean)this.predicts.getValue()).booleanValue() && this.preditTimer.passedMs(((Integer)this.attackFactor.getValue()).longValue()) && ((Boolean)this.predicts.getValue()).booleanValue() && ((Boolean)this.explode.getValue()).booleanValue() && ((Boolean)this.packetBreak.getValue()).booleanValue() && this.target != null) {
/* 371 */       if (!isPredicting(packet))
/*     */         return; 
/* 373 */       CPacketUseEntity predict = new CPacketUseEntity();
/* 374 */       predict.field_149567_a = packet.func_149001_c();
/* 375 */       predict.field_149566_b = CPacketUseEntity.Action.ATTACK;
/* 376 */       AutoCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)predict);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isPredicting(SPacketSpawnObject packet) {
/* 381 */     BlockPos packPos = new BlockPos(packet.func_186880_c(), packet.func_186882_d(), packet.func_186881_e());
/* 382 */     if (AutoCrystal.mc.field_71439_g.func_70011_f(packet.func_186880_c(), packet.func_186882_d(), packet.func_186881_e()) > ((Float)this.breakRange.getValue()).floatValue())
/* 383 */       return false; 
/* 384 */     if (!canSeePos(packPos) && AutoCrystal.mc.field_71439_g.func_70011_f(packet.func_186880_c(), packet.func_186882_d(), packet.func_186881_e()) > ((Float)this.breakWallRange.getValue()).floatValue())
/* 385 */       return false; 
/* 386 */     double targetDmg = calculateDamage(packet.func_186880_c() + 0.5D, packet.func_186882_d() + 1.0D, packet.func_186881_e() + 0.5D, (Entity)this.target);
/* 387 */     if (EntityUtil.isInHole((Entity)AutoCrystal.mc.field_71439_g) && targetDmg >= 1.0D)
/* 388 */       return true; 
/* 389 */     double selfDmg = calculateDamage(packet.func_186880_c() + 0.5D, packet.func_186882_d() + 1.0D, packet.func_186881_e() + 0.5D, (Entity)AutoCrystal.mc.field_71439_g);
/* 390 */     double d = ((Boolean)this.suicide.getValue()).booleanValue() ? 2.0D : 0.5D;
/* 391 */     if (selfDmg + d < (AutoCrystal.mc.field_71439_g.func_110143_aJ() + AutoCrystal.mc.field_71439_g.func_110139_bj()) && targetDmg >= (this.target.func_110139_bj() + this.target.func_110143_aJ()))
/* 392 */       return true; 
/* 393 */     this.armorTarget = false;
/* 394 */     for (ItemStack is : this.target.func_184193_aE()) {
/* 395 */       float green = ((is.func_77958_k() - is.func_77952_i()) / is.func_77958_k());
/* 396 */       float red = 1.0F - green;
/* 397 */       int dmg = 100 - (int)(red * 100.0F);
/* 398 */       if (dmg > ((Float)this.minArmor.getValue()).floatValue())
/*     */         continue; 
/* 400 */       this.armorTarget = true;
/*     */     } 
/* 402 */     if (targetDmg >= ((Float)this.breakMinDmg.getValue()).floatValue() && selfDmg <= ((Float)this.breakMaxSelfDamage.getValue()).floatValue())
/* 403 */       return true; 
/* 404 */     return (EntityUtil.isInHole((Entity)this.target) && this.target.func_110143_aJ() + this.target.func_110139_bj() <= ((Float)this.facePlace.getValue()).floatValue());
/*     */   }
/*     */   
/*     */   private boolean IsValidCrystal(Entity p_Entity) {
/* 408 */     if (p_Entity == null)
/* 409 */       return false; 
/* 410 */     if (!(p_Entity instanceof EntityEnderCrystal))
/* 411 */       return false; 
/* 412 */     if (this.target == null)
/* 413 */       return false; 
/* 414 */     if (p_Entity.func_70032_d((Entity)AutoCrystal.mc.field_71439_g) > ((Float)this.breakRange.getValue()).floatValue())
/* 415 */       return false; 
/* 416 */     if (!AutoCrystal.mc.field_71439_g.func_70685_l(p_Entity) && p_Entity.func_70032_d((Entity)AutoCrystal.mc.field_71439_g) > ((Float)this.breakWallRange.getValue()).floatValue())
/* 417 */       return false; 
/* 418 */     if (this.target.field_70128_L || this.target.func_110143_aJ() + this.target.func_110139_bj() <= 0.0F)
/* 419 */       return false; 
/* 420 */     double targetDmg = calculateDamage(p_Entity.func_180425_c().func_177958_n() + 0.5D, p_Entity.func_180425_c().func_177956_o() + 1.0D, p_Entity.func_180425_c().func_177952_p() + 0.5D, (Entity)this.target);
/* 421 */     if (EntityUtil.isInHole((Entity)AutoCrystal.mc.field_71439_g) && targetDmg >= 1.0D)
/* 422 */       return true; 
/* 423 */     double selfDmg = calculateDamage(p_Entity.func_180425_c().func_177958_n() + 0.5D, p_Entity.func_180425_c().func_177956_o() + 1.0D, p_Entity.func_180425_c().func_177952_p() + 0.5D, (Entity)AutoCrystal.mc.field_71439_g);
/* 424 */     double d = ((Boolean)this.suicide.getValue()).booleanValue() ? 2.0D : 0.5D;
/* 425 */     if (selfDmg + d < (AutoCrystal.mc.field_71439_g.func_110143_aJ() + AutoCrystal.mc.field_71439_g.func_110139_bj()) && targetDmg >= (this.target.func_110139_bj() + this.target.func_110143_aJ()))
/* 426 */       return true; 
/* 427 */     this.armorTarget = false;
/* 428 */     for (ItemStack is : this.target.func_184193_aE()) {
/* 429 */       float green = ((is.func_77958_k() - is.func_77952_i()) / is.func_77958_k());
/* 430 */       float red = 1.0F - green;
/* 431 */       int dmg = 100 - (int)(red * 100.0F);
/* 432 */       if (dmg > ((Float)this.minArmor.getValue()).floatValue())
/*     */         continue; 
/* 434 */       this.armorTarget = true;
/*     */     } 
/* 436 */     if (targetDmg >= ((Float)this.breakMinDmg.getValue()).floatValue() && selfDmg <= ((Float)this.breakMaxSelfDamage.getValue()).floatValue())
/* 437 */       return true; 
/* 438 */     return (EntityUtil.isInHole((Entity)this.target) && this.target.func_110143_aJ() + this.target.func_110139_bj() <= ((Float)this.facePlace.getValue()).floatValue());
/*     */   }
/*     */   
/*     */   EntityPlayer getTarget() {
/* 442 */     EntityPlayer closestPlayer = null;
/* 443 */     for (EntityPlayer entity : AutoCrystal.mc.field_71441_e.field_73010_i) {
/* 444 */       if (AutoCrystal.mc.field_71439_g == null || AutoCrystal.mc.field_71439_g.field_70128_L || entity.field_70128_L || entity == AutoCrystal.mc.field_71439_g || Phobos.friendManager.isFriend(entity.func_70005_c_()) || entity.func_70032_d((Entity)AutoCrystal.mc.field_71439_g) > 12.0F)
/*     */         continue; 
/* 446 */       this.armorTarget = false;
/* 447 */       for (ItemStack is : entity.func_184193_aE()) {
/* 448 */         float green = ((is.func_77958_k() - is.func_77952_i()) / is.func_77958_k());
/* 449 */         float red = 1.0F - green;
/* 450 */         int dmg = 100 - (int)(red * 100.0F);
/* 451 */         if (dmg > ((Float)this.minArmor.getValue()).floatValue())
/*     */           continue; 
/* 453 */         this.armorTarget = true;
/*     */       } 
/* 455 */       if (EntityUtil.isInHole((Entity)entity) && entity.func_110139_bj() + entity.func_110143_aJ() > ((Float)this.facePlace.getValue()).floatValue() && !this.armorTarget && ((Float)this.minDamage.getValue()).floatValue() > 2.2F)
/*     */         continue; 
/* 457 */       if (closestPlayer == null) {
/* 458 */         closestPlayer = entity;
/*     */         continue;
/*     */       } 
/* 461 */       if (closestPlayer.func_70032_d((Entity)AutoCrystal.mc.field_71439_g) <= entity.func_70032_d((Entity)AutoCrystal.mc.field_71439_g))
/*     */         continue; 
/* 463 */       closestPlayer = entity;
/*     */     } 
/* 465 */     return closestPlayer;
/*     */   }
/*     */   
/*     */   private void manualBreaker() {
/*     */     RayTraceResult result;
/* 470 */     if (this.manualTimer.passedMs(200L) && AutoCrystal.mc.field_71474_y.field_74313_G.func_151470_d() && AutoCrystal.mc.field_71439_g.func_184592_cb().func_77973_b() != Items.field_151153_ao && AutoCrystal.mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() != Items.field_151153_ao && AutoCrystal.mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() != Items.field_151031_f && AutoCrystal.mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() != Items.field_151062_by && (result = AutoCrystal.mc.field_71476_x) != null)
/* 471 */       if (result.field_72313_a.equals(RayTraceResult.Type.ENTITY)) {
/* 472 */         Entity entity = result.field_72308_g;
/* 473 */         if (entity instanceof EntityEnderCrystal) {
/* 474 */           if (((Boolean)this.packetBreak.getValue()).booleanValue()) {
/* 475 */             AutoCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(entity));
/*     */           } else {
/* 477 */             AutoCrystal.mc.field_71442_b.func_78764_a((EntityPlayer)AutoCrystal.mc.field_71439_g, entity);
/*     */           } 
/* 479 */           this.manualTimer.reset();
/*     */         } 
/* 481 */       } else if (result.field_72313_a.equals(RayTraceResult.Type.BLOCK)) {
/* 482 */         BlockPos mousePos = new BlockPos(AutoCrystal.mc.field_71476_x.func_178782_a().func_177958_n(), AutoCrystal.mc.field_71476_x.func_178782_a().func_177956_o() + 1.0D, AutoCrystal.mc.field_71476_x.func_178782_a().func_177952_p());
/* 483 */         for (Entity target : AutoCrystal.mc.field_71441_e.func_72839_b(null, new AxisAlignedBB(mousePos))) {
/* 484 */           if (!(target instanceof EntityEnderCrystal))
/*     */             continue; 
/* 486 */           if (((Boolean)this.packetBreak.getValue()).booleanValue()) {
/* 487 */             AutoCrystal.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(target));
/*     */           } else {
/* 489 */             AutoCrystal.mc.field_71442_b.func_78764_a((EntityPlayer)AutoCrystal.mc.field_71439_g, target);
/*     */           } 
/* 491 */           this.manualTimer.reset();
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   private boolean canSeePos(BlockPos pos) {
/* 497 */     return (AutoCrystal.mc.field_71441_e.func_147447_a(new Vec3d(AutoCrystal.mc.field_71439_g.field_70165_t, AutoCrystal.mc.field_71439_g.field_70163_u + AutoCrystal.mc.field_71439_g.func_70047_e(), AutoCrystal.mc.field_71439_g.field_70161_v), new Vec3d(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), false, true, false) == null);
/*     */   }
/*     */   
/*     */   private NonNullList<BlockPos> placePostions(float placeRange) {
/* 501 */     NonNullList<BlockPos> positions = NonNullList.func_191196_a();
/* 502 */     positions.addAll((Collection)getSphere(new BlockPos(Math.floor(AutoCrystal.mc.field_71439_g.field_70165_t), Math.floor(AutoCrystal.mc.field_71439_g.field_70163_u), Math.floor(AutoCrystal.mc.field_71439_g.field_70161_v)), placeRange, (int)placeRange, false, true, 0).stream().filter(pos -> canPlaceCrystal(pos, true)).collect(Collectors.toList()));
/* 503 */     return positions;
/*     */   }
/*     */   
/*     */   private boolean canPlaceCrystal(BlockPos blockPos, boolean specialEntityCheck) {
/* 507 */     BlockPos boost = blockPos.func_177982_a(0, 1, 0);
/* 508 */     BlockPos boost2 = blockPos.func_177982_a(0, 2, 0);
/*     */     try {
/* 510 */       if (!((Boolean)this.opPlace.getValue()).booleanValue()) {
/* 511 */         if (AutoCrystal.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() != Blocks.field_150357_h && AutoCrystal.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() != Blocks.field_150343_Z)
/* 512 */           return false; 
/* 513 */         if (AutoCrystal.mc.field_71441_e.func_180495_p(boost).func_177230_c() != Blocks.field_150350_a || AutoCrystal.mc.field_71441_e.func_180495_p(boost2).func_177230_c() != Blocks.field_150350_a)
/* 514 */           return false; 
/* 515 */         if (!specialEntityCheck)
/* 516 */           return (AutoCrystal.mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(boost)).isEmpty() && AutoCrystal.mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(boost2)).isEmpty()); 
/* 517 */         for (Entity entity : AutoCrystal.mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(boost))) {
/* 518 */           if (entity instanceof EntityEnderCrystal)
/*     */             continue; 
/* 520 */           return false;
/*     */         } 
/* 522 */         for (Entity entity : AutoCrystal.mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(boost2))) {
/* 523 */           if (entity instanceof EntityEnderCrystal)
/*     */             continue; 
/* 525 */           return false;
/*     */         } 
/*     */       } else {
/* 528 */         if (AutoCrystal.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() != Blocks.field_150357_h && AutoCrystal.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() != Blocks.field_150343_Z)
/* 529 */           return false; 
/* 530 */         if (AutoCrystal.mc.field_71441_e.func_180495_p(boost).func_177230_c() != Blocks.field_150350_a)
/* 531 */           return false; 
/* 532 */         if (!specialEntityCheck)
/* 533 */           return AutoCrystal.mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(boost)).isEmpty(); 
/* 534 */         for (Entity entity : AutoCrystal.mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(boost))) {
/* 535 */           if (entity instanceof EntityEnderCrystal)
/*     */             continue; 
/* 537 */           return false;
/*     */         } 
/*     */       } 
/* 540 */     } catch (Exception ignored) {
/* 541 */       return false;
/*     */     } 
/* 543 */     return true;
/*     */   }
/*     */   
/*     */   private float calculateDamage(double posX, double posY, double posZ, Entity entity) {
/* 547 */     float doubleExplosionSize = 12.0F;
/* 548 */     double distancedsize = entity.func_70011_f(posX, posY, posZ) / 12.0D;
/* 549 */     Vec3d vec3d = new Vec3d(posX, posY, posZ);
/* 550 */     double blockDensity = 0.0D;
/*     */     try {
/* 552 */       blockDensity = entity.field_70170_p.func_72842_a(vec3d, entity.func_174813_aQ());
/* 553 */     } catch (Exception exception) {}
/* 554 */     double v = (1.0D - distancedsize) * blockDensity;
/* 555 */     float damage = (int)((v * v + v) / 2.0D * 7.0D * 12.0D + 1.0D);
/* 556 */     double finald = 1.0D;
/* 557 */     if (entity instanceof EntityLivingBase)
/* 558 */       finald = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(damage), new Explosion((World)AutoCrystal.mc.field_71441_e, null, posX, posY, posZ, 6.0F, false, true)); 
/* 559 */     return (float)finald;
/*     */   }
/*     */   
/*     */   private float getBlastReduction(EntityLivingBase entity, float damageI, Explosion explosion) {
/* 563 */     float damage = damageI;
/* 564 */     if (entity instanceof EntityPlayer) {
/* 565 */       EntityPlayer ep = (EntityPlayer)entity;
/* 566 */       DamageSource ds = DamageSource.func_94539_a(explosion);
/* 567 */       damage = CombatRules.func_189427_a(damage, ep.func_70658_aO(), (float)ep.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
/* 568 */       int k = 0;
/*     */       try {
/* 570 */         k = EnchantmentHelper.func_77508_a(ep.func_184193_aE(), ds);
/* 571 */       } catch (Exception exception) {}
/* 572 */       float f = MathHelper.func_76131_a(k, 0.0F, 20.0F);
/* 573 */       damage *= 1.0F - f / 25.0F;
/* 574 */       if (entity.func_70644_a(MobEffects.field_76429_m))
/* 575 */         damage -= damage / 4.0F; 
/* 576 */       damage = Math.max(damage, 0.0F);
/* 577 */       return damage;
/*     */     } 
/* 579 */     damage = CombatRules.func_189427_a(damage, entity.func_70658_aO(), (float)entity.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
/* 580 */     return damage;
/*     */   }
/*     */   
/*     */   private float getDamageMultiplied(float damage) {
/* 584 */     int diff = AutoCrystal.mc.field_71441_e.func_175659_aa().func_151525_a();
/* 585 */     return damage * ((diff == 0) ? 0.0F : ((diff == 2) ? 1.0F : ((diff == 1) ? 0.5F : 1.5F)));
/*     */   }
/*     */   
/*     */   public enum SwingMode {
/* 589 */     MainHand, OffHand, None;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\combat\AutoCrystalCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */