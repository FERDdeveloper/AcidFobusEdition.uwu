/*     */ package me.earth.phobos.features.modules.movement;
/*     */ 
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Bind;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.Timer;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.network.play.server.SPacketPlayerPosLook;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPhase
/*     */   extends Module
/*     */ {
/*     */   public BlockPhase() {
/*  27 */     super("BlockPhase", "phase && pfly", Module.Category.MOVEMENT, true, false, false);
/*  28 */     Setting<Boolean> twodelay = register(new Setting("2Delay", Boolean.valueOf(true)));
/*  29 */     Setting<Integer> tickDelay = register(new Setting("TickDelay", Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(40)));
/*  30 */     Setting<Double> speed = register(new Setting("Speed", Double.valueOf(6.0D), Double.valueOf(0.0D), Double.valueOf(6.0D)));
/*  31 */     this.left = new Setting("Left", new Bind(203));
/*  32 */     this.right = new Setting("Right", new Bind(205));
/*  33 */     this.down = new Setting("Down", new Bind(208));
/*  34 */     this.up = new Setting("Up", new Bind(200));
/*  35 */     setInstance();
/*     */   }
/*     */   public static BlockPhase getInstance() {
/*  38 */     if (INSTANCE == null) {
/*  39 */       INSTANCE = new BlockPhase();
/*     */     }
/*  41 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   private void setInstance() {
/*  45 */     INSTANCE = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  50 */     long last = 0L;
/*     */     try {
/*  52 */       mc.field_71439_g.field_70145_X = true;
/*  53 */       if (((Integer)this.tickDelay.getValue()).intValue() > 0 && mc.field_71439_g.field_70173_aa % ((Integer)this.tickDelay.getValue()).intValue() != 0 && ((Boolean)this.twodelay.getValue()).booleanValue()) {
/*     */         return;
/*     */       }
/*  56 */       double yaw = ((mc.field_71439_g.field_70177_z + 90.0F) * 1.0F);
/*  57 */       double dO_numer = 0.0D;
/*  58 */       double dO_denom = 0.0D;
/*  59 */       if (mc.field_71474_y.field_74370_x.func_151470_d()) {
/*  60 */         dO_numer -= 90.0D;
/*  61 */         dO_denom++;
/*     */       } 
/*  63 */       if (mc.field_71474_y.field_74366_z.func_151470_d()) {
/*  64 */         dO_numer += 90.0D;
/*  65 */         dO_denom++;
/*     */       } 
/*  67 */       if (mc.field_71474_y.field_74368_y.func_151470_d()) {
/*  68 */         dO_numer += 180.0D;
/*  69 */         dO_denom++;
/*     */       } 
/*  71 */       if (mc.field_71474_y.field_74351_w.func_151470_d()) {
/*  72 */         dO_denom++;
/*     */       }
/*  74 */       if (dO_denom > 0.0D) {
/*  75 */         yaw += dO_numer / dO_denom % 361.0D;
/*     */       }
/*  77 */       if (yaw < 0.0D) {
/*  78 */         yaw = 360.0D - yaw;
/*     */       }
/*  80 */       if (yaw > 360.0D) {
/*  81 */         yaw %= 361.0D;
/*     */       }
/*  83 */       double xDir = Math.cos(Math.toRadians(yaw));
/*  84 */       double zDir = Math.sin(Math.toRadians(yaw));
/*  85 */       if (mc.field_71474_y.field_74351_w.func_151470_d() || mc.field_71474_y.field_74370_x.func_151470_d() || mc.field_71474_y.field_74366_z.func_151470_d() || mc.field_71474_y.field_74368_y.func_151470_d()) {
/*  86 */         mc.field_71439_g.field_70159_w = xDir * ((Double)this.speed.getValue()).doubleValue() / 100.0D;
/*  87 */         mc.field_71439_g.field_70179_y = zDir * ((Double)this.speed.getValue()).doubleValue() / 100.0D;
/*     */       } 
/*  89 */       mc.field_71439_g.field_70181_x = 0.0D;
/*  90 */       boolean yes = false;
/*  91 */       mc.field_71439_g.field_70145_X = true;
/*     */ 
/*     */ 
/*     */       
/*  95 */       mc.field_71439_g.field_70145_X = true;
/*     */ 
/*     */ 
/*     */       
/*  99 */       double dx = 0.0D;
/* 100 */       double dy = 0.0D;
/* 101 */       double dz = 0.0D;
/* 102 */       if (mc.field_71474_y.field_74311_E.func_151470_d()) {
/* 103 */         dy = -0.0625D;
/*     */       }
/* 105 */       if (mc.field_71474_y.field_74314_A.func_151470_d()) {
/* 106 */         dy = 0.0625D;
/*     */       }
/* 108 */       mc.field_71439_g.func_70012_b(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70177_z, mc.field_71439_g.field_70125_A);
/* 109 */       mc.func_147114_u().func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v, false));
/*     */     }
/* 111 */     catch (Exception e) {
/* 112 */       Command.sendMessage("§cException caught: " + e.getMessage());
/* 113 */       disable();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 119 */     if (mc.field_71439_g != null) {
/* 120 */       mc.field_71439_g.field_70145_X = false;
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/* 126 */     if (event.getPacket() instanceof SPacketPlayerPosLook) {
/* 127 */       SPacketPlayerPosLook pak = (SPacketPlayerPosLook)event.getPacket();
/* 128 */       pak.field_148936_d = mc.field_71439_g.field_70177_z;
/* 129 */       pak.field_148937_e = mc.field_71439_g.field_70125_A;
/*     */     } 
/* 131 */     if (event.getPacket() instanceof SPacketPlayerPosLook) {
/* 132 */       SPacketPlayerPosLook pak = (SPacketPlayerPosLook)event.getPacket();
/* 133 */       double dx = Math.abs(pak.func_179834_f().contains(SPacketPlayerPosLook.EnumFlags.X) ? pak.field_148940_a : (mc.field_71439_g.field_70165_t - pak.field_148940_a));
/* 134 */       double dy = Math.abs(pak.func_179834_f().contains(SPacketPlayerPosLook.EnumFlags.Y) ? pak.field_148938_b : (mc.field_71439_g.field_70163_u - pak.field_148938_b));
/* 135 */       double dz = Math.abs(pak.func_179834_f().contains(SPacketPlayerPosLook.EnumFlags.Z) ? pak.field_148939_c : (mc.field_71439_g.field_70161_v - pak.field_148939_c));
/* 136 */       if (dx < 0.001D) {
/* 137 */         dx = 0.0D;
/*     */       }
/* 139 */       if (dz < 0.001D) {
/* 140 */         dz = 0.0D;
/*     */       }
/* 142 */       if (pak.field_148936_d != mc.field_71439_g.field_70177_z || pak.field_148937_e != mc.field_71439_g.field_70125_A) {
/* 143 */         pak.field_148936_d = mc.field_71439_g.field_70177_z;
/* 144 */         pak.field_148937_e = mc.field_71439_g.field_70125_A;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static BlockPos getPlayerPos() {
/* 150 */     return new BlockPos(Math.floor(mc.field_71439_g.field_70165_t), Math.floor(mc.field_71439_g.field_70163_u), Math.floor(mc.field_71439_g.field_70161_v));
/*     */   }
/*     */ 
/*     */   
/* 154 */   private static BlockPhase INSTANCE = new BlockPhase(); public Setting<Boolean> twodelay; public Setting<Integer> tickDelay; public Setting<Double> speed;
/*     */   public Setting<Bind> left;
/*     */   public Setting<Bind> right;
/*     */   public Setting<Bind> down;
/*     */   public Setting<Bind> up;
/*     */   
/*     */   public static class IDtime { private final Vec3d pos;
/*     */     
/*     */     public IDtime(Vec3d pos, Timer timer) {
/* 163 */       this.pos = pos;
/* 164 */       (this.timer = timer).reset();
/*     */     }
/*     */     private final Timer timer;
/*     */     public Vec3d getPos() {
/* 168 */       return this.pos;
/*     */     }
/*     */     
/*     */     public Timer getTimer() {
/* 172 */       return this.timer;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\BlockPhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */