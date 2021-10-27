/*    */ package me.earth.phobos.features.modules.misc;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.network.NetHandlerPlayClient;
/*    */ import net.minecraft.network.play.server.SPacketDisconnect;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TextComponentString;
/*    */ 
/*    */ public class FakeKick extends Module {
/* 13 */   private final Setting<Boolean> healthDisplay = register(new Setting("HealthDisplay", Boolean.valueOf(false)));
/*    */   
/*    */   public FakeKick() {
/* 16 */     super("FakeKick", "Log with the press of a button", Module.Category.MISC, true, false, false);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 20 */     if (((Boolean)this.healthDisplay.getValue()).booleanValue()) {
/* 21 */       float health = mc.field_71439_g.func_110139_bj() + mc.field_71439_g.func_110143_aJ();
/* 22 */       ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(Minecraft.func_71410_x().func_147114_u())).func_147253_a(new SPacketDisconnect((ITextComponent)new TextComponentString("Logged out with " + health + " health remaining.")));
/* 23 */       disable();
/*    */     } 
/* 25 */     ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(Minecraft.func_71410_x().func_147114_u())).func_147253_a(new SPacketDisconnect((ITextComponent)new TextComponentString("Internal Exception: java.lang.UrDumb.Lmfaooo")));
/* 26 */     disable();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\FakeKick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */