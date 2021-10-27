/*    */ package me.earth.phobos.features.command.commands;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.movement.Phase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketEntityAction;
/*    */ import net.minecraft.network.play.client.CPacketPlayer;
/*    */ 
/*    */ public class VClipCommand
/*    */   extends Command {
/*    */   public VClipCommand() {
/* 13 */     super("Vclip", new String[] { "0/1/2/3/4/5" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/* 18 */     Phase.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)Phase.mc.field_71439_g, CPacketEntityAction.Action.START_FALL_FLYING));
/* 19 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 0.5346D, mc.field_71439_g.field_70161_v, false));
/* 20 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 1.2836D, mc.field_71439_g.field_70161_v, false));
/* 21 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 1.9231D, mc.field_71439_g.field_70161_v, false));
/* 22 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 2.3957D, mc.field_71439_g.field_70161_v, false));
/* 23 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 3.0121D, mc.field_71439_g.field_70161_v, false));
/* 24 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 4.9564D, mc.field_71439_g.field_70161_v, false));
/* 25 */     Command.sendMessage("Tried clipping");
/* 26 */     String s = commands[0];
/* 27 */     switch (s) {
/*    */       case "0":
/* 29 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 0.5346D, mc.field_71439_g.field_70161_v, false));
/* 30 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 1.2836D, mc.field_71439_g.field_70161_v, false));
/* 31 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 1.9231D, mc.field_71439_g.field_70161_v, false));
/* 32 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 2.3957D, mc.field_71439_g.field_70161_v, false));
/* 33 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 3.0121D, mc.field_71439_g.field_70161_v, false));
/* 34 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 4.9564D, mc.field_71439_g.field_70161_v, false));
/*    */       
/*    */       case "1":
/* 37 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 1.5346D, mc.field_71439_g.field_70161_v, false));
/* 38 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 2.2836D, mc.field_71439_g.field_70161_v, false));
/* 39 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 2.9231D, mc.field_71439_g.field_70161_v, false));
/* 40 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 3.3957D, mc.field_71439_g.field_70161_v, false));
/* 41 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 4.0121D, mc.field_71439_g.field_70161_v, false));
/* 42 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 5.9564D, mc.field_71439_g.field_70161_v, false));
/*    */       
/*    */       case "2":
/* 45 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 2.5346D, mc.field_71439_g.field_70161_v, false));
/* 46 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 3.2836D, mc.field_71439_g.field_70161_v, false));
/* 47 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 3.9231D, mc.field_71439_g.field_70161_v, false));
/* 48 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 4.3957D, mc.field_71439_g.field_70161_v, false));
/* 49 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 5.0121D, mc.field_71439_g.field_70161_v, false));
/* 50 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 6.9564D, mc.field_71439_g.field_70161_v, false));
/*    */       
/*    */       case "3":
/* 53 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 3.5346D, mc.field_71439_g.field_70161_v, false));
/* 54 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 4.2836D, mc.field_71439_g.field_70161_v, false));
/* 55 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 4.9231D, mc.field_71439_g.field_70161_v, false));
/* 56 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 5.3957D, mc.field_71439_g.field_70161_v, false));
/* 57 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 6.0121D, mc.field_71439_g.field_70161_v, false));
/* 58 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 7.9564D, mc.field_71439_g.field_70161_v, false));
/*    */       
/*    */       case "4":
/* 61 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 4.5346D, mc.field_71439_g.field_70161_v, false));
/* 62 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 5.2836D, mc.field_71439_g.field_70161_v, false));
/* 63 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 5.9231D, mc.field_71439_g.field_70161_v, false));
/* 64 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 6.3957D, mc.field_71439_g.field_70161_v, false));
/* 65 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 7.0121D, mc.field_71439_g.field_70161_v, false));
/* 66 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 8.9564D, mc.field_71439_g.field_70161_v, false));
/*    */       
/*    */       case "5":
/* 69 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 5.5346D, mc.field_71439_g.field_70161_v, false));
/* 70 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 6.2836D, mc.field_71439_g.field_70161_v, false));
/* 71 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 6.9231D, mc.field_71439_g.field_70161_v, false));
/* 72 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 7.3957D, mc.field_71439_g.field_70161_v, false));
/* 73 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 8.0121D, mc.field_71439_g.field_70161_v, false));
/* 74 */         mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 9.9564D, mc.field_71439_g.field_70161_v, false));
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\VClipCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */