/*     */ package me.earth.phobos.event.events;
/*     */ 
/*     */ import me.earth.phobos.event.EventStage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderCoolItemEvent
/*     */   extends EventStage
/*     */ {
/*     */   Object mainX;
/*     */   Object mainY;
/*     */   Object mainZ;
/*     */   Object offX;
/*     */   Object offY;
/*     */   Object offZ;
/*     */   double mainRAngel;
/*     */   double mainRx;
/*     */   double mainRy;
/*     */   double mainRz;
/*     */   Object offRAngel;
/*     */   Object offRx;
/*     */   Object offRy;
/*     */   double offRz;
/*     */   double mainHandScaleX;
/*     */   double mainHandScaleY;
/*     */   double mainHandScaleZ;
/*     */   double offHandScaleX;
/*     */   double offHandScaleY;
/*     */   double offHandScaleZ;
/*     */   
/*     */   public RenderCoolItemEvent(double mainX, double mainY, double mainZ, double offX, double offY, double offZ, double mainRAngel, double mainRx, double mainRy, double mainRz, double offRAngel, double offRx, double offRy, double offRz, double mainHandScaleX, double mainHandScaleY, double mainHandScaleZ, double offHandScaleX, double offHandScaleY, double offHandScaleZ) {
/*  34 */     this.mainX = Double.valueOf(mainX);
/*  35 */     this.mainY = Double.valueOf(mainY);
/*  36 */     this.mainZ = Double.valueOf(mainZ);
/*  37 */     this.offX = Double.valueOf(offX);
/*  38 */     this.offY = Double.valueOf(offY);
/*  39 */     this.offZ = Double.valueOf(offZ);
/*  40 */     this.mainRAngel = mainRAngel;
/*  41 */     this.mainRx = mainRx;
/*  42 */     this.mainRy = mainRy;
/*  43 */     this.mainRz = mainRz;
/*  44 */     this.offRAngel = Double.valueOf(offRAngel);
/*  45 */     this.offRx = Double.valueOf(offRx);
/*  46 */     this.offRy = Double.valueOf(offRy);
/*  47 */     this.offRz = offRz;
/*  48 */     this.mainHandScaleX = mainHandScaleX;
/*  49 */     this.mainHandScaleY = mainHandScaleY;
/*  50 */     this.mainHandScaleZ = mainHandScaleZ;
/*  51 */     this.offHandScaleX = offHandScaleX;
/*  52 */     this.offHandScaleY = offHandScaleY;
/*  53 */     this.offHandScaleZ = offHandScaleZ;
/*     */   }
/*     */   
/*     */   public void setMainX(Object v) {
/*  57 */     this.mainX = v;
/*     */   }
/*     */   
/*     */   public void setMainY(Object v) {
/*  61 */     this.mainY = v;
/*     */   }
/*     */   
/*     */   public void setMainZ(Object v) {
/*  65 */     this.mainZ = v;
/*     */   }
/*     */   
/*     */   public void setOffX(Object v) {
/*  69 */     this.offX = v;
/*     */   }
/*     */   
/*     */   public void setOffY(Object v) {
/*  73 */     this.offY = v;
/*     */   }
/*     */   
/*     */   public void setOffZ(Object v) {
/*  77 */     this.offZ = v;
/*     */   }
/*     */   
/*     */   public void setOffRAngel(Object v) {
/*  81 */     this.offRAngel = v;
/*     */   }
/*     */   
/*     */   public void setOffRx(Object v) {
/*  85 */     this.offRx = v;
/*     */   }
/*     */   
/*     */   public void setOffRy(Object v) {
/*  89 */     this.offRy = v;
/*     */   }
/*     */   
/*     */   public void setOffRz(Object v) {
/*  93 */     this.offRz = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setMainRAngel(Object v) {
/*  97 */     this.mainRAngel = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setMainRx(Object v) {
/* 101 */     this.mainRx = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setMainRy(Object v) {
/* 105 */     this.mainRy = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setMainRz(Object v) {
/* 109 */     this.mainRz = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setMainHandScaleX(Object v) {
/* 113 */     this.mainHandScaleX = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setMainHandScaleY(Object v) {
/* 117 */     this.mainHandScaleY = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setMainHandScaleZ(Object v) {
/* 121 */     this.mainHandScaleZ = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setOffHandScaleX(Object v) {
/* 125 */     this.offHandScaleX = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setOffHandScaleY(Object v) {
/* 129 */     this.offHandScaleY = ((Double)v).doubleValue();
/*     */   }
/*     */   
/*     */   public void setOffHandScaleZ(Object v) {
/* 133 */     this.offHandScaleZ = ((Double)v).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainX() {
/* 138 */     return ((Double)this.mainX).doubleValue();
/*     */   }
/*     */   
/*     */   public double getMainY() {
/* 142 */     return ((Double)this.mainY).doubleValue();
/*     */   }
/*     */   
/*     */   public double getMainZ() {
/* 146 */     return ((Double)this.mainZ).doubleValue();
/*     */   }
/*     */   
/*     */   public double getOffX() {
/* 150 */     return ((Double)this.offX).doubleValue();
/*     */   }
/*     */   
/*     */   public double getOffY() {
/* 154 */     return ((Double)this.offY).doubleValue();
/*     */   }
/*     */   
/*     */   public double getOffZ() {
/* 158 */     return ((Double)this.offZ).doubleValue();
/*     */   }
/*     */   
/*     */   public double getMainRAngel() {
/* 162 */     return this.mainRAngel;
/*     */   }
/*     */   
/*     */   public double getMainRx() {
/* 166 */     return this.mainRx;
/*     */   }
/*     */   
/*     */   public double getMainRy() {
/* 170 */     return this.mainRy;
/*     */   }
/*     */   
/*     */   public double getMainRz() {
/* 174 */     return this.mainRz;
/*     */   }
/*     */   
/*     */   public double getOffRAngel() {
/* 178 */     return ((Double)this.offRAngel).doubleValue();
/*     */   }
/*     */   
/*     */   public double getOffRx() {
/* 182 */     return ((Double)this.offRx).doubleValue();
/*     */   }
/*     */   
/*     */   public double getOffRy() {
/* 186 */     return ((Double)this.offRy).doubleValue();
/*     */   }
/*     */   
/*     */   public double getOffRz() {
/* 190 */     return this.offRz;
/*     */   }
/*     */   
/*     */   public double getMainHandScaleX() {
/* 194 */     return this.mainHandScaleX;
/*     */   }
/*     */   
/*     */   public double getMainHandScaleY() {
/* 198 */     return this.mainHandScaleY;
/*     */   }
/*     */   
/*     */   public double getMainHandScaleZ() {
/* 202 */     return this.mainHandScaleZ;
/*     */   }
/*     */   
/*     */   public double getOffHandScaleX() {
/* 206 */     return this.offHandScaleX;
/*     */   }
/*     */   
/*     */   public double getOffHandScaleY() {
/* 210 */     return this.offHandScaleY;
/*     */   }
/*     */   
/*     */   public double getOffHandScaleZ() {
/* 214 */     return this.offHandScaleZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\RenderCoolItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */