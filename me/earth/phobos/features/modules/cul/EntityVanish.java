/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraft.client.network.NetHandlerPlayClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketVehicleMove;
/*    */ 
/*    */ public class EntityVanish extends Module {
/*    */   Entity entity;
/*    */   
/*    */   public EntityVanish() {
/* 14 */     super("EntityVanish", "vanish", Module.Category.CUL, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 19 */     if (mc.field_71439_g.func_184187_bx() != null) {
/* 20 */       this.entity = mc.field_71439_g.func_184187_bx();
/* 21 */       mc.field_71439_g.func_184210_p();
/* 22 */       mc.field_71441_e.func_72900_e(this.entity);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 28 */     this; if (nullCheck()) {
/* 29 */       toggle();
/*    */       return;
/*    */     } 
/* 32 */     if (this.entity != null)
/*    */       try {
/* 34 */         this.entity.field_70165_t = mc.field_71439_g.field_70165_t;
/* 35 */         this.entity.field_70163_u = mc.field_71439_g.field_70163_u;
/* 36 */         this.entity.field_70161_v = mc.field_71439_g.field_70161_v;
/* 37 */         ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(mc.func_147114_u())).func_147297_a((Packet)new CPacketVehicleMove(this.entity));
/*    */       }
/* 39 */       catch (Exception exception) {} 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\EntityVanish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */