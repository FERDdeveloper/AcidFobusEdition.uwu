/*     */ package me.earth.phobos.manager;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.Feature;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.modules.client.Notifications;
/*     */ import me.earth.phobos.features.modules.render.PopChams;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class TotemPopManager
/*     */   extends Feature
/*     */ {
/*     */   private Notifications notifications;
/*  18 */   private Map<EntityPlayer, Integer> poplist = new ConcurrentHashMap<>();
/*  19 */   private final Set<EntityPlayer> toAnnounce = new HashSet<>();
/*     */   
/*     */   public void onUpdate() {
/*  22 */     if (this.notifications.totemAnnounce.passedMs(((Integer)this.notifications.delay.getValue()).intValue()) && this.notifications.isOn() && ((Boolean)this.notifications.totemPops.getValue()).booleanValue()) {
/*  23 */       for (EntityPlayer player : this.toAnnounce) {
/*  24 */         if (player == null)
/*  25 */           continue;  int playerNumber = 0;
/*  26 */         for (char character : player.func_70005_c_().toCharArray()) {
/*  27 */           playerNumber += character;
/*  28 */           playerNumber *= 10;
/*     */         } 
/*  30 */         Command.sendOverwriteMessage("§c" + player.func_70005_c_() + " popped §a" + getTotemPops(player) + "§c Totem" + ((getTotemPops(player) == 1) ? "" : "s") + ".", playerNumber, ((Boolean)this.notifications.totemNoti.getValue()).booleanValue());
/*  31 */         this.toAnnounce.remove(player);
/*  32 */         this.notifications.totemAnnounce.reset();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLogout() {
/*  39 */     onOwnLogout(((Boolean)this.notifications.clearOnLogout.getValue()).booleanValue());
/*     */   }
/*     */   
/*     */   public void init() {
/*  43 */     this.notifications = Phobos.moduleManager.<Notifications>getModuleByClass(Notifications.class);
/*     */   }
/*     */   
/*     */   public void onTotemPop(EntityPlayer player) {
/*  47 */     popTotem(player);
/*  48 */     if (!player.equals(mc.field_71439_g)) {
/*  49 */       this.toAnnounce.add(player);
/*  50 */       PopChams.INSTANCE.poppedPlayers.put(player, Integer.valueOf(0));
/*  51 */       this.notifications.totemAnnounce.reset();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onDeath(EntityPlayer player) {
/*  56 */     if (getTotemPops(player) != 0 && !player.equals(mc.field_71439_g) && this.notifications.isOn() && ((Boolean)this.notifications.totemPops.getValue()).booleanValue()) {
/*  57 */       int playerNumber = 0;
/*  58 */       for (char character : player.func_70005_c_().toCharArray()) {
/*  59 */         playerNumber += character;
/*  60 */         playerNumber *= 10;
/*     */       } 
/*  62 */       Command.sendOverwriteMessage("§c" + player.func_70005_c_() + " died after popping §a" + getTotemPops(player) + "§c Totem" + ((getTotemPops(player) == 1) ? "" : "s") + ".", playerNumber, ((Boolean)this.notifications.totemNoti.getValue()).booleanValue());
/*  63 */       this.toAnnounce.remove(player);
/*     */     } 
/*  65 */     resetPops(player);
/*     */   }
/*     */   
/*     */   public void onLogout(EntityPlayer player, boolean clearOnLogout) {
/*  69 */     if (clearOnLogout) {
/*  70 */       resetPops(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onOwnLogout(boolean clearOnLogout) {
/*  75 */     if (clearOnLogout) {
/*  76 */       clearList();
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearList() {
/*  81 */     this.poplist = new ConcurrentHashMap<>();
/*     */   }
/*     */   
/*     */   public void resetPops(EntityPlayer player) {
/*  85 */     setTotemPops(player, 0);
/*     */   }
/*     */   
/*     */   public void popTotem(EntityPlayer player) {
/*  89 */     this.poplist.merge(player, Integer.valueOf(1), Integer::sum);
/*     */   }
/*     */   
/*     */   public void setTotemPops(EntityPlayer player, int amount) {
/*  93 */     this.poplist.put(player, Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */   public int getTotemPops(EntityPlayer player) {
/*  97 */     Integer pops = this.poplist.get(player);
/*  98 */     if (pops == null) {
/*  99 */       return 0;
/*     */     }
/* 101 */     return pops.intValue();
/*     */   }
/*     */   
/*     */   public String getTotemPopString(EntityPlayer player) {
/* 105 */     return "§f" + ((getTotemPops(player) <= 0) ? "" : ("-" + getTotemPops(player) + " "));
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\manager\TotemPopManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */