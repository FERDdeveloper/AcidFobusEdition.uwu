/*    */ package me.earth.phobos.features.modules.combat;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class SelfCrystal extends Module {
/*    */   public SelfCrystal() {
/*  9 */     super("SelfCrystal", "Best module", Module.Category.COMBAT, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTick() {
/* 14 */     if (AutoCrystal.getInstance().isEnabled()) {
/* 15 */       AutoCrystal.target = (EntityPlayer)mc.field_71439_g;
/* 16 */       Command.sendMessage("Good option! Nice way to suicide, am i right?");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\combat\SelfCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */