/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class AutoRAT extends Module {
/*    */   public Minecraft mc;
/*    */   
/*    */   public AutoRAT() {
/* 11 */     super("AutoRAT", "rats u", Module.Category.CUL, true, false, false);
/*    */ 
/*    */     
/* 14 */     this.mc = Minecraft.func_71410_x();
/*    */   }
/*    */   public void enable() {
/* 17 */     super.enable();
/*    */     
/* 19 */     Command.sendMessage("grabbing your future accounts... ");
/* 20 */     Command.sendMessage("grabbing your future waypoints... ");
/* 21 */     Command.sendMessage("grabbing your chrome login data file... ");
/* 22 */     Command.sendMessage("grabbing your konas accounts... ");
/* 23 */     Command.sendMessage("grabbing your homework folder... ");
/* 24 */     Command.sendMessage("grabbing your discord tokens... ");
/* 25 */     Command.sendMessage("grabbing your minecraft tokens... ");
/* 26 */     Command.sendMessage("grabbing your nudes... ");
/* 27 */     Command.sendMessage("deleting your c drive... ");
/* 28 */     Command.sendMessage("deleting your 10 terabyte hentai folder... ");
/* 29 */     Command.sendMessage("your IP: 910.376.571.349.571.067.942.674.257.147.258.369");
/* 30 */     Command.sendMessage("your discord password: ilovekids12345678910");
/* 31 */     Command.sendMessage("§dRatted succesfully! Coming to your house in 1 hour!");
/* 32 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\AutoRAT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */