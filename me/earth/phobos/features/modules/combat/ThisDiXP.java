/*    */ package me.earth.phobos.features.modules.combat;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThisDiXP
/*    */   extends Module
/*    */ {
/*    */   Minecraft mc;
/*    */   
/*    */   public ThisDiXP() {
/* 16 */     super("ThisDiXP", "rightclickdelaytimer = 0; yeha", Module.Category.COMBAT, false, false, false);
/*    */ 
/*    */     
/* 19 */     this.mc = Minecraft.func_71410_x();
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 23 */     if (this.mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() instanceof net.minecraft.item.ItemExpBottle) {
/* 24 */       this.mc.field_71467_ac = 0;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 30 */     MinecraftForge.EVENT_BUS.register(this);
/* 31 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 35 */     MinecraftForge.EVENT_BUS.unregister(this);
/* 36 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\combat\ThisDiXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */