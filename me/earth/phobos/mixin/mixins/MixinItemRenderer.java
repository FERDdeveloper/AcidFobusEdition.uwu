/*     */ package me.earth.phobos.mixin.mixins;
/*     */ 
/*     */ import me.earth.phobos.event.events.RenderItemEvent;
/*     */ import me.earth.phobos.features.modules.render.NoRender;
/*     */ import me.earth.phobos.features.modules.render.SmallShield;
/*     */ import me.earth.phobos.features.modules.render.ViewModel;
/*     */ import me.earth.phobos.util.RenderUtil;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumHandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ 
/*     */ 
/*     */ @Mixin({ItemRenderer.class})
/*     */ public abstract class MixinItemRenderer
/*     */ {
/*     */   public Minecraft mc;
/*     */   private boolean injection = true;
/*     */   
/*     */   @Shadow
/*     */   public abstract void func_187457_a(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, EnumHand paramEnumHand, float paramFloat3, ItemStack paramItemStack, float paramFloat4);
/*     */   
/*     */   @Inject(method = {"renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void renderItemInFirstPersonHook(AbstractClientPlayer player, float p_187457_2_, float p_187457_3_, EnumHand hand, float p_187457_5_, ItemStack stack, float p_187457_7_, CallbackInfo info) {
/*  37 */     if (this.injection) {
/*  38 */       info.cancel();
/*  39 */       SmallShield offset = SmallShield.getINSTANCE();
/*  40 */       float xOffset = 0.0F;
/*  41 */       float yOffset = 0.0F;
/*  42 */       this.injection = false;
/*  43 */       if (hand == EnumHand.MAIN_HAND) {
/*  44 */         if (offset.isOn() && player.func_184614_ca() != ItemStack.field_190927_a) {
/*  45 */           xOffset = ((Float)offset.mainX.getValue()).floatValue();
/*  46 */           yOffset = ((Float)offset.mainY.getValue()).floatValue();
/*     */         } 
/*  48 */       } else if (!((Boolean)offset.normalOffset.getValue()).booleanValue() && offset.isOn() && player.func_184592_cb() != ItemStack.field_190927_a) {
/*  49 */         xOffset = ((Float)offset.offX.getValue()).floatValue();
/*  50 */         yOffset = ((Float)offset.offY.getValue()).floatValue();
/*     */       } 
/*  52 */       func_187457_a(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
/*  53 */       this.injection = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"transformSideFirstPerson"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, CallbackInfo cancel) {
/*  60 */     RenderItemEvent event = new RenderItemEvent(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*  69 */     if (ViewModel.getInstance().isEnabled()) {
/*  70 */       boolean bob = (ViewModel.getInstance().isDisabled() || ((Boolean)(ViewModel.getInstance()).doBob.getValue()).booleanValue());
/*  71 */       int i = (hand == EnumHandSide.RIGHT) ? 1 : -1;
/*  72 */       GlStateManager.func_179109_b(i * 0.56F, -0.52F + (bob ? p_187459_2_ : 0.0F) * -0.6F, -0.72F);
/*  73 */       if (hand == EnumHandSide.RIGHT) {
/*  74 */         GlStateManager.func_179137_b(event.getMainX(), event.getMainY(), event.getMainZ());
/*  75 */         RenderUtil.rotationHelper((float)event.getMainRotX(), (float)event.getMainRotY(), (float)event.getMainRotZ());
/*     */       }
/*     */       else {
/*     */         
/*  79 */         GlStateManager.func_179137_b(event.getOffX(), event.getOffY(), event.getOffZ());
/*  80 */         RenderUtil.rotationHelper((float)event.getOffRotX(), (float)event.getOffRotY(), (float)event.getOffRotZ());
/*     */       } 
/*     */ 
/*     */       
/*  84 */       cancel.cancel();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Redirect(method = {"renderArmFirstPerson"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 0))
/*     */   public void translateHook(float x, float y, float z) {
/*  91 */     SmallShield offset = SmallShield.getINSTANCE();
/*  92 */     boolean shiftPos = ((Minecraft.func_71410_x()).field_71439_g != null && (Minecraft.func_71410_x()).field_71439_g.func_184614_ca() != ItemStack.field_190927_a && offset.isOn());
/*  93 */     GlStateManager.func_179109_b(x + (shiftPos ? ((Float)offset.mainX.getValue()).floatValue() : 0.0F), y + (shiftPos ? ((Float)offset.mainY.getValue()).floatValue() : 0.0F), z);
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"renderFireInFirstPerson"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void renderFireInFirstPersonHook(CallbackInfo info) {
/*  99 */     if (NoRender.getInstance().isOn() && ((Boolean)(NoRender.getInstance()).fire.getValue()).booleanValue()) {
/* 100 */       info.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"transformEatFirstPerson"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void transformEatFirstPerson(float p_187454_1_, EnumHandSide hand, ItemStack stack, CallbackInfo cancel) {
/* 107 */     if (ViewModel.getInstance().isEnabled()) {
/* 108 */       if (!((Boolean)(ViewModel.getInstance()).noEatAnimation.getValue()).booleanValue()) {
/* 109 */         float f = (Minecraft.func_71410_x()).field_71439_g.func_184605_cv() - p_187454_1_ + 1.0F;
/* 110 */         float f1 = f / stack.func_77988_m();
/*     */         
/* 112 */         if (f1 < 0.8F) {
/* 113 */           float f2 = MathHelper.func_76135_e(MathHelper.func_76134_b(f / 4.0F * 3.1415927F) * 0.1F);
/* 114 */           GlStateManager.func_179109_b(0.0F, f2, 0.0F);
/*     */         } 
/* 116 */         float f3 = 1.0F - (float)Math.pow(f1, 27.0D);
/* 117 */         int i = (hand == EnumHandSide.RIGHT) ? 1 : -1;
/* 118 */         GlStateManager.func_179137_b((f3 * 0.6F * i) * ((Double)(ViewModel.getInstance()).eatX.getValue()).doubleValue(), (f3 * 0.5F) * -((Double)(ViewModel.getInstance()).eatY.getValue()).doubleValue(), 0.0D);
/* 119 */         GlStateManager.func_179114_b(i * f3 * 90.0F, 0.0F, 1.0F, 0.0F);
/* 120 */         GlStateManager.func_179114_b(f3 * 10.0F, 1.0F, 0.0F, 0.0F);
/* 121 */         GlStateManager.func_179114_b(i * f3 * 30.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/* 123 */       cancel.cancel();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"renderSuffocationOverlay"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void renderSuffocationOverlay(CallbackInfo ci) {
/* 130 */     if (NoRender.getInstance().isOn() && ((Boolean)(NoRender.getInstance()).blocks.getValue()).booleanValue())
/* 131 */       ci.cancel(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\MixinItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */