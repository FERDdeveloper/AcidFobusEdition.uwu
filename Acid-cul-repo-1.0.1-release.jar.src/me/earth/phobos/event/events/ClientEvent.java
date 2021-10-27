/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ import me.earth.phobos.features.Feature;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class ClientEvent
/*    */   extends EventStage {
/*    */   private Feature feature;
/*    */   private Setting setting;
/*    */   
/*    */   public ClientEvent(int stage, Feature feature) {
/* 15 */     super(stage);
/* 16 */     this.feature = feature;
/*    */   }
/*    */   
/*    */   public ClientEvent(Setting setting) {
/* 20 */     super(2);
/* 21 */     this.setting = setting;
/*    */   }
/*    */   
/*    */   public Feature getFeature() {
/* 25 */     return this.feature;
/*    */   }
/*    */   
/*    */   public Setting getSetting() {
/* 29 */     return this.setting;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\ClientEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */