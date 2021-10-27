/*    */ package me.earth.phobos.features.modules.render;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class PopChams extends Module {
/*    */   public static PopChams INSTANCE;
/*    */   public Setting<Mode> mode;
/*    */   public Setting<Float> width;
/*    */   public Setting<Boolean> texture;
/*    */   public Setting<Boolean> lighting;
/*    */   public Setting<Boolean> blend;
/*    */   
/*    */   public PopChams() {
/* 16 */     super("PopChams", "shows ez logs", Module.Category.RENDER, false, false, false);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.mode = register(new Setting("Mode", Mode.WIRE));
/* 21 */     this.width = register(new Setting("Width", Float.valueOf(2.0F), Float.valueOf(0.0F), Float.valueOf(5.0F), v -> (this.mode.getValue() == Mode.WIRE)));
/* 22 */     this.texture = register(new Setting("Texture", Boolean.valueOf(false)));
/* 23 */     this.lighting = register(new Setting("Lighting", Boolean.valueOf(false)));
/* 24 */     this.blend = register(new Setting("Blend", Boolean.valueOf(false)));
/* 25 */     this.transparent = register(new Setting("Transparent", Boolean.valueOf(false)));
/* 26 */     this.depth = register(new Setting("Depth", Boolean.valueOf(false)));
/* 27 */     this.xqz = register(new Setting("XQZ", Boolean.valueOf(false)));
/* 28 */     this.yTravel = register(new Setting("Y Travel", Integer.valueOf(0), Integer.valueOf(-10), Integer.valueOf(10)));
/* 29 */     this.aliveTicks = register(new Setting("Alive Ticks", Integer.valueOf(20), Integer.valueOf(0), Integer.valueOf(100)));
/*    */     
/* 31 */     this.poppedPlayers = new HashMap<>();
/*    */     setInstance();
/*    */   } public Setting<Boolean> transparent; public Setting<Boolean> depth; public Setting<Boolean> xqz; public Setting<Integer> yTravel; public Setting<Integer> aliveTicks; public HashMap<EntityPlayer, Integer> poppedPlayers;
/*    */   public void onRender3D(Render3DEvent event) {
/* 35 */     List<EntityPlayer> playersToRemove = new ArrayList<>();
/* 36 */     for (Map.Entry<EntityPlayer, Integer> player : this.poppedPlayers.entrySet()) {
/* 37 */       if (((Integer)player.getValue()).intValue() > ((Integer)this.aliveTicks.getValue()).intValue()) {
/* 38 */         playersToRemove.add(player.getKey());
/*    */       }
/* 40 */       ((EntityPlayer)player.getKey()).field_70163_u += ((Integer)this.yTravel.getValue()).intValue();
/* 41 */       mc.field_175616_W.func_188388_a((Entity)player.getKey(), event.getPartialTicks(), false);
/* 42 */       player.setValue(Integer.valueOf(((Integer)player.getValue()).intValue() + 1));
/*    */     } 
/* 44 */     for (EntityPlayer player : playersToRemove)
/* 45 */       this.poppedPlayers.remove(player); 
/*    */   }
/*    */   
/*    */   public static PopChams getInstance() {
/* 49 */     if (INSTANCE == null) {
/* 50 */       INSTANCE = new PopChams();
/*    */     }
/* 52 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   private void setInstance() {
/* 56 */     INSTANCE = this;
/*    */   }
/*    */   
/*    */   public enum Mode {
/* 60 */     MODEL,
/* 61 */     WIRE,
/* 62 */     SHINE,
/* 63 */     WIREMODEL;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\PopChams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */