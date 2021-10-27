/*    */ package me.earth.phobos.mixin.mixins;
/*    */ 
/*    */ import me.earth.phobos.features.modules.render.NoRender;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.client.gui.toasts.GuiToast;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({GuiToast.class})
/*    */ public class MixinGuiToast {
/*    */   @Inject(method = {"drawToast"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void drawToastHook(ScaledResolution resolution, CallbackInfo info) {
/* 15 */     if (NoRender.getInstance().isOn() && ((Boolean)(NoRender.getInstance()).advancements.getValue()).booleanValue())
/* 16 */       info.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\MixinGuiToast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */