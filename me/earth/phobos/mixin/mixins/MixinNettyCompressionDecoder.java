/*    */ package me.earth.phobos.mixin.mixins;
/*    */ 
/*    */ import me.earth.phobos.features.modules.misc.Bypass;
/*    */ import net.minecraft.network.NettyCompressionDecoder;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.Constant;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyConstant;
/*    */ 
/*    */ @Mixin({NettyCompressionDecoder.class})
/*    */ public abstract class MixinNettyCompressionDecoder {
/*    */   @ModifyConstant(method = {"decode"}, constant = {@Constant(intValue = 2097152)})
/*    */   private int decodeHook(int n) {
/* 13 */     if (Bypass.getInstance().isOn() && ((Boolean)(Bypass.getInstance()).packets.getValue()).booleanValue() && ((Boolean)(Bypass.getInstance()).noLimit.getValue()).booleanValue()) {
/* 14 */       return Integer.MAX_VALUE;
/*    */     }
/* 16 */     return n;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\mixin\mixins\MixinNettyCompressionDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */