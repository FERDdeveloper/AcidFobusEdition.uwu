/*     */ package me.earth.phobos.features.modules.movement;
/*     */ 
/*     */ import io.netty.util.internal.ConcurrentSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.event.events.PushEvent;
/*     */ import me.earth.phobos.event.events.UpdateWalkingPlayerEvent;
/*     */ import me.earth.phobos.features.Feature;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.util.EntityUtil;
/*     */ import me.earth.phobos.util.Timer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketConfirmTeleport;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.network.play.server.SPacketPlayerPosLook;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class CCPacketFly
/*     */   extends Module
/*     */ {
/*     */   private final Set<CPacketPlayer> packets;
/*     */   private final Map<Integer, IDtime> teleportmap;
/*     */   private int flightCounter;
/*     */   private int teleportID;
/*     */   private static CCPacketFly instance;
/*     */   
/*     */   public CCPacketFly() {
/*  33 */     super("CCPacketFly", "EZ CC PHASE", Module.Category.MOVEMENT, true, false, false);
/*  34 */     this.packets = (Set<CPacketPlayer>)new ConcurrentSet();
/*  35 */     this.teleportmap = new ConcurrentHashMap<>();
/*  36 */     this.flightCounter = 0;
/*  37 */     this.teleportID = 0;
/*  38 */     instance = this;
/*     */   }
/*     */   
/*     */   public static CCPacketFly getInstance() {
/*  42 */     if (instance == null) {
/*  43 */       instance = new CCPacketFly();
/*     */     }
/*  45 */     return instance;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
/*  50 */     if (event.getStage() == 1) {
/*     */       return;
/*     */     }
/*  53 */     mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
/*  54 */     boolean checkCollisionBoxes = checkHitBoxes();
/*  55 */     double speed = (mc.field_71439_g.field_71158_b.field_78901_c && (checkCollisionBoxes || !EntityUtil.isMoving())) ? (checkCollisionBoxes ? 0.062D : (resetCounter(10) ? -0.032D : 0.062D)) : (mc.field_71439_g.field_71158_b.field_78899_d ? -0.062D : (checkCollisionBoxes ? 0.0D : (resetCounter(4) ? -0.04D : 0.0D)));
/*  56 */     if (checkCollisionBoxes && EntityUtil.isMoving() && speed != 0.0D) {
/*  57 */       double antiFactor = 2.5D;
/*  58 */       speed /= 2.5D;
/*     */     } 
/*  60 */     boolean strafeFactor = true;
/*  61 */     double[] strafing = getMotion(checkCollisionBoxes ? 0.031D : 0.26D);
/*  62 */     for (int loops = 1, i = 1; i < loops + 1; i++) {
/*  63 */       double extraFactor = 1.0D;
/*  64 */       mc.field_71439_g.field_70159_w = strafing[0] * i * 1.0D;
/*  65 */       mc.field_71439_g.field_70181_x = speed * i;
/*  66 */       mc.field_71439_g.field_70179_y = strafing[1] * i * 1.0D;
/*  67 */       boolean sendTeleport = true;
/*  68 */       sendPackets(mc.field_71439_g.field_70159_w, mc.field_71439_g.field_70181_x, mc.field_71439_g.field_70179_y, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketSend(PacketEvent.Send event) {
/*  74 */     if (event.getPacket() instanceof CPacketPlayer && !this.packets.remove(event.getPacket())) {
/*  75 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPushOutOfBlocks(PushEvent event) {
/*  81 */     if (event.getStage() == 1) {
/*  82 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/*  88 */     if (event.getPacket() instanceof SPacketPlayerPosLook && !Feature.fullNullCheck()) {
/*  89 */       SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
/*  90 */       if (mc.field_71439_g.func_70089_S() && mc.field_71441_e.func_175668_a(new BlockPos(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v), false) && !(mc.field_71462_r instanceof net.minecraft.client.gui.GuiDownloadTerrain)) {
/*  91 */         this.teleportmap.remove(Integer.valueOf(packet.func_186965_f()));
/*     */       }
/*  93 */       this.teleportID = packet.func_186965_f();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean checkHitBoxes() {
/*  98 */     return !mc.field_71441_e.func_184144_a((Entity)mc.field_71439_g, mc.field_71439_g.func_174813_aQ().func_72317_d(-0.0D, -0.1D, -0.0D)).isEmpty();
/*     */   }
/*     */   
/*     */   private boolean resetCounter(int counter) {
/* 102 */     if (++this.flightCounter >= counter) {
/* 103 */       this.flightCounter = 0;
/* 104 */       return true;
/*     */     } 
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   private double[] getMotion(double speed) {
/* 110 */     float moveForward = mc.field_71439_g.field_71158_b.field_192832_b;
/* 111 */     float moveStrafe = mc.field_71439_g.field_71158_b.field_78902_a;
/* 112 */     float rotationYaw = mc.field_71439_g.field_70126_B + (mc.field_71439_g.field_70177_z - mc.field_71439_g.field_70126_B) * mc.func_184121_ak();
/* 113 */     if (moveForward != 0.0F) {
/* 114 */       if (moveStrafe > 0.0F) {
/* 115 */         rotationYaw += ((moveForward > 0.0F) ? -45 : 45);
/*     */       }
/* 117 */       else if (moveStrafe < 0.0F) {
/* 118 */         rotationYaw += ((moveForward > 0.0F) ? 45 : -45);
/*     */       } 
/* 120 */       moveStrafe = 0.0F;
/* 121 */       if (moveForward > 0.0F) {
/* 122 */         moveForward = 1.0F;
/*     */       }
/* 124 */       else if (moveForward < 0.0F) {
/* 125 */         moveForward = -1.0F;
/*     */       } 
/*     */     } 
/* 128 */     double posX = moveForward * speed * -Math.sin(Math.toRadians(rotationYaw)) + moveStrafe * speed * Math.cos(Math.toRadians(rotationYaw));
/* 129 */     double posZ = moveForward * speed * Math.cos(Math.toRadians(rotationYaw)) - moveStrafe * speed * -Math.sin(Math.toRadians(rotationYaw));
/* 130 */     return new double[] { posX, posZ };
/*     */   }
/*     */   
/*     */   private void sendPackets(double x, double y, double z, boolean teleport) {
/* 134 */     Vec3d vec = new Vec3d(x, y, z);
/* 135 */     Vec3d position = mc.field_71439_g.func_174791_d().func_178787_e(vec);
/* 136 */     Vec3d outOfBoundsVec = outOfBoundsVec(position);
/* 137 */     packetSender((CPacketPlayer)new CPacketPlayer.Position(position.field_72450_a, position.field_72448_b, position.field_72449_c, mc.field_71439_g.field_70122_E));
/* 138 */     packetSender((CPacketPlayer)new CPacketPlayer.Position(outOfBoundsVec.field_72450_a, outOfBoundsVec.field_72448_b, outOfBoundsVec.field_72449_c, mc.field_71439_g.field_70122_E));
/* 139 */     teleportPacket(position, teleport);
/*     */   }
/*     */   
/*     */   private void teleportPacket(Vec3d pos, boolean shouldTeleport) {
/* 143 */     if (shouldTeleport) {
/* 144 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketConfirmTeleport(++this.teleportID));
/* 145 */       this.teleportmap.put(Integer.valueOf(this.teleportID), new IDtime(pos, new Timer()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private Vec3d outOfBoundsVec(Vec3d position) {
/* 150 */     return position.func_72441_c(0.0D, 1337.0D, 0.0D);
/*     */   }
/*     */   
/*     */   private void packetSender(CPacketPlayer packet) {
/* 154 */     this.packets.add(packet);
/* 155 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)packet);
/*     */   }
/*     */   
/*     */   public static class IDtime
/*     */   {
/*     */     private final Vec3d pos;
/*     */     private final Timer timer;
/*     */     
/*     */     public IDtime(Vec3d pos, Timer timer) {
/* 164 */       this.pos = pos;
/* 165 */       (this.timer = timer).reset();
/*     */     }
/*     */     
/*     */     public Vec3d getPos() {
/* 169 */       return this.pos;
/*     */     }
/*     */     
/*     */     public Timer getTimer() {
/* 173 */       return this.timer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\CCPacketFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */