/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class BlockBreakingEvent
/*    */   extends EventStage {
/*    */   public BlockPos pos;
/*    */   public int breakingID;
/*    */   public int breakStage;
/*    */   
/*    */   public BlockBreakingEvent(BlockPos pos, int breakingID, int breakStage) {
/* 13 */     this.pos = pos;
/* 14 */     this.breakingID = breakingID;
/* 15 */     this.breakStage = breakStage;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\BlockBreakingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */