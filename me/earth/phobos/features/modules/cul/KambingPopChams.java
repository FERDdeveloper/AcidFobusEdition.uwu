/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import me.earth.phobos.event.events.TotemPopEvent;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.EntityUtil;
/*    */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.GameType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class KambingPopChams
/*    */   extends Module
/*    */ {
/* 21 */   public static KambingPopChams INSTANCE = new KambingPopChams();
/* 22 */   public static ConcurrentHashMap<Integer, Integer> pops = new ConcurrentHashMap<>();
/* 23 */   public Setting<Integer> red = register(new Setting("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
/* 24 */   public Setting<Integer> green = register(new Setting("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 25 */   public Setting<Integer> blue = register(new Setting("Blue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
/* 26 */   public Setting<Integer> alpha = register(new Setting("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/*    */ 
/*    */   
/*    */   public KambingPopChams() {
/* 30 */     super("KambingPopChams", "1 original module from kambing", Module.Category.CUL, false, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public static KambingPopChams getINSTANCE() {
/* 35 */     if (INSTANCE == null) {
/* 36 */       INSTANCE = new KambingPopChams();
/*    */     }
/* 38 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInstance() {
/* 43 */     INSTANCE = this;
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPopLol(TotemPopEvent event) {
/* 49 */     Color color = EntityUtil.getColor((Entity)event.getEntity(), ((Integer)this.red.getValue()).intValue(), ((Integer)this.green.getValue()).intValue(), ((Integer)this.blue.getValue()).intValue(), ((Integer)this.alpha.getValue()).intValue(), false);
/* 50 */     if (event.getEntity() != mc.field_71439_g) {
/* 51 */       EntityPlayer entityPlayer = event.getEntity();
/* 52 */       Command.sendMessage("PopEvent :^]");
/* 53 */       ArrayList<Integer> idList = new ArrayList<>();
/* 54 */       for (Entity e : mc.field_71441_e.field_72996_f) {
/* 55 */         idList.add(Integer.valueOf(e.field_145783_c));
/*    */       }
/* 57 */       EntityOtherPlayerMP popCham = new EntityOtherPlayerMP((World)mc.field_71441_e, event.getEntity().func_146103_bH());
/* 58 */       popCham.func_82149_j((Entity)entityPlayer);
/* 59 */       popCham.field_70759_as = entityPlayer.func_70079_am();
/* 60 */       popCham.field_70177_z = ((Entity)entityPlayer).field_70177_z;
/* 61 */       popCham.field_70125_A = ((Entity)entityPlayer).field_70125_A;
/* 62 */       popCham.func_71033_a(GameType.CREATIVE);
/* 63 */       popCham.func_70606_j(20.0F);
/* 64 */       for (int i = 0; i > -10000; i--) {
/* 65 */         if (!idList.contains(Integer.valueOf(i))) {
/* 66 */           mc.field_71441_e.func_73027_a(i, (Entity)popCham);
/* 67 */           pops.put(Integer.valueOf(i), Integer.valueOf(color.getAlpha()));
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\KambingPopChams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */