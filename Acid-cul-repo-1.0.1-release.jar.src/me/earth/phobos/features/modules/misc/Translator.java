/*     */ package me.earth.phobos.features.modules.misc;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.Util;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketChatMessage;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ public class Translator
/*     */   extends Module
/*     */ {
/*     */   public static final String API_KEY = "trnsl.1.1.20200403T133250Z.c0062863622d7503.ca7fca44b9d2259ba3dadd61ddf7c15a2c9f3876";
/*  24 */   private final Setting<Language> sourceLanguage = register(new Setting("SourceLanguage", Language.English));
/*  25 */   private final Setting<Language> targetLanguage = register(new Setting("TargetLanguage", Language.Spanish));
/*     */   public Translate translate;
/*     */   
/*     */   public Translator() {
/*  29 */     super("Translator", "Translates text to a different language", Module.Category.MISC, true, false, false);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onSendPacket(PacketEvent.Send event) {
/*  34 */     if (event.getPacket() instanceof CPacketChatMessage) {
/*  35 */       event.setCanceled(true);
/*  36 */       this.translate = new Translate(((CPacketChatMessage)event.getPacket()).func_149439_c(), (Language)this.sourceLanguage.getValue(), (Language)this.targetLanguage.getValue());
/*  37 */       this.translate.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   private JsonObject request(String URL2) throws IOException {
/*  42 */     URL url = new URL(URL2);
/*  43 */     URLConnection urlConn = url.openConnection();
/*  44 */     urlConn.addRequestProperty("User-Agent", "Mozilla");
/*  45 */     InputStream inStream = urlConn.getInputStream();
/*  46 */     JsonParser jp = new JsonParser();
/*  47 */     JsonElement root = jp.parse(new InputStreamReader((InputStream)urlConn.getContent()));
/*  48 */     inStream.close();
/*  49 */     return root.getAsJsonObject();
/*     */   }
/*     */   
/*     */   public enum Language {
/*  53 */     Azerbaijan("az"),
/*  54 */     Albanian("sq"),
/*  55 */     Amharic("am"),
/*  56 */     English("en"),
/*  57 */     Arabic("ar"),
/*  58 */     Armenian("hy"),
/*  59 */     Afrikaans("af"),
/*  60 */     Basque("eu"),
/*  61 */     Bashkir("ba"),
/*  62 */     Belarusian("be"),
/*  63 */     Bengali("bn"),
/*  64 */     Burmese("my"),
/*  65 */     Bulgarian("bg"),
/*  66 */     Bosnian("bs"),
/*  67 */     Welsh("cy"),
/*  68 */     Hungarian("hu"),
/*  69 */     Vietnamese("vi"),
/*  70 */     Haitian("ht"),
/*  71 */     Galician("gl"),
/*  72 */     Dutch("nl"),
/*  73 */     HillMari("mrj"),
/*  74 */     Greek("el"),
/*  75 */     Georgian("ka"),
/*  76 */     Gujarati("gu"),
/*  77 */     Danish("da"),
/*  78 */     Hebrew("he"),
/*  79 */     Yiddish("yi"),
/*  80 */     Indonesian("id"),
/*  81 */     Irish("ga"),
/*  82 */     Italian("it"),
/*  83 */     Icelandic("is"),
/*  84 */     Spanish("es"),
/*  85 */     Kazakh("kk"),
/*  86 */     Kannada("kn"),
/*  87 */     Catalan("ca"),
/*  88 */     Kyrgyz("ky"),
/*  89 */     Chinese("zh"),
/*  90 */     Korean("ko"),
/*  91 */     Xhosa("xh"),
/*  92 */     Khmer("km"),
/*  93 */     Laotian("lo"),
/*  94 */     Latin("la"),
/*  95 */     Latvian("lv"),
/*  96 */     Lithuanian("lt"),
/*  97 */     Luxembourgish("lb"),
/*  98 */     Malagasy("mg"),
/*  99 */     Malay("ms"),
/* 100 */     Malayalam("ml"),
/* 101 */     Maltese("mt"),
/* 102 */     Macedonian("mk"),
/* 103 */     Maori("mi"),
/* 104 */     Marathi("mr"),
/* 105 */     Mari("mhr"),
/* 106 */     Mongolian("mn"),
/* 107 */     German("de"),
/* 108 */     Nepali("ne"),
/* 109 */     Norwegian("no"),
/* 110 */     Russian("ru"),
/* 111 */     Punjabi("pa"),
/* 112 */     Papiamento("pap"),
/* 113 */     Persian("fa"),
/* 114 */     Polish("pl"),
/* 115 */     Portuguese("pt"),
/* 116 */     Romanian("ro"),
/* 117 */     Cebuano("ceb"),
/* 118 */     Serbian("sr"),
/* 119 */     Sinhala("si"),
/* 120 */     Slovakian("sk"),
/* 121 */     Slovenian("sl"),
/* 122 */     Swahili("sw"),
/* 123 */     Sundanese("su"),
/* 124 */     Tajik("tg"),
/* 125 */     Thai("th"),
/* 126 */     Tagalog("tl"),
/* 127 */     Tamil("ta"),
/* 128 */     Tatar("tt"),
/* 129 */     Telugu("te"),
/* 130 */     Turkish("tr"),
/* 131 */     Udmurt("udm"),
/* 132 */     Uzbek("uz"),
/* 133 */     Ukrainian("uk"),
/* 134 */     Urdu("ur"),
/* 135 */     Finnish("fi"),
/* 136 */     French("fr"),
/* 137 */     Hindi("hi"),
/* 138 */     Croatian("hr"),
/* 139 */     Czech("cs"),
/* 140 */     Swedish("sv"),
/* 141 */     Scottish("gd"),
/* 142 */     Estonian("et"),
/* 143 */     Esperanto("eo"),
/* 144 */     Javanese("jv"),
/* 145 */     Japanese("ja");
/*     */     
/*     */     private final String code;
/*     */     
/*     */     Language(String code) {
/* 150 */       this.code = code; } public static Language getByCode(String code) {
/*     */       Language[] arrayOfLanguage;
/*     */       int i;
/*     */       byte b;
/* 154 */       for (arrayOfLanguage = values(), i = arrayOfLanguage.length, b = 0; b < i; ) { Language language = arrayOfLanguage[b];
/* 155 */         if (!language.code.equals(code)) { b++; continue; }
/* 156 */          return language; }
/*     */       
/* 158 */       return null;
/*     */     }
/*     */     
/*     */     public String getCode() {
/* 162 */       return this.code;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Translate
/*     */     extends Thread {
/*     */     public String message;
/*     */     public Translator.Language sourceLang;
/*     */     public Translator.Language lang;
/* 171 */     public String finalMessage = null;
/*     */     Thread thread;
/*     */     
/*     */     public Translate(String message, Translator.Language sourceLang, Translator.Language lang) {
/* 175 */       super("Translate");
/* 176 */       this.message = message;
/* 177 */       this.sourceLang = sourceLang;
/* 178 */       this.lang = lang;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       try {
/* 184 */         this.finalMessage = request("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200403T133250Z.c0062863622d7503.ca7fca44b9d2259ba3dadd61ddf7c15a2c9f3876&text=" + this.message.replace(" ", "%20") + "&lang=" + this.sourceLang.getCode() + "-" + this.lang.getCode()).get("text").getAsString();
/* 185 */         Util.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketChatMessage(this.finalMessage));
/* 186 */       } catch (IOException e) {
/* 187 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void start() {
/* 193 */       if (this.thread == null) {
/* 194 */         this.thread = new Thread(this, "Translate");
/* 195 */         this.thread.start();
/*     */       } 
/*     */     }
/*     */     
/*     */     private JsonObject request(String URL2) throws IOException {
/* 200 */       URL url = new URL(URL2);
/* 201 */       URLConnection urlConn = url.openConnection();
/* 202 */       urlConn.addRequestProperty("User-Agent", "Mozilla");
/* 203 */       InputStream inStream = urlConn.getInputStream();
/* 204 */       JsonParser jp = new JsonParser();
/* 205 */       JsonElement root = jp.parse(new InputStreamReader((InputStream)urlConn.getContent()));
/* 206 */       inStream.close();
/* 207 */       return root.getAsJsonObject();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\Translator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */