/*    */ package me.earth.phobos.features.modules.client;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.util.Util;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GUIBlur
/*    */   extends Module
/*    */ {
/*    */   public GUIBlur() {
/* 12 */     super("GUIBlur", "nigga", Module.Category.CLIENT, true, false, false);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 16 */     if (mc.field_71441_e != null) {
/* 17 */       mc.field_71460_t.func_147706_e().func_148021_a();
/*    */     }
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 22 */     if (mc.field_71441_e != null)
/* 23 */       if (ClickGui.getInstance().isEnabled() || mc.field_71462_r != null) {
/* 24 */         if (OpenGlHelper.field_148824_g && mc.func_175606_aa() instanceof net.minecraft.entity.player.EntityPlayer) {
/* 25 */           if (mc.field_71460_t.func_147706_e() != null) {
/* 26 */             mc.field_71460_t.func_147706_e().func_148021_a();
/*    */           }
/*    */           try {
/* 29 */             Util.mc.field_71460_t.func_175069_a(new ResourceLocation("shaders/post/blur.json"));
/* 30 */           } catch (Exception e) {
/* 31 */             e.printStackTrace();
/*    */           } 
/* 33 */         } else if (mc.field_71460_t.func_147706_e() != null && mc.field_71462_r == null) {
/* 34 */           mc.field_71460_t.func_147706_e().func_148021_a();
/*    */         } 
/* 36 */       } else if (mc.field_71460_t.func_147706_e() != null) {
/* 37 */         mc.field_71460_t.func_147706_e().func_148021_a();
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\GUIBlur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */