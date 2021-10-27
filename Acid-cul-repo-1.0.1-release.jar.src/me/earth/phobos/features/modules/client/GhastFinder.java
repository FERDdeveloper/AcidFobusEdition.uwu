/*    */ package me.earth.phobos.features.modules.client;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GhastFinder
/*    */   extends Module
/*    */ {
/* 17 */   private final Set<Entity> ghasts = new HashSet<>();
/* 18 */   public Setting<Boolean> Chat = register(new Setting("Chat", Boolean.valueOf(true)));
/* 19 */   public Setting<Boolean> Sound = register(new Setting("Sound", Boolean.valueOf(true)));
/*    */   
/*    */   public GhastFinder() {
/* 22 */     super("GhastFinder", "find ghasts...", Module.Category.MISC, true, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 28 */     this.ghasts.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 34 */     for (Entity entity : mc.field_71441_e.func_72910_y()) {
/* 35 */       if (!(entity instanceof net.minecraft.entity.monster.EntityGhast) || this.ghasts.contains(entity))
/* 36 */         continue;  if (((Boolean)this.Chat.getValue()).booleanValue()) {
/* 37 */         Command.sendMessage("§ahehe, i saw a ghast near: " + entity.func_180425_c().func_177958_n() + "x, " + entity.func_180425_c().func_177956_o() + "y, " + entity.func_180425_c().func_177952_p() + "z.");
/*    */       }
/* 39 */       this.ghasts.add(entity);
/* 40 */       if (!((Boolean)this.Sound.getValue()).booleanValue())
/* 41 */         continue;  mc.field_71439_g.func_184185_a(SoundEvents.field_187680_c, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\GhastFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */