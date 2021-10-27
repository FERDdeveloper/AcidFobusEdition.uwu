/*    */ package me.earth.phobos.features.modules.player;
/*    */ 
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.handler.timeout.ReadTimeoutHandler;
/*    */ import java.util.NoSuchElementException;
/*    */ import me.earth.phobos.Phobos;
/*    */ import me.earth.phobos.event.events.ClientEvent;
/*    */ import me.earth.phobos.event.events.NettyChannelEvent;
/*    */ import me.earth.phobos.event.events.PlayerUpdateEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.Timer;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AntiKick
/*    */   extends Module {
/* 18 */   private final Setting<Integer> timeout = register(new Setting("Timeout", Integer.valueOf(240), Integer.valueOf(30), Integer.valueOf(600)));
/* 19 */   private final Setting<Mode> mode = register(new Setting("Mode", Mode.Change));
/*    */   private boolean handlerRemoved = false;
/* 21 */   private Channel nettyChannel = null;
/* 22 */   private Integer timeoutLast = Integer.valueOf(240);
/* 23 */   private Timer changeThrottle = new Timer();
/*    */   
/*    */   public AntiKick() {
/* 26 */     super("AntiKick", "ak", Module.Category.PLAYER, true, false, true);
/* 27 */     this.timeoutLast = (Integer)this.timeout.getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDisplayInfo() {
/* 32 */     return ((Mode)this.mode.getValue()).name() + ((this.mode.getValue() == Mode.Change) ? (" " + String.valueOf(this.timeoutLast)) : "") + " | " + ((this.nettyChannel == null) ? "NC" : "OK");
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onNettyChannelSet(NettyChannelEvent event) {
/* 37 */     this.nettyChannel = event.getChannel();
/* 38 */     this.handlerRemoved = false;
/* 39 */     if (isEnabled()) {
/* 40 */       updateTimeout(this.timeoutLast.intValue(), (this.mode.getValue() == Mode.Change));
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPlayerUpdate(PlayerUpdateEvent event) {
/* 46 */     if (isEnabled() && this.changeThrottle.passedMs(1000L) && this.timeout.getValue() != this.timeoutLast && this.nettyChannel != null) {
/* 47 */       this.timeoutLast = (Integer)this.timeout.getValue();
/* 48 */       this.changeThrottle.reset();
/* 49 */       updateTimeout(this.timeoutLast.intValue(), (this.mode.getValue() == Mode.Change));
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onSettingsUpdate(ClientEvent event) {
/* 55 */     if (event.getStage() == 2 && event.getSetting().getFeature().equals(this) && event.getSetting().equals(this.mode)) {
/* 56 */       this.timeoutLast = (Integer)this.timeout.getValue();
/* 57 */       updateTimeout(this.timeoutLast.intValue(), (this.mode.getPlannedValue() == Mode.Change));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void updateTimeout(int seconds, boolean addBack) {
/* 62 */     if (this.nettyChannel != null) {
/*    */       try {
/* 64 */         if (!this.handlerRemoved) {
/* 65 */           this.nettyChannel.pipeline().remove("timeout");
/*    */         }
/*    */       }
/* 68 */       catch (NoSuchElementException e) {
/* 69 */         Phobos.LOGGER.info("AntiLagKick: catched NSEE trying to remove timeout");
/*    */       } 
/* 71 */       if (addBack) {
/* 72 */         this.nettyChannel.pipeline().addFirst("timeout", (ChannelHandler)new ReadTimeoutHandler(seconds));
/*    */       }
/* 74 */       this.handlerRemoved = !addBack;
/*    */     } 
/*    */   }
/*    */   
/*    */   public enum Mode {
/* 79 */     Change,
/* 80 */     Remove;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\AntiKick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */