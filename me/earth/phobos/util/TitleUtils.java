/*    */ package me.earth.phobos.util;
/*    */ 
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ import org.lwjgl.opengl.Display;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitleUtils
/*    */ {
/* 16 */   int ticks = 0;
/* 17 */   int bruh = 0;
/* 18 */   int breakTimer = 0;
/* 19 */   String bruh1 = "Acid | 1.0.1 fobus edition";
/*    */   
/*    */   boolean qwerty = false;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 25 */     this.ticks++;
/* 26 */     if (this.ticks % 17 == 0) {
/* 27 */       Display.setTitle(this.bruh1.substring(0, this.bruh1.length() - this.bruh));
/* 28 */       if ((this.bruh == this.bruh1.length() && this.breakTimer != 0) || (this.bruh == 0 && this.breakTimer != 0)) {
/* 29 */         this.breakTimer++;
/*    */         return;
/*    */       } 
/* 32 */       this.breakTimer = 0;
/* 33 */       if (this.bruh == this.bruh1.length()) {
/* 34 */         this.qwerty = true;
/*    */       }
/* 36 */       this.bruh = this.qwerty ? --this.bruh : ++this.bruh;
/* 37 */       if (this.bruh == 0)
/* 38 */         this.qwerty = false; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\TitleUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */