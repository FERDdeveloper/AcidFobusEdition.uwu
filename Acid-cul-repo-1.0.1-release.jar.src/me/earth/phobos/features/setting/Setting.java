/*     */ package me.earth.phobos.features.setting;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import me.earth.phobos.event.events.ClientEvent;
/*     */ import me.earth.phobos.features.Feature;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Setting<T>
/*     */ {
/*     */   private final String name;
/*     */   private final T defaultValue;
/*     */   private T value;
/*     */   private T plannedValue;
/*     */   private T min;
/*     */   private T max;
/*     */   private boolean hasRestriction;
/*     */   private boolean shouldRenderStringName;
/*     */   private Predicate<T> visibility;
/*     */   private String description;
/*     */   private Feature feature;
/*     */   
/*     */   public Setting(String speed, String name, double v1, double v, T defaultValue, int i) {
/*  26 */     this.name = name;
/*  27 */     this.defaultValue = defaultValue;
/*  28 */     this.value = defaultValue;
/*  29 */     this.plannedValue = defaultValue;
/*  30 */     this.description = "";
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String name, T defaultValue, String description) {
/*  35 */     this.name = name;
/*  36 */     this.defaultValue = defaultValue;
/*  37 */     this.value = defaultValue;
/*  38 */     this.plannedValue = defaultValue;
/*  39 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Setting(String name, T defaultValue, T min, T max, String description) {
/*  45 */     this.name = name;
/*  46 */     this.defaultValue = defaultValue;
/*  47 */     this.value = defaultValue;
/*  48 */     this.min = min;
/*  49 */     this.max = max;
/*  50 */     this.plannedValue = defaultValue;
/*  51 */     this.description = description;
/*  52 */     this.hasRestriction = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String name, T defaultValue, T min, T max) {
/*  57 */     this.name = name;
/*  58 */     this.defaultValue = defaultValue;
/*  59 */     this.value = defaultValue;
/*  60 */     this.min = min;
/*  61 */     this.max = max;
/*  62 */     this.plannedValue = defaultValue;
/*  63 */     this.description = "";
/*  64 */     this.hasRestriction = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String name, T defaultValue, T min, T max, Predicate<T> visibility, String description) {
/*  69 */     this.name = name;
/*  70 */     this.defaultValue = defaultValue;
/*  71 */     this.value = defaultValue;
/*  72 */     this.min = min;
/*  73 */     this.max = max;
/*  74 */     this.plannedValue = defaultValue;
/*  75 */     this.visibility = visibility;
/*  76 */     this.description = description;
/*  77 */     this.hasRestriction = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String name, T defaultValue, T min, T max, Predicate<T> visibility) {
/*  82 */     this.name = name;
/*  83 */     this.defaultValue = defaultValue;
/*  84 */     this.value = defaultValue;
/*  85 */     this.min = min;
/*  86 */     this.max = max;
/*  87 */     this.plannedValue = defaultValue;
/*  88 */     this.visibility = visibility;
/*  89 */     this.description = "";
/*  90 */     this.hasRestriction = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String name, T defaultValue, Predicate<T> visibility) {
/*  95 */     this.name = name;
/*  96 */     this.defaultValue = defaultValue;
/*  97 */     this.value = defaultValue;
/*  98 */     this.visibility = visibility;
/*  99 */     this.plannedValue = defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String name, T defaultValue) {
/* 104 */     this.name = name;
/* 105 */     this.defaultValue = defaultValue;
/* 106 */     this.value = defaultValue;
/* 107 */     this.plannedValue = defaultValue;
/* 108 */     this.description = "";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 113 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getValue() {
/* 118 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(T value) {
/* 123 */     setPlannedValue(value);
/* 124 */     if (this.hasRestriction) {
/* 125 */       if (((Number)this.min).floatValue() > ((Number)value).floatValue()) {
/* 126 */         setPlannedValue(this.min);
/*     */       }
/* 128 */       if (((Number)this.max).floatValue() < ((Number)value).floatValue()) {
/* 129 */         setPlannedValue(this.max);
/*     */       }
/*     */     } 
/* 132 */     ClientEvent event = new ClientEvent(this);
/* 133 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 134 */     if (!event.isCanceled()) {
/* 135 */       this.value = this.plannedValue;
/*     */     } else {
/* 137 */       this.plannedValue = this.value;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public T getPlannedValue() {
/* 143 */     return this.plannedValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlannedValue(T value) {
/* 148 */     this.plannedValue = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getMin() {
/* 153 */     return this.min;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMin(T min) {
/* 158 */     this.min = min;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getMax() {
/* 163 */     return this.max;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMax(T max) {
/* 168 */     this.max = max;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueNoEvent(T value) {
/* 173 */     setPlannedValue(value);
/* 174 */     if (this.hasRestriction) {
/* 175 */       if (((Number)this.min).floatValue() > ((Number)value).floatValue()) {
/* 176 */         setPlannedValue(this.min);
/*     */       }
/* 178 */       if (((Number)this.max).floatValue() < ((Number)value).floatValue()) {
/* 179 */         setPlannedValue(this.max);
/*     */       }
/*     */     } 
/* 182 */     this.value = this.plannedValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Feature getFeature() {
/* 187 */     return this.feature;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFeature(Feature feature) {
/* 192 */     this.feature = feature;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEnum(String input) {
/* 197 */     for (int i = 0; i < (this.value.getClass().getEnumConstants()).length; ) {
/* 198 */       Enum e = (Enum)this.value.getClass().getEnumConstants()[i];
/* 199 */       if (!e.name().equalsIgnoreCase(input)) { i++; continue; }
/* 200 */        return i;
/*     */     } 
/* 202 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnumValue(String value) {
/* 207 */     for (Enum e : (Enum[])((Enum)this.value).getClass().getEnumConstants()) {
/* 208 */       if (e.name().equalsIgnoreCase(value)) {
/* 209 */         this.value = (T)e;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String currentEnumName() {
/* 215 */     return EnumConverter.getProperName((Enum)this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int currentEnum() {
/* 220 */     return EnumConverter.currentEnum((Enum)this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseEnum() {
/* 225 */     this.plannedValue = (T)EnumConverter.increaseEnum((Enum)this.value);
/* 226 */     ClientEvent event = new ClientEvent(this);
/* 227 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 228 */     if (!event.isCanceled()) {
/* 229 */       this.value = this.plannedValue;
/*     */     } else {
/* 231 */       this.plannedValue = this.value;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseEnumNoEvent() {
/* 237 */     this.value = (T)EnumConverter.increaseEnum((Enum)this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 242 */     if (isEnumSetting()) {
/* 243 */       return "Enum";
/*     */     }
/* 245 */     return getClassName(this.defaultValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> String getClassName(T value) {
/* 250 */     return value.getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 255 */     if (this.description == null) {
/* 256 */       return "";
/*     */     }
/* 258 */     return this.description;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNumberSetting() {
/* 263 */     return (this.value instanceof Double || this.value instanceof Integer || this.value instanceof Short || this.value instanceof Long || this.value instanceof Float);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnumSetting() {
/* 268 */     return (!isNumberSetting() && !(this.value instanceof String) && !(this.value instanceof Bind) && !(this.value instanceof Character) && !(this.value instanceof Boolean));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStringSetting() {
/* 273 */     return this.value instanceof String;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getDefaultValue() {
/* 278 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 283 */     return this.value.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRestriction() {
/* 288 */     return this.hasRestriction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisibility(Predicate<T> visibility) {
/* 293 */     this.visibility = visibility;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting<T> setRenderName(boolean renderName) {
/* 298 */     this.shouldRenderStringName = renderName;
/* 299 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRenderName() {
/* 304 */     if (!isStringSetting()) {
/* 305 */       return true;
/*     */     }
/* 307 */     return this.shouldRenderStringName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 312 */     if (this.visibility == null) {
/* 313 */       return true;
/*     */     }
/* 315 */     return this.visibility.test(getValue());
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\setting\Setting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */