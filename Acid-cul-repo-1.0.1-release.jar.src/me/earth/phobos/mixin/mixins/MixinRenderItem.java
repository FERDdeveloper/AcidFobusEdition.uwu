/*    */ package me.earth.phobos.mixin.mixins;
/*    */ 
/*    */ import me.earth.phobos.Phobos;
/*    */ import me.earth.phobos.event.events.RenderItemEvent;
/*    */ import me.earth.phobos.features.modules.render.GlintModify;
/*    */ import me.earth.phobos.features.modules.render.ViewModel;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.RenderItem;
/*    */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArg;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({RenderItem.class})
/*    */ public class MixinRenderItem
/*    */ {
/*    */   @Shadow
/*    */   private void func_191967_a(IBakedModel model, int color, ItemStack stack) {}
/*    */   
/*    */   @ModifyArg(method = {"renderEffect"}, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderItem.renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"), index = 1)
/*    */   private int renderEffect(int oldValue) {
/* 32 */     return Phobos.moduleManager.getModuleByName("GlintModify").isEnabled() ? GlintModify.getColor(1L, 1.0F).getRGB() : oldValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"renderItemModel"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE)})
/*    */   private void renderItemModel(ItemStack stack, IBakedModel bakedModel, ItemCameraTransforms.TransformType transform, boolean leftHanded, CallbackInfo ci) {
/* 39 */     RenderItemEvent event = new RenderItemEvent(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 48 */     if (ViewModel.getInstance().isEnabled())
/* 49 */       if (!leftHanded) {
/* 50 */         GlStateManager.func_179139_a(event.getMainHandScaleX(), event.getMainHandScaleY(), event.getMainHandScaleZ());
/*    */       } else {
/* 52 */         GlStateManager.func_179139_a(event.getOffHandScaleX(), event.getOffHandScaleY(), event.getOffHandScaleZ());
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\MixinRenderItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */