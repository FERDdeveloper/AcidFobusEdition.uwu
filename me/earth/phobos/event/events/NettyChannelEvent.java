/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import io.netty.channel.Channel;
/*    */ import me.earth.phobos.event.EventStage;
/*    */ 
/*    */ public class NettyChannelEvent
/*    */   extends EventStage {
/*    */   private final Channel channel;
/*    */   
/*    */   public NettyChannelEvent(Channel channel) {
/* 11 */     super(0);
/* 12 */     this.channel = channel;
/*    */   }
/*    */   
/*    */   public Channel getChannel() {
/* 16 */     return this.channel;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\NettyChannelEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */